
Documentos relacionados
* Configurar [VirtualBox](../virtualbox/debian.md)
* Configurar [Acceso remoto](../acceso-remoto/debian.md)

# Configurar MV GNU/Linux Debian

# Configurar Nombre EQUIPO, DOMINIO y USUARIO

* Nombre de equipo: `primer-apellido-del-alumnoXXd`.
    * Por ejemplo vargasXXd
    * Si tenemos varias máquinas las llamaremos vargasXXd, vargasXXe, vargasXXf, etc.
* Nombre de dominio: `curso1819` (Modificar los números al curso actual).
* Un usuario identificado con `nombre-del-alumno`.

> **ATENCIÓN**
>
> * Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
> * Fichero `/etc/hostname`
>     * Ponemos el `nombre-maquina.nombre-dominio`
>     * Por ejemplo: `vargasXXd.curso1617`
> * Fichero `/etc/hosts`.
>     * Asegurarse de que hay una línea con `ip nombre-de-host`
>     * Por ejemplo: `127.0.0.2   vargasXXd.curso1617   vargasXXd`

---

# Configuración de red

> * Donde aparezca AA debemos poner el código asignado al aula:
>     * 18 para el aula108
>     * 19 para el aula109
> * Donde aparezca XX debemos poner el código asignado al alumno.

* Tarjeta de red VBox en `modo puente`.
* IP: `172.AA.XX.41` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.AA.XX.42, 172.AA.XX.43, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.AA.0.1`
    * Servidor DNS: `8.8.4.4`

---

# Comprobaciones finales

Capturar imágen de la configuración del equipo:
```
date
uname -a
hostname -f           #Muestra nombre-máquina.nombre-dominio
hostname -a           #Muestra nombre-máquina
hostname -d           #Muestra nombre-dominio

tail -n 5 /etc/passwd #Comprobar que existe el usuario
id nombre-de-usuario  #Comprobar que existe el usuario
ip a
route -n
ping 8.8.4.4
host www.iespuertodelacruz.es
blkid
```

---

# Proceso para configurar la red.

Podemos configurar la red por entorno gráfico usando la aplicación NetworkManager.
Por comandos debemos modificar el contenido de los ficheros de configuración de red.

> Enlace de interés:
>
> * [Configurar tarjeta de red con IP estática en Debian sin interfaz gráfica](http://www.driverlandia.com/configurar-tarjeta-de-red-con-ip-estatica-en-debian-sin-interfaz-grafica/)

* Primero debemos averiguar el nombre de nuestra interfaces. 
    * Usaremos `ip a` o `ifconfig`.
* Para cambiar la configuración de red,modificar el fichero `/etc/network/interfaces`.
* Veamos un ejemplo, donde se configura el interfaz eth0 estático y el eth1 dinámico:

```
# Ejemplo configuración interfaz loopback
auto lo
iface lo inet loopback

# Ejemplo configuración interfaz enp0s3 en modo estático
auto enp0s3
iface enp0s3 inet static
  address 172.AA.XX.41
  netmask 255.255.0.0
  gateway 172.AA.0.1
  dns-nameserver 8.8.4.4

# Ejemplo configuración interfaz ethq en modo dinámico
auto eth1
iface eth1 inet dhcp
```

> NOTA: Si NO tenemos instalado el paquete `resolvconf`, para configurar la resolución de nombres 
(Servidor DNS) debemos modificar el fichero `/etc/resolv.conf` y añadir `nameserver 8.8.4.4`.

* Para que se apliquen los cambios hacemos lo siguiente:
   * Reiniciar el equipo o
   * `systemctl restart networking` o
   * `service networking restart` o

---

# ANEXO

## resolvconf

Si tuviéramos problemas con resolvconf podemos reconfigurarlo con:
* `sudo rm /etc/resolv.conf`
* `sudo dpkg-reconfigure resolvconf`

## Configurar temporalmente la red mediante comandos

También podemos usar comandos del sistema para definir una configuración de red temporal. No es fija porque al reiniciar el equipo no se mantiene.

* `ifconfig eth0 172.AA.XX.0 netmask 255.255.0.0`, para configurar la IP y la máscara de red.
* `route add default gw 172.AA.0.1`, para configurar la puerta de enlace.
* `echo nameserver 8.8.4.4 >> /etc/resolv.conf`, para configurar la IP del servidor DNS.

## Netplan vs /etc/network/interfaces
https://netplan.io/examples
