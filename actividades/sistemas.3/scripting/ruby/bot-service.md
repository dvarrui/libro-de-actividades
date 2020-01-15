
```
Curso      : 201920
Requisitos : GNU/Linux, Ruby, Telegram.
```

---
# Servicio de comunicaciones con Telegram (bot-service)

Vamos a crear un servicio de Systemd que iniciará un Bot de Telegram.

Propuesta de rúbrica:

| ID  | Criterio | Bien(2) | Regular(1) | Mal(0) |
| --- | -------- | ------- | ---------- | ------ |
| 1.4 | Entrega  ||||
| 2.4 | Entrega  ||||

---
# 1. Crear un bot de Telegram con ruby

Pasos:
1. Registrar el bot con BotFather
2. Asociar programa ruby con nuestro bot.

# 1.1 Crear bot con BotFather

1. Iniciar Telegram.
1. Buscar usuario `@BotFather`.
1. Escribir el mensaje/orden `/newbot`:
    * Bot name: `@Bot_dvarrui`
    * Username: `dvarrui_bot`


Obtendremos los siguientes datos:
```
Bot URL: t.me/dvarrui_bot
TOKEN (HTTP API): ...
```

> Usar `/help` para consultar el listado de comandos del bot.
>
> Para una descripción completa del Bot API, consultar la página siguiente: https://core.telegram.org/bots/api

* Crear un grupo de Telegram y añade tu bot.

---

## 1.2 Crear un programa de Ruby

