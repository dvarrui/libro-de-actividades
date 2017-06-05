```
* Práctica utilizada en los cursos 201213, 201314, 201415
* Actualizada para el curso 201516
```

# Almacenamiento NAS con OpenSUSE

* Trabajar de forma individual.
* Entregar informe con capturas de pantalla.

---

# 0. Definición de Wikipedia

El almacenamiento conectado en red, Network Attached Storage (NAS), es el nombre
dado a una tecnología de almacenamiento dedicada a compartir la capacidad de almacenamiento de un computador (servidor) con computadoras personales o servidores clientes a través de una red (normalmente TCP/IP), haciendo uso de un sistema operativo optimizado para dar acceso con los protocolos CIFS, NFS, FTP o TFTP.

Los sistemas NAS son dispositivos de almacenamiento a los que se accede desde los equipos a través de protocolos de red (normalmente TCP/IP). También se podría considerar un sistema NAS a un servidor (Microsoft Windows, Linux, etcétera) que comparte sus unidades por red, pero la definición suele aplicarse a sistemas específicos.

Los protocolos de comunicaciones NAS están basados en archivos por lo que el cliente solicita el archivo completo al servidor y lo maneja localmente, por lo que están orientados a manipular una gran cantidad de pequeños archivos. Los protocolos usados son protocolos de compartición de archivos como Network File System (NFS) o Microsoft Common Internet File System (CIFS).

Muchos sistemas NAS cuentan con uno o más dispositivos de almacenamiento para incrementar su capacidad total. Frecuentemente, estos dispositivos están dispuestos en RAID (Redundant Arrays of Independent Disks) o contenedores de almacenamiento redundante.

---

# 1. Prepara la máquina y los discos

Enlaces de interés:
* Montar en una MV con OpenSUSE el servicio Samba
(Consultar [configuración](../../global/configuracion/opensuse.md).
* Consultar vídeo [SAMBA Management with YaST on SUSE](https://youtu.be/Zh3J-HUYDY4?list=PL3E447E094F7E3EBB)

Normalmente los NAS usan un disco a parte para guardar los datos. Y para mayor
seguridad usan un almacenamiento en RAID1 o RAID5.
Vamos a usar el entorno gráfico Yast para crear RAID1.

* Ir a MV OpenSUSE NAS
* Configurar hostname con `nasXX`.
* Añadimos 2 discos de tamaño 500 MB a la MV VirtualBox.
* Iniciamos la MV.
* Ir a `Yast -> Particionador`.
* Crear un RAID1 con los 2 discos.
* Montar el RAID1 en la ruta `/mnt/nas`.
    * Pista. Ejecutando el comando `df -hT` debemos ver los discos montados en la ruta.
    * Pista. En el directorio `/mnt/nas` debe aparecer un `lost+found`.

---

# 2. Instalar y configurar Samba

* Instalar y configurar un servidor Samba (desde Yast por ejemplo o con zypper).

![nas-opensuse-yast-samba.png](./files/nas-opensuse-yast-samba.png)

> Samba es un software que permite que el equipo se comunique
usando el protocolo SMB/CIFS típico de las redes Windows.

* Ir a MV OpenSUSE NAS.
* Configurar Servidor Samba con:
    * Grupo de trabajo: `curso1617`
    * Sin controlador de Dominio
    * Inicio del servicio: `durante el arranque`
    * Puerto abierto en el cortafuegos

## 2.1 Crear recurso compartido (I)

* Crear el grupo `hobbitsXX`
    * Añadir los usuarios `frodoXX` y `bilboXX`
* Crear el recurso compartido (I):
    * Crear la carpeta `/mnt/nas/hobbitonXX.d`
    * Con permisos de lectura/navegación para todos.
    * Con permisos de escritura/lectura/navegación para el grupo `hobbitsXX`.
    * Usar `Yast -> Samba Server` para crear recursos compartido (SMB/CIFS)
    en la ruta anterior, con el nombre `hobbitonXX`.
    * Poner permisos al recurso de red de lectura para todos.
    * Heredar ACLS
    * `path = /mnt/nas/hobbitonXX.d`
    * `valid users = @hobbitsXX` (Los usuarios de este grupo pueden acceder al recurso)
    * `read only = No`

![nas-samba-share.png](./files/nas-samba-share.png)

## 2.2 Crear recurso compartido (II)

* Crear el grupo `humanosXX`
    * Añadir los usuarios `gandalfXX` y `aragornXX`
* Crear el recurso compartido (II):
    * Crear la carpeta `/mnt/nas/mordorXX.d`
    * Con permisos de lectura/navegación para todos.
    * Crear recursos compartido (SMB/CIFS) en dicha ruta, con el nombre `mordorXX`.
    * Poner permisos al recurso de red de lectura para todos.
    * Heredar ACLS
    * `path = /mnt/nas/mordorXX.d`
    * `valid users = gandalfXX, frodoXX` (Estos usuarios pueden acceder al recurso)
    * `read only = Yes`

## 2.3 Comprobar

* Poner también clave en Samba para los usuarios.
    * `smbpasswd -a USUARIO` para poner clave del usuario en samba.
    * `smbpasswd -e USUARIO` para activar el usuario en samba.
* Reiniciar el servicio:
    * `systemctl stop smb`
    * `systemctl start smb`
    * `systemctl status smb`
* Comprobar la configuración por comandos.
    * `cat /etc/samba/smb.conf`
    * `testparm`
* `netstat -untap`, comprobar el servicio desde la máquina local.

---

# 3. Comprobar desde un cliente OpenSUSE

* Ir a MV cliente OpenSUSE.
* Comprobar el acceso al servidor NAS desde otra máquina con todos los
usuarios, y todos los recursos.
* Comprobaciones:
    * Ejecutando `smbtree` en OpenSUSE veremos todos los recursos compartidos de red.
    * Ejecutando `smbclient -L ip-servidor-samba`, ven los recursos de una máquina concreta.
* Comprobar acceso a las carpetas compartidas (incluir captura de pantalla).

---

# 4. Comprobar desde un cliente Windows 7

* Ir a MV cliente Windows 7.
* Comprobar acceso a las carpetas compartidas (incluir captura de pantalla).
