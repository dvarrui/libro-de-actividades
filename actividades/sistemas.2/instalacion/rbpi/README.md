
```
Curso       : 201920
Area        : Sistemas operativos, instalación.
              Fundamentos de hardware.
Descripción : Preparar la RbPI con acceso remoto
Requisitos  :
Tiempo      :
```

---
# Raspberry PI

> Enlaces de interés:
> * [Página oficial Raspberry PI](https://www.raspberrypi.org/)

**Preparar el sistema**

* Instalar el SO en la RbPI.
    * Consultar la [página oficial Raspberry PI](https://www.raspberrypi.org/)
    * Elegir uno de los sistemas operativos GNU/Linux recomendados.
* Configurar la [Raspberry PI](../../../global/configuracion/rbpi.md)
    * Configurar la IP estática del interfaz de red.
    * Configurar la IP dinámica del interfaz Wifi (Sólo en la RbPI modelo 3).
* Instalar y activar el servicio SSH.
* Comprobar que funciona correctamente el acceso remoto SSH con nuestro usuario (`ssh nombre-del-alumno@ip`).

**Identificación**

Pegar una etiqueta (papel) a la RbPI donde ponga:
* ID de la RbPI (Por ejemplo "RbPI16")
* MAC de la tarjeta de red del dispositivo. El comando `ip a` nos muestra la MAC.

---
# Conectarse en clase

* Conectar la RbPI a la red, en el switch del aula, usando la boca (puerto) XX, donde XX es el identificador del alumno.
* Conectar la RbPI a la corriente.

---
# PROBLEMA: Localizar la IP de la máquina

Para conocer la IP de nuestra RbPI tenemos dos opciones:
* (a) Ya la sabemos porque le pusimos la IP estática 172.18.XX.51.
* (b) Tenemos que averiguarla porque tiene la IP dinámica. Sigue leyendo.

Para averiguar la IP hacemos lo siguiente:
* Ir a nuestro PC desktop.
* `sudo arp-scan 172.18.0.0/16` para hacer un barrido por un rango de IP's.

> Otras formas de hacer el barrido IP:
>
> * ping -b -c1 -i 20 172.18.99.255
> * ping -b -c3 -i 20 172.18.255.255
> * nmap -Pn 172.18.99.255
> * nmap -Pn 172.18.255.255

* Ejecutando `arp > arp.txt`, se cre el fichero arp.txt. Consulta el fichero y busca tu MAC.
* Ya tenemos la IP.
