
```
Estado      : EN CONSTRUCCION!!!
Curso       : 201920
Area        : Redes, sistemas operativos, seguridad
Descripción :
Requisitos  : SO GNU/Linux con iptables
```

---
# 1. Configuración

> Enlaces de interés:
> * https://www.linux-party.com/57-seguridad/1318-configuracion-paso-a-paso-de-una-nat-con-los-iptables*

* Crear MV con dos tarjetas de red.

## 1.1 Configurar Tarjeta de red 1

Parámetros:
* Nombre eth0
* WAN o acceso a la red externa (172.19.0.0)
* Reemplace: xx.xx.xx.xx con su IP WAN
* IP Pública

Comprobar `cat /etc/sysconfig/network-scripts/ifcfg-eth0`:

```
DEVICE=eth0
BOOTPROTO=none
BROADCAST=xx.xx.xx.255    # Optional Entry
HWADDR=00:50:BA:AA:72:BB  # Optional Entry
IPADDR=xx.xx.xx.xx
NETMASK=255.255.255.0    # Provided by the ISP
NETWORK=xx.xx.xx.0       # Optional
ONBOOT=yes
TYPE=Ethernet
USERCTL=no
IPV6INIT=no
PEERDNS=yes
GATEWAY=xx.xx.xx.1    # Provided by the ISP
```

## 1.2 Configurar Tarjeta de red 2

Parámetros:
* Nombre eth1
* LAN o acceso a la red interna (192.168.0.0).
* Reemplace: xx.xx.xx.xx con su IP WAN
* IP Privada

Comprobar: `cat /etc/sysconfig/network-scripts/ifcfg-eth1`

```
BOOTPROTO=none
PEERDNS=yes
HWADDR=CC:50:8B:CF:9C:FF  # Optional
TYPE=Ethernet
IPV6INIT=no
DEVICE=eth1
NETMASK=255.255.0.0       # Specify based on your requirement
BROADCAST=""
IPADDR=192.168.2.1        # Gateway of the LAN
NETWORK=192.168.0.0       # Optional
USERCTL=no
ONBOOT=yes
```

## 1.3 Configuración de puerta de enlace

Comprobamos `cat /etc/sysconfig/network`
```
NETWORKING=yes
HOSTNAME=nat
GATEWAY=xx.xx.xx.1    # Internet Gateway, provided by the ISP
```

## 1.4 Configuración DNS

Comprobamos `cat /etc/resolv.conf`

```
nameserver 80.58.61.250  # Servidor primario DNS proporcionado por el ISP
nameserver 80.58.61.254  # Servidor secundario DNS proporcionado por el ISP
nameserver 202.56.250.5  # Tercer servidor
```

---
# 2. Configuración NAT

## 2.1 IPTables

Eliminar y limpiar la Tabla por defecto
* `iptables --flush`, Flush all the rules in filter and nat tables
* `iptables --table nat --flush`
* `iptables --delete-chain`

Borrar todas las cadenas que no están en default filtro y tabla nat
* `iptables --table nat --delete-chain`

Activa el reenvío de IP y enmascaramiento
* `iptables --table nat --append POSTROUTING --out-interface eth0 -j MASQUERADE`
* `iptables --append FORWARD --in-interface eth1 -j ACCEPT`

## 2.2 reenvío

Habilita el reenvío de paquetes de kernel
`echo 1 > /proc/sys/net/ipv4/ip_forward`

# Aplicar la configuración

service iptables restart

---

# Ping a la puerta de enlace de la red desde el sistema cliente

ping 192.168.2.1

Pruébalo en sus sistemas cliente

ping google.com

Configuración de las PCs de la red (Clientes)

• A todos los PC de la red de la oficina privada debe establecer como "puerta de enlace"  la dirección local IP privada del equipo de puerta de enlace Linux.
• El DNS debe establecer en el del ISP en Internet.
Windows 95, 2000, XP, Configuración:

• Seleccione "Start" + Settings "+" Panel de control "
• Seleccione el icono "Red"
• Seleccione la ficha "Configuración" y haga doble clic en el componente "TCP / IP" para la tarjeta ethernet. (NO el TCP / IP -> Adaptador de acceso telefónico)
• Seleccione:
o "puerta de enlace": Utilice la dirección IP de la red interna de la máquina Linux. (192.168.2.1)
o "Configuration DNS": Utilice las direcciones IP de los servidores de nombres de dominio del ISP. (Dirección IP real a Internet)
o "Dirección IP": La dirección IP (192.168.XXX.XXX - estática) y máscara de red (normalmente 255.255.0.0 para una red local pequeña oficina).
