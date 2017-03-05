
*(Utilizado en los cursos 201415 y 2015-16)*

# Tareas programadas

* Realizar la entrega por el repositorio GitHub.
* Usar etiqueta `cron` al finalizar.

# 1. Windows

Vamos a hacer una tarea programada y otra diferida con Windows.

* [Configurar la MV](../../global/configuracion/windows.md)

## 1.1 Tarea diferida

La tarea diferida se define para ejecutarse una sola vez en una fecha futura.
* Vamos a programar una tarea diferida para que nos muestre un mensaje en pantalla.
* En Windows 7 para abrir el programador de tareas hacemos
`Panel de control -> Herramientas administrativas -> Programador de tareas`.

## 1.2 Tarea periódica

La tarea programada se define para ejecutarse periódicamente cada intervalo de tiempo.
* Vamos a programar una tarea periódica para apagar el equipo.
* El comando para apagar el sistema es `shutdown`.

> * `shutdown /?`: Muestra la ayuda del comando.

---

# 2. SO GNU/Linux

Vamos a hacer una tarea programada y otra diferida con GNU/Linux.

* [Configurar OpenSUSE](../../global/configuracion/opensuse.md)
* [Configurar Debian](../../global/configuracion/debian.md)

## 2.1 Tarea diferida

* Vídeo [Scheduling tasks with at](https://www.youtube.com/embed/cf-oUCobxiM?list=UUFFLP0dKesrKWccYscdAr9A). Ejemplos de comandos:
    * `at`, crea una tarea diferida.
    * `atq`, muestra los trabajos en cola.
    * `at -c 1`, muestra la configuración del trabajo ID=1.
    * `atrm 1`, elimina el trabajo con ID=1.
* Configurarmos nuestro usuario para que pueda ejecutar el comando at.
* Vamos a programar una tarea diferida (comando `at`) que nos mostrará un mensaje en pantalla.

> El servicio `atd` es el responsable de la ejecución de los comandos at.
> Para asegurarnos de que esté en ejecución:
> * `Yast -> Servicios`.
> * `systemctl status atd`    
>
> Si el usuario no tuviera permisos para ejecutar at, consultar los ficheros: `/etc/at.deny` y `/etc/at.allow`.

> Otra forma de trabajar con at: `at 11:45 Feb 28 < scriptname.sh`

## 2.2 Tarea periódica

* Vídeo [Scheduling tasks with cron](https://www.youtube.com/embed/yBkJQKinZKY)
* Programar una tarea periódica (crontab) para apagar el equipo.
* El comando para apagar el sistema es `shutdown`.
* Para programar una tarea periódica tenemos estas formas:
    * Los usuarios usan el comando `crontab`  para programar sus tareas.
    * El usuario root usa el fichero `/etc/crontab` para programar las tareas del sistema.

> Para definir una tarea ASINCRONA ponemos el script de ejecución en alguno
de los directorios siguientes:
> * `/etc/cron.hourly`, cada hora
> * `/etc/cron.daily`, diariamente
> * `/etc/cron.weekly`, semanalmente
> * `/etc/cron.monthly`, mensualmente

---

# ANEXO

## Ejemplos GNU/Linux
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

* Ejemplo de script que muestra un mensaje de aviso:

```
    #!/bin/sh
    # Mostrar mensaje en pantalla
    DISPLAY=:0
    export DISPLAY
    zenity --info --text="¡Que la fuerza te acompañe!"
```

Tarea asíncrona

* Vamos a programar una tarea asíncrona para realizar una copia de backup.
* Podemos usar el comando `tar` dentro de un script para realizar la
copia de los ficheros del usuario en una zona de backup.
    * Directorio de datos `/home/nombre-alumno1`.
    * Directorio para guardar el backup `/var/backup-XX/nombre-alumno1`.

## Windows  Tarea asíncrona

* Vamos a programar una tarea asíncrona para realizar una copia de backup.
* Como ejemplo podemos crear un fichero `backup.bat` con comandos del tipo `xcopy`,
para copiar los documentos del usuario en una zona de backup.
    * Directorio de datos `c:\Users\nombre-alumno1\`.
    * Directorio para guardar el backup `c:\backup-XX\nombre-alumno1`.

> Tareas de envío de **email**
> * La tarea de envío de email no va a funcionar por defecto.
> * Para poder usar la tarea de envío de correos, es necesario tener un servidor SMTP instalado de forma local.

Otras tareas que se podrían realizar en Windows:
* ¿Cómo podríamos programar el inicio/parada de un servicio con los comandos de PowerShell?
