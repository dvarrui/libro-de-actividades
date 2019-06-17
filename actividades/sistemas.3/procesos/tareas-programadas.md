
```
Curso           : 201819, 201718, 201617
Software        : Windows y GNU/Linux
Tiempo estimado : 5 horas
```
---

# Tareas programadas

* Realizar la entrega por el repositorio GitHub.
* Usar etiqueta `cron` al finalizar.

---

# 1. Windows

## 1.1 Windows - Tarea diferida.

Vamos a hacer una tarea diferida con Windows. Una tarea diferida se define para ejecutarse una sola vez en una fecha futura.

* [Configurar la MV](../../global/configuracion/windows.md)
* En Windows 7 para abrir el programador de tareas hacemos
`Panel de control -> Herramientas administrativas -> Programador de tareas`.
* Vamos a programar una tarea diferida. Ejemplos:
    * Mostrar un mensaje en pantalla.
    * Abrir un fichero de texto en pantalla.
    * Iniciar un programa determinado (Firefox).

## 1.2 Windows - Tarea periódica

La tarea programada se define para ejecutarse periódicamente cada intervalo de tiempo.

* Vamos a programar una tarea periódica para apagar el equipo.
* El comando para apagar el sistema es `shutdown`. `shutdown /?`, muestra la ayuda del comando.

---

# 2. SO GNU/Linux

## 2.1 SO GNU/Linux - Tarea diferida

Vamos a hacer una tarea diferida con GNU/Linux.

* [Programar tareas con cron y crontab](https://www.ochobitshacenunbyte.com/2018/03/22/programar-tareas-en-linux-facilmente-con-cron-y-crontab/)
* [Configurar OpenSUSE](../../global/configuracion/opensuse.md)
* Consultar el vídeo [Scheduling tasks with at](https://www.youtube.com/embed/cf-oUCobxiM?list=UUFFLP0dKesrKWccYscdAr9A).

El servicio `atd` es el responsable de la ejecución de los comandos at. Comprobar que esté en ejecución:

* `Yast -> Servicios`
* `systemctl status atd`    

> Ejemplos de comandos:
> * `at`, crea una tarea diferida.
> * `atq`, muestra los trabajos en cola.
> * `at -c 1`, muestra la configuración del trabajo ID=1.
> * `atrm 1`, elimina el trabajo con ID=1.
>
> Otra forma de trabajar con at: `at 11:45 Feb 28 < scriptname.sh`

* Si el usuario no tuviera permisos para ejecutar at, consultar los ficheros: `/etc/at.deny` y `/etc/at.allow`.
* `atq`, consultamos que no hay ninguna tarea programada.

Ejemplo de script que muestra un mensaje de aviso:

```
#!/bin/sh
# Mostrar mensaje en pantalla
DISPLAY=:0
export DISPLAY
zenity --info --text="¡Hola nombre-del-alumno!"
```

> Un script para mostrar información en pantalla, debe ser ejecutado por el usuario
que inició el entorno gráfico.

* Usar comando `at` para programar una tarea diferida. Por ejemplo para mostrar un mensaje en pantalla.
* `atq`, consultamos que SI hay una tarea programada.
* `at -c 1`, muestra la configuración del trabajo ID=1.
* Capturar imagen cuando se ejecute la tarea.
* `atq`, consultamos que ya NO hay tareas.

## 2.2 GNU/Linux - Tarea periódica

Consultar
* Vídeo [Scheduling tasks with cron](https://www.youtube.com/embed/yBkJQKinZKY)
* Enlaces de interés [Tareas programadas]](https://www.nerion.es/soporte/tutoriales/tareas-programadas-en-linux/)

> Para programar una tarea periódica tenemos dos formas:
>
> * Los usuarios normales usan el comando `crontab`  para programar sus tareas periódicas.
> * El usuario root, además puede usar el fichero `/etc/crontab` para programar las tareas del sistema.

* `crontab -l`, para consultar que no hay tareas programadas.
* Por defecto, la herramienta crontab usa el editor `vim` para modificar su configuración.
Si queremos usar, por ejemplo, el editor `nano`, en la consola escribimos `export VISUAL='nano'`.
* `crontab -e`, abre el editor para crear una nueva tarea periódica.
* Definir una tarea periódica (crontab) para apagar el equipo todos los días a una hora/minuto determinada.
* Para salir del editor `vim`, escribimos la secuencia `ESC`, `:` y `wq`.
* `crontab -l`, para consultar la tarea que tenemos programada.

> Otro script de ejemplo:
> ```
>     #!/bin/bash
>     # Añade la fecha/hora a un fichero cron.log
>     date >> /home/usuario/cron.log
> ```

> Para definir una tarea ASINCRONA ponemos el script de ejecución en alguno
de los directorios siguientes:
> * `/etc/cron.hourly`, cada hora
> * `/etc/cron.daily`, diariamente
> * `/etc/cron.weekly`, semanalmente
> * `/etc/cron.monthly`, mensualmente

---

# ANEXO

## Procesos en segundo plano

Enlaces de interés:
* [Procesos en segundo plano en Linux](https://www.atareao.es/como/procesos-en-segundo-plano-en-linux/)

## Ejemplos GNU/Linux

* Crear un script de prueba. Programar la ejecución del script con las configuraciones /etc/cron.hourly.

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
