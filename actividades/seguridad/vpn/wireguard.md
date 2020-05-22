
```
EN CONSTRUCCIÓN!!!
```

# Wireguard

Enlaces de interés:
* [WireGuard VPN: Instalación y configuración](https://www.redeszone.net/tutoriales/vpn/wireguard-vpn-configuracion/)

# 1. Servidor

## 1.1 Instalación y generación de las claves

> Enlaces de interés:
> * [Wireguard Installation](https://www.wireguard.com/install/)

* `apt-get install wireguard`, instalar la herramienta.
* `cd /etc/wireguard/`
* `wg genkey | tee claveprivadaservidor | wg pubkey > clavepublicaservidor`, para generar la pareja de claves pública y privada justamente en esta ubicación.

## 1.2 Configuración y levantar el servicio

Ejemplo del fichero `wg0.conf` (Suponiendo que el interfaz se llama wg0):

```
[Interface]
Address = 192.168.2.1/24
PrivateKey = 6JcquylvtJsHNCdWrYMj28XsLIFJUVjlr2y5o27rO2c=
ListenPort = 51820

#PostUp = iptables -A FORWARD -i %i -j ACCEPT; iptables -A FORWARD -o %i -j ACCEPT; iptables -t nat -A POSTROUTING -o ens33 -j MASQUERADE
#PostDown = iptables -D FORWARD -i %i -j ACCEPT; iptables -D FORWARD -o %i -j ACCEPT; iptables -t nat -D POSTROUTING -o ens33 -j MASQUERADE

[Peer]
PublicKey = 6c12jLkKzgU9len1kQ/6Fc61xm+LL98TPPlLsri8klE=
AllowedIPs = 0.0.0.0/0

[Peer]
PublicKey = clave pública del cliente 2
AllowedIPs = 0.0.0.0/0

[Peer]
PublicKey = clave pública del cliente 3
AllowedIPs = 0.0.0.0/0

[/code]
```
* `wg-quick up wg0`, para levantar el interfaz.

# 2. Clientes
