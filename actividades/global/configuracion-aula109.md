
*(Este documento es un borrador. NO está acabado, ni revisado)*

*Marzo 2016*

#Configuraciones de las máquinas virtuales

##1. Configuración Windows 7 Professional

Configuración de la máquina Windows 7 Professional:
* IP: `172.19.XX.11` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.19.XX.32, 172.19.XX.33,etc.
* Máscara de red: `255.255.0.0`
* Gateway: `172.19.0.1`
* Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumno+XXw`.
    * Por ejemplo: vargas30w
    * El nombre NetBIOS sólo puede tener 16 caractéres.
    * Si tenemos varias máquinas las llamaremos vargas30w, vargas30x, vargas30y, etc.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
* Grupo de trabajo: `curso1516`
* Tarjeta de red VBox en modo puente.

Capturar imágenes de las configuraciones.
```
date
ipconfig
route /PRINT
nslookup www.iespuertodelacruz.es
ping 8.8.4.4
``` 

> **Periodo de pruebas**
>
> Una vez instalado el SO Windows 7 disponemos de unos 30 días trabajar con el sistema,
antes de que pase al estado *"Copia ilegal"*.
> Al finalizar este plazo de tiempo podemos:
> 1. Activar el SO introduciendo un código de activación válido.
> 2. Renovar el perido de pruebas por 30 días más, mediante el comando: `slmgr -rearm`. 
Podemos renovar varias veces, pero el tiempo máximo que podemos usar el SO antes de activarlo
es de 90 días.
>

##2. Configuración Windows 2008 Server

Configuración de la máquina Windows 2008 Server Professional:
* IP: `172.19.XX.21` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.19.XX.22, 172.19.XX.23,etc.
* Máscara de red: `255.255.0.0`
* Gateway: `172.19.0.1`
* Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumno+XXs`.
    * Por ejemplo: vargas30s.
    * El nombre NetBIOS sólo puede tener 16 caractéres.
    * Si tenemos varias máquinas las llamaremos vargas30s, vargas30t, vargas30u, etc.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
* Grupo de trabajo: `curso1516`
* Tarjeta de red VBox en `modo puente`.

Capturar imágenes de las configuraciones.

``` 
date
ipconfig
route /PRINT
nslookup www.iespuertodelacruz.es
ping 8.8.4.4
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

* IP: `172.19.XX.31` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.19.XX.32, 172.19.XX.33,etc.
* Máscara de red: `255.255.0.0`
* Gateway: `172.19.0.1`
* Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumnoXXg`.
    * Por ejemplo vargas30g
    * Si tenemos varias máquinas las llamaremos vargas30g, vargas30h, vargas30i, etc.
* Nombre de dominio: `curso1516`.
* Tarjeta de red VBox en `modo puente`.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.

Además también:
* Instalar openssh-server para que el profesor pueda acceder de forma remota.
* Asegurarse de que el nombre de host está correctamente en el fichero /etc/hosts. 
Para que el comando hostname funcione bien.

Capturar imágen de la configuración del equipo:
```
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

##4. Configuración GNU/Linux Debian 8

* IP: `172.19.XX.41` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.19.XX.42, 172.19.XX.43,etc.
* Máscara de red: `255.255.0.0`
* Gateway: `172.19.0.1`
* Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumnoXXg`.
    * Por ejemplo vargas30g
    * Si tenemos varias máquinas las llamaremos vargas30g, vargas30h, vargas30i, etc.
* Nombre de dominio: `curso1516`.
* Tarjeta de red VBox en `modo puente`.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.

Además también:
* Instalar openssh-server para que el profesor pueda acceder de forma remota.
* Asegurarse de que el nombre de host está correctamente en el fichero /etc/hosts. 
Para que el comando hostname funcione bien.

Capturar imágen de la configuración del equipo:
```
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
