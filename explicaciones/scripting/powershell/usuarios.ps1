Function crea_usuario ($nombre, $pass, $adm)
{
       $computer=$env:COMPUTERNAME
       $cn = [adsi]"WinNT://$computer"  
       $Usr = $cn.Create("User",$nombre)
       $Usr.SetPassword($pass)
       $Usr.SetInfo()
       $Usr.UserFlags[0] = $Usr.UserFlags[0] -bor 0x10000  #ADS_UF_DONT_EXPIRE_PASSWD flag is 0x10000
       $Usr.SetInfo()
       if ($adm) {
            net localgroup Administradores /add $nombre   # Administrators -> versión inglés
       } else {
            net localgroup Usuarios /add $nombre # Users -> versión inglés
       }
       Write-Host "Se ha creado el usuario $nombre con la contraseña $pass"
}

Function borra_usuario($nombre)
{
       $computer=$env:COMPUTERNAME       
       Remove-Item -Recurse -Force "C:\Users\$nombre"
       $cn = [adsi]"WinNT://$computer"  
       # Borra el usuario pero NO el perfil asociado al mismo.
       $Usr = $cn.Delete("User",$nombre)  
       Write-Host "Se ha borrado el usuario $nombre"
   
}


[System.Collections.Hashtable]$cuentas = @{"1DAM A"="clave"; "1DAM B"="clave"; "2DAM"="clave" ; "3DAM"="clave"}
[System.Collections.ArrayList]$Usuarios=@(Get-WmiObject -Class Win32_UserAccount | Select-Object Name)
$cuentas.GetEnumerator() | % {
    if ( $Usuarios -match $($_.key)) {
        Write-Host El usuario ya existe
      #  borra_usuario($_.key)
    } else {
        Write-Host El usuario no existe
        crea_usuario ($_.key) ($_.value)  # Creamos usuarios 
          
    }
  
}

# Distintas formas de buscar dentro del array según la versión de PowerShell
# $Usuarios -match "flay"    "flay" -in $Usuarios   $Usuarios -contains "flay"
