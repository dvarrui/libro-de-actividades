
# Router BENDER

Bender es un router Cisco. En este documento tenemos algunas miniguías para hacer
las acciones más frecuentes en dicho router.

---

## Cambiar la asociación IP-MAC

Entrar al router:
* `ssh user@IP-de-Bender`, Iniciar conexión SSH para entrar al router
* `BENDER# show running-config`, para consultar la configuración y localizar el nombre
PC a cambiar (NAME). Debe tener la forma `aula109pcXX`.

Modificar la configuración:
* `BENDER# clear ip dhcp binding *`, limpiar las asociaciones pervias de IP para PC's invitados.
* `BENDER# conf terminal`, activar el modo de configuración.
* `BENDER(config)# ip dhcp pool NAME`, entrar en la configuración de NAME.
* `BENDER(dhcp-config)# hardware-address XXXX.XXXX.XXXX`, cambiar la MAC del PC NAME.
* `BENDER(dhcp-config)# exit`
* `BENDER(config)# exit`

Grabar los cambios de configuración de forma permanente:
* `BENDER# show running-config`, consultar los cambios realizados
* `BENDER# copy running-config startup-config`, grabar los cambios de forma permanente.
* `BENDER# exit`, salir del router.

---

## PENDIENTE - Redireccionar puertos

Para redireccionar puertos en un router cisco, hay que tener cierta información:
* El puerto que necesitamos abrir
* La ip fija del servidor que queremos abrir a internet
* El puerto que queremos abrir hacía internet

El comando sería:
```
ip nat inside source static [tcp/udp] [ip servidor local] [puerto local] interface [Interfaz wan] [puerto exterior]
```

> Un ejemplo para abrir un servidor web:
> ```
> Router(config)# ip nat inside source static tcp 192.168.1.1 80 interface FastEthernet0/1 80
> ```
>
> El puerto 80 del host que tenga la ip 192.168.1.1 será accesible desde fuera de la red local, con lo cual la gente de Internet podrá acceder a nuestra pagina web.

* `ssh user@IP-de-Bender`, Iniciar conexión SSH para entrar al router
* `BENDER# show running-config`, para consultar la configuración de las interfaces que vamos a modificar.

```
!
interface GigabitEthernet0/1
 ip address 192.168.1.254 255.255.255.0
 ip nat outside
 ip virtual-reassembly in
 duplex auto
 speed auto
!         
interface GigabitEthernet0/1/0
 no ip address
 duplex auto
 speed auto
!         
interface GigabitEthernet0/1/0.20
 encapsulation dot1Q 20
 ip address 172.20.0.1 255.255.0.0
 ip nat inside
 ip virtual-reassembly in
!
```

* `BENDER# configure terminal`, activar el modo de configuración.
* `BENDER(config)# ip nat source static tcp 172.20.1.2 22 interface GigabitEthernet0/1 2222`, activar la redirección del puerto 2222.

---

# Redes conectadas

| Interfaz    | IP               | Descripción |
| :---------: | :--------------- | :---------- |
| GE 0/0      |                  | Salida a Internet usando otra red externa |
| GE 0/1      | 192.168.1.254/24 | Conexión al router de Telefónica |
| GE 0/2      | 172.31.0.1/16    | Departamento |
| GE 0/0/0.18 | 172.18.0.1/16    | Aula 108 a través de VLAN en el Switch|
| GE 0/0/0.19 | 172.19.0.1/16    | Aula 109 a través de VLAN en el Switch|
| GE 0/1/0.20 | 172.20.0.1/16    | Red de servidores a través de VLAN en el Switch|
| GE 0/1/0.26 | 172.26.0.1/16    | Aula 206 a través de VLAN en el Switch|
| GE 0/1/0.29 | 172.29.0.1/16    | Aula 209 a través de VLAN en el Switch|
