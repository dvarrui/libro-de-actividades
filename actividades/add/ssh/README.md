
#0. Acceso remoto SSH

##0.1 Introducción

![secret](./image/secret.jpeg)

* Leer documentación proporcionada por el profesor.
* Atender a la explicación del profesor.
* Enlaces de interés: [Securizar un servidor SSH](http://rm-rf.es/como-securizar-un-servidor-ssh/)
* Vamos a necesitar las siguientes 3 MVs:
    1. Un servidor GNU/Linux OpenSUSE, con IP estática (172.18.XX.53).
    1. Un cliente GNU/Linux OpenSUSE, con IP estática (172.18.XX.54).
    1. Un cliente Windows7, con IP estática (172.18.XX.13).

> Es aconsejable poner el tamaño del disco de OpenSUSE en 10GB 

##0.2 Configuración de red
Para configurar la red en OpenSUSE lo más cómodo es usar el interfaz gráfico `yast`.
* Vamos a `yast -> Ajustes de red`
* En la pestaña `Vista resumen` pondremos:
    * IP estática
    * Máscara de red
    * Nombre de host
    * Pulsamos en `siguiente`.
* En la pestaña `Nombres de hosts` pondremos:
    * Nombre de host
    * Nombre de dominio
    * Asignar nombre a bucle local. Esto modifica el fichero `/etc/hosts` por nosotros.
    * Servidor DNS
* En la pestaña `Encaminamiento` pondremos:
    * La IP de l apuerta de enlace
    * Elegimos el dispositivo de red asociado a la puerta de enlace.    

#0.3 Entrega
* Añadir informe al repositorio git.
* Incluir capturas de pantalla de cada apartado para confirmar que está funcionando.
* Además se mostrará al profesor la práctica funcionando en clase y se responderá a las preguntas que pudieran hacerse en dicho instante.

#1. Preparativos

##1.1 Servidor SSH
* Configurar el servidor GNU/Linux con siguientes valores:
    * Nombre de usuario: nombre-del-alumno
    * Clave del usuario root: DNI-del-alumno
    * Nombre de equipo: ssh-server
    * Nombre de dominio: segundo-apellido-del-alumno
* Añadir en /etc/hosts los equipos ssh-client1 y ssh-client2-XX (Donde XX es el puesto del alumno).
* Para comprobar los cambios ejecutamos varios comandos. Capturar imagen:
```
ip a               (Comprobar IP y máscara)
route -n           (Comprobar puerta de enlace)
host www.google.es (Comprobar el servidor DNS)
lsblk              (Comprobar particiones)
blkid              (Comprobar UUID de la instalación)
``` 
* Crear los siguientes usuarios en ssh-server:
    * primer-apellido-del-alumno1
    * primer-apellido-del-alumno2
    * primer-apellido-del-alumno3
    * primer-apellido-del-alumno4

##1.2 Clientes GNU/Linux
* Configurar el cliente1 GNU/Linux con los siguientes valores:
    * Nombre de usuario: nombre-del-alumno
    * Clave del usuario root: DNI-del-alumno
    * Nombre de equipo: ssh-client1
    * Nombre de dominio: segundo-apellido-del-alumno
* Añadir en /etc/hosts el equipo ssh-server, y ssh-client2-XX.
* Comprobar haciendo ping a ambos equipos. 

##1.3 Cliente Windows
* Instalar software cliente SSH en Windows (PuTTY)
* Configurar el cliente2 Windows con los siguientes valores:
    * Nombre de usuario: nombre-del-alumno
    * Clave del usuario administrador: DNI-del-alumno
    * Nombre de equipo: ssh-client2-XX
* Añadir en `C:\Windows\System32\drivers\etc\hosts` el equipo ssh-server y ssh-client1.
* Comprobar haciendo ping a ambos equipos. 

#2 Instalación del servicio SSH

* Instalar el servicio SSH en la máquina ssh-server
    * Desde la herramienta `yast -> Instalar Software`
    * Desde terminal `zypper search ssh` muestra los paquetes instalados o no con nombre ssh*.
    * Desde terminal `zypper install openssh`, instala el paquete OpenSSH.

> * Los ficheros de configuración del servicio se guardan en /etc/ssh.
> * [Vídeo: Instalación y configuración de un servidor SSH en Windows Server](http://www.youtube.com/embed/QlqokjKt69I)

##2.1 Comprobación

* Desde el propio **ssh-server**, verificar que el servicio está en ejecución.
```
    systemctl status sshd  (Esta es la forma de comprobarlo en *systemd*) 
    ps -ef|grep sshd       (Esta es la forma de comprobarlo mirando los procesos del sistema)
```
![servicio-sshd](./opensuse/servicio-sshd.png)

![servicio-sshd-yast](./opensuse/servicio-sshd-yast.png)

* Para poner el servicio enable: `systemctl enable sshd`, si no lo estuviera.
* `netstat -ntap`: Comprobar que el servicio está escuchando por el puerto 22

![netstat](./opensuse/sshd-netstat.png)

##2.2 Primera conexión SSH desde cliente
* Comprobamos la conectividad con el servidor desde el cliente con `ping ssh-server`. 
* Desde el cliente comprobamos que el servicio SSH es visible con `nmap ssh-server`.
Debe mostrarnos que el puerto 22 está abierto. Esto es, debe aparecer una línea como  "22/tcp open  ssh".

![zypper-nmap](./opensuse/zypper-nmap.png)

![ssh-nmap](./opensuse/ssh-nmap.png)

* Si esto falla debemos comprobar en el servidor la configuración del cortafuegos:

![firewall1-yast](./opensuse/firewall1-yast.png)

![firewall2-yast](./opensuse/firewall2-yast.png)

* Vamos a comprobar el funcionamiento de la conexión SSH desde cada cliente usando el usuario *1er-apellido-alumno1*. 
* Desde el **ssh-client1** nos conectamos mediante `ssh 1er-apellido-alumno11@ssh-server`. Capturar imagen del intercambio de claves que se produce en el primer proceso de conexión SSH.

![ssh-conexion1](./opensuse/ssh-conexion1.png)

* Si nos volvemos a conectar tendremos:

![ssh-conexion2](./opensuse/ssh-conexion2.png)

* Comprobar contenido del fichero `$HOME/.ssh/known_hosts` en el equipo ssh-client1. OJO si el prompt
pone *ssh-server* están el el servidor, y si pone *ssh-client1* están el el cliente1.

![ssh-files](./opensuse/ssh-files.png)

* ¿Te suena la clave que aparece? Es la clave de identificación de la máquina ssh-server.
* Una vez llegados a este punto deben de funcionar correctamente las conexiones SSH desde los clientes. Seguimos.

##2.3 ¿Y si cambiamos las claves del servidor?
* Confirmar que existen los siguientes ficheros en `/etc/ssh`, 
Los ficheros `ssh_host*key` y `ssh_host*key.pub`, son ficheros de clave pública/privada
que identifican a nuestro servidor frente a nuestros clientes:

```
-rw-r--r-- 1 root root 136156 ago 24  2012 moduli
-rw-r--r-- 1 root root   1667 sep 12  2012 ssh_config
-rw-r--r-- 1 root root   2487 dic 27  2013 sshd_config
-rw------- 1 root root    672 dic 27  2013 ssh_host_dsa_key
-rw-r--r-- 1 root root    601 dic 27  2013 ssh_host_dsa_key.pub
-rw------- 1 root root    227 dic 27  2013 ssh_host_ecdsa_key
-rw-r--r-- 1 root root    173 dic 27  2013 ssh_host_ecdsa_key.pub
-rw------- 1 root root    528 dic 27  2013 ssh_host_key
-rw-r--r-- 1 root root    333 dic 27  2013 ssh_host_key.pub
-rw------- 1 root root   1675 dic 27  2013 ssh_host_rsa_key
-rw-r--r-- 1 root root    393 dic 27  2013 ssh_host_rsa_key.pub
```

![sshd-files](./opensuse/sshd-files.png)

* Modificar el fichero de configuración SSH (`/etc/ssh/sshd_config`) para dejar una única línea: 
`HostKey /etc/ssh/ssh_host_rsa_key`. Comentar el resto de líneas con configuración HostKey. 
Este parámetro define los ficheros de clave publica/privada que van a identificar a nuestro
servidor. Con este cambio decimos que sólo vamos a usar las claves del tipo RSA.
* Generar nuevas claves de equipo en **ssh-server**. Como usuario root ejecutamos: 
`ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key`. Estamos cambiando o volviendo a 
generar nuevas claves públicas/privadas para la identificación de nuestro servidor.
* Reiniciar el servicio SSH: `systemctl restart sshd`.
* Comprobar que el servicio está en ejecución correctamente: `systemctl status sshd`
* Comprobar qué sucede al volver a conectarnos desde los dos clientes, usando los 
usuarios 1er-apellido-alumno2 y 1er-apellido-alumno1. ¿Qué sucede?

> **Enlaces de inteŕes**
>
> Cliente SSH para Windows:
>
> * [Learning Linux : Lesson 14 Using Public key Authentication with PuTTY ](https://youtu.be/xe599gD4b5E?list=PL3E447E094F7E3EBB)
>
> Servicio SSH en Windows:
>
> * [Tutorial FreeSShd](http://www.redeszone.net/windows/freesshd-para-windows-instalacion-y-manual-de-configuracion-de-freesshd-para-windows-servidor-ssh-y-sftp/)
> * [Configuración de OpenSSH para Windows7 con SSH Cygwin +Putty](http://www.taringa.net/post/linux/15562479/Configuracion-de-OpenSSH-en-Windows-7-SSH-Cygwin-Putty.html)
> * [Installing Cygwin and Starting the SSH Daemon](http://docs.oracle.com/cd/E24628_01/install.121/e22624/preinstall_req_cygwin_ssh.htm#EMBSC150)
> * En Windows, la información relativa a los know_hosts, se almacena en el registro. En la ruta CURRENT_USER/Software/SimonTaham/Putty/SSHHostKeys. Para acceder al registro ejecutamos el comando "regedit".
>

#3. Personalización del prompt Bash

> [INFO] Esto sólo para servidores GNU/Linux o BSD.
>
> Personalizar Bash según la documentación, para cambiar el color cuando tenemos activa una sesión SSH.
>

* Por ejemplo, podemos añadir las siguientes líneas al fichero de configuración 
del usuario1 en la máquina servidor (Fichero /home/1er-apellido-alumno1/.bashrc)

```
#Cambia el prompt al conectarse vía SSH

if [ -n "$SSH_CLIENT" ]; then
  PS1="AccesoRemoto_\e[32;40m\u@\h: \w\a\$"
else
  PS1="\[$(ppwd)\]\u@\h:\w>"
fi
```
* Además, crear el fichero `/home/1er-apellido-alumno1/.alias` con el siguiente contenido:
```
alias c='clear'
alias g='geany'
alias p='ping'
alias v='vdir -cFl
alias s='ssh'
```
* Comprobar funcionamiento de la conexión SSH desde cada cliente.

#4. Autenticación mediante claves públicas

![clave-publica](./image/ssh-clave-publica.jpeg)

El objetivo de este apartado es el de configurar SSH para poder acceder desde el cliente1,
usando el usuario4 sin poner password, pero usando claves pública/privada.

Vamos a configurar la autenticación mediante clave pública para acceder con 
nuestro usuario personal desde el equipo cliente al servidor con el 
usuario 1er-apellido-alumno4.

* Vamos a la máquina cliente1. 
* ¡OJO! No usar el usuario root.

Capturar imágenes de los siguientes pasos:
* Iniciamos sesión con nuestro usuario *nombre-alumno* de la máquina ssh-client1.
* Ejecutamos `ssh-keygen -t rsa` para generar un nuevo par de claves para el 
usuario en `/home/nuestro-usuario/.ssh/id_rsa` y `/home/nuestro-usuario/.ssh/id_rsa.pub`.
* Ahora vamos a copiar la clave pública (id_rsa.pub) del usuario (nombre-de-alumno)de la máquina cliente, 
al fichero "authorized_keys" del usuario *remoteuser4* en el servidor. Hay dos formas de hacerlo: 
    * Modo 1. Usando un comando específico para ello `ssh-copy-id remoteuser4@ssh-server`
    * Modo 2. Usando el programa de copia segura `scp`:
        * Comprobar que existe el directorio /home/remoteuser4/.ssh en el servidor.
        * Hacemos `scp .ssh/id_rsa.pub remoteuser4@ssh-server:.ssh/authorized_keys`.
* Comprobar que ahora podremos acceder remotamente, sin escribir el password desde el cliente1.
* Comprobar que al acceder desde cliente2, si nos pide el password.

#5. Uso de SSH como túnel para X

![tunel](./image/ssh-tunel.jpeg)

* Instalar en el servidor una aplicación de entorno gráfico (APP1) que no esté en los clientes. 
Por ejemplo Geany. Si estuviera en el cliente entonces buscar otra aplicación o desinstalarla en el cliente.
* Modificar servidor SSH para permitir la ejecución de aplicaciones gráficas, desde los clientes. 
Consultar fichero de configuración `/etc/ssh/sshd_config` (Opción `X11Forwarding yes`)
* Comprobar funcionamiento de APP1 desde cliente1.
Por ejemplo, con el comando `ssh -X remoteuser1@ssh-server`, podemos conectarnos de forma 
remota al servidor, y ahora ejecutamos APP1 de forma remota.
> **OJO**: El parámetro es `-X` en mayúsculas, no minúsculas

#6. Aplicaciones Windows nativas

Podemos tener aplicaciones Windows nativas instaladas en ssh-server mediante el emulador WINE.
* Instalar emulador Wine en el ssh-server.
* Ahora podríamos instalar alguna aplicación (APP2) de Windows en el servidor SSH 
usando el emulador Wine. O podemos usar el Block de Notas que viene con Wine: wine notepad.
* Comprobar el funcionamiento de APP2 en ssh-server.
* Comprobar funcionamiento de APP2, accediendo desde ssh-client1.

> En este caso hemos conseguido implementar una solución similar a RemoteApps usando SSH.

#7. Restricciones de uso
Vamos a modificar los usuarios del servidor SSH para añadir algunas restricciones de uso del servicio.

##7.1 Sin restricción (tipo 1)
Usuario sin restricciones:

* El usuario 1er-apellido-alumno1, podrá conectarse vía SSH sin restricciones.
* En principio no es necesario tocar nada.

##7.2 Restricción total (tipo 2)
Vamos a crear una restricción de uso del SSH para un usuario:

* En el servidor tenemos el usuario remoteuser2. Desde local en el servidor podemos usar 
sin problemas el usuario.
* Vamos a modificar SSH de modo que al usar el usuario por ssh desde los clientes tendremos permiso denegado.

Capturar imagen de los siguientes pasos:
* Consultar/modificar fichero de configuración del servidor SSH (`/etc/ssh/sshd_config`) para 
restringir el acceso a determinados usuarios. Consultar las opciones `AllowUsers`, `DenyUsers`. 
Más información en: `man sshd_config` y en el Anexo de este enunciado.
* Comprobarlo la restricción al acceder desde los clientes.

##7.3 Restricción en las máquinas (tipo 3)
Vamos a crear una restricción para que sólo las máquinas clientes con las IP's 
autorizadas puedan acceder a nuestro servidor.

* Consultar los ficheros de configuración /etc/hosts.allow y /etc/hosts.deny
```
# /etc/hosts.allow
# Permitir acceso a las IP's conocidas
sshd : 172.19.255.53/255.255.255.0 : ALLOW
```

```
# /etc/hosts.deny
# Denegar acceso al servicio SSH a todas las IP's 
sshd : ALL EXCEPT LOCAL
```

* Modificar configuración en el servidor para denegar accesos de todas las máquinas, excepto nuestros clientes.
* Comprobar su funcionamiento.

##7.4 Restricción sobre aplicaciones (tipo 4)
Vamos a crear una restricción de permisos sobre determinadas aplicaciones.

* Usaremos el usuario remoteuser4
* Crear grupo remoteapps
* Incluir al usuario en el grupo.
* Localizar el programa APP1. Posiblemente tenga permisos 755.
* Poner al programa APP1 el grupo propietario a remoteapps
* Poner los permisos del ejecutable de APP1 a 750. Para impedir que los que no pertenezcan al grupo puedan ejecutar el programa.
* Comprobamos el funcionamiento en el servidor.
* Comprobamos el funcionamiento desde el cliente.


# ANEXO 1: Configuración de seguridad en OpenSSH

OpenSSH iconfig file: /etc/ssh/sshd_config

##OpenSSH locking parameters
* For locking down which users may or may not access the server you will want to look into one, or more, of the following directives:
User/Group Based Access

AllowGroups
* This keyword can be followed by a list of group name patterns, separated by spaces.If specified, login is allowed only for users whose primary group or supplementary group list matches one of the patterns.`*' and `?' can be used as wildcards in the patterns.Only group names are valid; a numerical group ID is not recognized.By default, login is allowed for all groups.

AllowUsers
* This keyword can be followed by a list of user name patterns, separated by spaces.If specified, login is allowed only for user names that match one of the patterns.`*' and `?' can be used as wildcards in the patterns.Only user names are valid; a numerical user ID is not recognized.By default, login is allowed for all users.If the pattern takes the form USER@HOST then USER and HOST are separately checked, restricting logins to particular users from particular hosts.

DenyGroups
* This keyword can be followed by a list of group name patterns, separated by spaces.Login is disallowed for users whose primary group or supplementary group list matches one of the patterns.
    `*' and `?' can be used as wildcards in the patterns.Only group names are valid; a numerical group ID is not recognized. By default, login is allowed for all groups.

DenyUsers
* This keyword can be followed by a list of user name patterns, separated by spaces.Login is disallowed for user names that match one of the patterns.`*' and `?' can be used as wildcards in the patterns.Only user names are valid; a numerical user ID is not recognized.By default, login is allowed for all users. If the pattern takes the form USER@HOST then USER and HOST are separately checked, restricting logins to particular users from particular hosts.

##Example configuring locking
The first thing to do is backup the original configuration file:

    cp /etc/ssh/sshd_config /etc/ssh/sshd_config{,.`date +%s`}

We will now need to edit the configuration file with your favorite editor (vi/vim/ed/joe/nano/pico/emacs.)

An example of only allowing two specific users, admin and bob, to login to the server will be:

/etc/ssh/sshd_config:

    AllowUsers admin bob

Ifyou would like to more easily control this for the future then you can create a Group on the server that will be allowed to login to the server, adding individual users as needed (replace username with the actual user):

shell:

    groupadd –r sshusers
    usermod –a –G sshusers username

With this we will no longer be using AllowUsers but AllowGroups

/etc/ssh/sshd_config:

    AllowGroups sshusers

The alternatives to these directives are DenyGroups and DenyUsers which perform the exact opposite of the aforementioned AllowGroups and AllowUsers. When complete you will want to make sure that sshd will read in the new configuration without breaking.

    /usr/sbin/sshd –t
    echo $?

We will want to see a 0 following the ``echo $?’’ command.Otherwise we should also see an error stating what the erroneous data is:

    sshd_config: line 112: Bad configuration option: allowuser
    sshd_config: terminating, 1 bad configuration options

After verification we will simply need to restart sshd.This can be performed via many different methods, for which we will assume a sysv-compatible system:

    /etc/init.d/sshd restart

> Make sure to not disconnect your ssh session but create a new one as a ‘just incase’.
> Verify that you can perform any required actions with this user(eg: su into root if you are not allowing root logins.)

#ANEXO 2: COnfiguración de seguridad en máquinas GNU/Linux
Editing hosts.allow and hosts.deny Files

To restrict access to your Unix or Linux machine, you must modify the /etc/hosts.allow and /etc/host.deny files. These files are used by the tcpd (tcp wrapper) and sshd programs to decide whether or not to accept a connection coming in from another IP address. ITS recommends that to start with, you restrict access to only those network addresses you are certain should be allowed access. The following two example files allow connections from any address in the virginia.edu network domain, but no others.
/etc/hosts.allow

ITS recommends using the configuration shown in the following /etc/hosts.allow file, to permit connections to any services protected by the tcpd or sshd from only systems within the virginia.edu domain:

    #
    # hosts.allow This file describes the names of the hosts which are
    # allowed to use the local INET services, as decided
    # by the '/usr/sbin/tcpd' server.
    #
    # Only allow connections within the virginia.edu domain.
    ALL: .virginia.edu
    /etc/hosts.deny

Following is ITS's suggested /etc/hosts.deny file content. With this configuration, access to your machine from all hosts is denied, except for those specified in hosts.allow.

    #
    # hosts.deny This file describes the names of the hosts which are
    # *not* allowed to use the local INET services, as decided
    # by the '/usr/sbin/tcpd' server.
    #
    # deny all by default, only allowing hosts or domains listed in hosts.allow.
    ALL: ALL 


