
```
EN CONSTRUCCIÓN!!!
* Curso 201617: Actividad copiada de Nagios-Debian-Windows
* Curso 201819: Se está intentando adaptar para Icinga-OpenSUSE-Windows.
```

# 1. Preparativos

## 1.1 Preparar las máquinas

Para esta actividad vamos a necesitar los siguientes MV's:

| ID  | Hostname   | IP | SSOO |
| --- | ---------- | -- | ---- |
| MV1 | monitorXX  | 172.AA.XX.31 |[OpenSUSE](../../global/configuracion/opensuse.md)|
| MV2 | clientXXg1 | 172.AA.XX.32 |[OpenSUSE](../../global/configuracion/opensuse.md)|
| MV3 | clientXXw1 | 172.AA.XX.11 |[Windows7](../../global/configuracion/windows.md)|

Supongamos que tenemos el siguiente esquema de red:

![](images/esquema-red.png)

## 1.2 Configurar DNS local

* Incluir en el `/etc/hosts` todas las máquinas de la práctica.
* Incluir en el fichero `c:\Windows\System32\drivers\etc\hosts` todas las máquinas de la práctica.

> Veamos una imagen de ejemplo:
>
> ![etc-hosts](./images/nagios3-etc-hosts.png)

---

# 2. Monitor: Instalación

