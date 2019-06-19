
```
Curso           :
Software        : Windows 2012 Server, Ubuntu
Tiempo estimado :
```
---

# Servidor NTP

* Entregar un informe con el proceso de
    * Instalación y configuración de un servicio NTP en Windows 2012 Server.
    * Instalación y configuración de un servicio NTP en GNU/Linux Ubuntu.
* Mostrar pruebas de activación y funcionamiento del servicio.

Preparativos:
* 1 MV Windows y otra Windows Server
* 2 MV GNU/Linux

---

# 1. NTP en Windows

Enlaces de interés:
* [Configurando NTP en Windows Server 2012](http://blogs.itpro.es/jioller/2013/09/25/config-ntp-w12/)
* [Videotutorial NTP Windows 2012](https://www.youtube.com/watch?v=wPUm5wcW2oE)

## 1.1 Configurar el servidor

Ir a la MV servidor:
* Instalar el servicio NTP.
* Crear una cuenta específica para el servicio.

## 1.2 Configurar el cliente

Ir a la MV cliente:
* Cambiar la hora, poniendo hora incorrecta. Comprobamos `time`.
* `net time /setsntp:IP-SERVIDOR_NTP`, configurar el servidor NTP que se va a usar.
* `net time /querysntp`, comprobamos la configuración del servidor NTP.
* `net stop w32time`, para el servicio NTP
* `net start w32time`, iniciar el servicio NTP.
* Comprobar la hora `time`.

---

# 2. NTP en GNU/Linux

Enlaces de interés:
* [Configurar Servidor NTP en Ubuntu ](https://echaleunvistazo.wordpress.com/2015/02/17/configurar-servidor-ntp-en-ubuntu/)

## 2.1 Configurar el servidor

## 2.2 Configurar el cliente
