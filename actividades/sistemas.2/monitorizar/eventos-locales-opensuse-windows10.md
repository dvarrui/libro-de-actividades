
```
Curso       : nuevo
Area        : Sistemas operativos, monitorización, eventos, log.
Descripción : Usar logger para generar eventos.
              Usar journalctl para consultar eventos.
Requisitos  : GNU/Linux
Tiempo      : 4 sesiones
```

# Generar y consultar eventos en varias plataformas


# 1. GNU/Linux

## 1.1 Generar eventos usando el comando logger

```
logger --tag nombre-alumnoXX \"IDP:\$USER:\$PWD:\$OLDPWD:\$(tail -n 1 \$HISTFILE)\"'"
```

## 1.2 Crear eventos con script

```bash
#!/usr/bin/env bash

ALUMNO=nombre-alumnoXX

echo "[INFO] Generador de eventos de $ALUMNO"
logger --tag $ALUMNO "IDP:$USER:$PWD:$OLDPWD:$(tail -n 1 $HISTFILE)"

echo -e "\n[INFO] Fin!"
exit 0
```

# 2. Windows PowerShell

## 2.1 Generar eventos

## 2.2 Consultar eventos

## 2.3 Activar la ejecución de script de PowerShell

```
@echo off

echo Enables PowerShell scripts execution:
rem Info: https://codelucidate.wordpress.com/powershell/change-execution-policy-in-the-registry/
reg add "HKLM\SOFTWARE\Microsoft\PowerShell\1\ShellIds\Microsoft.PowerShell" /v ExecutionPolicy /t REG_SZ /d "Unrestricted" /f > nul
```


########



$App = "nombre-alumnoXX"
$EventSourcePath = "HKLM:\SYSTEM\CurrentControlSet\Services\EventLog\Application\$App"
$CmdAutorunPath = "HKLM:\Software\Microsoft\Command Processor"
$CmdAuditScriptPath = "$env:windir\System32\cmd-audit.bat"

##########################################################################
# Auxiliary functions
##########################################################################

<#
    Checks if the specified value exists in Windows Registry
#>
function Test-RegistryValue($Path, $Value) {
    try {
        Get-ItemProperty -Path $Path | Select-Object -ExpandProperty $Value -ErrorAction Stop | Out-Null
        return $true
    } catch {
        return $false
    }
}

##########################################################################
# Enabling monitorization
##########################################################################

<#
    Creates an event source in Windows Registry to log every executed command
    Info: https://msdn.microsoft.com/es-es/library/windows/desktop/aa363661(v=vs.85).aspx
#>
function New-EventSource() {
    Write-Host "- Creating $App event source in Windows Registry"
    try {
        New-Item -Path $EventSourcePath | Out-Null
        New-ItemProperty -Path $EventSourcePath -Name CustomSource -PropertyType DWord -Value 1 | Out-Null
        New-ItemProperty -Path $EventSourcePath -Name EventMessageFile -PropertyType ExpandString -Value "$env:windir\System32\EventCreate.exe" | Out-Null
        New-ItemProperty -Path $EventSourcePath -Name TypesSupported -PropertyType DWord -Value 7 | Out-Null
    } catch {
        Write-Host $_.Exception.Message        
    }
}

<#
	Generates CMD monitorization script
#>
function Generate-CmdAuditScript {
    # There's a problem if the user uses options that are considered valid for "call" / we cannot ignore "call" because the variables wouldn't be expanded
	$script = @"
@echo off
title Monitored CMD
setlocal EnableDelayedExpansion
echo.
echo This terminal is being monitored by $App
:loop
echo.
set OLDPWD=!CD!
set COMMAND=
set /P COMMAND=!CD!^>
if "!COMMAND!" == "" goto loop
call !COMMAND!
set COMMAND=%COMMAND:"=\"%
eventcreate /id 1 /L Application /T Information /SO $App /D "cmd:!USERNAME!:'!CD!':'!OLDPWD!':!COMMAND!" > nul
goto loop
"@
	Set-Content $CmdAuditScriptPath $script
}

<#
	Generates PowerShell monitorization script
#>
function Generate-PSAuditScript {
	$script = @"
`$global:OLDPWD = Get-Location
`$global:FIRSTTIME = `$true

# Logs an event for the last executed Cmdlet in PowerShell
function Write-LastPSCmdletEvent (`$cmdlet) {
	`$username = `$env:USERNAME
	`$pwd = (`$PWD).Path
	`$message = "powershell:" + `$username + ":'" + `$pwd + "':'" + `$global:OLDPWD + "':" + `$cmdlet
    `$eventId = 1
    `$type = 1 	# information event
    `$id = New-Object System.Diagnostics.EventInstance(`$eventId, `$type)
    `$event = New-Object System.Diagnostics.EventLog
    `$event.Log = "Application"
    `$event.Source = "$App"
    `$event.WriteEvent(`$id, `$message)
}

