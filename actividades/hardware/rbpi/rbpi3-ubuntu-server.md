
# Raspberry PI 3 con Ubuntu Server

Vamos a instalar el SO Ubuntu Classis Server 16.04 en una Raspberry Pi 3(216MB).

---

# 1. Instalar el SO

## 1.1 Descargar el SO

* Primero consultamos la página para [descargar el SO](https://ubuntu-pi-flavour-maker.org/download).
* Vemos a hay muchas versiones para la RbPi3. Nosotros vamos a elegir la versión `Ubuntu Classic Server 16.06 para RbPi3 (216MB)`.
* `ubuntu-16.04-preinstalled-server-armhf+raspi3.img.xz`, éste es el fichero que me he descargado. Es un fichero comprimido.

## 1.2 Proceso de instalación o Making a microSDHC

La imagen la podemos:
* grabar en un DVD
* montar como un fichero ISO o
* escribir directamente en un USB usando dd

Pero nosotros vamos a usar `ddrescue`.
* `sudo zypper in gnu_ddrescue` para instalar `ddrescue` en OpenSUSE.
* `unxz ubuntu-mate-16.04-desktop-armhf-raspberry-pi.img.xz`, para descomprimir el fichero con formato xz.
* Usar `lsblk`para comprobar cuál es el dispositivo (/dev/sdx) de la RbPi.

> **Particiones:**
>
> Identifico que el SD de la RpPi es /dev/sdc y que tiene 5 particiones. Espero que el disco sdc de "reparticione" (No se si existe esta palabra en español) completamente al grabar la imagen que nos hemos descargado.
>
> Por ese motivo considero innecesario configurar las particiones ahora.

* `sudo ddrescue -d -D --force FILENAME.img /dev/sdc`, para grabar la imagen en el dispositivo de la RbPi.

Veamos ejemplo:

```
david@camaleon:~/Descargas/rbpi> sudo ddrescue -d -D --force ubuntu-16.04-preinstalled-server-armhf+raspi3.img /dev/sdc
GNU ddrescue 1.23
Press Ctrl-C to interrupt
     ipos:    2361 MB, non-trimmed:        0 B,  current rate:   1900 kB/s
     opos:    2361 MB, non-scraped:        0 B,  average rate:   7496 kB/s
non-tried:        0 B,  bad-sector:        0 B,    error rate:       0 B/s
  rescued:    2361 MB,   bad areas:        0,        run time:      5m 14s
pct rescued:  100.00%, read errors:        0,  remaining time:         n/a
                              time since last successful read:         n/a
Finished                              
```

Se crearon dos particiones:
```
david@camaleon:~/Descargas/rbpi> lsblk
NAME   MAJ:MIN RM   SIZE RO TYPE MOUNTPOINT
...
sdc      8:32   1   7,3G  0 disk
├─sdc1   8:33   1   128M  0 part
└─sdc2   8:34   1   2,1G  0 part
david@camaleon:~/Descargas/rbpi>

```

Una partición
* sdc1 contiene el arranque/boot
* sdc2 contiene el SO

```
david@camaleon:~/Descargas/rbpi> df -hT
S.ficheros     Tipo     Tamaño Usados  Disp Uso% Montado en
...
/dev/sdc2      ext4       2,0G   1,1G  906M  55% /run/media/david/cloudimg-rootfs
/dev/sdc1      vfat       128M    31M   97M  25% /run/media/david/system-boot
```
---

# 2. Configuración

## 2.1 Acceder al SO

Para entrar en la Raspberry PI3, la conectamos a un monitor HDMI, teclado, red RJ45 y corriente. Esperamos y vemos la ventana de login.

¿Cuál es el usuario/clave para entrar? A ver... no vas a ser el primero con la misma duda ¿no? Pues consulta las [FAQ](http://ubuntu-pi-flavour-maker.org/faq/)

Ahí vemos:

```
What are the default usernames and passwords?

The Ubuntu Core images will setup SSH key authentication using the keys linked with your Ubuntu SSO account account.

The Ubuntu Classic Server (for the Raspberry Pi 2 and 3) images have a default user provisioned. The credentials are:
        Username: ubuntu
        Password: ubuntu
        You will be asked to change the password on first login.

The Lubuntu, Ubuntu MATE and Xubuntu images use a variant of oem-config and you will be prompted to create your own user account during the first boot.
```
¿Todo bien?

# 2.2 Configurar el SO

Configurar usuarios:
* `sudo bash`, para entrar como root.
* `passwd`, para poner la clave de root.
* `useradd -m USERNAME`, crear nuestro usuario.
* `passwd USERNAME`, poner la clave del usuario.

Configurar la máquina con lo siguiente:
* `/etc/hostname`, nombre equipo.
* `/etc/hosts`, nombre de equipo.
* `hostname -f`, comprobar nombres.

Servicio SSH:
* `systemctl status sshd`, comprobar que el servicio está funcionando.
* `ip a`, consulto la IP.
* Comprobar SSH:
    * `ssh ubuntu@localhost`, comprobar desde dentro.
    * `ssh ubuntu@172.18.99.252`, comprobar desde fuera.

Configurar la red:
* Ponemos la configuración solicitada en el fichero `/etc/network/interfaces.d/16-sergio.cfg`.
* Reboot y comprobamos con `ip a`
