# Cómo instalar Mac OS X en VirtualBox

A continuación se explica el proceso para instalar Mac OS X en una máquina virtual con VirtualBox, teniendo Windows o GNU/Linux como sistema anfitrión.

## Requisitos

### VirtualBox

Ir a [https://www.virtualbox.org/wiki/Downloads](https://www.virtualbox.org/wiki/Downloads), y descargar e instalar la última versión de VirtualBox.

### Mac OS X ISO

Descargar el ZIP del siguiente [enlace](https://drive.google.com/file/d/0Bx7BAMlD-ZOQTEpoVTF0Q0NpOVU/view) y extraer la ISO.

> La ISO corresponde a Mac OS X Sierra (10.12).

## Crear la máquina virtual en VirtualBox

Creamos la máquina con la siguiente configuración:

* Nombre: **Mac OS X 10.12**
* Tipo: **Mac OS X**
* Versión: **macOS 10.12 Sierra (64-bit)**
* Memoria RAM: **4GB (4096MB)** o más (dependiendo de la capacidad en el anfitrión).
* Disco duro: **tamaño por defecto o más** (dependiendo de la capacidad en el anfitrión).

Abrimos la "Configuración" de la máquina y hacemos los siguientes cambios:

#### Sistema > Placa base

* En la lista "Orden de arranque" deshabilitar "Disquete".

#### Sistema > Procesador

* Incrementar el número de "Procesadores" al máximo permitido (color verde).

#### Pantalla > Pantalla

* Aumentar la "Memoria de vídeo" hasta 128MB.

#### Almacenamiento

* En "Dispositivos de almacenamiento" seleccionar la "Controladora SATA" y establecer "Usar caché de I/O anfitrión".

#### Audio

* Desmarcar "Habilitar audio".

Al terminar pulsamos "Aceptar" para guardar los cambios hechos en la configuración de la máquina.

En Windows creamos el siguiente script, lo guardamos con extensión ".BAT" y lo ejecutamos.

```bash
@echo off
set NAME="Mac OS X 10.12"
%SystemDrive%
cd %ProgramFiles%\Oracle\VirtualBox
VBoxManage modifyvm %NAME% --cpuidset 00000001 000106e5 00100800 0098e3fd bfebfbff
VBoxManage setextradata %NAME% "VBoxInternal/Devices/efi/0/Config/DmiSystemProduct" "iMac11,3"
VBoxManage setextradata %NAME% "VBoxInternal/Devices/efi/0/Config/DmiSystemVersion" "1.0"
VBoxManage setextradata %NAME% "VBoxInternal/Devices/efi/0/Config/DmiBoardProduct" "Iloveapple"
VBoxManage setextradata %NAME% "VBoxInternal/Devices/smc/0/Config/DeviceKey" "ourhardworkbythesewordsguardedpleasedontsteal(c)AppleComputerInc"
VBoxManage setextradata %NAME% "VBoxInternal/Devices/smc/0/Config/GetKeyFromRealSMC" 1
VBoxManage setextradata %NAME% "VBoxInternal2/EfiGopMode" 4
```

En GNU/Linux creamos el siguiente script, lo guardamos con extensión ".sh", le asignamos permisos de ejecución y lo ejecutamos.

```bash
#!/bin/bash
NAME="Mac OS X 10.12"
VBoxManage modifyvm $NAME --cpuidset 00000001 000106e5 00100800 0098e3fd bfebfbff
VBoxManage setextradata $NAME "VBoxInternal/Devices/efi/0/Config/DmiSystemProduct" "iMac11,3"
VBoxManage setextradata $NAME "VBoxInternal/Devices/efi/0/Config/DmiSystemVersion" "1.0"
VBoxManage setextradata $NAME "VBoxInternal/Devices/efi/0/Config/DmiBoardProduct" "Iloveapple"
VBoxManage setextradata $NAME "VBoxInternal/Devices/smc/0/Config/DeviceKey" "ourhardworkbythesewordsguardedpleasedontsteal(c)AppleComputerInc"
VBoxManage setextradata $NAME "VBoxInternal/Devices/smc/0/Config/GetKeyFromRealSMC" 1
VBoxManage setextradata $NAME "VBoxInternal2/EfiGopMode" 4
```

> A la variable `NAME` debemos asignarle el nombre de la máquina virtual, en nuestro caso `"Mac OS X 10.12"`.

El siguiente comando permite establecer una resolución de pantalla distinta a la que viene por defecto (1024x768); por ejemplo, para establecer 1920x1080:

```
VBoxManage setextradata "Mac OS X 10.12" "CustomVideoMode1" 1920x1080x32
VBoxManage setextradata "Mac OS X 10.12" "VBoxInternal2/EfiGraphicsResolution" 1920x1080
```

## Instalación de Mac OS X

Una vez configurada la máquina, colocamos la ISO en la unida de CD de la máquina virtual, la iniciamos y comenzamos el proceso de instalación de Mac OS X.

> Es necesario preparar el disco de destino de la instalación con la **Utilidad de discos** antes de comrnzar la instalación.
