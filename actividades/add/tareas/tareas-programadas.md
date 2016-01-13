
#Tareas programadas

#1. SO OpenSUSE

* Hacer un ejemplo de tarea programada diferida (at). Por ejemplo ejecutar 
el apagado de la máquina con el comando `shutdown`.
* Hacer un ejemplo de tarea programada periódica (crontab). Ejemplos:

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

* Hacer un ejemplo de tarea programada asíncrona. Elegir alguna tarea útil para la administración de sistema.

#2. Windows

> En Windows 7 para iniciar el programador de tareas hacemos 
`Panel de control -> Herramientas administrativas -> Programador de tareas`.

##2.1 Tarea diferida
* Hacer un ejemplo de tarea programada diferida de ejecutar el apagado de la máquina. 

> Para ver la ayuda del comando shutdown hacemos "shutdown /?". 
> Programar un apagado usando "shutdown /s".

##2.2 Tarea periódica
* Hacer un ejemplo de tarea programada periódica de mostrar mensaje a pantalla.

##2.3 Tarea asíncrona
* Hacer un ejemplo de tarea programada asíncrona elegida por el usuario.

> Para poder usar la tarea de envío de correos, es necesario tener un servidor SMTP instalado de forma local.


> **Vídeos**
>
> * [Scheduling tasks with cron](https://www.youtube.com/embed/yBkJQKinZKY)
> * [Scheduling tasks with at](https://www.youtube.com/embed/cf-oUCobxiM?list=UUFFLP0dKesrKWccYscdAr9A)
>

#ANEXO
Otras tareas que se podrían realizar en GNU/Linux:
* Utilizar el servicio SSH. Programar el inicio/parada del servicio con el cron/crontab. Comprobarlo.
* Utilizar el servicio del servicio-prueba. Programar el inicio/parada del servicio con el anacron.
* Crear un script de prueba. Programar la ejecución del script con las configuraciones /etc/cron/hourly.

Otras tareas que se podrían realizar en Windows:
* ¿Cómo podríamos programar el inicio/parada de un servicio con los comandos de PowerShell?

