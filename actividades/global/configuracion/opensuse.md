
> Otros documentos relacionados:
>
> * Configurar [VirtualBox](../virtualbox/opensuse.md)
> * Configurar [Acceso remoto](../acceso-remoto/opensuse.md)
> * Configurar [Firewall](../firewall.md).
> * Cómo trabajar en [casa](../casa.md).

---
# Configurar MV GNU/Linux OpenSUSE

# Antes de empezar

Crear la máquina VirtualBox con:
* Crear un **disco VirtualBox de 20 GB**.
* Tarjeta de red VBox en `modo puente`.

> Para comprobar la ISO de instalación usamos el siguiente comando: `sha256sum -c opensuse-version.sha256`.

# Durante la instalación del SO

* Redordar: Hacer la primera partición FAT32 de 500 MB para Boot EFI.
* Crear usuario identificado como `nombre-del-alumno`.
* Seleccionar entorno gráfico ligero (XFCE o MATE preferiblemente).
    * Escritorio -> Genérico.
    * En la pantalla de comprobación final -> Software -> Añadir escritorio XFCE.
* Recordatorio.
    * Abrir puerto SSH.
    * Habilitar servicio SSH.
* Configurar lo siguiente durante la instalación:

> ![opensuse-instalacion-configuracion.png](./images/opensuse-instalacion-configuracion.png)

---
# Con el sistema operativo ya instalado

> * Donde aparezca AA debemos poner el código asignado al aula. Esto es:
>     * 18 para el aula103
>     * 19 para el aula109
> * Donde aparezca XX debemos poner el código asignado al alumno. Esto es su número de PC.

**Configurar la red y el nombre del equipo por YAST.** Una vez instalado el sistema operativo, podemos hacer cambios en la configuración de red usando la herramienta Yast. Vamos a `Inicio -> Configuración -> Yast -> Ajustes de red`.
* Seleccionamos el interfaz de red y editamos.
* Marcamos IP fija.
* IP: `172.AA.XX.31` (Donde XX corresponde al nº de cada puesto).
    * Máscara de red: `255.255.0.0`
* Siguiente

Vamos a `Nombre de Host/DNS` y hacemos lo siguiente:
* Desmarcamos `Modificar nombre mediante DHCP`
* Marcamos `Asignar nombre de host a la IP bucle local`
* Nombre de equipo: `primer-apellido-del-alumnoXXg`.
    * Por ejemplo vargas30g1
* Nombre de dominio: `curso1920` (Modificar los números al curso actual).
* Servidor DNS: `1.1.1.1`.
* Vamos a `Encaminamiento`, y ponemos Gateway o pasarela IPv4: `172.AA.0.1`. Esto es la puerta de enlace o encaminamiento.
* Ir dispositivo y elegir el interfaz de red.


> INFORMACIÓN sobre los **ficheros de configuración de red** gestionados por Wicked.
>
> El fichero de configuración de red es `/etc/sysconfig/network/ifcfg-eth0` para la tarjeta `eth0`.
> Ejemplo de configuración del modo automático o dinámico:
> ```
> BOOTPROTO='dhcp'
> STARTMODE='auto'
> DHCLIENT_SET_DEFAULT_ROUTE='yes'
> ```
>
> Ejemplo de configuración del modo manual o estático:
> ```
> BOOTPROTO='static'
> STARTMODE='auto'
> IPADDR='192.168.16.11/24'
> ```
>
> Fichero de configuración de la puerta de enlace `/etc/sysconfig/network/ifroute-eth0` para la tarjeta `eth0`. Ejemplo: `default 172.19.0.1 - eth0`
>
> Fichero de configuración del servidor DNS `/etc/sysconfig/network/config`. Parámetro
`NETCONFIG_DNS_STATIC_SERVERS="1.1.1.1"`

---
# Comprobaciones finales

**Comprobar la configuración de RED**. Cuando la red no vaya bien, debemos primero hacer estas comprobaciones para averiguar dónde tenemos el problema.

```
ip a                     # Información de red
ip route                 # Información de la puerta de enlace
ifstatus NOMBREINTERFAZ  # Configuración del interfaz de red
ping 1.1.1.1             # Verifica la conectividad con Internet
traceroute 1.1.1.1       # Comprobar enrutamiento   
host www.nba.com         # Comprueba DNS
```

> * Si tenemos varias máquinas usaremos las IP 172.AA.XX.32, 172.AA.XX.33, etc.
> * Si tenemos varias máquinas las llamaremos vargas30g2, vargas30g3, vargas30g4, etc.

**Usuario**. Comprobar que se ha creado el usuario correctamente con los siguientes comandos:
* `id nombre-alumno`, muestra información del usuario
* `su -l nombre-alumno`, abrimos una sesión con el usuario.

**Nombre de EQUIPO**: Comprobar nombre del equipo.
* Comprobar hostname. Ejemplo:
```
david@vargas42g:~> cat /etc/hostname
vargas42g.curso1819
```
* Comprobar host. Ejemplo:

```
david@vargas42g:~> cat /etc/hosts

127.0.0.1	localhost
127.0.0.2 vargas42g.curso1819   vargas42g

# special IPv6 addresses
::1             localhost ipv6-localhost ipv6-loopback
fe00::0         ipv6-localnet
ff00::0         ipv6-mcastprefix
ff02::1         ipv6-allnodes
ff02::2         ipv6-allrouters
ff02::3         ipv6-allhosts
```

* Comprobar con los siguientes comandos:

```
hostname -f      # Muestra nombre-maquina.nombre-dominio
hostname -a      # Muestra nombre-maquina
hostname -d      # Muestra nombre-dominio
```

---
# ANEXO

## Cambiar el idioma/country desde comandos

https://www.suse.com/documentation/opensuse110/opensuse110_reference/data/sec_suse_l10n.html