> Enlaces de interés:
> * [Create telegram bot in ruby](https://www.sitepoint.com/quickly-create-a-telegram-bot-in-ruby/)
> * [gem telegram-bot-ruby](https://github.com/atipugin/telegram-bot-ruby)

* `gem install telegram-bot-ruby`, instalar la gems de ruby que permite comunicarse con Telegram.
* Ejecutar este programa de ejemplo [bot-demo.rb](files/bot-demo.rb).

## 1.3 Personalizar el bot

Modificar el bot para personalizar los comandos que acepta.
* Añadir 2 nuevas órdenes del bot para que el programa Ruby ejecute comandos en el sistema operativo donde se está ejecutando y devuelva el resultado al Bot de Telegram.

## 1.4 Entrega

* Entregar el script del bot (`/usr/local/bin/botXXd`).
* URL vídeo Youtube donde se muestre el Bot en funcionamiento.

---
# 2. Systemd

> Enlaces de interés:
> * EN - [Use systemd to Start a Linux Service at Boot](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/)
> * EN - [Systemd System and Service Manager](https://www.freedesktop.org/wiki/Software/systemd/)

Vamos a crear un servicio para nuestro bot, de modo que se inicie siempre al arrancar el equipo y que podemos gestionarlo como el resto de servicios (usando el comando `systemctl`).

## 2.1 Crear un servicio

* Vamos a usar el script demonio, creado en el apartado anterior. Un demonio (daemon) es un programa que se ejecuta en segundo plano permanentemente para dar algún servicio.
* Copiar el script a `/usr/local/bin/botXXd` y hacerlo ejecutable.
* Crear el directorio `/etc/botXX`. Lo usaremos para poner configuraciones de nuestro servicio.
* Crear el fichero `/etc/botXX/token`. Lo usaremos para guardar dentro el TOKEN de nuestro bot de Telegram.
* Modificar el contenido del script para cambiar la ruta del TOKEN:
```
token = %x[cat /etc/botXX/token].strip
```

> **Informacion**: Si invocamos al script desde el fichero `/home/nombre-alumno/.bashrc`, entonces nuestro programa se iniciará cuando el usuario nombre-alumno inicie sesión en el sistema. Pero como queremos que el programa se inicie al arrancar en sistema operativo... entonces seguimos con la práctica...

El init es el proceso que inicia todo el sistema y arranca los servicios. Cada sistema operativo puede tener distintos "init" como Systemd, SystemV, Upstart, Openrc, etc. Nuestro sistema operativo viene con Systemd, así vamos a configurar Systemd para gestionar nuestro servicio.

Cada servicio de Systemd se define en un fichero `Unit file`

* Crear el fichero `/lib/systemd/system/botXX.service` o `/usr/lib/systemd/system/botXX.service`:
```
[Unit]
Description=Servicio Bot del alumnoXX.

[Service]
Type=simple
ExecStart=/usr/bin/ruby /usr/local/bin/botXXd

[Install]
WantedBy=multi-user.target
```
> Así se define un servicio sencillo. La directiva ExecStart especifica el comando que se ejecutará para iniciar el servicio.

# 2.2 Iniciar y activar el servicio

* Iniciar sesión como superusuario.
* `systemctl status botXX`, comprobamos que el estado está parado.
* `systemctl start botXX`, para iniciar el servicio.
* `systemctl status botXX`, comprobamos que ahora sí está corriendo.
* `systemctl enable botXX`, para activar el servicio. De este modo se iniciará automáticamente al arrancar el sistema.
* Reiniciamos la MV.
* `systemctl status botXX`, para confirmar que el servicio se ha iniciado automáticamente.

## 2.3 Parar el proceso

Lo más cómodo para parar el servicio es `systemctl stop botXX`, pero también podemos parar el servicio botXX "matando" el programa botXXd. Veamos como:
* `ps -ef | grep botXXd`, localizar el identificador del proceso(PID) botXXd.
* `kill -9 PID`, emitimos una señal/orden (-9) al proceso (PID). La señal -9 ordena al proceso que "muera" (que se cierre).

## 2.4 Entrega

* Fichero de configuración del servicio:
    * `/lib/systemd/system/botXX.service` o
    * `/usr/lib/systemd/system/botXX.service`
* Capturas de pantalla donde se muestre que podemos iniciar el parar el servicio botXX con el comando systemctl.

---
# 3. Programar tareas

Ya sabemos como configurar nuestro programa demonio (botXXd) para que se comporte como un servicio, y además para que se inicie de forma automática al iniciar la máquina.

Supongamos que nos preocupa que nuestro programa (botXXd) se pueda detener de forma inesperada y no nos demos cuenta. Vamos a crear otro script que se va a encargar de iniciar el demonio si se para. Este script va a controlar el demonio cada 5 minutos.

## 3.1 Controlador

Creamos un nuevo script `/usr/local/bin/botXXcontroller`. Este script hará lo siguiente:
* Consulta si el demonio está en ejecución. Se podría hacer con:
    * `ps -ef |grep botXXd` o
    * `systemctl status botXX`
    * etc.
* Si no está en ejecución, entonces se inicia el servicio (`systemctl ...`).
* Se termina el script.

## 3.2 Tareas programadas

> Enlaces de interés:
> * [Cómo utilizar crontab para programar tareas](https://www.redeszone.net/2017/01/09/utilizar-cron-crontab-linux-programar-tareas/)

Vamos a programar el script `botXXcontroller` para que se ejecute cada 5 minutos. Usaremos la herramienta crontab.

* `crontab -l`, vemos que no hay ninguna configuración creada.
* `crontab -e`, se nos abre un editor.
* Pulsar `i`(insert) para activar el modo de empezar a escribir.

> Información para configurar crontab:
> * m: minuto
> * h: hora
> * mon: mes
> * dow: día del mes
> * dom: día de la semana (0=domingo, 1=lunes, etc)
> * Comando a ejecutar

* Escribir algo parecido a lo siguiente:
```
*/5 * * * *   /usr/local/bin/botXXcontroller
```

Esta configuración programa una ejecución del script cada 5 minutos.

* Escribir: `ESC`, `:`, `wq`, cuando hayamos acabado. Así grabamos(w=write) y salimos (q=quiet) del editor de crontab.
* `crontab -l`, para consultar la tarea programada.
* Paramos el servicio.
* Esperamos 5 minutos y ahora debe haberse iniciado de forma automática (con crontab).

**Preguntas**:
* _¿Se podría instalar Ruby en Windows?_ RubyInstaller
* _¿Se podría pasar el demonio (botXXd) a Windows?_ Cambiando los comandos de GNU/Linux dentro del script por los comandos de Windows.
* _¿Sabrías crear una tarea programa en Windows?_ Panel de control -> tareas programadas.
