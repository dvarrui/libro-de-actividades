
# Switch Cisco SOL

SOL es un switch Cisco. En este documento tenemos algunas miniguías para hacer
acciones en dicho switch.

## Cambiar la asociación de los puertos a VLAN

Entrar al switch:
* `ssh user@IP-de-SOL`, Iniciar conexión SSH para entrar al switch.
* `Switch2960X# show running-config`, para consultar la configuración.

Podemos ver cómo están asociados los puertos del switch a cada VLAN:

```
!
interface FastEthernet0
 no ip address
!
interface GigabitEthernet0/1
 switchport access vlan 18
 switchport mode access
!
interface GigabitEthernet0/2
 switchport access vlan 18
 switchport mode access
!
interface GigabitEthernet0/3
 switchport access vlan 19
 switchport mode access
!         
interface GigabitEthernet0/4
 switchport access vlan 19
 switchport mode access
!         
interface GigabitEthernet0/5
 switchport access vlan 26
 switchport mode access
```

* `Switch2960X# show vlan`, consultar la asociación de puertos a cada VLAN.

```
VLAN Name                             Status    Ports
---- -------------------------------- --------- -------------------------------
1    default                          active    Gi0/9, Gi0/10, Gi0/11, Gi0/12, Gi0/21, Gi0/24, Gi0/25, Gi0/26
16   VLAN0016                         active    Gi0/15, Gi0/16, Gi0/17, Gi0/18, Gi0/19, Gi0/20
18   VLAN0018                         active    Gi0/1, Gi0/2
19   VLAN0019                         active    Gi0/3, Gi0/4
20   VLAN0020                         active    Gi0/13, Gi0/14
26   VLAN0026                         active    Gi0/5, Gi0/6
29   VLAN0029                         active    Gi0/7, Gi0/8
30   VLAN0030                         active    
```

Vamos a cambiar la asociación de puertos:

* `Switch2960X# configure terminal`, activar el modo de configuración.
* `Switch2960X(config)# interface range GigabitEthernet 0/13-14`, defino el rango de puertos que quiero configurar.
* `Switch2960X(if-config)# switchport mode access`
* `Switch2960X(if-config)# switchport access vlan 20`, asigno los puertos a una vlan.
* `Switch2960X(if-config)# exit`
* `Switch2960X(config)# exit`

Comprobamos:
* `Switch2960X# show vlan`

Grabar los cambios de configuración de forma permanente:
* `Switch2960X# show running-config`, consultar los cambios realizados
* `Switch2960X# copy running-config startup-config`, grabar los cambios de forma permanente.
* `Switch2960X# exit`, salir del switch.
