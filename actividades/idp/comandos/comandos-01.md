
#Comandos 01

## Máquina Virtual
Partimos que ya tenemos creada la MV Debian 7 con:
* Un usuario que se llama como nombre alumno.
* Nombre de la máquina es 1er apellido del alumno.
* Nombre del dominio el el 2º apellido del alumno.
* La clave de root debe ser dni del alumno con letra en minúsculas.

##Acceso remoto
Vamos a configurar la máquina para permitir el acceso remoto al profesor.
* En VirtualBox cambiamos la red de NAT a modo puente (Bridge).
* En SO pondremos la configuración de red estática con los siguientes datos:
    * IP: 172.19.XX.30 (XX será el identificador del puesto de cada uno)
    * Máscara: 255.255.0.0
    * Enlace (gateway): 172.19.0.1
    * DNS: 80.58.61.250
* Comprobamos que la configuración está bien con:
```
ifconfig
ip a
nslookup www.google.es
ping www.google.es
```
* Instalar el software de acceso remoto SSH `openssh-server`.

> **Instalación de SSH por comandos**
>
> * `su`: Pasa a superusuario
> * `apt-get update`: Actualiza los repositorios
> * `apt-get install openssh-server`: Instala el paquete
>

> **Configuración SSH**
>
> Asegurarse de que el fichero de configuración de SSH `/etc/ssh/sshd_config`,
tiene la opción `PermitRootLogin yes`
>

##Tarea
Realizar las siguientes acciones en la MV usando los comandos:
* Crear la siguiente estructura de directorios en la carpeta Documentos de nuestro home:
```
curso1516/fuw
curso1516/idp
curso1516/lnd
curso1516/lnt
```
* Dentro de cada carpeta crear documento `leeme.txt`
* Dentro de dicho documento escribir el nombre y apellidos en minúsculas, sin tildes ni eñes.
