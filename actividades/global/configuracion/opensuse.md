
> Documentos relacionados:
>
> * Configurar [VirtualBox](../virtualbox/opensuse.md)
> * Configurar [Acceso remoto](../acceso-remoto/opensuse.md)
> * Configurar [Firewall](../firewall.md).

# Configurar MV GNU/Linux OpenSUSE

## Introducción

> * Donde aparezca AA debemos poner el código asignado al aula:
>     * 18 para el aula108
>     * 19 para el aula109
> * Donde aparezca XX debemos poner el código asignado al alumno.

---

## Máquina VirtualBox

* Usar un disco VirtualBox de 15GB.
* Tarjeta de red VBox en `modo puente`.

---

## Durante la instalación del SO

Recomendaciones:
* Crear usuario identificado con `nombre-del-alumno`.
* Seleccionar entorno gráfico ligero como Xfce.
* Recordatorio.
    * Abrir puerto SSH.
    * Habilitar servicio SSH.
* Configurar lo siguiente durante la instalación:

> ![opensuse-instalacion-configuracion.png](./images/opensuse-instalacion-configuracion.png)

---

## Después de la instalación del SO

Una vez instalado el sistema operativo, podemos hacer cambios en la configuración,
usando la herramienta `Inicio -> Configuración -> Yast -> Ajustes de red`.

Vamos a `Vista resumen -> Interfaz -> Editar`
* Marcamos IP fija.
* IP: `172.AA.XX.31` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.AA.XX.32, 172.AA.XX.33, etc.
    * Máscara de red: `255.255.0.0`
* Siguiente

Vamos a `Nombre de Host/DNS` y ponemos:
* Desmarcamos `Modificar nombre mediante DHCP`
* Marcamos `Asignar nombre de host a la IP bucle local`
* Nombre de equipo: `primer-apellido-del-alumnoXXg1`.
    * Por ejemplo vargas30g1
    * Si tenemos varias máquinas las llamaremos vargas30g2, vargas30g3, vargas30g4, etc.
* Nombre de dominio: `curso1819` (Modificar los números al curso actual).
* Servidor DNS: `8.8.4.4`.
* Vamos a `Encaminamiento`y ponemos Gateway o pasarela IPv4: `172.AA.0.1`. Esto es la puerta de enlace o encaminamiento.
* Ir dispositivo y elegir interfaz de red.

## Comprobar nombres

Comprobar hostname:
```
david@vargas42g1:~> cat /etc/hostname
vargas42g1.curso1819
```

Comprobar host

```
david@vargas42g1:~> cat /etc/hosts
#
# hosts         This file describes a number of hostname-to-address
#               mappings for the TCP/IP subsystem.  It is mostly
#               used at boot time, when no name servers are running.
#               On small systems, this file can be used instead of a
#               "named" name server.

127.0.0.1	localhost
127.0.0.2 vargas42g1.curso1819   vargas42g1

# special IPv6 addresses
::1             localhost ipv6-localhost ipv6-loopback
fe00::0         ipv6-localnet
ff00::0         ipv6-mcastprefix
ff02::1         ipv6-allnodes
ff02::2         ipv6-allrouters
ff02::3         ipv6-allhosts
```

---

## Comprobaciones finales

Estos comandos nos van a ayudar a verificar que lo tenemos todo correctamente:
```
uname -a         # Muestra información del SO
id nombre-alumno # Muestra información del usuario
hostname -f      # Muestra nombre-maquina.nombre-dominio
hostname -a      # Muestra nombre-maquina
hostname -d      # Muestra nombre-dominio

ifstatus         # Muestra información de la configuración de red
ping 8.8.4.4     # Verifica la conectividad con Internet
host www.nba.com # Comprueba DNS
```

---

# ANEXO

## Ficheros de configuración

El fichero de configuración de red de OpenSUSE es `/etc/sysconfig/network/ifcfg-eth0`
En el modo automático tiene un contenido similar al siguiente:
```
BOOTPROTO='dhcp'
STARTMODE='auto'
DHCLIENT_SET_DEFAULT_ROUTE='yes'
```
En el modo manual puede ser como:
```
BOOTPROTO='static'
IPADDR='192.168.16.11/24'
STARTMODE='ifplugd'
```

## EFI + GPT

* Creamos una MV VirtualBox y Activamos EFI. Ir
`VBox -> Configuración -> Sistema -> EFI -> Habilitar`.
* Consultar la siguiente propuesta de particionamiento y comprobar que se
proponen 3 particiones (swap, para el sistema y una nueva para el boot/efi).

![opensuse-particiones-efi.png](./images/opensuse-particiones-efi.png)

![opensuse-particiones-efi2.png](./images/opensuse-particiones-efi2.png)
