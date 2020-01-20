
```
Curso           : 201819
Requisitos      : SO GNU/Linux y Windows
Tiempo estimado : 11 sesiones (1semana)
Módulos         : Sistemas Operativos
```
[Metodología](../../global/metodologia.md)

---
# Volúmenes Lógicos (OpenSUSE + Windows)

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (1.3) Comprobación instalación LVM | | | |
| (2.2) Comprobamos | | | |
| (3.6) Comprobamos | | |. |

---
# 1. Instalar SO sobre LVM

Vamos a instalar un sistema operativo GNU/Linux OpenSUSE desde cero, sobre unos discos con LVM.

## 1.1 Preparar MV

Realizar las siguientes tareas:
* Crear MV para OpenSUSE, con lo siguiente:
    * Sistema UEFI-BIOS activo.
    * Un disco de 8GB.
    * [Configuración](../../global/configuracion/opensuse.md).
* Comenzar a instalar el sistema operativo, pero **parar al llegar al particionado**.

Por la experiencia de instalaciones previas de OpenSUSE con entorno gráfico, ya sabemos que el espacio en disco se nos va a quedar pequeño enseguida. Cuando se nos llene el espacio del sistema, vamos a hacer uso de los volúmenes (LVM) para ampliarlo sin necesidad de reinstalar el sistema.

## 1.2 Particionar

* Crearemos una partición para el **sistema de arranque EFI** independiente. De modo que se quedará fuera de los volúmenes (LVM).

| ID | Size   | Tipo de partición       | Formato |
| -- | ------ | ----------------------- | ------- |
| 1  | 500 MB | Sistema de arranque EFI | FAT     |

* Ir a la gestión de volúmenes (LVM).
* Crear un grupo de volumen llamado `grupoXX`. Donde XX es el número asociado a cada alumno.
* Dentro del `grupoXX`, crearemos los volúmenes lógicos (LV) siguientes:

| Logical Volume | Tamaño | Formato | Descripción         |
| -------------- | ------ | ------- | ------------------- |
| volXXswap      | 500 MB | swap    | Area de intercambio |
| volXXraiz      | 6 GB   | ext4    | Instalar el SO (/)  |
| volXXhome      | 100 MB | ext3    | Datos de usuario (/home) |

* Vemos que nos ha sobrado espacio. Lo dejamos así porque lo usaremos más adelante.
* Terminamos la instalación del sistema operativo.

## 1.3 Comprobación de la instalación con volúmenes (LVM)

Podemos hacernos la idea de que los "grupos de volumen" son como si fueran discos, y los "volúmenes lógicos" particiones.

* Reiniciamos el sistema y comprobamos lo que tenemos:

```
date
hostname -f
ip a
fdisk -l
vgdisplay          # Muestra los grupos de volumen
lvdisplay grupoXX  # Muestra los volúmenes lógicos de un grupo
```

---
# 2 Aumentar el tamaño del VL en caliente

Ahora podremos ampliar _"en caliente"_, el espacio de `volXXhome` de 100MB a 400MB.

## 2.1 Ampliamos

* Consultar el tamaño actual del volumen lógico: `lvdisplay -v /dev/grupoXX/volXXhome`
* Para ampliar el tamaño del volumen lógico podemos:
    * Usar entorno gráfico: `Yast`
    * Usar comandos: `lvextend --resizefs -L 400 /dev/grupoXX/volXXhome`
* Comprobar con: `lvdisplay -v /dev/grupoXX/volXXhome`

## 2.2 Comprobamos

* Comprobamos lo que tenemos por ahora:
```
vgdisplay          # Comprobar que ha aumentado el espacio ocupado
lvdisplay grupoXX
df -hT
```

> Si el comando `df -hT` no nos devuelve el tamaño que esperamos para el volumen, entonces podemos usar `resize2fs /dev/grupoXX/volXXhome` sirve ajustar dicho valor.

---
# 3. Modificar el espacio físico LVM

* Hacer una instantánea o copia de seguridad de la MV antes de seguir.

Vamos a añadir al sistema anterior, más almacenamiento físico LVM, puesto que ya hemos agotado todo el espacio libre de los discos físicos.

Esquema de PV, VG y LV:

![lvm-esquema1](./images/lvm-esquema1.jpeg)

## 3.1 Preparar la MV

* Añadimos 2 discos virtuales nuevos:
    * Disco de 200MB (B): con una partición completa del disco
    * Disco de 750MB (C): con 3 particiones de 250MB sin formato, ni tipo.

> NOTA: Las particiones las podemos crear con fdisk, gparted, Yast, etc.
>
> * Para crear particiones del disco sdb con fdisk:
>     * `fdisk /dev/sdb`
>     * `m` ver el menú con las opciones
>     * `n` para crear partición -> `p` primaria -> Número de la partición
>     * `w` grabar y salir
>     * `q` salir sin grabar

## 3.2 Crear VG y VL

* Crear un Grupo de Volumen llamado `grupoXXextra`, con el disco (B) y las 2 primeras particiones del (C). Veamos un ejemplo de cómo
crear un grupo de volúmenes con vgcreate: `vgcreate /dev/NOMBRE-GRUPO-VOLUMEN disco1 particion`
* Crear un nuevo Volumen Lógico llamado `volXXextra` con tamaño 690MB.
Comando: `lvcreate -L690M -n volXXextra grupoXXextra`.

