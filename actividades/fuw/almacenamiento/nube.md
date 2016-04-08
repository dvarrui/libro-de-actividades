
```
Utilizada en los cursos 201415 y 201314
Modificada en el curso 201516 para usar OpenSUSE13.2
``` 

#1. Entrega

* Apartado 2:
    * Trabajo individual.
    * Vídeo que muestre la práctica en funcionamiento.
* Apartados 4 y 5:
    * Colaborar con otro compañero.
        * Montar nuestro servidor para que lo use el compañero.
        * Usar el servidor de otro compañero.
    * Entregar un informe de los pasos realizados

#2. Nube ajena

Almacenamiento en la nube de un proveedor externo.

* Realizar la instalación y configuración de alguna de las siguientes herramientas a elegir por el alumno: 
    * DropBox
    * Windows Live Mesh, OneDrive, 
    * Ubuntu One, ZumoDrive.
* Realizar una instalación sobre SO Windows y otra sobre GNU/Linux. Mostrar su uso mediante ejemplos.

#3. Nube propia

Últimamente se están poniendo de moda servicios de almacenamiento y sincronización 
de ficheros en la nube, entre los que destacan Dropbox y Google Drive. Ambas soluciones son cerradas.

Dentro de las soluciones libres disponemos de ownCloud, por el que parece 
que apuesta Suse, y que utilizan varios proveedores para ofrecer servicios 
de almacenamiento en la nube con un modelo de negocio freemium, como son OwnCubey GetFreeCloud.

Las fuentes están disponibles para poder instalarlo en máquinas propias o
 alquiladas, así como clientes de sincronización para Windows, Linux, Android y próximamente para iOs y Mac.

#4. OwnCloud Server

##4.1 Enlaces de interés:

Descarga e instalación de paquetes para GNU/Linux, y crea tu propio 
servidor de datos en la nube con owncloud

Enlaces de interés:
* [OwnCloud en OpenSuse13.2](https://www.howtoforge.com/owncloud-install-on-opensuse-13.2)
* [OwnCloud en Debian/Ubuntu](http://hipertextual.com/archivo/2014/10/owncloud/)

> **Instalación del servidor OwnCloud para Debian7**
>
> * Añadimos un nuevo repositorio con el paquete que queremos instalar: 
>     * echo 'deb http://download.opensuse.org/repositories/isv:/ownCloud:/community:/nightly/Debian_7.0/ /' >> /etc/apt/sources.list.d/owncloud.list
> * Actualizamos la lista de repositorios: `apt-get up...`
> * Instalamos el paquete: `apt-get .... owncloud`

##4.2 Instalar OwnCloud

* Elegir una MV con OpenSUSE13.2 para instalar OwnCloud Server.
    * [OwnCloud en OpenSuse13.2](https://www.howtoforge.com/owncloud-install-on-opensuse-13.2)
* Abrimos un navegador web, y ponemos en el URL "http://localhost/owncloud"
* Creamos nuestro usuario/clave
* Subiremos algunos archivos al servidor.

> Hemos conseguido habilitar el acceso web a nuestro servidor ownCloud desde localhost. 
> Para permitir el acceso web desde otros equipos añadimos la IP del servidor a las opciones 
"trusted_domains" dentro del fichero de configuración "/var/www/owncloud/config/config.php".

#5. OwnCloud Desktop Client

* Ir a una MV con Windows 7.
* Instalar el sofware cliente de OwnCloud.
* Comprobar cómo se mantienen sincronizados los archivos entre las máquinas.

#ANEXO
* [BTSync: Clone Dropbox with a Raspberry Pi and BTSync](http://reustle.io/blog/btsync-pi)

