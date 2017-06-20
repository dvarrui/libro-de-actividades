

# Servidor de instalaciones PXE con OpenSUSE

Hemos utilizado los enlaces que se mencionan más abajo para confeccionar esta práctica.

Enlaces de interés:
* [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)
* [Instalación OpenSUSE mediante TFTP y PXE](https://miguelcarmona.com/articulos/instalacion-de-opensuse-por-red-mediante-tftp-pxe)
* Vídeo [LINUX: PXE Installation Server](https://youtu.be/59TwMw_CJwQ)
* Vídeo [LINUX: Installing from the network](https://www.youtube.com/watch?v=mPARmfWizBI)

# 1. Introducción

`Texto extraído del enlace [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)`

Vamos a ver cómo openSUSE para montar un servidor PXE en nuestra red local. 
Un servidor PXE nos permite iniciar la instalación de un sistema operativo a través
de la red sin necesidad de grabar un disco CD/DVD o utilizar un pendrive.

También nos servirá para arrancar un sistema Live u otras herramientas de diagnosis, antivirus, etc., en nuestras máquinas sin sistema operativo instalado o averiadas. Supondremos que ya existe un servidor DHCP funcionando en la red y no queremos interactuar con él ni tocar su configuración (como puede ser el caso de un router doméstico). Haremos todo esto profundizando un poco en la sintaxis de SYSLINUX así como en las distintas opciones que podemos ir encontrando, ya que la documentación al respecto en castellano es algo escasa y se encuentra bastante más dispersa que en inglés.
Probado en openSUSE 	Artículos recomendados 	Artículos relacionados
Icon-checked.png 	

   12.3
   13.1

 Icon-manual.png 		Icon-ayuda.png 	

Prácticamente todos los ordenadores hoy en día soportan arranque mediante PXE. Revisa la EFI/BIOS de tu equipo y actívalo.
---

# 1. Preparativos

Usaremos 2 MV:
* MV1
    * SO OpenSUSE ([Configuración](../../../global/configuracion/opensuse.md)).
    * Nombre de host: `pxe-serverXX`.
    * Dos interfaces de red:
        * Una interfaz externa con IP estática(172.19.XX.31/16).
        * y otra interfaz en red interna (`netint`) con IP estática (192.168.XX.31/24).
* MV2
    * Sin sistema operativo instalado.
    * Una interfaz de red dentro de la red interna.
    * Configurar VirtualBox -> Sistema -> Arranque -> Red/LAN/PXE.

---

## 2. DHCP

zypper in atftp dhcp-server nfs-kernel-server syslinux

## 3. TFTP

## 4. syslinux

## 5. nfs

## 6. Comprobamos