> NOTA: La partición 3 del disco (C) NO la estamos usando por ahora.

* Comprobamos lo que tenemos:
```
vgdisplay grupoXXextra # Información del grupo de volumen
lvdisplay grupoXXextra # Volúmenes lógicos de un grupo
```

## 3.3 Escribir información

* El nuevo dispositivo `/dev/grupoXXextra/volXXextra` no tiene formato. Vamos a darle formato ext4. Ejemplo: `mkfs.ext4 nombre-del-dispositivo`.
* Crear directorio (`/mnt/folderXXextra`),donde vamos a montar el nuevo dispositivo (Volumen lógico).
* Montar el nuevo dispositivo (Volumen Lógico) en la carpeta `/mnt folderXXextra`.
* Comprobamos que se ha montado correctamente con `df -hT`.

A partir de ahora todo lo que escribamos en dicha carpeta se estará guardando en el dispositivo montado.
* Comprobar el espacio usado en `/mnt/folderXXextra` (df -hT).
* Escribir información en `/mnt/folderXXextra`. Crear algunas carpetas y ficheros con tamaño mayor a cero.

> Por ejemplo para crear un archivo de tamaño 1M podemos hacer `dd if=/dev/zero of=/mnt/folderXXextra/file-size-1M bs=512 count=2048`.
>
> **El comando dd hay que usarlo con precaución**.

* Comprobar el espacio usado en `/mnt/folderXXextra` (df -hT).

## 3.4 Añadir más tamaño

* Añadir la tercera partición del disco (C) (no utilizada) al VG vg-extra. Podemos hacerla por Yast o usando los siguientes comandos:

```
pvcreate  /dev/sdc3              # Crear un dispositivo físico de LVM
vgextend  grupoXXextra /dev/sdc3 # Ampliar el grupo de volumen.
vgdisplay grupoXXextra           # Para comprobar el cambio
```

* Ampliar el tamaño de volXXextra a 930MB (Comando lvextend). Comprobar el aumento del espacio (lvdisplay)
* Comprobar que los datos/información no se han borrado al ampliar el volumen lógico.

## 3.5 Quitar un disco físico del VG

> En LVM los discos físicos se llaman volúmenes físicos (Physical Volumes).

El grupo de volumen grupoXXextra, tiene dos volúmenes físicos que son los discos (B) y (C). En los pasos siguientes vamos a dejar de usar disco (C) dentro del VG, sin perder la información almacenada en él. Y además en "caliente".

* Primero comprobamos el tamaño utilizado de nuestros datos: `du -sh /mnt/folderXXextra`. Este valor debe ser menor a 50 MB.
* Reducir el tamaño del volumen lógico lvXXextra a 50 MB: `lvreduce --size 50MB /dev/grupoXXextra/volXXextra`.
* Redimensionar el sistema de ficheros para adaptarlo al nuevo espacio. `df -hT` debe mostrar el mismo tamaño que el que tiene el volumen ahora.
* Comprobamos: `lvdisplay /dev/grupoXXextra/volXXextra`.

Antes de quitar el disco hay que asegurarse de que no guarda datos.
* Movemos la información del disco sdc al disco sdb:

```
pvmove /dev/sdc1 /dev/sdb1
pvmove /dev/sdc2 /dev/sdb1
pvmove /dev/sdc3 /dev/sdb1
```

* Reducimos el tamaño del grupo de volumen:

```
vgreduce grupoXXextra /dev/sdc1
vgreduce grupoXXextra /dev/sdc2
vgreduce grupoXXextra /dev/sdc3
```

* Comprobar que se mantiene la información almacenada.

## 3.6 Comprobamos

* Comprobamos lo que tenemos:

```
vgdisplay
lvdisplay grupoXXextra
```
* Ahora podemos a quitar el disco `/dev/sdc` de la MV sin problemas.

---

# 4. Discos dinámicos en Windows

En Windows las particiones se llaman volúmenes básicos. Para poder hacer el mismo efecto de LVM debemos convertir las particiones a volúmenes básicos.

## 4.1 Volumen Distribuido

* Vídeo sobre la [Creación de un volumen distribuido en Windows7](https://www.youtube.com/watch?v=prXBbHvqgx8)
* Añadimos 2 discos virtuales nuevos:
    * Disco de 200MB (B): con una partición completa del disco
    * Disco de 600MB (C): con 2 particiones de 300MB sin formato, ni tipo.
* Vamos a crear un volumen *Distribuido* con el disco (B) y las
primera partición del disco (C).

> * Nota: los volúmenes simples del primer disco deben permanecer intactos.
> * Un volumen Distribuido NO es RAID0. Se parece a RAID0 y usa discos de distinto tamaño para crear otro mayor. Es el mismo efecto que el conseguido con LVM y los volúmenes lógicos.

* Con la misma letra de unidad se acceden a una zona de almacenamiento (volumen dinámico) formada por partes (particiones o
volúmenes básicos) de varios discos. Comprobarlo.
