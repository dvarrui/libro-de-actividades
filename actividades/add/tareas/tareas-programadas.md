
#Tareas programadas

#1. SO OpenSUSE

> **Vídeos**
>
> * [Scheduling tasks with cron](https://www.youtube.com/embed/yBkJQKinZKY)
> * [Scheduling tasks with at](https://www.youtube.com/embed/cf-oUCobxiM?list=UUFFLP0dKesrKWccYscdAr9A)
>

##1.1 Configuración de la máquina

Configurar el equipo GNU/Linux OpenSUSE 13.2 con:
* IP: 172.18.XX.51 (Donde XX corresponde al nº de cada puesto).
* Máscara de red: 255.255.0.0
* Gateway: 172.18.0.1
* Servidor DNS: 8.8.4.4
* Nombre de equipo: primer-apellido-del-alumno+3. Ejemplo VARGAS3
* Nombre de dominio: segundo-apellido-del-alumno.
* Tarjeta de red VBox en modo puente.

Además también:
* Instalar openssh-server para que el profesor pueda acceder de forma remota.
* Asegurarse de que el nombre de host está correctamente en el fichero `/etc/hosts`.
Para que el comando `hostname` funcione bien.

Capturar imágen de la configuración del equipo:

    uname -a
    hostname -a
    hostname -d
    ip a
    route -n
    blkid

##1.2 Tarea diferida

* Hacer un ejemplo de tarea programada diferida (comando `at`). Por ejemplo ejecutar 
el apagado de la máquina con el comando `shutdown`.

##1.3 Tarea periódica

* Hacer un ejemplo de tarea programada periódica (crontab).
* Ejemplos de scripts para programar:

```
    #!/bin/sh
    # Mostrar mensaje en pantalla
    DISPLAY=:0
    export DISPLAY
    zenity --info --text="Tarea ejecutada"
```

```
    #!/bin/bash
    # Añade la fecha/hora a un fichero
    date >> /home/usuario/cron.log
```
* Para programar una tarea periódica:
    * Los usuarios usan el comando `crontab`  para programar sus tareas.
    * El usuario root usa el fichero `/etc/crontab` para programar las tareas del sistema. 

##1.4 Tarea asíncrona

* Hacer un ejemplo de tarea programada asíncrona. Elegir alguna tarea útil para la administración de sistema.

#2. Windows7

> En Windows 7 para iniciar el programador de tareas hacemos 
`Panel de control -> Herramientas administrativas -> Programador de tareas`.

##2.1 Configuración de la máquina

Configurar máquina *Windows 7 Professional* con:
* IP: 172.18.XX.11 (Donde XX corresponde al nº de cada puesto).
* Nombre de equipo: primer-apellido-del-alumno+1. Por ejemplo: VARGAS1
* Máscara de red: 255.255.0.0
* Gateway: 172.18.0.1
* Servidor DNS: 8.8.4.4
* Grupo de trabajo: AULA109
* Tarjeta de red VBox en modo puente.

Capturar imágenes de las configuraciones.

##2.2 Tarea diferida
* Hacer un ejemplo de tarea programada diferida de ejecutar el apagado de la máquina. 

> Para ver la ayuda del comando shutdown hacemos "shutdown /?". 
> Programar un apagado usando "shutdown /s".

##2.3 Tarea periódica
* Hacer un ejemplo de tarea programada periódica de mostrar mensaje a pantalla.

##2.4 Tarea asíncrona
* Hacer un ejemplo de tarea programada asíncrona elegida por el usuario.

> Para poder usar la tarea de envío de correos, es necesario tener un servidor SMTP instalado de forma local.

#ANEXO
Otras tareas que se podrían realizar en GNU/Linux:
* Utilizar el servicio SSH. Programar el inicio/parada del servicio con el cron/crontab. Comprobarlo.
* Utilizar el servicio del servicio-prueba. Programar el inicio/parada del servicio con el anacron.
* Crear un script de prueba. Programar la ejecución del script con las configuraciones /etc/cron/hourly.

Otras tareas que se podrían realizar en Windows:
* ¿Cómo podríamos programar el inicio/parada de un servicio con los comandos de PowerShell?
