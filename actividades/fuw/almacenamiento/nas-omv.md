

# Almacenamiento NAS con Open Media Vault

Enlace de interés:
* https://www.openmediavault.org/

openmediavault es una herramienta NAS (network attached storage) basada en Debian.
Contiene servicios como SSH, (S)FTP, SMB/CIFS, DAAP media server, RSync, cliente
BitTorrent client, etc. Tiene un diseño modular que le permite ampliar funciones
mediante el uso de plugins.

openmediavault está diseñado para usarse principalmente para pequeñas oficinas o
entornos domésticos, pero no está limitado a esos entornos.
Es simple y fácil de usar para un usuario no experto.

## Crear la MV

* Crear una nueva MV (tipo Debian 64 bits) con 3 discos:
    * Disco 8 GB (Para instalar el sistema)
    * Disco de 500 MB (Para almacenamiento).
    * Disco de 500 MB (Para almacenamiento).
* Red en modo puente.

## Instalar Open Media Vault.

* Nombre de máquina: `openmediavaultXX`
* Dominio: `curso1718`
* Elegir el disco1 (sda) para instalar el sistema operativo.
* Al terminar la instalación se nos muestra la IP del NAS.
    * Apuntar la IP del NAS.
    * Usuario/clave del panel Web: `admin/openmediavault`
* Entrar con el usuario `root`.
* Ejecutar el comando `omv-firstaid`
    * Configurar IP estática para el NAS.
    * Cambiar clave de acceso al panel Web.

## Crear almacenamiento RAID

* Desde otra máquina abrimos navegador con URL `ip-del-nas`
* Poner usuario/clave del panel Web.
* Ir a `Almacenamiento -> Gestión de Raid`
    * Crear un `espejo` con el nombre `nasraid1`, usando los discos `sdb` y `sdc`.
* Ir a `Almacenamiento -> Sistema de archivos`
    * Crear un nuevo sistema de archivos
        * Usar el dispositivo RAID1 (`nasraid1`)
        * Nombre `nasdatos`
        * Formato `ext4`
    * Montar el disco de `nasraid1`.

## Crear recurso compartido

* Ir a `Servicios -> SMB/CIFS -> Compartidos`
* Añadir
    * Habilitar `SI`
    * Carpeta compartida -> Crear `+`
        * Nombre: `public`
        * Dispositivo: `nasraid1`
        * Ruta: `public/`
        * Con permisos de Lectura/escritura para los usuarios.
    * Público `NO`
    * Sólo lectura `NO`

## Crear usuario

* Ir a `Permisos de Acceso -> Usuarios`.
    * Crear un nuevo usuario `nombre-alumnoXX`.
    * Comprobar que pertenece al grupo `users`.
* Ir a `Permisos de Acceso -> Carpetas compartidas`.
    * Dar permisos al usuario `nombre-alumnoXX` de lectura/escritura sobre la carpeta compartida.

## Activar el servicio

* Ir a `Servicios -> SMB/CIFS -> Configuración -> Opciones Generales -> Habilitar`
* Ir a `Diagnósticos -> Sevicios` para verificar que el servicio SMB/CIFS está habilitado y en ejecución.
* Si el servicio no está en ejecución podemos reiniciar el equipo o probar con `systemctl start smbd`.

## Comprobar

Ir a MV Windows 7:
* Comprobar el acceso al recurso compartido (incluir captura de pantalla).
* Podemos encontrar la MV más rápido poniendo `\\\ip-del-servidor` en la búsqueda de red.
* `net use` para comprobar sesiones de red abiertas.
* `netstat`, comprobar que hay una conexión establecida con el servidor.

> Después de cada conexión se quedan guardada la información en el cliente Windows (Ver comando `net use`).
> Para cerrar las conexión SMB/CIFS que ha realizado el cliente al servidor, usamos el comando: `C:>net use * /d /y`.

Ir a MV GNU/Linux:
* Comprobar el acceso al recurso compartido  (incluir captura de pantalla).
    * Abrir explorador de archivos -> CTRL+L
    * Poner URL `smb://ip-del-nas`
* `netstat`, comprobar que hay una conexión establecida con el servidor.

---

# ANEXO

`firstaid`para cambiar clave.
