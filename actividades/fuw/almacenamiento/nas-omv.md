

# Almacenamiento NAS con Open Media Vault

Enlace de interés:
* https://www.openmediavault.org/

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
* Crear un sistema de archivos con el nombre `nasdatos`, usando el dispositivo RAID1 (`nasraid1`)

## Crear usuario

* Ir a `Permisos de Acceso -> Usuarios`
* Crear un nuevo usuario `nombre-alumnoXX`

## Crear recurso compartido

* Ir a `Servicios -> SMB/CIFS -> Compartido`
* Crear recurso compartido `public` con permisos de Lectura/escritura para los usuarios.
* Ir a `Servicios -> SMB/CIFS` -> `Habilitar`
* Ir a `Diagnósticos -> Sevicios` para verificar que el servicio SMB/CIFS está habilitado y en ejecución.
* Si el servicio no está en ejecución podemos reiniciar el equipo o probar con `systemctl start smb`.

## Comprobar

* Desde un cliente Windows 7 comprobar el acceso al recurso compartido.
* Desde un cliente GNU/Linux comprobar el acceso al recurso compartido.
    * `smb://ip-del-nas`

---

# ANEXO

`firstaid`para cambiar clave.
