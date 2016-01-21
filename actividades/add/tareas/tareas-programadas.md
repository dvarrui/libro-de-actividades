
*(Utilizado en el curso 2015-16)*

#Tareas programadas

* Realizar la entrega por el repositorio GitHub.
* Usar etiqueta `cron` al finalizar.

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

* Vamos a programar una tarea diferida (comando `at`) que nos mostrará
un mensaje en pantalla de ánimo.
* Ejemplo de script que muestra un mensaje de aviso:

```
    #!/bin/sh
    # Mostrar mensaje en pantalla
    DISPLAY=:0
    export DISPLAY
    zenity --info --text="¡Que la fuerza te acompañe!"
```

> * Si el servicio `atd` (responsable de la ejecución de los comandos at) no estuviera
en ejecución en OpenSUSE, iremos a `Yast -> Servicios` y lo iniciamos.
> * Si el usuario no tuviera permisos para ejecutar at, consultar los ficheros:
>     * `/etc/at.deny`
>     * `/etc/at.allow`

##1.3 Tarea periódica

* Programar una tarea periódica (crontab) para apagar el equipo.
* El comando para apagar el sistema es `shutdown`.
* Para programar una tarea periódica tenemos estas formas:
    * Los usuarios usan el comando `crontab`  para programar sus tareas.
    * El usuario root usa el fichero `/etc/crontab` para programar las tareas del sistema. 

##1.4 Tarea asíncrona

* Vamos a programar una tarea asíncrona para realizar una copia de backup.
* Podemos usar el comando `tar` dentro de un script para realizar la 
copia de los ficheros del usuario en una zona de backup.
    * Directorio de datos `/home/nombre-alumno1`.
    * Directorio para guardar el backup `/var/backup-XX/nombre-alumno1`.

> Para definir una tarea asíncrona ponemos el script de ejecución en alguno 
de los directorios siguientes:
> * /etc/cron.hourly
> * /etc/cron.daily
> * /etc/cron.weekly
> * /etc/cron.monthly
    
#2. Windows7

##2.1 Configuración de la máquina

Configurar máquina *Windows 7 Professional* con:
* IP: 172.18.XX.11 (Donde XX corresponde al nº de cada puesto).
* Nombre de equipo: primer-apellido-del-alumno+1. Por ejemplo: VARGAS1
* Máscara de red: 255.255.0.0
* Gateway: 172.18.0.1
* Servidor DNS: 8.8.4.4
* Grupo de trabajo: AULA108
* Tarjeta de red VBox en modo puente.

Capturar imágenes de las configuraciones.

##2.2 Tarea diferida

* Vamos a programar una tarea diferida para que nos muestre un mensaje 
de ánimo en pantalla.
* En Windows 7 para abrir el programador de tareas hacemos 
`Panel de control -> Herramientas administrativas -> Programador de tareas`.

##2.3 Tarea periódica

* Vamos a programar una tarea periódica para apagar el equipo.
* El comando para apagar el sistema es `shutdown`.

> * `shutdown /?`: Muestra la ayuda del comando.
> * `shutdown /s`: Programar un apagado.

##2.4 Tarea asíncrona

* Vamos a programar una tarea asíncrona para realizar una copia de backup.
* Como ejemplo podemos crear un fichero `backup.bat` con comandos del tipo `xcopy`,
para copiar los documentos del usuario en una zona de backup.
    * Directorio de datos `c:\Users\nombre-alumno1\`.
    * Directorio para guardar el backup `c:\backup-XX\nombre-alumno1`.

> Tareas de envío de **email**
> * La tarea de envío de email no va a funcionar por defecto.
> * Para poder usar la tarea de envío de correos, es necesario tener un servidor SMTP instalado de forma local.

#ANEXO

Otras tareas que se podrían realizar en GNU/Linux:
* Ejemplo de script:
```
#!/bin/bash
# Añade la fecha/hora a un fichero
date >> /home/usuario/cron.log
```
* Utilizar el servicio SSH. Programar el inicio/parada del servicio con el cron/crontab. Comprobarlo.
* Utilizar el servicio del servicio-prueba. Programar el inicio/parada del servicio con el anacron.
* Crear un script de prueba. Programar la ejecución del script con las configuraciones /etc/cron.hourly.

Otras tareas que se podrían realizar en Windows:
* ¿Cómo podríamos programar el inicio/parada de un servicio con los comandos de PowerShell?
