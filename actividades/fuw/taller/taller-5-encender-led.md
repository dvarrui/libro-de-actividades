

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

* `nmap -Pn 172.19.99.0-254`, ejecutamos este comando en nuestro PC, para hacer un barrido por todas las máquinas con IP 172.19.99.* que hay en nuestra red.
* `arp`, consultamos la tabla ARP que muestra una lista de IP-MAC de todos los equipos de la LAN.
* Buscamos la MAC de nuestra Raspberry para encontrar la IP asociada.
* `ssh profesor@IP`, ahora ya podemos entrar de forma remota dentro de nuestra Raspberry.

# Preparar el circuito

* Cogemos los siguientes componentes:
    * 1 protoboard
    * 1 diodo LED (La pata más corta va conectada a tierra)
    * 1 resistencia
    * Cables de conexión
* Montamos el circuito con los componentes anteriores.

# Comprobar el LED

Vamos a comprobar que podemos activar/desactivar el LED mediante comandos.

La manera de la que vamos a acceder a los GPIO (en este caso al 17) es como si fuesen directorios. Podemos utilizar comandos como 'ls', 'cat' o 'echo', entre otros, para conocer la estructura y contenidos de los directorios.

Ahora mismo no tenemos ningún pin accesible. Ni de entrada ni de salida. Tenemos que crearlo nosotros mismos. Queremos tener acceso al GPIO 17, así que introducimos el siguiente comando:

echo 17 > /sys/class/gpio/export

Tras esto, el sistema ha creado un archivo con una estructura GPIO que corresponde al número 17. A continuación, tenemos que informar a la Raspberry Pi de si el pin va a ser de salida o de entrada. Como lo que queremos es encender un LED, el GPIO 17 será de salida. Introducimos el siguiente comando:

echo out > /sys/class/gpio/gpio17/direction

Con esto, el sistema ya sabe que el pin será de salida. Ahora tendremos que darle valores. Existen dos posibles: '0' y '1'.

Para encender el LED:

echo 1 > /sys/class/gpio/gpio17/value

 Para apagar el LED:

echo 0 > /sys/class/gpio/gpio17/value

Una vez hayamos acabado de encender y apagar el LED, tendremos que eliminar la entrada GPIO creada, es decir, el GPIO 17. Para ello introduciremos el siguiente comando:

echo 17 > /sys/class/gpio/unexport

Con esto, ya sabemos crear, eliminar y cacharrear con los pines GPIO para encender y apagar LEDs. Todos sabemos que esto es algo incómodo, así que lo ideal sería crear un programa.

# Crear un script

Vamos a crear un script Bash que haga parpadear el LED.

```
```
