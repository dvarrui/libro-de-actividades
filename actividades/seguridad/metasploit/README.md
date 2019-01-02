
# Metasploit


# 1. Instalación

## 1.1 La base de datos

* Instalar `postgresql` y `postgresql-server`
* `systemctml start postgresql`

## 1.2 La aplicación

* Descargar paquete rpm de https://rpm.metasploit.com.
* `sudo rpm -i metasploit-framework-XXX.x86_64.rpm`
* `msfdb init`, crear el schema de la BBDD para MS.
* `msfconsole`, iniciar MS y comprobamos la BBDD.
```
david@camaleon> msfconsole

 ** Welcome to Metasploit Framework Initial Setup **
    Please answer a few questions to get started.


Would you like to use and setup a new database (recommended)?
Please answer yes or no.
Would you like to use and setup a new database (recommended)? yes
Creating database at /home/david/.msf4/db
Starting database at /home/david/.msf4/db...success
Creating database users
Creating initial database schema

 ** Metasploit Framework Initial Setup Complete **


 _                                                    _
/ \    /\         __                         _   __  /_/ __
| |\  / | _____   \ \           ___   _____ | | /  \ _   \ \
| | \/| | | ___\ |- -|   /\    / __\ | -__/ | || | || | |- -|
|_|   | | | _|__  | |_  / -\ __\ \   | |    | | \__/| |  | |_
      |/  |____/  \___\/ /\ \\___/   \/     \__|    |_\  \___\


       =[ metasploit v4.17.34-dev-                        ]
+ -- --=[ 1845 exploits - 1045 auxiliary - 320 post       ]
+ -- --=[ 541 payloads - 44 encoders - 10 nops            ]
+ -- --=[ Free Metasploit Pro trial: http://r-7.co/trymsp ]

msf > db_status
[*] postgresql connected to msf
msf > exit
```

---

# 2. TEORÍA: Comandos de Metasploit

* Iniciamos:
    * `sudo systemctl start postgresql`
    * `msfconsole`
* Comando `help`, ver los comandos de Metasploit
