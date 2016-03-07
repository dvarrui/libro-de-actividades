
# Registro de eventos remotos

#1. Introducción

Para esta actividad vamos a necesitar lo siguiente:
* MV1: GNU/Linux cliente local syslog
* MV2: GNU/Linux servidor remoto syslog (IP fija)

> NOTA:
>
> * El fichero de configuración de syslog es /etc/rsyslog.conf.
> * En algunas distribuciones existe además el directorio /etc/rsyslog.d que 
contiene la configuración de syslog, repartida en varios ficheros para hacerla 
más manejable. Pero funciona igual que el caso anterior.

#2. Registros de sesiones local

Realizar la siguientes tareas en un GNU/Linux (MV1):
* Crear los usuarios nombre-alumno1, nombre-alumno2 y nombre-alumno3 en el sistema.
* Entrar con nombre-alumno1 y salir.
* Entrar con nombre-alumno2, pero poner la clave incorrectamente varias veces.
* No entrar con nombre-alumno3.
* Volvemos a entrar al sistema con nuestro usuario habitual.
* Averiguar cómo podemos consultar en los logs, las últimas entradas 
al sistema de los usuarios(Consultar comando last), y los intentos fallidos.
* Averiguar donde se registra cada vez que usamos el comando sudo.

#3. Registro en servidor remoto

##3.1 Configuración del servidor syslog remoto

Ahora vamos a usar la máquina MV2:

> En la máquina servidor remoto tenemos que configurar syslog para aceptar entradas de log remotas (Através de la red). 
> 
> Para ello modificamos el fichero /etc/rsyslog.conf de modo que activamos (quitamos las almohadillas) las líneas siguientes:
> ```
> $ModLoad imudp
> $UDPServerRun 514
> ```

* Configuramos el fichero /etc/rsyslog.d/50-default del servidor remoto, para usar:
    * Un fichero de log llamado /var/log/nombrealumno/prueba-red.log.
    * El recurso "local1" para en esta configuración en syslog.
* Veamos ejemplo:
```
local1.* /var/log/nombrealumno/prueba-red.log
```
* Reiniciar el servicio de syslog para que surtan efecto los cambios.

##3.2 Configuración del cliente syslog local

En la máquina MV1 hacemos lo siguiente.
* Configuramos /etc/rsyslog.d/50-default del cliente para que nuestros mensajes 
de prueba se redirijan al servidor de syslog.
* Los mensajes del recurso "local1" son los que deben guardarse en el servidor remoto. Ejemplo:
```
local1.* @nombre-del-servidor-de-log-remoto
local1.* @ip-del-servidor-de-log-remoto
local0.* /var/log/nombre-alumno/prueba-local.log
```
* Para que esta máquina pueda enviar mensaje de log por red, hay que iniciar sysdlogd 
con el parámetro -r. Para ello debemos modificar el fichero /etc/default/rsyslog.
* Reiniciamos el servicio de rsyslog en el cliente local.
* Desde el cliente invocamos al comando logger, y comprobamos que los 
mensajes de log se han almacenado en la máquina remota.

#4. NOTA

##4.1 Error al iniciar syslog con escucha de red

* Si modificamos el servicio de syslog para escuchar por la red, y obtenemos mensajes de error al iniciarlo, 
probar a poner el valor "-c4" en el fichero /etc/default/rsyslog. Ejemplo: RSYSLOGD_OPTIONS="-c4"

##4.2 Versiones anteriores de syslog

En versiones anteriores de syslog, la forma de activar el servicio para aceptar entradas 
por la red, era modificar el fichero /etc/default/rsyslog, de la siguiente forma:
```
    # For remote UDP logging use SYSLOGD="-r"
    #
    RSYSLOGD_OPTIONS="-c2 -r"
```
* Esto es, añadir el parámetro -r a las opciones de inicio del servicio.