# Ovewrites "prompt()" function to inject the execution of "Write-LastPSCmdletEvent" before showing prompt to the user
function prompt {
	`$lastCmdlet = (Get-History -Count 1).CommandLine
	if (`$lastCmdlet -ne "") {
		Write-LastPSCmdletEvent `$lastCmdlet
	}
	`$global:OLDPWD = Get-Location
	`$prompt = "PS `$(`$executionContext.SessionState.Path.CurrentLocation)`$('>' * (`$nestedPromptLevel + 1)) ";
	if (`$global:FIRSTTIME) {
		`$prompt = "``nThis terminal is being monitored by $App.``n``n" + `$prompt
		`$global:FIRSTTIME = `$false
	}
	return `$prompt
}
"@
	Set-Content $PSHOME\Profile.ps1 $script
}

<#
    Enables CMD monitorization (system is configured to log every executed command in command prompt)
#>
function Enable-CmdAudit() {
    # Copies the script to audit CMD in System32 folder
    Write-Host "- Generating CMD auditing script in $CmdAuditScriptPath"
    Generate-CmdAuditScript

    # Sets the script automatic execution when CMD is opened
    Write-Host "- Setting AutoRun value in Windows Registry to run the monitoring script when CMD is opened"
    New-ItemProperty -Path $CmdAutorunPath -Name Autorun -PropertyType ExpandString -Value $CmdAuditScriptPath | Out-Null
}

<#
    Enables PowerShell monitorization
#>
function Enable-PSAudit() {  
    # Creates the PowerShell global profile (for all users) which overwrites "prompt()" function
    Write-Host "- Generating PowerShell global profile in order to ovewrite 'prompt' function"
    Generate-PSAuditScript
}

##########################################################################
# Checking monitorization
##########################################################################

<#
    Checks if 'HKLM:\SYSTEM\CurrentControlSet\Services\EventLog\Application\TerminalTrainer' key exists,
    corresponding to TerminalTrainer event source
#>
function Test-EventSource() {
    return (Test-Path $EventSourcePath)
}

<#
    Checks if CMD monitorization is enabled
#>
function Test-CmdAudit() {
    # Checks if CMD monitoring script exists in "System32" folder,
    # if "Autorun" value exists in "HKLM\Software\Microsoft\Command Processor" key
    # and if this value points to the script
    return  ((Test-Path $CmdAuditScriptPath) -and
            (Test-RegistryValue -Path $CmdAutorunPath -Value "Autorun") -and
            ((Get-ItemProperty -Path $CmdAutorunPath -Name "Autorun").Autorun -ieq $CmdAuditScriptPath))
}

<#
    Checks if PowerShell monitorization is enabled
#>
function Test-PSAudit() {
    # Comprueba si existe el perfil global de PowerShell
    return (Test-Path "$PSHOME\Profile.ps1")
}

##########################################################################
# Disabling monitorization
##########################################################################

<#
    Removes TerminalTrainer event source from Windows Registry
#>
function Remove-EventSource() {
    Write-Host "- Removing $App event source from Windows Registry"
    Remove-Item -Path $EventSourcePath | Out-Null
}

<#
    Disabling CMD monitorization
#>
function Disable-CmdAudit() {
    # Removes CMD monitoring script from "System32" folder
    Write-Host "- Removing CMD monitoring script from $CmdAuditScriptPath"
    Remove-Item $CmdAuditScriptPath | Out-Null

    # Removes Autorun value from Windows Registry
    Write-Host "- Removing AutoRun value from Windows Registry, disabling the CMD monitoring script automatic execution"
    Remove-ItemProperty -Path $CmdAutorunPath -Name Autorun | Out-Null
}

<#
    Disabling PowerShell monitorization
#>
function Disable-PSAudit() {  
    # Removes PowerShell global profile (for all users)
    Write-Host "- Removing PowerShell global profile"
    Remove-Item $PSHOME\Profile.ps1 | Out-Null
}

##########################################################################
# Main functions
##########################################################################

If ($Test) {
    $ReturnValue = 0

    Write-Host -NoNewline "- $App event source exists? "
    if (-not (Test-EventSource)) {
        Write-Host "[NO]"
        $ReturnValue = 1
    } else {
        Write-Host "[YES]"
    }

    Write-Host -NoNewline "- CMD monitorization is enabled? "
    if (-not (Test-CmdAudit)) {
        Write-Host "[NO]"
        $ReturnValue = 1
    } else {
        Write-Host "[YES]"
    }

    Write-Host -NoNewline "- PowerShell monitorization is enabled? "
    if (-not (Test-PSAudit)) {
        Write-Host "[NO]"
        $ReturnValue = 1
    } else {
        Write-Host "[YES]"
    }

    exit $ReturnValue
}

If ($Disable) {
    try {
        Remove-EventSource
        Disable-CmdAudit
        Disable-PSAudit
        exit 0
    } catch {
        Write-Host $_.Exception.Message
        exit 1
    }
}

If ($Enable) {
    try {
        New-EventSource
        Enable-CmdAudit
        Enable-PSAudit
        exit 0
    } catch {
        Write-Host $_.Exception.Message
        exit 1
    }
}

exit 1
