
# Entrega

* Trabajo individual.
* Apartado 1: Vídeo que muestre la práctica en funcionamiento.
* Apartado2: Entregar un informe de los pasos realizados

#1. Almacenamiento en la nube

* Realizar la instalación y configuración de alguna de las siguientes herramientas a elegir por el alumno: 
    * DropBox
    * Windows Live Mesh, OneDrive, 
    * Ubuntu One, ZumoDrive.
* Realizar una instalación sobre SO Windows y otra sobre GNU/Linux. Mostrar su uso mediante ejemplos.

# 2. OwnCloud

##2.1 Introducción

Últimamente se están poniendo de moda servicios de almacenamiento y sincronización 
de ficheros en la nube, entre los que destacan Dropbox y Google Drive. Ambas soluciones son cerradas.

Dentro de las soluciones libres disponemos de ownCloud, por el que parece 
que apuesta Suse, y que utilizan varios proveedores para ofrecer servicios 
de almacenamiento en la nube con un modelo de negocio freemium, como son OwnCubey GetFreeCloud.

Las fuentes están disponibles para poder instalarlo en máquinas propias o
 alquiladas, así como clientes de sincronización para Windows, Linux, Android y próximamente para iOs y Mac.

##2.2 OwnCloud Server

Enlaces de interés:

    Descarga e instalación de paquetes para GNU/Linux
    Crea tu propio servidor de datos en la nube con owncloud
    http://hipertextual.com/archivo/2014/10/owncloud/

Instalación del servidor OwnCloud para Debian7:

    Añadimos un nuevo repositorio con el paquete que queremos instalar: echo 'deb http://download.opensuse.org/repositories/isv:/ownCloud:/community:/nightly/Debian_7.0/ /' >> /etc/apt/sources.list.d/owncloud.list
    Actualizamos la lista de repositorios: apt-get update
    Instalamos el paquete: apt-get install owncloud
    Abrimos un navegador web, y ponemos en el URL "http://localhost/owncloud"
    Creamos nuestro usuario/clave
    Subiremos algunos archivos al servidor.
    NOTA: Hemos conseguido habilitar el acceso web a nuestro servidor ownCloud desde localhost. Para permitir el acceso web desde otros equipos añadimos la IP del servidor a las opciones "trusted_domains" dentro del fichero de configuración "/var/www/owncloud/config/config.php".


##2.3 OwnCloud Desktop Client

Si instalamos el sofware cliente de OwnCloud en otra máquina podremos comprobar cómo se mantienen sincronizados los archivos entre las máquinas.

 


ANEXO

    BTSync: [INFO] Clone Dropbox with a Raspberry Pi and BTSync (http://reustle.io/blog/btsync-pi)

