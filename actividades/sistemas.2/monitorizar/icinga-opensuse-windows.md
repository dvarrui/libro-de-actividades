
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
* EN - [Getting Started](https://icinga.com/docs/icinga2/latest/doc/02-getting-started/)
* EN - [Install IcingaWeb2](https://www.2daygeek.com/install-icinga-web2-on-centos-rhel-fedora-opensuse-ubuntu-debian-mint/)
* ES - Vídeo sobre [Instalar Icinga2 e IcingaWeb2 en Centos 7](https://youtu.be/eVFqyFJN9nk)
* ES - [Monitorizar sistemas y redes con Icinga2](https://www.ochobitshacenunbyte.com/2015/10/30/monitoriza-sistemas-redes-icinga2/)

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
    * `firewall-cmd --permanent --add-service=http` o
    * `Yast -> Contafuegos -> Abrir servicio http(80) y https(443)`
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

**Problema con la versión de PHP**

Hemos comprobado que la versión actual de IcingaWeb2 no funciona correctamente con php7.2.5. Hay una solución propuesta por Aarón Rodríguez Pérez. Esto es, cambiar la versión de php7.2.5 por php7.1.27. Foro: https://forums.opensuse.org/showthread.php/530164-php7-is-only-available-whith-version-7-2-and-i-don-t-find-way-to-install-7-1-version

Proceso para instalar la versión php7.1.27.
* `zypper ar http://download.opensuse.org/repositories/devel:/languages:/php:/php71/openSUSE_leap_15.0/ devel:languages:php:php71`
* `zypper install --oldpackage php7-7.1.27`
* Si hay errores instalar php-Icinga

> NOTA:
>
> * Otra forma de cambiar la versión de PHP es cambiando los paquetes rpm. Primero lo descargamos y luego lo instalamos con `rpm -i PACKAGENAME.rpm`
> * Enlace de interés para cambiar paquetes de php7.2.5 a php7.1.27 (https://software.opensuse.org/package/php7). Buscar `php7-7.1.27-lp150.1.1.x86_64.rpm`.

* Reiniciamos el equipo. Comprobamos el cambio de versión `php -v`.

## 3.6 Preparing Web Setup


* `icingacli module list`, Debe aparecer el módulo `setup` como disponible. En caso contrario lo activamos con `icingacli module enable setup`.
* `icingacli setup token create`, to generate a token use the icingacli. When using the web setup you are required to authenticate using a token.
* `icingacli setup token show`, In case you do not remember the token you can show it using the icingacli:
* `chgrp -R icingaweb2 /etc/icingaweb2`. dar permisos a todos los miembros del grupo `icingaweb2` para acceder a este directorio.

## 3.7 Usar navegador para acceder a Icingaweb2

Vamos a configurar IcingaWeb2 por el navegador.
* Abrimos un navegador y ponemos el URL `http://localhost/icingaweb2/`. Se nos muestra la ventana de autenticación del panel web de la herramienta.
* Ponemos el token y siguiente.
* `Modules > Monitoring > ENABLE` -> NEXT
* Debemos instalar los paquetes que faltan (paquetes en color amarillo).     Para [descargar paquetes PHP versión 7.1.27](https://software.opensuse.org/package/php7) o también se pondrán en el Moodle para descargar:
    * Ejemplo para localizar los nombres de los paquetes: `zypper se php |grep ldap` => `php7-ldap`
    * En nuestro caso necesitaremos los siguientes :
        * php7-curl-7.1.27-lp150.1.1.x86_64.rpm
        * php7-ldap-7.1.27-lp150.1.1.x86_64.rpm
        * php7-mysql-7.1.27-lp150.1.1.x86_64.rpm
        * php7-pgsql-7.1.27-lp150.1.1.x86_64.rpm

> ERROR: No hemos podidoinstalar `php-imagick` para php 7.1.27 en OpenSUSE Leap 15.0. Para nuestra práctica no es necesario y podemos seguir.

* `systemctl restart apache2`, reiniciar el servidor web Apache2.
* Consultar la página web y refrescar (F5). Ahora deben aparecer los módulos en verde. Eso indica que están isntalados. Sequimos.
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
* Ahora se nos pide un usuario/clave con privilegios para crear la base de datos y usuario en la Base de datos MySQL. Esto es, usar `root` (De MySQL) sin clave. Tal y como hicimos en el apartado 3.1.
* Backend name : `icingaweb2`
* Crear usuario para icingaweb2. Por ejemplo usuario `profesor` con clave `profesor`.
* Configuración de la aplicación -> siguiente.
* Monitoring IDO resource. BBDD/usuario/clave => icinga/icinga/icinga.
* Command transport: `local`
* API User/API Password: Poner lo que tenemos en `/etc/icinga2/conf.d/api-users.conf` como ApiUser object.

> **NOTA**
>
> Si en la fase final del proceso de configuración de IcingaWeb2, aparece un error al habilitar Módulos por falta de permisos, entonces es necesario revisar los permisos que tiene la carpeta `/etc/icingaweb2/enableModules` para el usuario `wwwrun` (Este es el usuario que utiliza el servidor Web Apache2 en OpenSUSE).
>
> * `chown -R wwwrun:icingaweb2 /etc/icingaweb2/enabledModules`

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

* Crear fichero `ALUMNODIR/clients-gnulinux.conf`.

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
```

* Crear fichero `ALUMNODIR/clients-windows.conf`.
```
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

> **¡OJO!**: Asegurarse de que el usuario `icinga` es el propietario de los archivos que acabamos de crear en la ruta ALUMNODIR. Si no tiene permisos de lectura sobre dichas configuraciones, éstas no tendrán efecto.

* `systemctl restart icinga2`, reinciar el servicio para forzar la lectura de los nuevos ficheros de configuración. En caso de error, consultar log `var/log/icinga2.log`.
* Comprobar los cambios por IcingaWeb2.

---

# 5. Agent-based monitoring (cliente GNU/Linux)

Enlaces de interés:
* https://stackoverflow.com/questions/42167778/icinga2-disk-space-check-or-with-three-arguments

Por ahora el monitor, sólo puede obtener la información que los
equipos dejan ver desde el exterior. Cuando queremos obtener más información del interior los hosts, tenemos que acceder dentro de la máquina remota. En nuestro caso, usaremos SSH para acceder a esta información: Consumo CPU, consumo de memoria, consumo de disco, etc.

## 5.1 INFO: Teoría sobre agentes SSH de Icinga

Podemos usar varios protocolos de comunicación diferente con el nodo (Agente). En nuestro caso vamos a usar el protocolo SSH.

Calling a plugin using the SSH protocol to execute a plugin on the remote server fetching its return code and output. The by_ssh command object is part of the built-in templates and requires the check_by_ssh check plugin which is available in the Monitoring Plugins package.

```
object CheckCommand "by_ssh_swap" {
  import "by_ssh"
  vars.by_ssh_command = "/usr/lib/nagios/plugins/check_swap -w $by_ssh_swap_warn$ -c $by_ssh_swap_crit$"
  vars.by_ssh_swap_warn = "75%"
  vars.by_ssh_swap_crit = "50%"
}

object Service "swap" {
  import "generic-service"
  host_name = "remote-ssh-host"
  check_command = "by_ssh_swap"
  vars.by_ssh_logname = "icinga"
}
```

> NOTA: En el directorio `/usr/lib/nagios/plugins/`, tenemos muchos check commands para usar.

## 5.2 Cliente GNULinux

* Crear fichero `ALUMNODIR/agents-gnulinux.conf` para incluir monitorización del disco duro.
* Editar `ALUMNODIR/agents-gnulinux.conf` para incluir el commando para monitorizar el disco.
```
object CheckCommand "by_ssh_disk" {
  import "by_ssh"
  vars.by_ssh_command = "/usr/lib/nagios/plugins/check_disk ...."
  vars.by_ssh_disk_warn = "75%"
  vars.by_ssh_disk_crit = "50%"
}
```
* El parámetro `vars.by_ssh_command` está incompleto.Para saber cómo completarlo debemos consultar la ayuda del comando check_disk (`/usr/lib/nagios/plugins/check_disk -h`). Consultar los ejemplos.
* Editar `ALUMNODIR/agents-gnulinux.conf` para incluir el servicio para monitorizar el disco.
```
object Service "disk_clientXXg1" {
  import "generic-service"
  host_name = "clientXXg1"
  check_command = "by_ssh_disk"
  vars.by_ssh_logname = "root"
  vars.by_ssh_password = "CLAVE-DE-ROOT"
}
```

## 5.3 Cliente Window

```
Este apartado es OPCIONAL.
NO ES OBLIGATORIO hacerlo.
```

* Modificar fichero `ALUMNODIR/agents-windows.conf` para incluir monitorización del disco duro.

---

# ANEXO A

## A.1 Icinga2: Backup

Ensure to include the following in your backups:
* Configuration files in /etc/icinga2
* Certificate files in /var/lib/icinga2/ca (Master CA key pair) and /var/lib/icinga2/certs (node certificates)
* Runtime files in /var/lib/icinga2
* Optional: IDO database backup
* Backup: Database

## A.2 Configuration Overview

Apart from its web configuration capabilities, (depending on your configuration setup).

| File/Directory     | Description |
| ------------------ | ----------- |
| /etc/icingaweb2    | configuration stored in by default |
| config.ini	       | General configuration |
| resources.ini	     | Global resources |
| roles.ini	         | User specific roles |
| authentication.ini | Authentication backends |
| enabledModules     | Symlinks to enabled modules |

## A.3 Icingaweb2: Installing Requirements

* Icinga 2 with the IDO database backend (MySQL or PostgreSQL)
* A web server, e.g. Apache or Nginx
* PHP version >= 5.6.0
* The following PHP modules must be installed: cURL, gettext, intl, mbstring, OpenSSL and xml
* Default time zone configured for PHP in the php.ini file
* LDAP PHP library when using Active Directory or LDAP for authentication
* MySQL or PostgreSQL PHP libraries
