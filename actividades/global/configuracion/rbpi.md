
# Configurar Raspberry PI

1. Usuario
2. Nombre del equipo
3. Interfaz de red

---
# 1. Configurar USUARIO

* Crear un usuario identificado con `nombre-del-alumno` (en minúsculas).
* `id nombre-del-alumno`, comprobamos.

---
# 2. Configurar Nombre EQUIPO, DOMINIO

Usaremos los siguientes valores:
* Nombre de equipo: `primer-apellido-del-alumnoXXrb`.
    * Por ejemplo vargas42rb
    * Si tenemos varias máquinas las llamaremos vargasXXrb, vargasXXrb2, etc.
* Nombre de dominio: `curso1920` (Modificar los números al curso actual).

> **ATENCIÓN**: Los nombres de máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.

Modificar los siguientes archivos de configuración:
* Fichero `/etc/hostname`
     * Ponemos el `nombre-maquina.nombre-dominio`
     * Por ejemplo: `vargasXXrb.curso1920`
* Fichero `/etc/hosts`.
     * Asegurarse de que hay una línea con `ip equipo.dominio  equipo`
     * Por ejemplo: `127.0.0.2   vargasXXrb.curso1920   vargasXXrb`
* Comprobamos:
    * `hostname -f`, muestra nombre-máquina.nombre-dominio
    * `hostname -a`, muestra nombre-máquina
    * `hostname -d`, muestra nombre-dominio

---
# 3. Configuración de red

Recordar:
* Donde aparezca AA debemos poner el código asignado al aula:
   * 18 para el aula108
   * 19 para el aula109
* Donde aparezca XX debemos poner el código asignado al alumno.

Usaremos los siguientes valores:
* IP: `172.AA.XX.51` (Donde XX corresponde al nº de cada puesto). Si tenemos varias máquinas usaremos las IP 172.AA.XX.52, 172.AA.XX.53, etc.
* Máscara de red: `255.255.0.0`
* Gateway: `172.AA.0.1`
* Servidor DNS: `8.8.4.4`

## 3.1 Proceso para configurar la red.

Dependiendo del SO que hayamos instalado en la máquina tendremos que usar un método u otro. Podemos configurar la red:
1. Por entorno gráfico usando la aplicación NetworkManager o Yast.
2. Por comandos debemos modificar el contenido de los ficheros de configuración de red.

**Cambiar la IP/máscara por ficheros Raspbian**

* Primero debemos averiguar el nombre de nuestra interface de red (Usaremos `ip a`)
* Para cambiar la configuración de red, editar y modificar el fichero `/etc/dhcp.conf`.

**Cambiar la IP/máscara por ficheros Debian**

* Primero debemos averiguar el nombre de nuestra interface de red (Usaremos `ip a` o `ifconfig`)
* Para cambiar la configuración de red:
    1. Editar y modificar el fichero `/etc/network/interfaces` .
    2. Crear y modificar el fichero `/etc/network/interfaces.d/red-de-clase` .

Veamos ejemplo de configuración del interfaz de red eth0 estático:

```
auto lo
iface lo inet loopback

auto eth0
iface eth0 inet static
  address 172.AA.XX.51
  netmask 255.255.0.0
  gateway 172.AA.0.1
  dns-nameserver 8.8.4.4
```

Veamos ejemplo de configuración del interfaz de red eth1 dinámico:

```
auto lo
iface lo inet loopback

auto eth1
iface eth1 inet dhcp
```

**Configurar Servidor DNS por ficheros**: Si NO tenemos instalado el paquete `resolvconf`, para configurar la resolución de nombres (Servidor DNS) debemos modificar directamente el fichero `/etc/resolv.conf` y añadir `nameserver 8.8.4.4`.

**Aplicar los cambios**: Para que se apliquen los cambios anterior haremos alguna de las siguientes acciones dependiendo del SO que tengamos instalado:
1. Reiniciar el equipo o
2. `systemctl restart networking` o
3. `service networking restart` o

**Comprobar la red:**
* `ip a`, muestra configuración de red
* `ip route`, muestra Gateway y la tabla de enrutamiento. Antes se usaba "route -n".
* `ping 8.8.4.4`, comprueba la conexión con una máquina de Internet. Esto es, si funciona la confiuración de la puerta de enlace.
* `host www.nba.com`, comprueba que funciona bien el DNS.

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
