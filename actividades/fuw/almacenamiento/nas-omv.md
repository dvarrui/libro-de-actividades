

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

## Acceso Web

* Desde otra máquina abrimos navegador con URL `ip-del-nas`
* Usuario/clave `admin/openmediavault`

## Recurso compartido

* Activar el servicio SMB/CIFS.
* Crear un recurso compartido SMB/CIFS.
* Crear usuario con permisos de lectura/escritura sobre el recurso.
* Desde un cliente Windows 7 comprobar el acceso al recurso compartido.
* Desde un cliente GNU/Linux comprobar el acceso al recurso compartido.

---

# ANEXO

`firstaid`para cambiar clave.
