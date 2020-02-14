
# Acceso remoto de Windows

## Windows10

**Opción 1: Usando script de instalación**

Requirements:
* Windows 7+ / Windows Server 2003+
* PowerShell v2+

Run this command on PowerShell (PS) as Administrator user:
```
Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://raw.githubusercontent.com/teuton-software/teuton/master/bin/windows_s-node_install.ps1'))
```

**Opción 2: Proceso manual**

* Ir a [ SSH Windows](./windows-ssh.md)

## Windows7

* Ir a [Telnet Windows](./windows-telnet.md)
