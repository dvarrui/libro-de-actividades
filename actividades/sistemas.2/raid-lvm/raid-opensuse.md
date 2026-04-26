
```
Curso       : 202122, 202021, 201920, 201819
Área        : Sistemas operativos, fundamentos de hardware
Descripción : Practicar las configuraciones RAID0 y RAID1 por software.
Requisitos  : OpenSUSE Leap 15, Windows Server
Tiempo      : 8 sesiones
```

# Instalar OpenSUSE en disco RAID0 software

Vamos a instalar un sistema operativo OpenSUSE sobre varios discos usando RAID0 software.

# 1. Introducción

Leer sobre el tema y consultar las dudas:
* [Raid - Wikipedia](https://es.wikipedia.org/wiki/RAID)
* [Niveles RAID](https://blog.elhacker.net/2013/12/tipos-niveles-raid-hardware-software.html?m=1&s=09)
* [Niveles RAID (Tipos, ventajas e inconvenientes](https://blog.elhacker.net/2013/12/tipos-niveles-raid-hardware-software.html?m=1&s=09)

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (1.3) Comprobar RAID-0 | | | |
| (2.3) Comprobar RAID-1 | | | |
| (3) Quitar disco y probar RAID-1 | | |. |

## 1.1 Creación de la MV

**Teoría sobre EFI BIOS**
A partir de ahora, vamos a hacer las instalaciones en máquinas con EFI BIOS. Los ordenadores actuales todos vienen con EFI BIOS. Es una característica del hardware de la placa base.

Para crear una MV con EFI: `Ir VirtualBox -> Configuración de la MV -> Sistema -> Placa base -> Habilitar EFI`

Durante la instalación hay crear la partición EFI. La particion EFI la usan los sistemas operativos para guardar sus ficheros de arranque. En el caso de una instalación dual EFI...  tanto Windows como GNU/Linux guardarán sus ficheros de arranque en la partición EFI. Recordar que habrá que montar /boot/efi en la partición EFI. Esta carpeta contiene los ficheros de arranque del sistema operativo.

**Ir a VirtualBox:**
* Crear una máquina virtual sin discos.
* Configurar la MV con EFI activo (`Configuración -> Sistema -> EFI`).
* Añadir 3 discos virtuales SATA a la MV:
    * (a) 300 MiB
    * (b) 10 GiB
    * (c) 10 GiB
* OJO: Los discos deben tener esos tamaños, y en ese orden concreto.

## 1.2 Partición EFI

* Empezamos el proceso de instalación del SO.
* Elegimos `particionado experto o manual -> Continuar con particiones existentes`.

**Partición de arranque**: El arranque del sistema operativo (boot) lo pondremos en una partición normal, fuera del RAID.
* Ir a `Discos -> sda -> Particiones -> Crear nueva partición`, para crear la siguiente partición en el disco sda:

| Dispositivo   | Size   | Tipo                      | Formato | Montar    |
| ------------- | ------ | ------------------------- | ------- | --------- |
| /dev/sda1     | 300 MB | Partición de Arranque EFI | fat     | /boot/efi |

**OJO: /dev/sda es el disco y /dev/sda1 es la partición 1.**

> El sistema de arranque irá en el disco (a). Por tanto, los ficheros que inician el SO (boot) irán en una partición aparte sin RAID, para evitar problemas durante el arranque del sistema.

## 1.3 Partición del sistema operativo

**Dispositivo para el sistema operativo**: El sistema operativo lo vamos a instalar en un dispositivo virtual RAID0.
* Ir a `Particionador -> RAID -> Añadir nuevo RAID`, y elegimos:
    * Hacer un `raid0`
    * Le pondremos el nombre `deviceXXr0` al dispositivo RAID0. Donde XX es el número asignado al alumno.
    * Elegir los discos `sdb` y `sdc` para conformar el RAID0.
* Aceptar.

Ya tenemos creado el nuevo dispositivo. Ahora vamos a crear una partición dentro.

* Ir a `RAID -> deviceXXr0 -> Particiones -> Crear nueva partición`:

| Partición            | Size   | Tipo                  | Formato | Montar |
| -------------------- | ------ | --------------------- | ------- | ------ |
| /dev/md/deviceXXr0p1 | 20 GiB | Partición del sistema | ext4    | /      |

> NOTA:
> * En esta ocasión no crearemos área de intercambio(swap), ni tampoco una partición independiente para `/home`.
> * El "tamaño de la porción" se refiere al tamaño del cluster o bloque de asignación del sistema de formateo (Lo explicamos en la Unidad 1). Dejaremos el valor por defecto.

* Seguimos la instalación teniendo en cuenta lo siguiente:
    * Consultar la [configuración](../../global/configuracion/opensuse.md).
    * Elegir escritorio XFCE porque ocupa menos espacio en disco.

## 1.4 Comprobar RAID0

> Como resultado final obtendremos una instalación de SO GNU/Linux OpenSUSE en un disco RAID0 de 20 GiB, formado por la unión de dos discos físicos `sdb` (10 GiB) y `sdc` (10 GiB).

* Una vez instalado ejecutar los siguientes comandos, e incluir su salida en el informe:

```
date              # Muestra la fecha/hora actual
hostnamectl       # Nombre de la máquina
ip a              # Muestra configuración interfaces de red
ip route          # Muestra información de enrutamiento
host www.nba.com  # Comprueba la resolución de nombres
```

> Si estos comandos no devuelven la información esperada, entonces solucionar el problema y volver a comprobar la salida de los comandos.

Información sobre los discos, particiones y dispositivos:

```
lsblk             # Muestra esquema de discos/particiones/montaje
df -hT            # Muestra los puntos de montaje
cat /proc/mdstat  # Muestra la configuración RAID
```

NOTA: Es posible que la salida de algunos comandos el nombre del dispositivo RAID0 se vea también como `/dev/md127`. No preocuparse por este hecho. Es la nomenclatura antigua. Algunos SSOO mantienen ambos nombres por compatibilidad con herramientas antiguas.

> Si estos comandos no devuelven la información esperada, entonces solucionar el problema y volver a comprobar la salida de los comandos.

---
# 2. RAID-1 software

Ahora vamos a practicar el RAID1 con nuestra MV anterior.

**IMPORTANTE**: Antes de seguir, haz una instantánea de la MV. Esto es:
* Ir a VirtualBox.
* Elegir la MV.
* Ir a `Snapshot/instantánea -> Tomar instantánea`.

## 2.1 Preparar la MV

Vamos a añadir a la MV, varios discos más para montar un RAID-1 software:
* Apagamos la MV.
* Crear 2 discos virtuales (del mismo tamaño) a la MV:
    * (d) 500 MiB
    * (e) 500 MiB
* OJO: estos discos deben estar al final del resto.
* Reiniciar la MV
* Usar `lsblk` para asegurarnos que los discos nuevos son `/dev/sdd` y `/dev/sde`.
* En caso contrario apagar la MV y reordenar los discos como se pide en la práctica.

## 2.2 Crear y montar RAID-1

> Enlace de interés:
> * [URL wikipedia sobre mdadm](https://en.wikipedia.org/wiki/Mdadm):

Vamos a crear RAID-1 con los discos `sdd` y `sde`:
* Ir a la MV.
* Crear directorio `/mnt/folderXXr1`. Este es el directorio que vamos a usar para montar el dispositivo nuevo (RAID1).
* Ir a `Yast -> Particionador -> RAID`, y elegimos:
    * Elegir tipo `raid1`
    * Elegir los discos `sdd` y `sde`.
    * Le pondremos el nombre `deviceXXr1`.
* Aceptar
* Crear una partición en el nuevo dispositivo `deviceXXr1`:
    * Formato `ext3`.
    * Tamaño: `Disco completo`.
    * Montar en `/mnt/folderXXr1`

## 2.3 Comprobar RAID-1 (configuración y montaje)

* Para comprobar si se ha creado el dispositivo RAID1 correctamente:

```
cat /proc/mdstat                  # Muestra info de discos RAID
lsblk                             # Muestra info de los discos/particiones
mdadm --detail /dev/md/deviceXXr1 # Muestra info del disposivo RAID1
```
En el fichero `/etc/mdadm.conf`, se guardan todas las configuraciones relacionadas con los dispositivos RAID.

> Si estos comandos no devuelven la información esperada, entonces solucionar el problema y volver a comprobar la salida de los comandos.

* `df -hT | grep XX`, comprobar si el dispositivo está correctamente montado.
* `cat /etc/fstab`, comando para consultar el fichero de configuración de los montajes automáticos. Esto es para que se monte el dispositivo automáticamente en cada reinicio de la máquina.
* Reiniciar equipo.
* Comprobar que el dispositivo RAID1 está montado en `/mnt/folderXXr1`.

## 2.4 Escribir datos en el RAID-1

Crea lo siguiente:
* Directorio `/mnt/folderXXr1/naboo`
* Fichero `/mnt/folderXXr1/naboo/yoda.txt`
* Directorio `/mnt/folderXXr1/endor`
* Fichero `/mnt/folderXXr1/endor/sandtrooper.txt`

Reiniciar la MV y comprobar que se mantienen los datos:

```
df -hT |grep XX
tree /mnt/folderXXr1
```

---
# 3. Quitar disco y probar

Como tenemos un dispositivo RAID1, entonces podemos quitar uno de los discos y comprobar que el sistema sigue funcionando.

## 3.1 Quitar un disco

* Apagamos la MV.
* Quitar en VirtualBox uno el disco `/dev/sde` de la MV.
* Reiniciamos la MV y comprobamos que la información no se ha perdido, aunque el disco no esté.

```
lsblk                 # Muestra info de los discos/particiones
tree /mnt/folderXXr1  # Muestra el contenido de la carpeta
```

## 3.2 Poner el disco

* Apagar la MV Vbox.
* Volver a poner el disco en la MV
* Reiniciar la MV

Vamos a sincronizar los discos y comprobar que todo está correcto.
* `mdadm --detail /dev/md/deviceXXr1`, comprobamos que los dos discos del RAID1 están bien configurados.

> **Si tenemos algún problema:**
> * `mdadm /dev/md/deviceXXr1 --manage --add /dev/sdX`, añadimos el disco que falta (sdd o sde, depende de cada caso).
> * `mdadm --detail /dev/md/deviceXXr1`, comprobamos que están los dos.
>
> Enlace de interés: [Arreglar dispositivos RAID1](http://www.seavtec.com/en/content/soporte/documentacion/mdadm-raid-por-software-ensamblar-un-raid-no-activo).

Una vez realizado lo anterior, ejecutar los siguientes comandos, y comprobar su salida:

```
date
fdisk -l
cat /proc/mdstat
lsblk
```
