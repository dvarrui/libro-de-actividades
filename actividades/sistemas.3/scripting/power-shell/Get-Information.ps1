# PC information Powershell
# URL: https://github.com/Kevinsillo/pc-info-powershell
#
# That is a simple script for gather information of
# any computer with Powershell and send to email.
#
# Modify
# Edit the file to change email options and users/groups list
#

# ---------------------------------
# - Script for gather information -
# ---------------------------------
ECHO OFF
CLS
# -----------
# - Options -
# -----------
# Show list of users and groups
$users='true'
$groups='true'
# -------------------
# - Send email data -
# -------------------
$from = ''
$to = ''
$smtp = ''
$port = ''
# -------------
# - Variables -
# -------------
$ip = Get-WmiObject Win32_NetworkAdapterConfiguration | Where { $_.Ipaddress.length -gt 1 }
$ip = $ip.ipaddress[0]

$core = Get-CimInstance -ClassName Win32_Processor | Select-Object Name
$core = $core.name

$ram = Get-Ciminstance Win32_OperatingSystem | Select-Object @{Name = "total";Expression = {[int]($_.TotalVisibleMemorySize/1mb)}}
$ram = $ram.total

$domain = Get-CimInstance -ClassName Win32_ComputerSystem | Select-Object Domain
$domain = $domain.domain

$client = read-host "Client name?"

$file="$client - $env:COMPUTERNAME.txt"

$outlookApplication = New-Object -ComObject 'Outlook.Application'
$outlook = $outlookApplication.Application.Session.Accounts | Select-Object DisplayName
# --------------
# - Out format -
# --------------
ECHO "-----------------------------------------------------------------------" > $file
ECHO "COMPUTER INFORMATION - $((get-date).tostring('dd-MM-yyyy'))" >> $file
ECHO "-----------------------------------------------------------------------" >> $file
ECHO "Client: $client" >> $file
ECHO "Computer name: $env:COMPUTERNAME" >> $file
ECHO "Actual user: $env:USERNAME" >> $file
ECHO "IP address: $ip" >> $file
ECHO "Domain/Workgroup: $domain" >> $file
ECHO "Processor: $core" >> $file
ECHO "Memory RAM: $ram GB" >> $file
ECHO "" >> $file
Get-Wmiobject win32_logicaldisk -Filter "DriveType=3" | Select @{name="Unit";Expression={$_.Name}},@{name="Format";Expression={$_.FileSystem}},@{name="Name";Expression={$_.VolumeName}},@{n="Free Space";e={[math]::truncate($_.freespace / 1GB)}} >> $file
ECHO "-----------------------------------------------------------------------" >> $file
ECHO "Email Address" >> $file
ECHO "-----------------------------------------------------------------------" >> $file
ECHO $outlook.DisplayName >> $file

if ($users -eq 'true') {
    ECHO "" >> $file
    ECHO "-----------------------------------------------------------------------" >> $file
    ECHO "Local users" >> $file
    ECHO "-----------------------------------------------------------------------" >> $file
    $users = Get-Localuser
    ECHO $users.name >> $file
}
if ($groups -eq 'true') {
    ECHO "" >> $file
    ECHO "-----------------------------------------------------------------------" >> $file
    ECHO "Local groups" >> $file
    ECHO "-----------------------------------------------------------------------" >> $file
    $groups = Get-Localgroup
    ECHO $groups.name >> $file
}
# --------------
# - Send email -
# --------------
$send = read-host "Send mail with summary? [Y/N]"
if ($send -eq 'Y') {
    $subject = "$client - $env:COMPUTERNAME"
    $body = Get-Content $file -Encoding 'UTF8' | Out-String
    $credencial = Get-Credential
    Send-MailMessage -From $from -To $to -Subject $subject -Body $body -SmtpServer $smtp -Port $port -Encoding 'UTF8' -Credential $credencial
}
