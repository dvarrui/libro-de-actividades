

# Raspberry PI - encender LED

Enlaces de interés:
* [Controlar un LED con bash y python](https://www.peatonet.com/raspberry-pi-y-los-pines-gpio-controlando-un-led-con-bash-y-con-python/)
* [Encender LED con bash 1/2](https://geekytheory.com/tutorial-raspberry-pi-gpio-parte-1-control-de-un-led)
* [Encender LED con python 2/2](https://geekytheory.com/tutorial-raspberry-pi-gpio-parte-2-control-de-leds-con-python)

# Preparar la Raspberry

* Montar la Raspberry y encenderla.

> De la práctica anterior:
> * Conocemos la MAC de la tarjeta de red de nuestra Raspberry
> * En servicio SSH está activo en la Raspberry

* `nmap -Pn 172.19.99.0.254`, ejecutamos este comando en nuestro PC, para hacer un barrido por todas las máquinas con IP 172.19.99.* que hay en nuestra red.
* `arp`, consultamos la tabla ARP que muestra una lista de IP-MAC de todos los equipos de la LAN.
* Buscamos la MAC de nuestra Raspberry para encontrar la IP asociada.
* `ssh profesor@IP`, ahora ya podemos entrar de forma remota dentro de nuestra Raspberry.

# Preparar LED

# Hacer script
