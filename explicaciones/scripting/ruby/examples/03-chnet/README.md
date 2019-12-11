
# Script: cambiar la configuración de red

Vamos a hacer un script Ruby para cambiar la configuración de red de una máquina con GNU/Linux.

## Menú interactivo

Al iniciar el script se nos mostrará este menú para poder elegir la acción a realizar:

```
============================
CHange NETwork configuration
============================
 r. Reset
 1. Classroom 109
 2. My home

 Select option [Enter=exit]:
```

## Comandos de red

> Enlaces de interés:
> * [Learn about IP configuration](URL: https://www.tecmint.com/ip-command-examples/)

| Acción | Descripción | Comandos |
| ------ | ----------- | -------- |
| Reset  | Resetar la configuración de red | ifdown eth; ifup eth0 |
| Classroom109 | Poner configuración de red para el aula 109 | ip addr add 172.19.XX.33/16 dev eth0 |
| MyHome | Poner configuración de red para casa | ip addr add 192.168.1.1XX/24 dev eth0 |