Enlaces de interés:
* [Getting Started](https://icinga.com/docs/icinga2/latest/doc/02-getting-started/)
* Vídeo sobre [Instalar Icinga2 e IcingaWeb2 en Centos 7](https://youtu.be/eVFqyFJN9nk)
* [Monitorizar sistemas y redes con Icinga2](https://www.ochobitshacenunbyte.com/2015/10/30/monitoriza-sistemas-redes-icinga2/)

## 2.1 Instalar el software principal

Instalar software:
* `zypper install icinga2`
* `icinga2 feature list`, verificar las características habilitadas o deshabilitadas.
* `zypper install monitoring-plugins`, instalar los plugins.

Iniciar y comprobar:
* `systemctl enable icinga2`, activar el servicio.
* `systemctl start icinga2`, iniciar el servicio.
* `systemctl status icinga2`, ver el estado del servicio.

## 2.2 Configurar los editores

Configurar el editor vim (con usuario root):
* `zypper install vim-icinga2`
* `vim ~/.vimrc`
* `syntax on` (ESC : wq)
* Test it: `vim /etc/icinga2/conf.d/templates.conf` (ESC : q)

Configurar el editor nano (con usuario root):
* `zypper install nano-icinga2`
* `nano ~/.nanorc`
* Include the icinga2.nanorc file.
```
## Icinga 2
include "/usr/share/nano/icinga2.nanorc"
```
* Test it: `nano /etc/icinga2/conf.d/templates.conf`

**Installation Paths**

By default Icinga 2 uses the following files and directories:

| Path	| Description |
| ----- | ----------- |
| /etc/icinga2 | Contains Icinga 2 configuration files |
| /usr/lib/systemd/system/icinga2.service	| The Icinga 2 Systemd service file on systems using Systemd |
| /usr/sbin/icinga2	| Shell wrapper for the Icinga 2 binary |
| /usr/share/doc/icinga2 | Documentation files that come with Icinga 2 |
| /var/run/icinga2 | PID file |
| /var/log/icinga2 | Log file location and compat/ directory for the CompatLogger feature |

---

# 3 Instalar el panel web

En principio se supone que no es estrictamente necesario tener un panel web para monitorizar los equipos de la red, pero entendemos que visualmente es cómodo tenerlo, así que seguimos.

## 3.1 Base de datos

* En este punto podemos usar una de las siguientes bases de datos:
    * MySQL
    * PosgreSQL
* Elegimos MySQL (por votación en clase. Es la misma que usan ya en BBDD)

> De momento dejo los textos tal cual están en inglés. Y cuando se acabe de revisar se traducirán a español.

**Configuring DB IDO MySQL**

* `zypper install mysql mysql-client`, Installing MySQL database server
* `systemctl enable mysql`
* `systemctl status mysql`
* `zypper install icinga2-ido-mysql`
* Set up a MySQL database for Icinga 2 (El usuario root de mysql NO tiene clave):
```
# mysql -u root -p

CREATE DATABASE icinga;
GRANT SELECT, INSERT, UPDATE, DELETE, DROP, CREATE VIEW, INDEX, EXECUTE ON icinga.* TO 'icinga'@'localhost' IDENTIFIED BY 'icinga';
quit
```
* `mysql -u root -p icinga < /usr/share/icinga2-ido-mysql/schema/mysql.sql`

**Enabling the IDO MySQL module**

* `more /etc/icinga2/features-available/ido-mysql.conf`, You can update the database credentials in this file.
* `icinga2 feature enable ido-mysql`, You can enable the ido-mysql feature configuration file using icinga2 feature enable.
* `systemctl restart icinga2` Restart Icinga 2.

## 3.2 Servidor Web

Podemos usar como servidor web Apache2 o Nginx. En nuestro ejemplo elegimos Apache2, por ser el primero que aparece. No tenemos ningún motivo y/o criterio de elección.

* `zypper in apache2`
* `a2enmod rewrite`
* `a2enmod php7`
* `systemctl enable apache2`
* `systemctl start apache2`
* `systemctl status apache2`

## 3.3 Cortafuegos

* Firewall Rules: Enable port 80 (http). Best practice is to only enable port 443 (https) and use TLS certificates.
    * `Yast -> Contafuegos -> Abrir servicio http(80) y https(443)`, o
    * `firewall-cmd --add-service=http` o
    * `firewall-cmd --permanent --add-service=http`
* `nmap -Pn localhost`, comprobar que el puerto http(80) está abierto.

> **Servicios que deben estár iniciados**: icinga2, mysql, apache2 y firewalld.

## 3.4 Setting Up Icinga 2 REST API

Icinga Web 2 and other web interfaces require the REST API to send actions (reschedule check, etc.) and query object details.

* `icinga2 api setup`, to enable the api feature and set up certificates. Adding new API user root in `/etc/icinga2/conf.d/api-users.conf`.
* `systemctl restart icinga2`, Restart Icinga 2 to activate the configuration.

## 3.5 Instalar icingaweb2

You can install Icinga Web 2 by using your distribution’s package manager to install the icingaweb2 package. The additional package icingacli is necessary to follow further steps in this guide.

* `zypper search icingaweb2`, comprobar que está disponible.
* `zypper install icingaweb2`
* `zypper install icingaweb2-icingacli`
* Quitar php7.25 y ponemos php7.1.27 (https://software.opensuse.org/package/php7).
    * **Problema con la versión de PHP**: Posible solución (versión php) de Aarón Rodríguez Pérez - lunes, 11 de marzo de 2019, 09:39
    * Foro: https://forums.opensuse.org/showthread.php/530164-php7-is-only-available-whith-version-7-2-and-i-don-t-find-way-to-install-7-1-version
* Reiniciamos el equipo. Comprobamos el cambio de versión `php -v`.

## 3.6 Preparing Web Setup

You can set up Icinga Web 2 quickly and easily with the Icinga Web 2 setup wizard which is available the first time you visit Icinga Web 2 in your browser.

* `icingacli setup token create`, to generate a token use the icingacli. When using the web setup you are required to authenticate using a token.
* `icingacli setup token show`, In case you do not remember the token you can show it using the icingacli:
* `chgrp -R icingaweb2 /etc/icingaweb2`. dar permisos a todos los miembros del grupo `icingaweb2` para acceder a este directorio.

## 3.7 Usar la navegador para acceder a Icingaweb2

* Abrimos un navegador y ponemos el URL `http://localhost/icingaweb2/`. Se nos muestra la ventana de autenticación del panel web de la herramienta.
* Ponemos el token -> NEXT
* `Modules > Monitoring > ENABLE` -> NEXT
* Debemos instalar los paquetes que faltan (paquetes en color amarillo):
    * Ejemplo para localizar los nombres de los paquetes: `zypper se php |grep ldap` => `php7-ldap`
    * [Descargar paquetes PHP versión 7.1.27](https://software.opensuse.org/package/php7) e instalarlos.
    * Se pondrán rpm en el Moodle.

> Sigo sin poder instalar `php-imagick` para php 7.1.27 en OpenSUSE Leap 15.0.

* `systemctl restart apache2`, reiniciar el servidor web Apache2.
* Consultar la página web y refrescar (F5). Ahora deben aparecer los módulos en verde. Sequimos.
* Autentificación -> Database.
* Database Resource:

| Campo         | Valor        |
| ------------- | ------------ |
| Resource name | icingaweb_db |
| Database type | MySQL |
| Host          | localhost |
| Database name | icingaweb2 |
| User name     | icingaweb2 |
| Password      | icingaweb2 |

* Validar y siguiente.
* Poner usuario/clave del administrador de mysql.
* Backend name : icingaweb2
* Crear usuario para icingaweb2: profesor/profesor
* COnfiguración de la aplicación -> siguiente.
* Monitoring IDO resource: BBDD/usuario/clave => icinga/icinga/icinga.
* Command transport: local
* API User/API Password: Poner lo que tenemos en `/etc/icinga2/conf.d/api-users.conf` como ApiUser object.

---

# 4. Configurar objetos para monitorizar

**Objetivo**

Nos vamos a plantear como objetivo monitorizar lo siguente:

| Grupo   | Hosts      | IP           | Comprobar |
| ------- | ---------- | ------------ | ----------- |
| routers | benderXX   | 172.AA.0.1   | Host activo |
| routers | caronteXX  | 192.168.1.1  | Host activo |
| servers | leelaXX    | 172.20.1.2   | Servicio HTTP y SSH |
| clients | clientXXg1 | 172.AA.XX.32 | Host activo |
| clients | clientXXw1 | 172.AA.XX.11 | Host activo |

* Sea ALUMNODIR=`/etc/icinga2/conf.d/nombre-del-alumno.d`.
* Crear directorio ALUMNODIR. Creamos el directorio para guardar nuestras configuraciones.

## 4.1 Configurar HOST servidores

* Crear fichero `ALUMNODIR/servers.conf`.

```
object Host "leelaXX" {
  address = "172.20.1.2"
  vars.os = "Linux"
  check_command = "hostalive"
}

object Service "http_leela" {
  host_name = "leelaXX"
  check_command = "http"
}
```
> Fijarse en todos los parámetros anteriores y preguntar las dudas.
> * Host: Nombre del host
> * address: Dirección IP
> * vars.os: Sistema Operativo
> * check_command: Comando usado para verificar el host.

## 4.2 Configurar HOSTS routers

* Crear fichero `ALUMNODIR/routers.conf`.

```
object Host "benderXX" {
  address = "ip-del-host"
  check_command = "hostalive"
}

object Host "caronteXX" {
  address = "ip-del-host"
  check_command = "hostalive"
}
```

## 4.3 Configurar HOSTS clientes

* Crear fichero `ALUMNODIR/clients.conf`.

```
object Host "clientXXg1" {
  address = "ip-del-host"
  vars.os = "Linux"
  check_command = "hostalive"
}

object Service "ssh_clientXXg1" {
  host_name = "clientXXg1"
  check_command = "ssh"
}

object Host "clientXXw1" {
  address = "ip-del-host"
  vars.os = "Windows"
  check_command = "hostalive"
}

object Service "ssh_clientXXw1" {
  host_name = "clientXXw1"
  check_command = "ssh"
}
```

* `systemctl restart icinga2`, reinciar el servicio para forzar la lectura de los nuevos ficheros de configuración. En caso de error, consultar log `var/log/icinga2.log`.
* Comprobar los cambios por IcingaWeb2.

```
========================
Comprobado hasta AQUI!!!
========================
```

---

# 5. Agente: Servicios Internos en el cliente GNU/Linux

## 5.1 Documentación

Por ahora el monitor, sólo puede obtener la información que los
equipos dejan ver desde el exterior. Cuando queremos obtener más información del interior los hosts, tenemos que instalar una utilidad llamada "Agente" en cada uno.

El agente es una especie de "chivato" que nos puede dar datos de:
Consumo CPU, consumo de memoria, consumo de disco, etc.

Aquí vemos un ejemplo del estado de los "servicios internos" monitorizados,
en el host "localhost". Con la instalación de los "agentes",
podremos tener esta información desde los clientes remotos.

![nagios3-details](./images/nagios3-details.png)

> Enlaces de interés:
> * [install-nagios-nrpe-client-and-plugins-in-ubuntudebian](https://viewsby.wordpress.com/2013/02/14/install-nagios-nrpe-client-and-plugins-in-ubuntudebian/)
> * [instalacion-de-nagios-como-cliente-en-windows-y-linux](http://www.nettix.com.pe/documentacion/administracion/114-instalacion-de-nagios-como-cliente-en-windows-y-linux)
> * [monitoring-linux](http://nagios.sourceforge.net/docs/3_0/monitoring-linux.html)

## 5.2 Instalar y configurar el cliente1

En el cliente:
* Debemos instalar el agente en la máquina cliente (paquete NRPE server y los plugin básicos)
* Editar el fichero `/etc/.../nrpe_local.cfg` del cliente y modificar lo siguiente:

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
 # reciba comandos con parámetros poe seguridad.
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

* Reiniciar el servicio en el cliente (`systemctl restart nagios-nrpe-server ...`)

## 5.3 Configurar en el servidor

* Ir al servidor.
* `/usr/lib/nagios/plugins/check_nrpe -H ip-del-cliente`, comprobar desde el servidor la conexión NRPE al cliente de la siguiente forma.
* Crear el fichero `DIRBASE/servicios-gnulinuxXX.cfg`, para definir nuevos servicios a monitorizar.
* Añadir las siguientes líneas:

```
define service{
  use                  generic-service
  host_name            NOMBRE_DEL_HOST
  service_description  Espacio en disco
  check_command        check_nrpe_1arg!check_disk
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

* Consultar el estado de los servicios monitorizados en el panel.

---

# 6. Agente: Servicios Internos en el Cliente Windows

## 6.1 Instalar en el cliente2

* Descargar el programa Agente Windows (NSCLient++)
    * Recomendado [http://nsclient.org/nscp/downloads](http://nsclient.org/nscp/downloads).
    * [http://www.nagios.org/download/addons](http://www.nagios.org/download/addons).
* Instalar el programa nsclient.
    * Activar las opciones `common check plugins`, `nsclient server` y `NRPE server`

> * En este caso hemos elegido NRPE como protocolo de comunicación entre el agente
Windows y el servidor Nagios.
> * Si tuviéramos un fichero de instalación MSI, al ejecutarlo nos hará la
instalación del programa con las opciones por defecto sin preguntarnos.

* Servicio `Agente` en el cliente
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

> Para estandarizar, en la configuración utilizaremos los mismos alias que en el host Linux
> Así es posible realizar grupos de hosts que incluyan tanto servidores
GNU/Linux como Windows, y ejecutar los mismos comandos en ambos.

* Enlaces de interés:
    * [Instalación y configuración del servidor Nagios, y de los agentes para Linux y Windows](http://itfreekzone.blogspot.com.es/2013/03/nagios-monitoreo-remoto-de-dispositivos.html)
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
* `/usr/lib/nagios/plugins/check_nrpe -H IP_DEL_CLIENTE2`, comprobar desde el servidor la conexión NRPE al cliente.

> [Consultar documentación](http://nagios.sourceforge.net/docs/3_0/monitoring-windows.html) sobre cómo configurar los servicios del host Windows en Nagios Master

* Crear el fichero `DIRBASE/servicios-windowsXX.cfg`, para definir servicios a monitorizar en Windows.
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
* Consultar los servicios monitorizados por el panel.

---

# ANEXO A

---
Icinga2: Backup ¶
Ensure to include the following in your backups:

Configuration files in /etc/icinga2
Certificate files in /var/lib/icinga2/ca (Master CA key pair) and /var/lib/icinga2/certs (node certificates)
Runtime files in /var/lib/icinga2
Optional: IDO database backup
Backup: Database ¶
MySQL/MariaDB:

Documentation
PostgreSQL:

Documentation
---


Configuration Overview

Apart from its web configuration capabilities,
 (depending on your configuration setup).

| File/Directory     | Description |
| ------------------ | ----------- |
| /etc/icingaweb2    | configuration stored in by default |
| config.ini	       | General configuration |
| resources.ini	     | Global resources |
| roles.ini	         | User specific roles |
| authentication.ini | Authentication backends |
| enabledModules     | Symlinks to enabled modules |

Icingaweb2: Installing Requirements:
* Icinga 2 with the IDO database backend (MySQL or PostgreSQL)
* A web server, e.g. Apache or Nginx
* PHP version >= 5.6.0
* The following PHP modules must be installed: cURL, gettext, intl, mbstring, OpenSSL and xml
* Default time zone configured for PHP in the php.ini file
* LDAP PHP library when using Active Directory or LDAP for authentication
* MySQL or PostgreSQL PHP libraries



---
