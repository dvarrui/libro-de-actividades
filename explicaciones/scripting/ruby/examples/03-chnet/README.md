
# Script: cambiar la configuración de red

Vamos a hacer un script Ruby para cambiar la configuración de red de una máquina con GNU/Linux.

---
# 1. Usando comandos de red

## 1.1 Menú interactivo

Al iniciar el script se nos mostrará este menú para poder elegir la acción a realizar:

```
=================================
CHange NETwork configuration (v1)
=================================
 r. Reset
 1. Classroom 109
 2. My home

 Select option [Enter=exit]:
```

## 1.2 Comandos de red

> Enlaces de interés:
> * [Learn about IP configuration](URL: https://www.tecmint.com/ip-command-examples/)

| Acción | Descripción | Comandos |
| ------ | ----------- | -------- |
| Reset  | Resetar la configuración de red | ifdown eth0; ifup eth0 |
| Classroom109 | Poner configuración de red para el aula 109 | ip addr add 172.19.XX.33/16 dev eth0 |
| MyHome | Poner configuración de red para casa | ip addr add 192.168.1.1XX/24 dev eth0 |
| Refresh | Refrescar la IP dinámica | dhclient -r eth0 |

---
# 2. Sustituyendo los ficheros de configuración

## 2.1 Teoría: ficheros de configuración

En GNU/Linux OpenSUSE, se usa el fichero `/etc/sysconfig/network-scripts/ifcfg-eth0`, para guardar la configuración del interfaz eth0 con el siguiente formato:

```
DEVICE="eth0"
BOOTPROTO=static
ONBOOT=yes
TYPE="Ethernet"
IPADDR=192.168.50.2
NAME="System eth0"
HWADDR=00:0C:29:28:FD:4C
GATEWAY=192.168.50.1
```

> For Ubuntu/Debian/Linux Mint
>
> Edit configuration file /etc/network/interfaces
>
> auto eth0
> iface eth0 inet static
> address 192.168.50.2
> netmask 255.255.255.0
> gateway 192.168.50.1

## 2.2 Menú interactivo

Al iniciar el script se nos mostrará este menú para poder elegir la acción a realizar:

```
=================================
CHange NETwork configuration (v2)
=================================
 r. Reset
 1. nc-classroom109
 2. nc-myhome

 Select option [Enter=exit]:
```

* r: Resetea la configuración de red.
* 1: Sustituye el contenido del fichero de configuración de red de eth0 por el contenido del fichero `eth0-classroom109`.
* 2: Sustituye el contenido del fichero de configuración de red de eth0 por el contenido del fichero `eth0-myhome`.

> Los más sencillo para averiguar el SO instalado es usar la gema `os`.
> Más información en rubygems.org.

---

# ANEXO

## Averiguar el SO

Podemos usar la salida de los comandos `which zypper`, `which apt` para averiguar el SO en el que nos encontramos. Por ejemplo:

```
> which zypper
/usr/bin/zypper

> echo $?
0

> which packman
packman not found

> echo $?
1
>
```

Si el SO tiene instalado:
* `zypper` entonces es OpenSUSE.
* `apt` entonces es Debian/Ubuntu.
* Si no, es otro diferente.
