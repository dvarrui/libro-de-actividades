
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
* Realizar la instalación y configuración de la herramienta elegida sobre los SSOO Windows y GNU/Linux. 
* Mostrar su uso mediante ejemplos.

---

# 3. Nube propia con OwnCloud Server en OpenSUSE Leap

En los servicios de almacenamiento y sincronización de ficheros en la nube destacan Dropbox y Google Drive. 
Pero ambas soluciones son cerradas.

Dentro de las soluciones libres disponemos de ownCloud o NetxCloud.

Las fuentes están disponibles para poder instalarlo en máquinas propias o alquiladas,
así como clientes de sincronización para Windows, Linux, Android y próximamente para iOs y Mac.

Para más información [official documentation](https://doc.owncloud.org/) sobre OwnCloud.

## 3.1 Instalar OwnCloud

* Vamos a seguir el siguiente [tutorial](https://github.com/iosifidis/owncloud-opensuse-leap).
* Elegir una MV con OpenSUSE Leap para instalar OwnCloud Server.
* Instalamos lo siguientes paquetes:

```
zypper in apache2 mariadb apache2-mod_php5 php5-gd php5-json php5-fpm php5-mysql php5-curl php5-intl php5-mcrypt php5-zip php5-mbstring php5-zlib
```

> Si tenemos problemas con MySQL. Desinstaler los paquetes, actualizar el sistema y volver a instalar los paquetes.

## 3.2 Create Database

Iniciar el servicio para poder crear la base de datos.

```
systemctl enable mysql.service
systemctl start mysql.service
```

* Por defecto la clave de root está vacía. Vamos a establecer una clave al usuario `root`de la base de datos:
    * `mysqladmin -u root password nueva-clave`
* Crear la base de datos y un usuario:

```
mysql -u root -p
(you'll be asked for your root password)

CREATE DATABASE ocdatabase;
GRANT ALL ON ocdatabase.* TO ocuser@localhost IDENTIFIED BY 'dbpass';
FLUSH PRIVILEGES;
exit
```

Tenemos que:
* Database user: `ocuser`
* Database name: `ocdatabase`
* Database user password: `dbpass`

## 3.3 PHP changes

* Hacer copia de seguridad del fichero /etc/php5/apache2/php.ini
* Now you should edit the file `/etc/php5/apache2/php.ini` and change the values

```
post_max_size = 50G
upload_max_filesize = 25G
max_file_uploads = 200
max_input_time = 3600
max_execution_time = 3600
session.gc_maxlifetime = 3600
memory_limit = 512M
```

Finalmente habilitar las siguientes extensiones:

```
extension=php_gd2.dll
extension=php_mbstring.dll
```

## 3.4 Apache Configuration

* Habilitar/activar los siguientes módulos de Apache. Algunos ya deberían estar habilitados/activos.
```
a2enmod php5
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
* Iniciar el servicio de Apache.
```
systemctl start apache2.service
systemctl enable apache2.service
```

## 3.5 Instalar ownCloud

Antes de la instalación, crear la carpeta de datos con los permisos adecuados.

```
mkdir /opt/owncloud-data
chmod -R 0770 /opt/owncloud-data
chown wwwrun /opt/owncloud-data
```

Vamos a descargar [OwnCloud](https://owncloud.org/install/). Descomprimir y mover a
la carpeta. Comandos de ejemplo:

```
wget https://download.owncloud.org/community/owncloud-X.Y.Z.zip
unzip owncloud-X.Y.Z.zip
cp -r owncloud /srv/www/htdocs
chown -R wwwrun /srv/www/htdocs/owncloud/
```

* Abrir navegador e ir al URL  `http://localhost/owncloud`
    * Poner un usuario/clave que será el administrador de OwnCloud.
    * **¡OJO! Antes de seguir, desplegar la pestaña "Storage & Database"...**
    + Elegir `MyDSQL/MariaDB`.
    * Definir el directorio de datos (DATA FOLDER): `/opt/owncloud-data` 
    * Database user: `ocuser`
    * Database name: `ocdatabase`
    * Database user password: `dbpass`
* Esperar a que termine la instalación.
* Crear el archivo `/srv/www/htdocs/index.html`
* Escribir el nombre del alumno dentro de `index.html`
* Con URL localhost accedemos a `index.html`
* Con URL localhost/owncloud accedemos a la aplicación OwnCloud

---

## 4 Comprobar vía web

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

# 5. OwnCloud Desktop Client

* Ir a una MV con Windows 7.
* Instalar el sofware cliente de OwnCloud.
   * Usar URL `http://ip-servidor/owncloud`.
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
