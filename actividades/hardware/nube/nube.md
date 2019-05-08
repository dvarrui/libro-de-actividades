
```
Curso    : 201819
Software : Migrando OwnCloud a NextCloud
SO       : OpenSUSE Leap
```

# 1. Entrega

* Apartado 1:
    * URL Vídeo que muestre la práctica en funcionamiento.
    * Trabajo individual.
* Apartados 3, 4 y 5:
    * Colaborar con otro compañero.
        * Montar nuestro servidor para que lo use el compañero.
        * Usar el servidor de otro compañero.
    * Hacer un informe de esta parte.
* Entregar un informe de los pasos realizados y el URL del vídeo subido a Youtube.

---

# 2. Nube ajena

En este apartado vamos a practicar el almacenamiento usando la nube de un proveedor externo.

* Elegir alguna de las siguientes herramientas: DropBox, Google Drive, OneDrive, Mega. Consultar con el profesor si queremos elegir otra opción.
* Realizar la instalación y configuración de la herramienta elegida sobre Windows, GNU/Linux o Android.
* Mostrar su uso mediante ejemplos.

---

# 3. Nube propia con NextCoud Server en OpenSUSE Leap

En los servicios de almacenamiento y sincronización de ficheros en la nube destacan Dropbox y Google Drive. Pero ambas soluciones son cerradas.

Dentro de las soluciones libres disponemos de ownCloud o NetxCloud.

Las fuentes están disponibles para poder instalarlo en máquinas propias o alquiladas, así como clientes de sincronización para Windows, Linux, Android y próximamente para iOs y Mac.

Enlaces de interés:
* [NextCloud OpenSUSE Leap 15](https://en.opensuse.org/SDB:Nextcloud)
* [Nextcloud, almacenamiento en la nube. Instalación](https://colaboratorio.net/davidochobits/sysadmin/2017/nextcloud-almacenamiento-en-la-nube-instalacion/)

---

## 3.1 Servidor Web Apache: Instalación y configuraciḉón

Enlace de interés:
* Primero nos Aseguramos de tener correctamente instalado y configurado Apache, PHP y la base de datos (https://en.opensuse.org/SDB:LAMP_setup).

Instalación y configuración de Apache2
* `zypper in apache2`, Installing Apache2
* `systemctl start apache2`, Starting Apache2
* `systemctl enable apache2`, automatically start the apache server after a reboot:
* Permitir acceso público al servidor Web: using YaST and selecting Security and Users --> Firewall --> Allowed services and add HTTP server.

## 3.2 PHP: Instalación y configuración

* `zypper in php7 php7-mysql apache2-mod_php7`, Installing PHP7
* `a2enmod php7`, to enable mod-php.

## 3.3 Database MariaDB

Instalación:
* `zypper in mariadb mariadb-tools`, Installing MariaDB
* `systemctl start mariadb`, Starting the MariaDB server
* `systemctl enable mariadb`, ensure that the server will start at every boot.
* `mysql_secure_installation`, to configure the MariaDB server with improved security.

Configuración para Nextcloud:
* `mysql -u root -p`
* `create database nextcloud;` Create the Nextcloud database. Enter the line exactly as displayed, including the semicolon.
* `create user nextclouduser@localhost identified by 'some-password-here';`, Create the Nextcloud user with a password of your choice.
* `grant all privileges on nextcloud.* to nextclouduser@localhost identified by 'some-password-here';` Grant the needed privileges of nextclouduser to the database nextcloud with the password of your choice.
* `exit;` Exit the database configuration application.

## 3.4 Nextcloud

Instalación:
* `zypper install nextcloud`

Configuración:
* Navigate to the Nextcloud web portal. Open http://localhost/nextcloud to install your instance.
* Create an admin account with a username an password of your choice
* Select the Storage & database drop down
* The data folder is set to the default path.
* Below Configure Database, Select MySQL/MariaDB
* Enter the MariaDB user for Nextcloud
    * Database User: **nextclouduser**
    * Database User Password (for nextclouduser)
    * Database name: **nextcloud**
    * Hostname (such as localhost)

---

# 4 Comprobar vía web

* Hacer una copia de seguridad del fichero de configuración de OwnCloud ( `/srv/www/htdocs/owncloud/config/config.php`).
* Para permitir el acceso desde otros equipos, tenemos que añadir la IP del servidor a las opciones
`trusted_domains` dentro del fichero de configuración `/srv/www/htdocs/owncloud/config/config.php`. Ver ejemplo:

![owncloud-config-php](./files/owncloud-config-php.png)

> **IMPORTANTE**: Revisar bien los cambios que realicemos en el fichero de configuración anterior. Un fallo de sintaxis puede dejar nuestro servidor sin funcionar.

* Hacer captura de pantalla del fichero `/srv/www/htdocs/owncloud/config/config.php`.
* Abrimos un navegador URL: `ip-del-servidor/owncloud`. Ahora debe funcionar el acceso usando la IP tanto desde el propio servidor como desde otra máquina. Comprobarlo.

> Si no funciona el acceso a `http://ip-del-servidor/owncloud` desde otra máquina:
> * Primero comprobar si el cortafuegos del servidor está bloqueando el acceso. Vamos a otra máquina y hacemos `nmap -Pn IP-del-servidor`. Debe mostrar los servicios del servidor.
> * Para abrir el cortafuegos, vamos a `Yast -> Cortafuegos`. Añadir en `Servicios Autorizados` de la `Zona externa` a `HTTP Server` y  `HTTPS Server`.

* Abrimos un navegador web, y ponemos en el URL `http://localhost/owncloud`
* Usamos nuestro usuario/clave administrador.
* Crear un usuario normal `nombre-del-alumnoXX`.
* Subiremos algunos archivos al servidor con el usuario anterior.
* Crear un usuario normal `nombre-del-compañeroXX`.
* Le diremos al compañero que suba algunos archivos al servidor con el usuario anterior.

---

# 5. NextCloud Desktop Client

Enlace de interés: 
* [Instalar y configurar un cliente de Nextcloud en Linux](https://geekland.eu/instalar-cliente-de-nextcloud-linux/)

Ir a una MV con Windows 7:
* Instalar el sofware cliente de NextCloud.
   * Usar URL `http://ip-servidor/nextcloud`.
* Comprobar cómo se mantienen sincronizados los archivos entre las máquinas.

---

# ANEXO

## A.1 Instalación del servidor OwnCloud para Ubuntu

* [OwnCloud en Debian/Ubuntu](http://hipertextual.com/archivo/2014/10/owncloud/)

## A.2 Instalación del servidor OwnCloud para Debian7

* Añadimos un nuevo repositorio con el paquete que queremos instalar:
    * echo 'deb http://download.opensuse.org/repositories/isv:/ownCloud:/community:/nightly/Debian_7.0/ /' >> /etc/apt/sources.list.d/owncloud.list
* Actualizamos la lista de repositorios: `apt-get update`
* Instalamos el paquete: `apt-get install owncloud`

## A.3 Instalación del servidor OwnCloud para Raspberry PI

* [BTSync: Clone Dropbox with a Raspberry Pi and BTSync](http://reustle.io/blog/btsync-pi)
