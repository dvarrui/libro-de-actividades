
#Configurar MV GNU/Linux Debian 8

> * Donde aparezca AA debemos poner el código asignado al aula:
>     * 18 para el aula108
>     * 19 para el aual109
> * Donde aparezca XX debemos poner el código asignado al alumno.

* IP: `172.AA.XX.41` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.AA.XX.42, 172.AA.XX.43, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.AA.0.1`
    * Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumnoXXd`.
    * Por ejemplo vargas30d
    * Si tenemos varias máquinas las llamaremos vargas30d, vargas30e, vargas30f, etc.
* Nombre de dominio: `curso1516` (Modificar los números al curso actual).

* Tarjeta de red VBox en `modo puente`.
* Configurar [acceso remoto](../acceso-remoto.md).
* Configurar [firewall](../firewall.md).

* Usuarios:
    * Un usuario identificado con `nombre-del-alumno`.
    * Poner al usuario `root` la clave del alumno con la letra en minúscula.

> **ATENCIÓN**
>
> * Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
> * Fichero `/etc/hostname`
>     * Ponemos el `nombre-maquina.nombre-dominio`
>     * Por ejemplo: `vargas116g.curso1617`
> * Fichero `/etc/hosts`.
>     * Asegurarse de que hay una línea con `ip nombre-de-host`
>     * Por ejemplo: `127.0.0.2   vargas116g.curso1617   vargas116g`

## Comprobaciones finales

Capturar imágen de la configuración del equipo:
```
date
uname -a
hostname -f           #Muestra nombre-maquina.nombre-dominio
hostname -a           #Muestra nombre-maquina
hostname -d           #Muestra nombre-dominio

tail -n 5 /etc/passwd #Comprobar que existe el usuario
id nombre-de-usuario  #Comprobar que existe el usuario
ip a
route -n
ping 8.8.4.4
host www.iespuertodelacruz.es
blkid
```

## Entorno gráfico

En Debian/Ubuntu, para configurar la red mediante entorno gráfico podemos usar
NetworkManager.

## Ficheros de configuración

* En máquinas Debian/Ubuntu podemos cambiar la configuración de red,
modificando el fichero `/etc/network/interfaces`.
* Para averiguar los nombres de nuestras interfaces usamos `ip a` o `ifconfig`.
* Veamos un ejemplo, donde se configura el interfaz eth0 estático y el eth1 dinámico:
```
auto lo
iface lo inet loopback

auto eth0
iface eth0 inet static
  address 172.19.42.41
  netmask 255.255.0.0
  gateway 172.19.0.1
  dns-nameservers 8.8.4.4
  dns-search vargas42g.curso1617 vargas42g
  dns-domain vargas42g.curso1617

auto eth1
iface eth1 inet dhcp
```
* Para que se tengan en cuenta los cambios podemos:
   * `service networking restart` o
   * Reiniciar el equipo.

> Si tuviéramos problemas con resolvconf podemos reconfigurarlo con:
> * `sudo rm /etc/resolv.conf`
> * `sudo dpkg-reconfigure resolvconf`
