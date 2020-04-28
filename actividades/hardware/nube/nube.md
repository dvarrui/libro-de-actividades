
```
Curso       : 201920, 201819
Area        : Almacenamiento en la nube, CPD, servicios
Descripción : Instalar un servidor de almacenamiento en una nube
              con NextCloud en nuestras propias máquinas.
Requisitos  : NextCloud, OpenSUSE Leap
Tiempo      : 4 horas
```

# Nube de almacenamiento

Propuesta de rúbrica:

| Id | Criterio                  | Nivel 0 | Nivel 1 | Nivel 2 |
| -- | ------------------------- | ------- | ------- | ------- |
| C1 | Funcionamiento nube ajena ||||
| C2 | Instalación de NextCloud  ||||
| C3 | Comprobar acceso web      ||||
| C4 | Desktop client            |||.|

# 1. Nube ajena

En este apartado vamos a practicar el almacenamiento usando la nube de un proveedor externo.

Entregar:
* URL Vídeo que muestre la práctica en funcionamiento.
* Trabajo individual.

Pasos a realizar:
* Elegir alguna de las siguientes herramientas: DropBox, Google Drive, OneDrive, Mega. Consultar con el profesor si queremos elegir otra opción.
* Realizar la instalación y configuración de la herramienta elegida sobre Windows, GNU/Linux o Android.
* Mostrar su uso mediante ejemplos.

# 2. Nube propia con NextCoud Server en OpenSUSE Leap

En los servicios de almacenamiento y sincronización de ficheros en la nube destacan Dropbox y Google Drive. Pero ambas soluciones son cerradas.

Dentro de las soluciones libres disponemos de ownCloud o NetxCloud.

Las fuentes están disponibles para poder instalarlo en máquinas propias o alquiladas, así como clientes de sincronización para Windows, Linux, Android y próximamente para iOs y Mac.

Entregar:
* Informe de los pasos realizados.
* Colaborar con otro compañero.
    * Montar nuestro servidor para que lo use el compañero.
    * Usar el servidor de otro compañero.

Enlaces de interés:
* [NextCloud OpenSUSE Leap 15](https://en.opensuse.org/SDB:Nextcloud)
* [Nextcloud, almacenamiento en la nube. Instalación](https://colaboratorio.net/davidochobits/sysadmin/2017/nextcloud-almacenamiento-en-la-nube-instalacion/)

## 2.1 Servidor Web Apache: Instalación y configuración

Enlace de interés:
* Primero nos aseguramos de tener correctamente instalado y configurado Apache, PHP y la base de datos (https://en.opensuse.org/SDB:LAMP_setup).

Instalación y configuración de Apache2
* `zypper in apache2`, Installing Apache2
* `systemctl start apache2`, Starting Apache2
* `systemctl enable apache2`, automatically start the apache server after a reboot.

## 2.2 Cortafuegos

Abrir el acceso Web desde el cortafuegos. El cortafuegos filtra las comunicaciones entrantes y salientes, así que debemos modificar su configuración para permitir acceso por el puerto http(80).

* Abrir el puerto http(80) en el cortafuegos por comandos:
    * `firewall-cmd --add-service=http`
    * `firewall-cmd --permanent --add-service=http`

> También se puede hacer por Yast:
> * Vamos a `Yast -> Cortafuegos`.
> * Añadir en `Servicios Autorizados` de la zona `public` los puertos `http`,`https`.

## 2.3 PHP: Instalación y configuración

* `zypper in php7 php7-mysql apache2-mod_php7`, Installing PHP7
* `a2enmod php7`, to enable mod-php.

## 2.4 Database MariaDB

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

## 2.5 Nextcloud

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

# 3 Comprobar vía web

* Hacer una copia de seguridad del fichero de configuración `/srv/www/htdocs/nextcloud/config/config.php`.
* Para permitir el acceso desde otros equipos, tenemos que añadir la IP del servidor a las opciones
`trusted_domains` dentro del fichero de configuración `/srv/www/htdocs/nextcloud/config/config.php`. Ver ejemplo:

![owncloud-config-php](./files/owncloud-config-php.png)

> **IMPORTANTE**: Revisar bien los cambios que realicemos en el fichero de configuración anterior. Un fallo de sintaxis puede dejar nuestro servidor sin funcionar.

* Hacer captura de pantalla del fichero `/srv/www/htdocs/nextcloud/config/config.php`.
* Abrimos un navegador URL: `ip-del-servidor/nextcloud`. Ahora debe funcionar el acceso usando la IP tanto desde el propio servidor como desde otra máquina. Comprobarlo.

> Si no funciona el acceso a `http://ip-del-servidor/nextcloud` desde otra máquina:
> * (1) Comprobar si el cortafuegos del servidor está bloqueando el acceso. El comando `nmap -Pn IP-del-servidor`, debe mostrar los servicios web abiertos.
> * (2) Para abrir el cortafuegos, vamos a `Yast -> Cortafuegos`. Añadir en `Servicios Autorizados` de la `Zona externa` a `HTTP Server` y  `HTTPS Server`.

* Abrimos un navegador web, y ponemos en el URL `http://localhost/nextcloud`
* Usamos nuestro usuario/clave administrador.

| Crear usuario | Acción a realizar |
| ------------- | ----------------- |
| Crear usuario normal `nombre-del-alumnoXX` | Nosotros subiremos algunos archivos al servidor |
| Crear un usuario normal `nombre-del-compañeroXX` | Nuestro compañeor subirá algunos archivos al servidor |

---

# 4. NextCloud Desktop Client

Enlace de interés:
* [Instalar y configurar un cliente de Nextcloud en Linux](https://geekland.eu/instalar-cliente-de-nextcloud-linux/)

Ir a una MV con Windows 7:
* Instalar el sofware cliente de NextCloud.
   * Usar URL `http://ip-servidor/nextcloud`.
* Comprobar cómo se mantienen sincronizados los archivos entre las máquinas.
