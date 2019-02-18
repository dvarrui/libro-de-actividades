
> Otros temas relacionados
> * No vamos a configurar VirtualBox
> * Configurar Acceso remoto
>     * Activar el servidor SSH.
>     * Comprobar que funciona correctamente el acceso remoto SSH con nuestro usuario.
> * NO tiene configurado el cortafuegos por defecto.

---

# Configurar Raspberry PI

# Configurar Nombre EQUIPO, DOMINIO y USUARIO

* Nombre de equipo: `primer-apellido-del-alumnoXXr1`.
    * Por ejemplo vargas42r1
    * Si tenemos varias máquinas las llamaremos vargasXXr1, vargasXXr2, etc.
* Nombre de dominio: `curso1819` (Modificar los números al curso actual).
* Un usuario identificado con `nombre-del-alumno`.

> **ATENCIÓN**
>
> * Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
> * Fichero `/etc/hostname`
>     * Ponemos el `nombre-maquina.nombre-dominio`
>     * Por ejemplo: `vargasXXr1.curso1819`
> * Fichero `/etc/hosts`.
>     * Asegurarse de que hay una línea con `ip nombre-de-host`
>     * Por ejemplo: `127.0.0.2   vargasXXr1.curso1819   vargasXXr1`

*Comprobar nombre de equipo y usuario:*
```
date
uname -a
hostname -f           # Muestra nombre-máquina.nombre-dominio
hostname -a           # Muestra nombre-máquina
hostname -d           # Muestra nombre-dominio

id nombre-de-usuario  # Comprobar que existe el usuario
```

---

# Configuración de red

> * Donde aparezca AA debemos poner el código asignado al aula:
>     * 18 para el aula108
>     * 19 para el aula109
> * Donde aparezca XX debemos poner el código asignado al alumno.

* IP: `172.AA.XX.51` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.AA.XX.52, 172.AA.XX.53, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.AA.0.1`
    * Servidor DNS: `8.8.4.4`

---

# Proceso para configurar la red.

* Podemos configurar la red:
    * Por entorno gráfico usando la aplicación NetworkManager.
    * Por comandos debemos modificar el contenido de los ficheros de configuración de red.
* Primero debemos averiguar el nombre de nuestra interfaces.
    * Usaremos `ip a` o `ifconfig`.
* Para cambiar la configuración de red,modificar el fichero `/etc/network/interfaces` (Si está basado en Debian)
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

> NOTA: Si NO tenemos instalado el paquete `resolvconf`, para configurar la resolución de nombres (Servidor DNS) debemos modificar el fichero `/etc/resolv.conf` y añadir `nameserver 8.8.4.4`.

* Para que se apliquen los cambios anterior haremos alguna de las siguientes acciones:
   * Reiniciar el equipo o
   * `systemctl restart networking` o
   * `service networking restart` o

*Comprobar la red:*
```
ip a              # Muestra configuración de red
ip route          # Muestra la tabla de enrutamiento. Antes se usaba "route -n"
ping 8.8.4.4      # Comprueba la conexión con una máquina de Internet
host www.nba.com  # Comprueba que funciona bien el DNS
```

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
