
# 1. Entrega

* Apartado 2:
    * Trabajo individual.
    * Vídeo que muestre la práctica en funcionamiento.
* Apartados 3, 4 y 5:
    * Colaborar con otro compañero.
        * Montar nuestro servidor para que lo use el compañero.
        * Usar el servidor de otro compañero.
    * Hacer un informe de esta parte.
* Entregar un informe de los pasos realizados y el URL del vídeo subido a Youtube.

---

# 2. Nube ajena

Almacenamiento en la nube de un proveedor externo.

* Realizar la instalación y configuración de alguna de las siguientes herramientas a elegir por el alumno:
    * DropBox
    * Google Drive
    * OneDrive
    * Mega
* Realizar una instalación sobre SO Windows y otra sobre GNU/Linux. Mostrar su uso mediante ejemplos.

---

# 3. Nube propia con OwnCloud Server con Debian

En los servicios de almacenamiento y sincronización de ficheros en la nube destacan Dropbox y Google Drive.
Pero ambas soluciones son cerradas.

Dentro de las soluciones libres disponemos de ownCloud o NetxCloud.

Las fuentes están disponibles para poder instalarlo en máquinas propias o alquiladas,
así como clientes de sincronización para Windows, Linux, Android y próximamente para iOs y Mac.

Para más información [official documentation](https://doc.owncloud.org/) sobre OwnCloud.

## 3.1 Instalar OwnCloud

* Enlaces de interés:
    * https://doc.owncloud.org/server/latest/admin_manual/installation/source_installation.html
* Elegir una MV con Debian para instalar OwnCloud Server.
* Instalar las dependencias de OwnCloud:
```
apt-get install -y apache2 mariadb-server libapache2-mod-php7.0 \
    openssl php-imagick php7.0-common php7.0-curl php7.0-gd \
    php7.0-imap php7.0-intl php7.0-json php7.0-ldap php7.0-mbstring \
    php7.0-mcrypt php7.0-mysql php7.0-pgsql php-smbclient php-ssh2 \
    php7.0-sqlite3 php7.0-xml php7.0-zip
```
* Descargar OwnCloud https://owncloud.org/download/
* `unzip owncloud-x.y.z.zip` descomprimir
* `cp -r owncloud /var/www`, copiar ficheros al directorio `document root`del servidor Web Apache2.
* `chown -R www-data:www-data /var/www/owncloud/`, damos permisos al usuario www-data sobre los ficheros de OwnCloud.
Este usuario es el responsable de ejecutar el servidor web.

## 3.2 Configurar Servidor Web Apache2

* Crear `/etc/apache2/sites-available/owncloud.conf` con:
```
Alias /owncloud "/var/www/owncloud/"

<Directory /var/www/owncloud/>
  Options +FollowSymlinks
  AllowOverride All
  Satisfy Any

 <IfModule mod_dav.c>
  Dav off
 </IfModule>

 SetEnv HOME /var/www/owncloud
 SetEnv HTTP_HOME /var/www/owncloud

</Directory>
```
* Crear enlace simbólico a /etc/apache2/sites-enabled:
```
ln -s /etc/apache2/sites-available/owncloud.conf /etc/apache2/sites-enabled/owncloud.conf
```
* Activar módulos de Apache2 ejecutando los siguientes comandos:
```
a2enmod rewrite
a2enmod headers
a2enmod env
a2enmod dir
a2enmod mime
```
* Asegurarse de que tenemos bien configurado los siguientes ficheros, para que Apache2 no de advertencias 
de que está mal configurado el parámetro `Server Name`:
    * `/etc/hostname`con el `1er-apellidoXX.curso1718` y
    * en `/etc/hosts` tenemos una línea con `IP   1er-apellidoXX.curso1718   1er-apellidoXX`.     
* Comprobar si los módulos de Apache2 están activos:
    * `apache2ctl -t -D DUMP_MODULES`, muestra todos los módulos activos o cargados.
    * `apache2ctl -t -D DUMP_MODULES | grep NOMBRE`, muestra si el módulo NOMBRE está activo o cargado. 
* `service apache2 restart`, reiniciar el servicio Apache2.
* Crear el archivo `/var/www/html/alumno.html`
* Escribir el nombre del alumno dentro de `alumno.html`
* Acceder al URL `localhost/alumno.html`

## 3.3 Crear la Base de datos

* El paquete `mariadb-server` ya debe estar instalado.
* Vamos a crear la base de datos.
```
sudo mysql --user=root mysql
CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'dbpass';
GRANT ALL PRIVILEGES ON *.* TO 'dbadmin'@'localhost' WITH GRANT OPTION;

CREATE DATABASE ocdatabase;
GRANT ALL ON ocdatabase.* TO ocuser@localhost IDENTIFIED BY 'dbpass';

FLUSH PRIVILEGES;
exit
```

Tenemos que:
* Database user: `ocuser`
* Database name: `ocdatabase`
* Database user password: `dbpass`

## 3.4 Configurar OwnCloud

* Abrir navegador e ir al URL  `http://localhost/owncloud`
    * **¡OJO! Antes de seguir, desplegar la pestaña para continuar definiendo el almacenamiento...**
    * Poner usuario/clave del administrador de OwnCloud.
    * Elegimos `MariaDB/MySQL`
    * El directorio de datos (DATA FOLDER): `/opt/owncloud-data`
    * Database user: `ocuser`
    * Database name: `ocdatabase`
    * Database user password: `dbpass`
* Esperar a que termine la configuración.
* Con URL `localhost/owncloud` accedemos a la aplicación OwnCloud

---

## 4 Comprobar vía web

* Hacer una copia de seguridad del fichero de configuración de OwnCloud ( `/var/www/owncloud/config/config.php`).
* Para permitir el acceso desde otros equipos, tenemos que añadir la IP del servidor a las opciones
`trusted_domains` dentro del fichero de configuración `/var/www/owncloud/config/config.php`. Ver ejemplo:

![owncloud-config-php](./files/owncloud-config-php.png)

> **IMPORTANTE**: Revisar bien los cambios que realicemos en el fichero de configuración anterior. Un fallo de sintaxis puede dejar nuestro servidor sin funcionar.

* Hacer captura de pantalla del fichero `/var/www/owncloud/config/config.php`.
* Abrimos un navegador URL: `ip-del-servidor/owncloud`. Ahora debe funcionar el acceso usando la IP tanto desde el propio servidor como desde otra máquina. Comprobarlo.

> Si no funciona el acceso a `http://ip-del-servidor/owncloud` desde otra máquina comprobar si el cortafuegos del servidor está bloqueando el acceso.
> * Vamos a otra máquina y hacemos `nmap -Pn IP-del-servidor`. Debe mostrar los servicios del servidor.

* Abrimos un navegador web, y ponemos en el URL `http://localhost/owncloud`
* Usamos nuestro usuario/clave administrador.
* Crear un usuario normal `nombre-del-alumnoXX`.
* Subiremos algunos archivos al servidor con el usuario anterior.
* Crear un usuario normal `nombre-del-compañeroXX`.
* Le diremos al compañero que suba algunos archivos al servidor con el usuario anterior.

---

# 5. OwnCloud Desktop Client

* Ir a una MV con Windows 7.
* Instalar el sofware cliente de OwnCloud.
   * Usar URL `http://ip-servidor/owncloud`.
* Comprobar cómo se mantienen sincronizados los archivos entre las máquinas.

---

# ANEXO

## A.1 Instalación del servidor OwnCloud para Ubuntu

* [OwnCloud en Debian/Ubuntu](http://hipertextual.com/archivo/2014/10/owncloud/)

## A.2 Instalación del servidor OwnCloud para Raspberry PI

* [BTSync: Clone Dropbox with a Raspberry Pi and BTSync](http://reustle.io/blog/btsync-pi)
