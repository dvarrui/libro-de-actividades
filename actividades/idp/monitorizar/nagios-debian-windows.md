*(Actividad realizada en los cursos: 201314, 201415, 201516. Mejorada para el curso 201516)*

#1. Preparativos

Para esta actividad vamos a necesitar 3 MV's (Consultar la [configuración](../../global/configuracion-aula109.md) ).
* Monitorizador
    * SO Debian 8 - GNU/Linux
    * IP estática 172.19.XX.41
    * Incluir en el `/etc/hosts` todas las máquinas de la práctica.
* Cliente1:
    * SO Debian 8 - GNU/Linux para ser monitorizado.
    * IP estática 172.19.XX.42
    * Incluir en el `/etc/hosts` todas las máquinas de la práctica.
* Cliente2:
    * SO Windows 7 para ser monitorizado.
    * IP estática 172.19.XX.11
    * Incluir en el fichero `hosts` todas las máquinas de la práctica.

Enlaces de interés:
* Recomendado - [Instalación y configuración del servidor Nagios, y de los agentes para Linux y Windows](http://itfreekzone.blogspot.com.es/2013/03/nagios-monitoreo-remoto-de-dispositivos.html)
* [Instalar y configurar nagios usando check_nt](www.tropiezosenlared.com/instalar-y-configurar-nagios-para-la-monitorizacion-de-equipos-en-la-red/) 
* [Configuring nagios to monitor remote host using nrpe](https://kura.io/2010/03/21/configuring-nagios-to-monitor-remote-load-disk-using-nrpe/).  


#2. Instalación del servidor Nagios

* Leer los documentos proporcionados por el profesor.
* Instalar el monitorizador Nagios3, su documentación y el plugin NRPE.
    * En Debian se usa `apt-get ...`.
    * Comprobación: `dpkg -l nagios*`
* Durante la instalación se pedirá la clave del usuario `nagiosadmin` (Administrador Nagios). 
Además se instalará un servidor web.

![nagios3-password.png](./images/nagios3-password.png)

* ¿Está todo instalado y en ejecución?
    * Comprobación `netstat -ntap`.
    * Comprobación `nmap localhost`.
* Abrimos un navegador y ponemos el URL `http://localhost/nagios3`.
    * Ponemos usuario/clave (nagiosadmin/clavesecreta), y ya podemos 
    interactuar con el programa de monitorización.
    * Si vamos a las opciones del menú izquierdo *"Hosts"* y *"Services"*, 
    vemos que ya estamos monitorizando nuestro propio equipo *"localhost"*.

#3. Configuración del servidor Nagios

Ahora vamos a plantear como objetivo el configurar Nagios para monitorizar:
* Routers:
    * Si están activos el router Bender (172.19.0.1) y el router externo (192.168.1.1).
* Servidores:
    * Si el host LEELA (172.20.1.2) tiene activos los servicios HTTP y SSH.
* Clientes:
    * Si están activos los equipos: cliente 1, y el cliente 2.

##3.1 Directorio de configuraciones personales

* Creamos el directorio `/etc/nagios3/nombre-del-alumno.d`, para 
guardar nuestras configuraciones.
* Modificamos fichero de configuración principla `/etc/nagios3/nagios.cfg`, 
y añadiremos la siguiente definición: `cfg_dir=/etc/nagios3/nombre-del-alumno.d`,
para que Nagios tenga en cuenta también estos ficheros al iniciarse.

##3.2 Grupos de hosts

Cuando se tienen muchos *hosts* es más cómodo agruparlos. Estos son los `hostgroup`.

* Vamos crear `hostgroups`:
    * Creamos el fichero `/etc/nagios3/nombre-del-alumno.d/grupos.cfg`.
    * Dentro definimos 3 grupos de hosts: `routers`, `servidores` y `clientes`.
    * Veamos un ejemplo:
    
```
define hostgroup {
hostgroup_name clients
alias Equipos clientes
members localhost
}

define hostgroup {
hostgroup_name servers
alias Servidores del departamento
members fry
}
```

##3.3 Grupo de routers

* Crear el fichero `/etc/nagios3/nombre-del-alumno/grupo-routers.cfg` para
incluir las definiciones de las máquinas de tipo router.
* Veamos un ejemplo:
```
    #Define FRY router
    define host{
    host_name fry
    alias Servidor fry
    address 172.20.1.1
    check_command check-host-alive
    check_interval 5
    retry_interval 1
    max_check_attempts 1
    check_period 24x7
    hostgroups servidores, http-servers, ssh-servers
    icon_image cook/server.png
    statusmap_image cook/server.png
    }
```
> Fijarse en todos los parámetros y preguntar las dudas.
> host_name, alias, address, hostgroups, icon_image, etc.
* Reiniciamos Nagios (Pista `service ...`)
    * Comprobación `netstat -ntap |grep nagios`.
* Consultar la lista de hosts monitorizados por Nagios.

##3.4 Grupo de servidores

* Crear el fichero `/etc/nagios3/nombre-del-alumno/grupo-servidores.cfg` para
incluir las definiciones de las máquinas de tipo servidor.
* Reiniciamos Nagios (Pista `service ...`)
    * Comprobación `netstat -ntap |grep nagios`.
* Consultar la lista de hosts monitorizados por Nagios.

##3.5 Grupo de clientes

* Crear el fichero `/etc/nagios3/nombre-del-alumno/grupo-clientes.cfg` para
incluir las definiciones de las máquinas de tipo cliente.
* Reiniciamos Nagios (Pista `service ...`)
    * Comprobación `netstat -ntap |grep nagios`.
* Consultar la lista de hosts monitorizados por Nagios.


```
#Define leela server
define host{
host_name leela
alias Servidor LEELA
address 192.168.1.3
check_command check-host-alive
check_interval 5
retry_interval 1
max_check_attempts 1
check_period 24x7
process_perf_data 0
retain_nonstatus_information 0
hostgroups servers, http-servers, ssh-servers
contact_groups admins
notification_interval 30
notification_period 24x7
notification_options d,u,r
icon_image cook/server.png
statusmap_image cook/server.png
parents fry
}

    #Define router
    define host{
    host_name router
    alias Router1
    address 192.168.1.1
    check_command check-host-alive
    check_interval 5
    retry_interval 1
    max_check_attempts 1
    check_period 24x7
    contact_groups admins
    icon_image cook/server.png
    statusmap_image cook/server.png
    parents fry
    }
```


##3.4 Resto de hosts

    Ahora hay que añadir configuración para el resto de los equipos clientes (/etc/nagios3/mydevices.d/clients.cfg). Veamos un ejemplo:

define host{
use generic-host
host_name client1-windows
alias client1-windows
address 172.16.108.250
hostgroups clients
}

define host{
use generic-host
host_name client2-debian
alias client2-debian
address 172.16.108.150
hostgroups clients
}

A continuación vemos una imagen donde se muestran los hosts que estamos monitorizando. El verde significa que está OK, y el rojo que el equipo presenta algún problema y requiere atención.
hosts

    Las imágenes PNG están en /usr/share/nagios/htdocs/images/logos/cook. Poner a cada host una imagen que lo represente.

Además podemos tener una visión completa de la red en la opción "map".
map

    Consultar la lista de hosts y el mapa de Nagios.


4. Monitorizar más información

Debemos instalar una utilidad llamada "Agente Nagios" en los clientes para poder monitorizar desde el servidor más información ( Consumo CPU, consumo de memoria, consumo de disco, etc. )

Aquí vemos un ejemplo del estado de los servicios monitorizados, en el host "localhost". Con la instalación de los "agentes", podremos tener esta información desde los clientes.
servicios


4.1 Agente Nagios en Windows

MODO 1:

    Descargar el programa Agente Windows (NSCLient++) desde
        http://nsclient.org/nscp/downloads
        http://www.nagios.org/download/addons.

nsclient


    Activaremos las opciones "common check plugins", "nsclient server" y "NRPE server".
    Si nos un fichero de instalación MSI, al ejecutarlo nos hará la instalación del programa con las opciones por defecto sin preguntarnos.
    Para iniciar el servicio "Agente Nagios" en el cliente hacemos "net start nsclient" y para pararlo "net stop nsclient".


Configuración de los servicios del host Windows en Nagios Master.

    Consultar documentación http://nagios.sourceforge.net/docs/3_0/monitoring-windows.html.
    Veamos un ejemplo. En al monitorizador Nagios podemos crear el fichero /etc/nagios3/mydevices.d/servicios-windows.cfg, y añadir las siguientes líneas:


# Define a services

define service{
use generic-service
host_name client1-windows
service_description Disk Space
check_command check_nt!USEDDISKSPACE!-l c -w 80 -c 90
}

define service{
use generic-service
host_name client1-windows
service_description Mem Use
check_command check_nt!MEMUSE!-w 80 -c 90
}


define service{
use generic-service
host_name client1-windows
service_description Proc State Explorer
check_command check_nt!PROCSTATE!-d SHOWALL -l Explorer.exe
}

define service{
use generic-service
host_name client1-windows
service_description NSClient++ Version
check_command check_nt!CLIENTVERSION
}

define service{
use generic-service
host_name client1-windows
service_description Uptime
check_command check_nt!UPTIME
}

    Consultar los servicios monitorizados por Nagios

4.2 Monitorizando cliente GNU/Linux
Enlaces de interés:

    https://viewsby.wordpress.com/2013/02/14/install-nagios-nrpe-client-and-plugins-in-ubuntudebian/
    http://www.nettix.com.pe/documentacion/administracion/114-instalacion-de-nagios-como-cliente-en-windows-y-linux
    http://nagios.sourceforge.net/docs/3_0/monitoring-linux.html.

En el cliente:

    Debemos instalar el agente nagios en la máquina cliente: "apt-get install nagios-nrpe-server nagios-plugins-basic"
    Editar el fichero "/etc/nagios/nrpe.cfg" en el cliente y modificar la línea: "allowed_hosts=127.0.0.1,ip-del-servidor-nagios"
    Reiniciar el servicio en el cliente: "service nagios-nrpe-server stop",
    "service nagios-nrpe-server start"

En el servidor Nagios:

    Vamos a comprobar desde el servidor la conexión NRPE al cliente de la siguiente forma: "/usr/lib/nagios/plugins/check_nrpe -H ip-del-cliente"
    En el monitorizador Nagios podemos crear el fichero /etc/nagios3/mydevices.d/servicios-linux.cfg, y añadir las siguientes líneas:


# Define a service to check the disk space

define service{
use generic-service
host_name client2-debian
service_description Disk Space
check_command check_nrpe_1arg!check_disk
}

define service{
use generic-service
host_name client2-debian
service_description Current Users
check_command check_nrpe_1arg!check_users
}

define service{
use generic-service
host_name client2-debian
service_description Total Processes
check_command check_nrpe_1arg!check_procs
}

define service{
use generic-service
host_name client2-debian
service_description Current Load
check_command check_nrpe_1arg!check_load
}

    Consultar el estado de los servicios monitorizados por Nagios.


5. Monit en el Servidor

    Renombrar el fichero /etc/monit/monitrc a /etc/monit/monitrc.000
    Copiar el fichero de ejemplo proporcionado por el profesor a la ruta /etc/monit/monitrc. Veamos un ejemplo:


# Fichero /etc/monit/monirc de ejemplo
# config general
set daemon 120
set logfile /var/log/monit.log
set mailserver localhost

# Plantilla de email que se envía en las alertas
set alert nombreusuarios@correousuario.com
set mail-format {
from: monitusuario@correousuario.es
subject: $SERVICE $EVENT at $DATE
message: Monit $ACTION $SERVICE at $DATE on $HOST: $DESCRIPTION.
Yours sincerely, monit
}

set httpd port 2812 and use address localhost
allow nombreusuario:claveusuario

# Monitorizar los recursos del sistema
check system localhost
if loadavg (1min) > 4 then alert
if loadavg (5min) > 2 then alert
if memory usage > 75% then alert
if cpu usage (user) > 70% then alert
if cpu usage (system) > 30% then alert
if cpu usage (wait) > 20% then alert

# Monitorizar el servicio SSHD
check process sshd with pidfile /var/run/sshd.pid
start program "/etc/init.d/ssh start"
stop program "/etc/init.d/ssh stop"
if failed port 22 protocol ssh then restart
if 5 restarts within 5 cycles then timeout

    Modificar el fichero /etc/monit/monitrc para adaptarlo a nuestra máquina.
    Reiniciar el servicio: /etc/init.d/monit restart
    Comprobar la lectura de datos de monit vía comandos: monit status
    Comprobar la lectura de datos de monit vía GUI. Abrir un navegador web en la propia máquina, y poner URL "http://localhost:2812". Escribir nombreusuario/claveusuario de monit (Según hayamos configurado en monitrc).
    Capturar pantalla.



ANEXO

Para revisar:
define host{
host_name winserver
alias Windows XP del profesor
address 172.16.108.250
check_command check-host-alive
check_interval 5
retry_interval 1
max_check_attempts 1
check_period 24x7
hostgroups aula108
icon_image cook/windows_pc.png
statusmap_image cook/windows_pc.png
}


define service{
use generic-service
host_name winserver
service_description CPU Load
check_command check_nt!CPULOAD!-l 5,80,90
}

define service{
use generic-service
host_name winserver
service_description Memory Usage
check_command check_nt!MEMUSE!-w 80 -c 90
}

define service{
use generic-service
host_name winserver
service_description C:\ Drive Space
check_command check_nt!USEDDISKSPACE!-l c -w 80 -c 90
}

define service{
use generic-service
host_name winserver
service_description W3SVC
check_command check_nt!SERVICESTATE!-d SHOWALL -l W3SVC
}

define service{
use generic-service
host_name winserver
service_description Explorer
check_command check_nt!PROCSTATE!-d SHOWALL -l Explorer.exe
}

