
# 1. Introducción

Vídeos de interés:
* [LVM. Learning Linux : "Lesson 20 Managing LVM"](https://youtu.be/m9SNN6IWyZo?list=PL3E447E094F7E3EBB)
* [LVM. Learning Linux: "Lesson 21 LVM snapshots"](https://youtu.be/N8rUlYL2O_g?list=PL3E447E094F7E3EBB)

---

# 2. Instalar SO sobre LVM

Vamos a instalar un sistema operativo GNU/Linux Debian, sobre unos discos con LVM.

> Otra forma de producir un efecto similar al de LVM, es mediante el uso de
sistemas de ficheros como BtrFS o ZFS (Consultar).
> Nosotros NO vamos a usar ZFS sino LVM en nuestra práctica, pero si algún
grupo desea usar ZFS debe hablar con el profesor.

## 2.1 Preparar MV

Realizar las siguientes tareas:
* Crear MV para Debian, con un disco de 8GB.
* [Configuración](../../global/configuracion/debian.md) de la máquina.
* Comenzar a instalar GNU/Linux Debian, parar al llegar al particionado.

## 2.2 Particionar

### Partición BOOT

* Crearemos una partición primaria de 100MB para `/boot` formato ext2.

Tener en cuenta que en la partición se monta `/boot` y va a parte (Fuera de LVM).

### Partición para LVM

* Crear una partición lógica con todo lo que nos queda de espacioo en disco. Definir la partición de tipo LVM.
* Ir a la gestión de volúmenes.
* En la partición LVM, crearemos un grupo de volumen llamado `vg-debian`.
* Dentro del `vg-debian`, podemos los VL (volúmenes lógicos) siguientes:
    * `lv-swap` (500 MB) usar para área de intercambio
    * `lv-raiz` (5 GB ext4) usar como raíz de la instalación del SO.
    * `lv-datos` (100MB ext3) usar como /home del sistema.
* Vemos que nos ha sobrado espacio. Lo dejamos así porque lo usaremos más adelante.

A continuación se muestran imágenes de referencia que NO tienen porqué coincidir con lo que se solicita.

![lvm-debian-01](./images/lvm-debian-01.png)

A continuación se muestra imagen de muestra que NO tiene que coincidir con lo que se solicita.

![lvm-debian-02](./images/lvm-debian-02.png)

* Terminamos la instalación del sistema operativo.
* Reiniciamos el sistema y comprobamos lo que tenemos:

```
date
hostname
hostname -a
hostname -f
hostname -d
ip a
route -n
host www.google.es
fdisk -l
vgdisplay
lvdisplay vg-debian
```

## 2.3 Ampliar VL

Ahora podremos ampliar *"en caliente"*, el espacio de lv-datos de 100MB a 400MB.

* Consultar el tamaño actual del volumen lógico: `lvdisplay -v /dev/vg-debian/lv-datos`
* Para ampliar el tamaño del volumen lógico: `lvextend -L 400 /dev/vg-debian/lv-datos`
* Comprobar con: `lvdisplay -v /dev/vg-debian/lv-datos`
* Comprobamos lo que tenemos ahora:
```
vgdisplay
lvdisplay vg-debian
```

---

# 3. Modificar el espacio físico LVM

* Hacer una instantánea o copia de seguridad de la MV antes de seguir.

Vamos a añadir al sistema anterior, más almacenamiento físico LVM, puesto que ya hemos agotado
todo el espacio libre de los discos físicos.

Esquema de PV, VG y LV:

![lvm-esquema1](./images/lvm-esquema1.jpeg)

## 3.1 Preparar la MV

* Añadimos 2 discos virtuales nuevos:
    * Disco de 200MB (B): con una partición completa del disco
    * Disco de 750MB (C): con 3 particiones de 250MB sin formato, ni tipo.

> NOTA: Las particiones las pueden crear con fdisk, gparted, etc.
>
> * Para crear particiones del disco sdb con fdisk:
>     * `fdisk /dev/sdb`
>     * `m` ver el menú con las opciones
>     * `n` para crear partición -> `p` primaria -> Número de la partición
>     * `w` grabar y salir
>     * `q` salir sin grabar

## 3.2 Crear VG y VL

* Crear un Grupo de Volumen llamado `vg-extra`, con el disco (B) y las 2
primeras particiones del (C). Veamos un ejemplo de cómo
crear un grupo de volúmenes con vgcreate:
`vgcreate /dev/NOMBRE-GRUPO-VOLUMEN /dev/discoA /dev/discoB1`
* Crear un nuevo Volumen Lógico llamado `lv-extra` con tamaño 690MB.
Comando: `lvcreate -L690M -n lv-extra vg-extra`.

> NOTA: La partición 3 del disco (C) NO la estamos usando por ahora.

* Comprobamos lo que tenemos:
```
ip a               # Muestra información de la configuración de red del equipo
vgdisplay vg-extra # Muestra información del grupo de volumen
lvdisplay vg-extra # Muestra información de los volúmenes lógicos de un grupo de volumen concreto
```

## 3.3 Escribir información

* El nuevo dispositivo `/dev/vg-extra/lv-extra` no tiene formato. Vamos a darle formato ext4.
Ejemplo: `mkfs.ext4 nombre-del-dispositivo`.
* Crear directorio (`/mnt/vol-extra`),donde vamos a montar el nuevo dispositivo (Volumen lógico).
* Montar el nuevo dispositivo (Volumen Lógico) en la carpeta /mnt/vol-extra.
* Comprobamos que se ha montado correctamente con `df -hT`.

A partir de ahora todo lo que escribamos en dicha carpeta se estará guardando en el dispositivo montado.
* Comprobar que apenas hay espacio usado en `/mnt/vol-extra` (df -hT).
* Escribir información en `/mnt/vol-extra`. Crear algunas carpetas y ficheros con tamaño mayor a cero.
Por ejemplo para crear un archivo de tamaño 1M podemos hacer `dd if=/dev/zero of=/mnt/vol-extra/file-size-1M bs=512 count=2048`.
El comando dd hay que usarlo con precaución.
* Comprobar el espacio usado en `/mnt/vol-extra` (df -hT).

## 3.4 Añadir más tamaño

* Añadir la tercera partición del disco (b) (no utilizada) al VG vg-extra.

```
pvcreate /dev/sdc3
vgextend vg-extra /dev/sdc3
vgdisplay vg-extra (Para comprobar el cambio)
```

* Ampliar el tamaño de lv-extra a 930MB (Comando lvextend). Comprobar el aumento del espacio (lvdisplay)
* Comprobar que los datos/información no se han borrado al ampliar el volumen lógico.

## 3.5 Quitar un disco físico del VG

> En LVM los discos físicos se llaman volúmenes físicos (Physical Volumes).

El grupo de volumen vg-extra, tiene dos volúmenes físicos que son los discos (B) y (C).
Vamos a quitar el disco (C) del VG, sin perder la información almacenada en él.

* Primero comprobamos el tamaño utilizado de nuestros datos: `du -sh /mnt/vol-extra`.
Este valor debe ser menor a 50 MB.
* Reducir el tamaño del volumen lógico lv-extra a 50 MB: `lvreduce --size 50MB /dev/vg-extra/lv-extra`
* Comprobamos: `lvdisplay /dev/vg-extra/lv-extra`.

Antes de quitar el disco hay que asegurarse de que no guarda datos.
* Movemos la información del disco sdc al disco sdb:

```
pvmove /dev/sdc1 /dev/sdb1
pvmove /dev/sdc2 /dev/sdb1
pvmove /dev/sdc3 /dev/sdb1
```

* Reducimos el tamaño del grupo de volumen:

```
vgreduce vg-extra /dev/sdc1
vgreduce vg-extra /dev/sdc2
vgreduce vg-extra /dev/sdc3
```

* Comprobar que se mantiene la información almacenada.
* Comprobamos lo que tenemos:

```
ip a
vgdisplay
lvdisplay vg-extra
```
* Ahora podemos a quitar el disco `/dev/sdc` de la MV sin problemas.

---

# 4. Discos dinámicos en Windows

En windows las particiones se llaman volúmenes básicos. Para poder hacer el mismo
efecto de LVM debemos convertir las particiones a volúmenes básicos.

## 4.1 Volumen Distribuido

* Vídeo sobre la [Creación de un volumen distribuido en Windows7](https://www.youtube.com/watch?v=prXBbHvqgx8)

* Añadimos 2 discos virtuales nuevos:
    * Disco de 200MB (B): con una partición completa del disco
    * Disco de 600MB (C): con 2 particiones de 300MB sin formato, ni tipo.
* Vamos a crear un volumen *Distribuido* con el disco (B) y las
primera partición del disco (C).

> * Nota: los volúmenes simples del primer disco deben permanecer intactos.
> * Un volumen Distribuido NO es RAID0. Se parece a RAID0 y usa discos de distinto tamaño
para crear otro mayor. Es el mismo efecto que el conseguido con LVM y los volúmenes lógicos.

* ¿Te das cuenta como con la misma letra de unidad se acceden a una zona
de almacenamiento (volumen dinámico) formada por partes (particiones o
volúmenes básicos) de varios discos?

---

# ANEXO A: Esquemas LVM

![lv-esquema2.jpeg](./images/lvm-esquema2.jpeg)

![lv-esquema3.jpeg](./images/lvm-esquema3.jpeg)

![lv-esquema4.jpeg](./images/lvm-esquema4.jpeg)
