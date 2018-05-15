

# Almacenamiento NAS con Open Media Vault

Enlace de interés: 
* https://www.openmediavault.org/

## Crear la MV

* Crear una nueva MV (tipo Debian) con 3 discos:
    * Disco 8 GB (Para instalar el sistema)
    * Disco de 500 MB (Para almacenamiento).
    * Disco de 500 MB (Para almacenamiento).
    
## Instalar Open Media Vault.

* Nombre de máquina: `openmediavaultXX`
* Dominio: `curso1718`
* Elegir el disco para instalar el sistema operativo.
* Al terminar la instalación entramos para consultar la IP de la máquina.

## Crear recurso compartido

* Desde otra máquina abrimos navegador con URL `ip-del-nas`
* Usuario/clave `admin/openmediavault`
* Ir a `Almacenamiento -> Gestión de Raid`
* Crear un `espejo` con el nombre `nasraid1`, usando los discos `sdb` y `sdc`.
* Ir a `Almacenamiento -> Sistema de archivos`
* Crear un sistema de archivos con el nombre `nasdatos`, usando el dispositivo RAID1 (`nasraid1`)
* Ir a `Permisos de Acceso -> Usuarios`
* Crear un nuevo usuario `nombre-alumnoXX`
* Ir a `Servicios -> SMB/CIFS -> Compartido`
* Crear recurso compartido `public` con permisos de Lectura/escritura para los usuarios.
* Ir a `Servicios -> SMB/CIFS` -> `Habilitar`
* Ir a `Diagnósticos -> Sevicios` para verificar que el servicio SMB/CIFS está habilitado y en ejecución.

## Comprobar

* Desde un cliente Windows 7 comprobar el acceso al recurso compartido.
* Desde un cliente GNU/Linux comprobar el acceso al recurso compartido.

---

# ANEXO

`firstaid`para cambiar clave.
