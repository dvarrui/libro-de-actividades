
# 1. Preparativos

## 1.1 Preparar las máquinas

Para esta actividad vamos a necesitar 3 MV's:
* (MV1) Monitorizador
    * SO Debian 8 - GNU/Linux
    * Consultar [configuración](../../global/configuracion/debian.md) de las MV's.
    * Nombre del equipo: `nagiosXX`
    * IP estática 172.19.XX.41
    * Incluir en el `/etc/hosts` todas las máquinas de la práctica.
* (MV2) Cliente1:
    * SO Debian 8 - GNU/Linux para ser monitorizado.
    * Consultar [configuración](../../global/configuracion/debian.md) de las MV's.
    * IP estática 172.19.XX.42
    * Incluir en el `/etc/hosts` todas las máquinas de la práctica.
* (MV3) Cliente2:
    * SO Windows 7 para ser monitorizado.
    * Consultar [configuración](../../global/configuracion/windows.md) de las MV's.
    * IP estática 172.19.XX.11
    * Incluir en el fichero `c:\Windows\System32\drivers\etc\hosts` todas las máquinas de la práctica.

> Veamos una imagen de ejemplo:
>
> ![etc-hosts](./images/nagios3-etc-hosts.png)

## 1.2 Consultar la documentación

* Leer los documentos proporcionados por el profesor.
* Enlaces de interés:
    * Recomendado - [Instalación y configuración del servidor Nagios, y de los agentes para Linux y Windows](http://itfreekzone.blogspot.com.es/2013/03/nagios-monitoreo-remoto-de-dispositivos.html)
    * [Instalar y configurar nagios usando check_nt](www.tropiezosenlared.com/instalar-y-configurar-nagios-para-la-monitorizacion-de-equipos-en-la-red/)
    * [Configuring nagios to monitor remote host using nrpe](https://kura.io/2010/03/21/configuring-nagios-to-monitor-remote-load-disk-using-nrpe/).

---

# 2. Instalar el servidor

* Instalar Nagios3, la documentación y el plugin NRPE de Nagios.
    * En Debian se podemos usar los comandos `apt-get update` y `apt-get install ...`,
    o el gestor de paquetes Synaptic.
    * Comprobación: `dpkg -l nagios*`
* Durante la instalación se pedirá la clave del usuario `nagiosadmin` (Administrador Nagios).
Además se instalará un servidor web.

![nagios3-password.png](./images/nagios3-password.png)

* Comprobar lo siguiente:
    * `systemctl status nagios3` o `service nagios3 status`, comprobar que el servicio Nagios está en ejecución.
    * `netstat -ntap | grep 80`, comprobar que Apache2 es el servidor Web que funciona en el puerto 80.
    * `nmap -Pn localhost`, comprobar que el servidor http(puerto 80) se ve desde el exterior.
* Abrimos un navegador y ponemos el URL `http://localhost/nagios3`.
    * Ponemos usuario/clave (nagiosadmin/clavesecreta), y ya podemos
    interactuar con el programa de monitorización.
    * Si vamos a las opciones del menú izquierdo *"Hosts"* y *"Services"*,
    vemos que ya estamos monitorizando nuestro propio equipo *"localhost"*.

---

# 3. Configurar el servidor

Nos vamos a plantear como objetivo configurar Nagios para monitorizar los
siguientes hosts:
* Routers:
    * Hosts: router `benderXX` (172.19.0.1) y el router `caronteXX` (192.168.1.1).
    * Vamos a monitorizar si están activos.
* Servidores:
    * Hosts: `leelaXX` (172.20.1.2)
    * Vamos a monitorizar si están activos los servicios HTTP y SSH.
* Clientes:
    * Hosts: `cliente1`, y el `cliente2`.
    * Vamos a monitorizar si están activos.

## 3.1 Directorio personal

* Creamos el directorio `/etc/nagios3/nombre-del-alumno.d`, para
guardar nuestras configuraciones.
* Modificamos fichero de configuración principal `/etc/nagios3/nagios.cfg`,
y añadiremos la siguiente línea: `cfg_dir=/etc/nagios3/nombre-del-alumno.d`,
para que Nagios tenga en cuenta también estos ficheros al iniciarse.

## 3.2 Grupos

Cuando se tienen muchos *hosts* es más cómodo agruparlos.
Los grupos los definimos con `hostgroup`.

* Vamos crear varios `hostgroup`:
    * Sustituir XX por el identificador del alumno.
    * Creamos el fichero `/etc/nagios3/nombre-del-alumno.d/gruposXX.cfg`.
    * Hay que definir 3 grupos de hosts: `routersXX`, `servidoresXX` y `clientesXX`.
    * Veamos un ejemplo (no sirve copiarlo) para definir un grupo:

```
define hostgroup {
  hostgroup_name NOMBRE_DEL_GRUPO
  alias NOMBRE_LARGO_DEL_GRUPO
}
```

## 3.3 Hosts

### Routers

* Crear el fichero `/etc/nagios3/nombre-del-alumno.d/grupo-de-routersXX.cfg` para
incluir las definiciones de las máquinas de tipo router.
* Los host serán miembros también de los grupos `http-servers`, `ssh-servers`. NOTA: 
Los grupos `http-servers` y `ssh-servers` ya están predefinidos en Nagios.

> **Significado de algunos parámetros que vamos a usar**
>
> * host_name: Nombre del host
> * alias: Nombre largo asociado al host
> * address: Dirección IP
> * hostgroups: Grupos a los que pertenece
> * icon_image: Imagen asociada. NOTA: Las imágenes PNG están en `/usr/share/nagios3/htdocs/images/logos/cook`.
>   Poner a cada host una imagen que lo represente.
> * parents: Nombre del equipo padre o anterior.
> * [Más información sobre los parámetros](http://itfreekzone.blogspot.com.es/2013/03/nagios-monitoreo-remoto-de-dispositivos.html)

* Veamos un ejemplo (no sirve copiarlo):
```
define host{
  host_name          NOMBRE_DEL_HOST
  alias              NOMBRE_LARGO_DEL_HOST
  address            IP_DEL_HOST
  hostgroups         GRUPO_AL_QUE_PERTENECE, OTRO_GRUPO, OTRO_MAS
  icon_image         cook/NOMBRE_IMAGEN.png
  statusmap_image    cook/NOMBRE_IMAGEN.png
  #parents

  check_command      check-host-alive
  check_interval     5
  retry_interval     1
  max_check_attempts 1
  check_period       24x7
}
```

* El router `caronteXX` tiene como padre (parent) a `benderXX`.

A continuación se muestran los comandos para manejar servicios:

| Comando systemctl | Comando service | Descripción |
| ----------------- | --------------- | ----------- |
| `systemctl status nagios3` | `service nagios3 status` | muestra el estado actual del servicio|
| `systemctl stop nagios3` | `service nagios3 stop` | detiene el servicio |
| `systemctl start nagios3` | `service nagios3 start` | arranca el servicio |
| `systemctl restart nagios3` | `service restart status` | hace un stop y un start del servicio |
| `systemctl reload nagios3` | `service reload status` | se vuelven a leer los ficheros de configuración del servicio |

* Reiniciamos el servicio de Nagios para que coja los cambios en la configuración.
* Comprobamos si el servicio está activo `systemctl status nagios3`.

> **Si tenemos PROBLEMAS**
>
> Si tenemos problemas al iniciar Nagios, entonces casi seguro tenemos un error en
la configuración que hemos añadido:
> * `/usr/sbin/nagios3 -v /etc/nagios3/nagios.cfg`, Comando para verificar el fichero de configuración de Nagios3.
> * Consultar log `/var/log/nagios3/nagios.log`.

* Consultar la lista de hosts monitorizados por Nagios.

### Servidores

* Crear el fichero `/etc/nagios3/nombre-del-alumno.d/grupo-de-servidoresXX.cfg` para
incluir las definiciones de las máquinas de tipo servidor.
* Todos los host servidores deben ser miembros de servidoresXX, http-servers, ssh-servers.
* El equipo leelaXX tiene como parent a benderXX.
* Reiniciamos Nagios. Si hay problemas, consultar el apartado anterior de cómo revisar los problemas.
* Consultar la lista de hosts monitorizados por Nagios.

### Clientes

* Crear el fichero `/etc/nagios3/nombre-del-alumno.d/grupo-de-clientesXX.cfg` para
incluir las definiciones de las máquinas de tipo cliente.
* Veamos un ejemplo (no sirve copiar) de cómo definir un host:

```
define host{
  use        generic-host
  host_name  NOMBRE_HOST
  alias      NOMBRE_LARGO_DEL_HOST
  address    IP_DEL_HOST
  hostgroups GRUPO_AL_QUE_PERTENECE
}
```

* Personalizar los parámetros: host_name, alias, address y hostgroups.
* Reiniciamos Nagios para que coja los cambios y comprobamos (`systemctl status nagios3`).
* Consultar la lista de hosts monitorizados por Nagios.

---

# 4 Ver algunos ejemplos

A continuación vemos una imagen donde se muestran los hosts que estamos monitorizando.
* El verde significa OK.
* El rojo que el equipo presenta algún problema y requiere atención.

![nagios3-hosts](./images/nagios3-hosts.png)

Además podemos tener una visión completa de la red en la opción "map".

![nagios3-map](./images/nagios3-map.png)

* Consulta la lista de hosts, el mapa de Nagios.

---

# 5. Agente Nagios GNU/Linux

## 5.1 Documentación

Por ahora el servidor Nagios sólo puede obtener la información que los
equipos dejan ver desde el exterior.

Cuando queremos obtener más información del interior los hosts,
tenemos que instalar una utilidad llamada "Agente Nagios" en cada uno.
El agente es una especie de "chivato" que nos puede dar datos de:
Consumo CPU, consumo de memoria, consumo de disco, etc.

Aquí vemos un ejemplo del estado de los "servicios internos" monitorizados,
en el host "localhost". Con la instalación de los "agentes",
podremos tener esta información desde los clientes remotos.

![nagios3-details](./images/nagios3-details.png)

Enlaces de interés:
* [install-nagios-nrpe-client-and-plugins-in-ubuntudebian](https://viewsby.wordpress.com/2013/02/14/install-nagios-nrpe-client-and-plugins-in-ubuntudebian/)
* [instalacion-de-nagios-como-cliente-en-windows-y-linux](http://www.nettix.com.pe/documentacion/administracion/114-instalacion-de-nagios-como-cliente-en-windows-y-linux)
* [monitoring-linux](http://nagios.sourceforge.net/docs/3_0/monitoring-linux.html)

## 5.2 Instalar y configurar el cliente1

En el cliente:
* Debemos instalar el agente nagios en la máquina cliente (paquete NRPE server y los plugin básicos)
    * Pista: `apt-get update` y luego `apt-get install ...` o bien usar el gestor de paquetes.
* Editar el fichero `/etc/nagios/nrpe.cfg` del cliente y modificar lo siguiente:

> En el **CURSO1617** usaremos fichero nrpe_local.cfg

```
 # define en qué puerto (TCP) escuchará el agente.
 # Por defecto es el 5666.
server_port=5666

 # indica en qué dirección IP escuchará el agente,                              
 # en caso que la MV posea más de una IP.
server_address=IP_DEL_CLIENTE

 # define qué IPs tienen permitido conectarse al agente en busca de datos.
 # Es un parámetro de seguridad para limitar desde qué máquinas se conectan al agente.
allowed_hosts=127.0.0.1,IP_DEL_SERVIDOR

 # Esta variable indica que NO se permite que el agente
 # reciba comandos con parámetros por seguridad.
dont_blame_nrpe=0

 # alias check_user para obtener la cantidad de usuarios logueados
 # y alertar si hay más de 5 logueados al mismo tiempo.
command[check_users]=/usr/lib/nagios/plugins/check_users -w 5 -c 10

 # alias check_load para obtener la carga de CPU
command[check_load]=/usr/lib/nagios/plugins/check_load -w 15,10,5 -c 30,25,20

 #alias check_disk para obtener el espacio disponible en el disco /dev/sda
 # y alertar si queda menos de 20% de espacio en alguna partición.
command[check_disk]=/usr/lib/nagios/plugins/check_disk -w 20% -c 10% -x sda


command[check_procs]=...
```

* Reiniciar el servicio en el cliente:
    * Pista `service nagios-nrpe-server ...`

## 5.3 Configurar en el servidor

En el servidor Nagios:
* Vamos a comprobar desde el servidor, la conexión NRPE al cliente, de la siguiente forma:
    * `/usr/lib/nagios/plugins/check_nrpe -H ip-del-cliente`
* A continuación, vamos a definir varios servicios a monitorizar
   * Crear el fichero `/etc/nagios3/nombre-del-alumno.d/servicios-gnulinuxXX.cfg`
   * Añadir las siguientes líneas:

```
define service{
  use                 generic-service
  host_name           NOMBRE_DEL_HOST
  service_description Espacio en disco
  check_command       check_nrpe_1arg!check_disk
}

define service{
  use                 generic-service
  host_name           NOMBRE_DEL_HOST
  service_description Usuarios actuales
  check_command       check_nrpe_1arg!check_users
}

define service{
  use                 generic-service
  host_name           NOMBRE_DEL_HOST
  service_description Procesos totales
  check_command       check_nrpe_1arg!check_procs
}

define service{
  use                 generic-service
  host_name           NOMBRE_DEL_HOST
  service_description Carga actual
  check_command       check_nrpe_1arg!check_load
}
```

* Consultar el estado de los servicios monitorizados por Nagios. Esto es, ir al panel de Nagios3 -> Sección de servicios.

---

# 6. Agente Nagios en Windows

* Enlaces de interés:
    * https://assets.nagios.com/downloads/nagioscore/docs/nagioscore/4/en/monitoring-windows.html

## 6.1 Instalar en el cliente2

* Descargar el programa Agente Windows (NSCLient++)
    * Recomendado [http://nsclient.org/nscp/downloads](http://nsclient.org/nscp/downloads).
    * [http://www.nagios.org/download/addons](http://www.nagios.org/download/addons).
* Instalar el programa nsclient.
    * Activar las opciones `common check plugins`, `nsclient server` y `NRPE server`
    * En este caso hemos elegido NRPE como protocolo de comunicación entre el agente
Windows y el servidor Nagios.

> Si tuviéramos un fichero de instalación MSI, al ejecutarlo nos hará la
instalación del programa con las opciones por defecto sin preguntarnos.

* Servicio `Agente Nagios` en el cliente
    * Por entorno gráfico:
        * Ir a `Equipo -> Administrar -> Servicios -> Nagios -> Reiniciar`.
    * Por comandos:
        * `net start nsclient` para iniciar el servicio del agente.
        * `net stop nsclient` para parar el servicio del agente.

## 6.2 Configurar el cliente2

Toda la configuración se guarda en el archivo `C:\Program Files\NSClient++\nsclient.ini`
 (o `C:\Archivos de Programas\NSClient++\nsclient.ini`).

NSClient no utiliza el mismo formato de configuración que el visto en el host Linux.
Para empezar, la configuración se divide en secciones.
Por otra parte, los plugins se deben habilitar antes de ser utilizados.
Además los plugins se llaman con nombres de ejecutables diferentes
(CheckCpu. CheckDriveSize, etc), y los alias se definen de otra manera.

* Enlaces de interés:
    * [Instalación y configuración del servidor Nagios, y de los agentes para Linux y Windows](http://itfreekzone.blogspot.com.es/2013/03/nagios-monitoreo-remoto-de-dispositivos.html)

> Para estandarizar, en la configuración utilizaremos los mismos alias que en el host Linux
> Así es posible realizar grupos de hosts que incluyan tanto servidores
GNU/Linux como Windows, y ejecutar los mismos comandos en ambos.

* La configuración que utilizaremos será la siguiente:

```
[/settings/default]
;Desactivar el password
;password=

; permitimos el acceso al servidor Nagios para las consultas.
allowed hosts=IP_DEL_SERVIDOR

[/settings/NRPE/server]
ssl options = no-sslv2, no-sslv3
verify mode = none
insecure = true

[/modules]
; habilitamos el uso de NRPE
NRPEServer=1

; habilitamos plugins a utilizar
CheckSystem=1
CheckDisk=1
CheckExternalScripts=1

[/settings/external scripts/alias]

; alias para chequear la carga de CPU. Si sobrepasa el 80% en un intervalo de 5 minutos, nos alertará.
check_load=CheckCpu MaxWarn=80 time=5m

; alias para chequear el espacio en todos los discos del servidor
check_disk=CheckDriveSize ShowAll MinWarnFree=10% MinCritFree=5%

; alias para chequear el servicio del firewall de Windows (llamado MpsSvc).
check_firewall_service=CheckServiceState MpsSvc

```

## 6.3 Configurar en el Servidor

En el servidor Nagios:
* Vamos a comprobar desde el servidor la conexión NRPE al cliente de la siguiente forma:
    * `/usr/lib/nagios/plugins/check_nrpe -H IP_DEL_CLIENTE2`

> [Consultar documentación](http://nagios.sourceforge.net/docs/3_0/monitoring-windows.html)
sobre cómo configurar los servicios del host Windows en Nagios Master

* A continuación, vamos a definir servicios a monitorizar
   * Crear el fichero `/etc/nagios3/nombre-del-alumno.d/servicios-windowsXX.cfg`
   * Veamos un ejemplo.

```
define service {
  use                  generic-service
  host_name            NOMBRE_DEL_HOST
  service_description  Carga media
  check_command        check_nrpe_1arg!check_load
}

define service{
  use                  generic-service
  host_name            NOMBRE_DEL_HOST
  service_description  Espacio en disco
  check_command        check_nrpe_1arg!check_disk
}

define service{
  use                  generic-service
  host_name            NOMBRE_DEL_HOST
  service_description  Firewall
  check_command        check_nrpe_1arg!check_firewall_service
}

```

* Reiniciar el servicio.
* Consultar los servicios monitorizados por Nagios. Esto es, ir al panel de Nagios3 -> Sección de servicios.

---

# 7. Monit en el Servidor

* Instalar Monit en MV Debian1.
* Renombrar el fichero `/etc/monit/monitrc` a `/etc/monit/monitrc.bak`.
* Copiar el fichero de ejemplo proporcionado por el profesor a la ruta `/etc/monit/monitrc`.
* Veamos un ejemplo:

```
 # Fichero /etc/monit/monirc de ejemplo
 # config general
set daemon 120
set logfile /var/log/monit.log
set mailserver localhost

 # Plantilla de email que se envía en las alertas
set alert nombreusuarios@correousuario.com
set mail-format {
  from: ALUMNO@EMAIL.ES
  subject: $SERVICE $EVENT at $DATE
  message: Monit $ACTION $SERVICE at $DATE on $HOST: $DESCRIPTION.
  Yours sincerely, monit
}

set httpd port 2812 and use address localhost
allow NOMBRE_ALUMNO:CLAVE_ALUMNO

 # Monitorizar los recursos del sistema
check system localhost
if loadavg (1min) > 4 then alert
if loadavg (5min) > 2 then alert
if memory usage > 75% then alert
if cpu usage (user) > 70% then alert
if cpu usage (system) > 30% then alert
if cpu usage (wait) > 20% then alert

 # Monitorizar el servicio SSH
check process sshd with pidfile /var/run/sshd.pid
start program "service sshd start"
stop program  "service sshd stop"
if failed port 22 protocol ssh then restart
if 5 restarts within 5 cycles then timeout
```

* Modificar el fichero /etc/monit/monitrc para adaptarlo a nuestra máquina.
    * Cuando aparece un texto como $TEXTO, esto es una variable.
* Reiniciamos el servicio para que coja los cambios de configuración:
    * `systemctl stop monit`, parar.
    * `systemctl start monit`, reiniciamos el servicio.
* `systemctl status monit`, comprobamos que el servicio está en ejecución.
    * En caso de error podemos comprobar la sintaxis del fichero de configuración
    de monit con `/usr/bin/monit -t /etc/monit/monitrc`.
    * Para las opciones del comando monit hacemos `/usr/bin/monit -h`
* Comprobar la lectura de datos de monit vía comandos: `monit status`
* Comprobar la lectura de datos de monit vía GUI.
    * Abrir un navegador web en la propia máquina, y poner URL `http://localhost:2812`.
    * Escribir nombreusuario/claveusuario de monit (Según hayamos configurado en monitrc).

---

# ANEXO

## A.1 Agente Windows

Configuración del servidor para acceder usando comandos check_nt al agente Windows:

```
define service{
  use                 generic-service
  host_name           client1-windows
  service_description Disk Space
  check_command       check_nt!USEDDISKSPACE!-l c -w 80 -c 90
}

define service{
  use                 generic-service
  host_name           client1-windows
  service_description Mem Use
  check_command       check_nt!MEMUSE!-w 80 -c 90
}

define service{
  use                 generic-service
  host_name           client1-windows
  service_description Proc State Explorer
  check_command       check_nt!PROCSTATE!-d SHOWALL -l Explorer.exe
}

define service{
  use                 generic-service
  host_name           client1-windows
  service_description NSClient++ Version
  check_command       check_nt!CLIENTVERSION
}

define service{
  use                 generic-service
  host_name           client1-windows
  service_description Uptime
  check_command       check_nt!UPTIME
}
```

## A.2 Para revisar

```
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
```

## A.3 Configuraciones de ejemplo

```
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
