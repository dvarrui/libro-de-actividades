
```
* Creado en Marzo 2016 para el curso1516
* Configuración de las MV del aula 108
```

Otros documentos
* [Configurar acceso remoto](acceso-remoto.md)
* [Configurar VirtualBox](virtualbox.md)

#Configurar las máquinas virtuales

##1. Configuración Windows 7 Professional

Configuración de la máquina Windows 7 Professional:
* IP: `172.18.XX.11` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.18.XX.12, 172.18.XX.13, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.18.0.1`
    * Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumno+XXw`.
    * Por ejemplo: vargas30w
    * El nombre NetBIOS sólo puede tener 16 caractéres.
    * Si tenemos varias máquinas las llamaremos vargas30w, vargas30x, vargas30y, etc.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
* Grupo de trabajo: `curso1516`
* Tarjeta de red VBox en modo puente.
* Configurar [acceso remoto](./acceso-remoto.md).
* Configurar [firewall](./firewall.md).

**Comandos de comprobación**

```
date
ipconfig
route PRINT
nslookup www.iespuertodelacruz.es
ping 8.8.4.4
```

> **Periodo de pruebas**
>
> Una vez instalado el SO Windows 7 disponemos de unos 30 días trabajar con el sistema,
antes de que pase al estado *"Copia ilegal"*.
>
> Al finalizar este plazo de tiempo podemos:
>
> 1. Activar el SO introduciendo un código de activación válido.
> 2. Renovar el perido de pruebas por 30 días más, mediante el comando: `slmgr -rearm`.
Podemos renovar varias veces, pero el tiempo máximo que podemos usar el SO antes de activarlo
es de 90 días.
>


##2. Configuración Windows 2008 Server

Configuración de la máquina Windows 2008 Server Enterprise:
* IP: `172.18.XX.21` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.18.XX.22, 172.18.XX.23, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.18.0.1`
    * Servidor DNS1: `127.0.0.1`
    * Servidor DNS2: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumno+XXs`.
    * Por ejemplo: vargas30s.
    * El nombre NetBIOS sólo puede tener 16 caractéres.
    * Si tenemos varias máquinas las llamaremos vargas30s, vargas30t, vargas30u, etc.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
* Grupo de trabajo: `curso1516`

> En el caso de tener un PDC, pondremos como nombre de dominio `segundo-apellido-del-alumnoXXdom.curso1516`.

* Tarjeta de red VBox en `modo puente`.
* Instalar el servidor Telnet (consultar más arriba)

**Comandos de comprobación**

```  
date
ipconfig
ping 8.8.4.4
nslookup www.iespuertodelacruz.es
```   

> **Periodo de pruebas**
>
> Una vez instalado el SO Windows 2008 Server disponemos de unos 60 días trabajar con el sistema,
antes de que pase al estado *"Copia ilegal"*.
> Al finalizar este plazo de tiempo podemos:
> 1. Activar el SO introduciendo un código de activación válido.
> 2. Renovar el perido de pruebas por 60 días más, mediante el comando: `slmgr -rearm`.
Podemos renovar varias veces, pero el tiempo máximo que podemos usar el SO antes de activarlo
es de 180 días.
>

##3. Configuración GNU/Linux OpenSUSE 13.2

> Es aconsejable poner el tamaño del disco de OpenSUSE en 10GB

Al instalar openSUSE tener en cuenta la siguiente imagen:

![opensuse-instalacion-configuracion.png](./images/opensuse-instalacion-configuracion.png)

###3.1 Parámetros de configuración

* IP: `172.18.XX.31` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.18.XX.32, 172.18.XX.33, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.18.0.1`
    * Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumnoXXg`.
    * Por ejemplo vargas30g
    * Si tenemos varias máquinas las llamaremos vargas30g, vargas30h, vargas30i, etc.
* Nombre de dominio: `curso1516`.
* Tarjeta de red VBox en `modo puente`.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.

Además también:
* Asegurarse de que el nombre de host está correctamente en el fichero /etc/hosts.
Para que el comando hostname funcione bien.

###3.2 Proceso de configuración en Yast

Para configurar la red en OpenSUSE lo más cómodo es usar el interfaz gráfico `yast`.
* Vamos a `yast -> Ajustes de red`
* En la pestaña `Vista resumen` pondremos:
    * IP estática
    * Máscara de red
    * Nombre de host
    * Pulsamos en `siguiente`.
* En la pestaña `Nombres de hosts` pondremos:
    * Nombre de host
    * Nombre de dominio
    * Asignar nombre a bucle local. Esto modifica el fichero `/etc/hosts` por nosotros.
    * Servidor DNS
* En la pestaña `Encaminamiento` pondremos:
    * La IP de l apuerta de enlace
    * Elegimos el dispositivo de red asociado a la puerta de enlace.    

###3.3 Comprobaciones finales

**Comandos de comprobación**

```
id NOMBRE-USUARIO
date
uname -a
hostname -f
hostname -a
hostname -d
tail -n 5 /etc/passwd
ip a
route -n
host www.iespuertodelacruz.es
ping 8.8.4.4
blkid
```

##4. Configuración GNU/Linux Debian/Ubuntu

* IP: `172.18.XX.41` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.18.XX.42, 172.18.XX.43, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.18.0.1`
    * Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumnoXXg` (Fichero `/etc/hostname` y `/etc/hosts`).
    * Por ejemplo vargas30g
    * Si tenemos varias máquinas las llamaremos vargas30g, vargas30h, vargas30i, etc.
* Nombre de dominio: `curso1617` (Fichero `/etc/hostname` y `/etc/hosts`).
* Tarjeta de red VBox en `modo puente`.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.

Además también:
* Asegurarse de que el nombre de host está correctamente en el fichero /etc/hosts.
Para que el comando hostname funcione bien.

**Comandos de comprobación**

```
id NOMBRE-USUARIO
date
uname -a
hostname -f
hostname -a
hostname -d
tail -n 5 /etc/passwd
ip a
route -n
host www.iespuertodelacruz.es
ping 8.8.4.4
blkid
```

##4.1 Configuración de red para Debian/Ubuntu

En máquinas Debian/Ubuntu podemos cambiar la configuración de red,
modificando el fichero `/etc/network/interfaces`.

Veamos un ejemplo, donde se configura el interfaz eth0 estático y el eth1 dinámico:

```
auto lo
iface lo inet loopback

auto eth0
iface eth0 inet static
  address 172.16.108.240
  netmask 255.255.0.0
  gateway 172.16.1.1
  dns-nameservers 172.16.108.40 172.16.1.1
  dns-search vargas1w.idp vargas1w
  dns-domain vargas1w.idp

auto eth1
iface eth1 inet dhcp
```

Si tuviéramos problemas con resolvconf podemos reconfigurarlo con:

```
sudo rm /etc/resolv.conf
sudo dpkg-reconfigure resolvconf
```
