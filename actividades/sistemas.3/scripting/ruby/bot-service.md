
```
Curso      : 201920
Requisitos : GNU/Linux, Ruby, Telegram.
```

---
# Servicio de comunicaciones con Telegram (bot-service)

Vamos a crear un servicio de Systemd que iniciará un Bot de Telegram.

## Entrega

* Script demonio `/usr/local/bin/botdaemon`.
* Fichero de confoiguración del servicio `/lib/systemd/system/botXX.service`.
* URL vídeo Youtube donde se muestre el Bot en funcionamiento.

---
# 1. Create Telegram bot with ruby

Steps:
1. Create bot with BotFather
2. Add ruby program to our bot.

# 1.1 Create bot with BotFather

1. Open Telegram
1. Search user @BotFather
1. `/newbot`
    * Bot name: `@Bot_dvarrui`
    * Username: `dvarrui_bot`

You will get thos information:
```
Bot URL: t.me/dvarrui_bot
TOKEN (HTTP API): ...
```

> Use `/help` for a list of commands.
>
> For a description of the Bot API, see this page: https://core.telegram.org/bots/api

* Create a Telegram group and add your bot.

---

## 1.2 Add Ruby program

> Related links:
> * [Create telegram bot in ruby](https://www.sitepoint.com/quickly-create-a-telegram-bot-in-ruby/)
> * [gem telegram-bot-ruby](https://github.com/atipugin/telegram-bot-ruby)

* `gem install telegram-bot-ruby`, install ruby gem.
* Run this program [bot-demo.rb](files/bot-demo.rb)
d

## 1.3 Personalizar el bot

Modificar el bot para personalizar los comandos que acepta.

---
# 2. Systemd

> Enlaces de interés:
> * EN - [Use systemd to Start a Linux Service at Boot](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/)
> * EN - [Systemd System and Service Manager](https://www.freedesktop.org/wiki/Software/systemd/)

## 2.1 Crear un servicio (S1)

* Vamos a usar el script demonio, creado en el apartado anterior.
* Copiar el script a `/usr/local/bin/botdaemon` y hacerlo ejecutable.

> Si invocamos al script desde el fichero `/home/nombre-alumno/.bashrc`, entonces nuestro programa se iniciará cuando el usuario nombre-alumno inicie sesión en el sistema. Pero como queremos que el programa se inicie al arrancar en sistema operativo... entonces seguimos con la práctica...

* Crear el `Unit file` para definir el servicio de systemd. Creamos el fichero `/lib/systemd/system/botXX.service`:
```
[Unit]
Description=Servicio Bot de alumnoXX.

[Service]
Type=simple
ExecStart=/bin/bash /usr/local/bin/botdaemon

[Install]
WantedBy=multi-user.target
```
> Así se define un servicio sencillo. La directiva ExecStart especifica el comando que se ejecutará para iniciar el servicio.

---

# 2.2 Iniciar y activar el servicio (S1)

* `sudo systemctl start botXX`, para iniciar el servicio.
* `sudo systemctl status botXX`, para comprobar el estado actual del servicio.
* Otros comandos:
    * `sudo systemctl stop botXX`, para parar el servicio.
    * `sudo systemctl restart botXX`, para reiniciar el servicio.
* `sudo systemctl enable botXX`, para activar el servicio y asegurarnos de que se iniciará al arrancar el sistema.
