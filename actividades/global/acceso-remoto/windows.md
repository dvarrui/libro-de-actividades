
# Acceso remoto de Windows

## Windows10

**Opción 1:**

Desde el escritorio:
* Descargar OpenSSH para Windows desde la URL https://github.com/PowerShell/Win32-OpenSSH/releases.
* Descomprimir el fichero en `c:\Archivos de programas\OpenSSH`

Abrir PowerShell como administrador:
* Ejecutar la orden `Set-ExecutionPolicy Bypass -Scope Process -Force`, para permitir la ejecución de scripts.
* Ejecutar `./install-sshd.ps1`, para instalar SSHD.

Configurar el servicio SSH:
* `Set-Service sshd -StartupType Automatic`, configurar para que se inicie el sercivio al iniciar el sistema.
* `Start-Service sshd`, arrancar el servicio.

Comprobamos desde la máquina real:
* `ssh usuario@ip`, probamos a conectar con el SSH del Windows.
* Deshabilitar el cortafuegos si hay problemas de conectividad.

**Opción 2: Proceso manual**

* Ir a [ SSH Windows](./windows-ssh.md)

## Windows7

* Ir a [Telnet Windows](./windows-telnet.md)
