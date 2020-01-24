
```
Curso           : 201920
Requisitos      : SO OpenSUSE (Yast) y Windows
Tiempo estimado : 6 sesiones
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

Vamos a instalar un sistema operativo GNU/Linux OpenSUSE desde cero, sobre volúmenes(LVM) en lugar de particiones.

## 1.1 Preparar MV

Realizar las siguientes tareas:
* Crear MV para OpenSUSE, con lo siguiente:
    * Sistema UEFI-BIOS activo.
    * Un disco de 8GB.
    * [Configuración](../../global/configuracion/opensuse.md).
* Comenzar a instalar el sistema operativo.
* Elegir escritorio genérico.
* **Parar al llegar al particionado**.

Por la experiencia de instalaciones previas de OpenSUSE con entorno gráfico, ya sabemos que el espacio en disco se nos va a quedar pequeño enseguida. Cuando se nos llene el espacio del sistema, vamos a hacer uso de los volúmenes (LVM) para ampliarlo sin necesidad de reinstalar el sistema.

## 1.2 Particionar

* Crearemos una partición para el **sistema de arranque EFI** independiente. De modo que se quedará fuera de los volúmenes (LVM).

| ID | Size   | Tipo de partición       | Formato |
| -- | ------ | ----------------------- | ------- |
| 1  | 500 MB | Sistema de arranque EFI | FAT     |
| 2  | Resto  | Linux LVM               | -       |

* Ir a la gestión de volúmenes (LVM).
* Crear un grupo de volumen llamado `grupoXX`. Donde XX es el número asociado a cada alumno.
* Crear los siguientes volúmenes lógicos (LV) dentro del `grupoXX`:

| Nombnre   | Tamaño | Tipo                | Formato | Montar |
| --------- | ------ | ------------------- | ------- | ------ |
| volXXswap | 500 MB | Area de intercambio | swap    | -      |
| volXXraiz | 6 GB   | Sistema operativo   | ext4    | /      |
| volXXhome | 100 MB | Datos de usuario    | ext3    | /home  |

> Vemos que nos ha sobrado espacio. Lo dejamos así porque lo usaremos más adelante.

* Seguimos con la instalación del sistema operativo.
* En Software elegir "Escritorio XFCE" para tener un escritorio ligero.
* Cambiar gestor de red "Network Manager" por "Wicked". Si nos olvidamos de este punto, lo podemos hacer por `Yast` con el sistema instalado.

## 1.3 Comprobación de la instalación con volúmenes (LVM)

Podemos hacernos la idea de que los "grupos de volumen" son como si fueran discos, y los "volúmenes lógicos" particiones.

* Reiniciamos el sistema y comprobamos lo que tenemos:

```
date
hostname
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
* Podemos usar `Yast` para ampliar el tamaño del volumen lógico. O también podemos hacerlo por comandos: `lvextend --resizefs -L 400 /dev/grupoXX/volXXhome`.
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

* Crear un Grupo de Volumen llamado `grupoXXextra`, con el disco (B) y las 2 primeras particiones del (C).
* Crear un nuevo Volumen Lógico
    * Nombre: `volXXextra`
    * Tamaño: 690MB.
    * Formato: `ext4`
    * Punto de montaje: `/mnt/folderXXextra`.

> NOTA: La partición 3 del disco (C) NO la estamos usando por ahora.

* Comprobamos lo que tenemos:
```
vgdisplay grupoXXextra # Información del grupo de volumen
lvdisplay grupoXXextra # Volúmenes lógicos de un grupo
df -hT                 # Volmnen montado
```

## 3.3 Escribir información

A partir de ahora todo lo que escribamos en la carpeta `/mnt/folderXXextra` se estará guardando en el dispositivo montado.

* Comprobar el espacio usado en `/mnt/folderXXextra` (df -hT).
* Escribir información en `/mnt/folderXXextra`. Crear algunas carpetas y ficheros con tamaño mayor a cero.

> Por ejemplo para crear un archivo de tamaño 1M podemos hacer `dd if=/dev/zero of=/mnt/folderXXextra/file-size-1M bs=512 count=2048`.
> **OJO**: El comando dd hay que usarlo con precaución.

* Comprobar el espacio usado en `/mnt/folderXXextra` (df -hT).

## 3.4 Añadir más tamaño

* Añadir la tercera partición del disco (C) (no utilizada) al VG vg-extra. Podemos hacerla por Yast.
* `vgdisplay grupoXXextra`, para comprobar el cambio.
* Ampliar el tamaño de volXXextra a 930MB. Comprobar el aumento del espacio (lvdisplay)
* Comprobar que los datos/información no se han borrado al ampliar el volumen lógico.

## 3.5 Quitar un disco físico del VG

> En LVM los discos físicos se llaman volúmenes físicos (Physical Volumes).

El grupo de volumen grupoXXextra, tiene dos volúmenes físicos que son los discos (B) y (C). En los pasos siguientes vamos a dejar de usar disco (C) dentro del VG, sin perder la información almacenada en él. Y además en "caliente".

* Primero comprobamos el tamaño de nuestros datos: `du -sh /mnt/folderXXextra`. Este valor debe ser menor a 50 MB si queremos reducir el volumen.
* **Reducir el tamaño del volumen lógico** lvXXextra a 50 MB. Lo podemos hace por `Yast`. Si lo hacemos por comandos sería algo como `lvreduce --size 50MB /dev/grupoXXextra/volXXextra`.
* Redimensionar el sistema de ficheros para adaptarlo al nuevo espacio. `df -hT` debe mostrar el mismo tamaño que el que tiene el volumen ahora.
* Comprobamos: `lvdisplay /dev/grupoXXextra/volXXextra`.

Antes de quitar el disco hay que asegurarse de que no guarda datos.
* **Movemos la información** del disco sdc al disco sdb:

```
pvmove /dev/sdc1 /dev/sdb1
pvmove /dev/sdc2 /dev/sdb1
pvmove /dev/sdc3 /dev/sdb1
```

* **Reducir el tamaño del grupo** de volumen:

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

En Windows las particiones se llaman volúmenes básicos. Para poder hacer el mismo efecto que hicimos con LVM, primero debemos convertir las particiones a volúmenes dinámicos.

## 4.1 Volumen Distribuido

Un volumen Distribuido de Windows se parece a RAID0 pero NO es RAID0. Un volumen distribuido usa discos de distinto tamaño para crear otro mayor. Es el mismo efecto que el conseguido con LVM y los volúmenes lógicos.

* Vídeo sobre la [Creación de un volumen distribuido en Windows7](https://www.youtube.com/watch?v=prXBbHvqgx8)
* Añadimos 2 discos virtuales nuevos:
    * Disco de 200MB (B): con una partición completa del disco
    * Disco de 600MB (C): con 2 particiones de 300MB sin formato, ni tipo.
* Nota: los volúmenes simples del primer disco (A) deben permanecer intactos.
* Vamos a crear un volumen *Distribuido* con el disco (B) y la
primera partición del disco (C).
* Comprobar que con la letra de unidad que hayamos escogido para el volumen, se accede a una zona de almacenamiento (volumen dinámico) formada por partes (particiones o volúmenes básicos) de varios discos diferentes.
