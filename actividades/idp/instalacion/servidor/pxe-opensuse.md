

# Servidor de instalaciones PXE con OpenSUSE

Enlaces de interés:

* [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)
* [Instalación OPenSUSE mediante TFTP y PXE](https://miguelcarmona.com/articulos/instalacion-de-opensuse-por-red-mediante-tftp-pxe)
* Vídeo [LINUX: PXE Installation Server](https://youtu.be/59TwMw_CJwQ)
* Vídeo [LINUX: Installing from the network](https://www.youtube.com/watch?v=mPARmfWizBI)

---

# 1. Preparativos

Usaremos 2 MV:
* MV1
    * con SO OpenSUSE
    * Dos interfaces de red: una externa (172.19.XX.31/16) y otra interna (192.168.XX.31/24)
* MV2
    * Sin sistema operativo instalado.
    * Una interfaz de red dentro de la red interna.

---

## 2. DHCP

zypper in atftp dhcp-server nfs-kernel-server syslinux

## 3. TFTP

## 4. syslinux

## 5. nfs

## 6. Comprobamos
