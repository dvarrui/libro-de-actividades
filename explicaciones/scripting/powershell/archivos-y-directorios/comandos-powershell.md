# Comandos de gestión de archivos y directorios desde PowerShell

## Listar archivos y directorios

Para listar archivos y directorios disponemos del comando [`Get-ChildItem`](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.management/get-childitem?view=powershell-7.1). 

Alias: `dir`, `ls`, `gci`.

| Ejemplo                                   | Descripción                                                  |
| ----------------------------------------- | ------------------------------------------------------------ |
| `Get-ChildItem`                           | Listar archivos y directorios.                               |
| `Get-ChildItem C:\Windows`                | Listar el contenido del directorio “C:\Windows”.             |
| `Get-ChildItem -Hidden`                   | Listar sólo los ocultos y del sistema.                       |
| `Get-ChildItem –Force`                    | Listar también los ocultos y del sistema.                    |
| `Get-ChildItem –Force "C:\Program files"` | Listar el contenido del directorio “C:\Program files”, incluyendo los ocultos y del sistema. |
| `Get-ChildItem *.pdf`                     | Listar archivos y directorios que acaban en “.pdf”.          |
| `Get-ChildItem –Recurse C:\Windows`       | Listar el contenido del directorio C:\Windows y el de todos sus subdirectorios. |

## Mostrar el directorio actual o de trabajo

Para mostrar el directorio de trabajo actual utilizamos el comando [`Get-Location`](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.management/get-location?view=powershell-7.1).

Alias: `pwd`, `gl`

| Ejemplo        | Descripción                                |
| -------------- | ------------------------------------------ |
| `Get-Location` | Muestra el directorio actual o de trabajo. |

## Cambiar el directorio de trabajo

Para cambiar el directorio de trabajo actual utilizamos el comando [`Set-Location`](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.management/set-location?view=powershell-7.1).

Alias: `cd`, `sl`.

| Ejemplo                        | Descripción                                                  |
| ------------------------------ | ------------------------------------------------------------ |
| `Set-Location $env:HOMEPATH`   | Cambia al directorio personal del usuario.                   |
| `Set-Location ..`              | Sube al directorio padre.                                    |
| `Set-Location Downloads`       | Cambia al directorio “Downloads” (ruta relativa).            |
| `Set-Location C:\Windows`      | Cambia al directorio “C:\Windows” (ruta absoluta).           |
| `Set-Location ..\Desktop`      | Sube un directorio y accede a “Desktop” (ruta relativa).     |
| `Set-Location ..\..\Downloads` | Sube dos directorios y accede a “Downloads” (ruta relativa). |
| `Set-Location .`               | Cambia al directorio actual (no hace nada).                  |

## Crear un directorio o archivo

Para crear archivos y directorios utilizamos el comando [`New-Item`](https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.management/new-item?view=powershell-7.1).

Alias: `md`, `mkdir`, `ni`.

| Ejemplo                                                      | Descripción                                           |
| ------------------------------------------------------------ | ----------------------------------------------------- |
| `New-Item documento.txt`                                     | Crea el fichero vacío “documento.txt”                 |
| `New-Item –ItemType Directory Datos`                         | Crea el directorio “Datos”.                           |
| `New-Item –ItemType Directory 	Datos\Documentos\Confidenciales` | Crea todos los directorios en caso de que no existan. |

## Eliminar directorios y archivos

Para eliminar archivos y directorios disponemos del comando `Remove-Item`.

## Copiar un directorio o archivo

## Mover o renombrar un archivo o directorio

## Cambiar el nombre a un archivo o directorio

