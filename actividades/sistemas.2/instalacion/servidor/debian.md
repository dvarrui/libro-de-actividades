```
Estado   : En construcción...
Curso    :
Software : SO Debian
```
---

# Servidor de actualizaciones (Debian)

En esta práctica vamos a crear un servidor de actualizaciones para Debian.
Podemos elegir entre varios métodos a la hora de crear nuestro propio repositorio.

Algunas de estas herramientas crean un repositorio completo con todos
los paquetes de la distribución, y otras permiten crear un repositorio parcial
seleccionando los paquetes que queramos tener.

Por motivos de espacio en disco, consumo del ancho de banda de red y
tiempo vamos a crear un repositorio parcial (apartado 2.2)

---

# 1. Crear un repositorio completo

## 1.1 apt-mirror

Características:
* Con apt-mirror podemos crear una copia de otro repositorio externo existente.
* Los clientes se conectan vía HTTP (servidor web apache2).
* Se requieren 139GB de espacio en disco.

Enlace de interés para Crear un repositorio con apt-mirror para Ubuntu/Debian

Instalar los paquetes apt-mirror, apache2.

* La herramienta "apt-mirror" se usa para convertir la máquina en un repositorio propio.
* y "apache2" es un servidor web que dará acceso a los clientes al repositorio a través de la red.

Modificar el fichero de configuración /etc/apt/mirror.lst.

* Modificamos el parámetro "base_path", para que apunte a la ruta donde tendremos nuestro repositorio.
* Dejaremos las rutas a los repositorios principal, seguridad y actualizaciones.

Ponemos en marcha el repositorio local

## 1.2 debmirror

Características:
* Con debmirror creamos un repositorio local que se mantiene sincronizado (usando rsync) con otro repositorio externo.
* Los clientes se conectan vía HTTP (servidor web apache2).
* Se requieren 139GB de espacio en disco.

Enlace de interés: Como crear un repositorio Debian con debmirror

## 1.3. Usando los DVD's de instalación

Características:
* Crear un repositorio local usando el contenido de 3DVDs de instalación.
* Los clientes se conectan vía HTTP (servidor web apache2).
* Se requieren 15GB de espacio en disco.

Enlace de interés: Crear un repositorio local Debian desde los DVDs

---

# 2. Crear un repositorio parcial

## 2.1. Repositorio local

Características:
* El respositorio sólo estará disponible para la máquina local
* El consumo de disco depende de los paquetes que elijamos para nuestro repositorio.

Enlace de interés: Crear un repositorio local con unos pocos paquetes

NOTA:
* Para asegurarnos que nuestros paquetes se instalan desde el repositorio
y no se cogen de la caché, ejecutamos el comando "apt-get clean".
Con este comando "limpiamos" la caché que contiene todos los paquetes deb
descargados hasta la fecha.
* Modificamos el fichero con la lista de los repositorios de actualizaciones
para que apunte lo que cabamos de crear.
* Hacer copia de seguridad al fichero /etc/apt/sources.list
* Modificar el fichero /etc/apt/sources.list, de modo que sólo tenga activo
el repositorio "deb file:///var/www/repo ./"
* Actualizamos el catálogo con el comando "apt-get update".

## 2.2 reprepro

Características:
* Los clientes se conectan vía HTTP, a través de un servicio web.
* El consumo de disco depende de los paquetes que elijamos para nuestro repositorio.

Servidor de actualizaciones

    Vamos a la máquina que será el servidor de actualizaciones.
    El repositorio que vamos a crear no tendrá los paquetes firmados.
    Primero vamos a obtener varios paquetes deb seleccionados por nosotros para nuestro futuro repositorio personal.
        Creamos el directorio /opt/misdescargas.
        Descargamos los paquetes: cd /opt/misdescargas; apt-get download tree geany ruby fish
    Instalar y configurar la herramienta reprepro que nos servirá para crear nuestro repositorio.
        Instalamos la herramienta: apt-get install reprepro
        Creamos el directorio para nuestro repositorio: mkdir -p /opt/mirepositorio/conf
        cd /opt/mirepositorio
        Creamos conf/distributions con el siguiente contenido:
    Origin: nombre-del-alumno
    Label: nombre-del-alumno
    Codename: nombre-del-alumno
    Architectures: amd64
    Components: main
    Description: IES Puerto de la Cruz
    DebIndices: Packages Release . .gz .bz2
        Ejecutamos el comando tree para comprobar los directorios y ficheros que tenemos por el momento en nuestro repositorio.
        Para crear los ficheros con los índeces usamos el comando: cd /opt/mirepositorio; reprepro -VVV export
        Ejecutamos el comando tree para comprobar que se nos han creado algunos directorios y los ficheros con los índices.
        Crear enlaces: cd /opt/mirepositorio; reprepro -VVV createsymlinks
    Ya tenemos creado el repositorio, a continuación vamos a añadir algunos paquetes deb al mismo.
        reprepro -S main includedeb nombre-del-alumno /opt/misdescargas/fish...deb
        reprepro -S main includedeb nombre-del-alumno /opt/misdescargas/geany...deb
        reprepro -S main includedeb nombre-del-alumno /opt/misdescargas/tree...deb
        reprepro -S main includedeb nombre-del-alumno /opt/misdescargas/ruby...deb
        Ejecutamos el comando tree para comprobar que los paquetes seleccionados se han incorporado a la estructura de directorios del repositorio.
        Ejecutamos el comando reprepro list nombre-del-alumno para comprobar los paquetes disponibles en nuestro repositorio.

Instalación del servicio web

    Ahora hay que instalar el servicio web para que los clientes se puedan conectar vía HTTP con el servidor de actualizaciones.
        En este caso elegiremos el servidor web apache. Para instalarlo: apt-get install apache2
        Dentro de la carpeta del servidor web, creamos un enlace a la ruta de nuestro repositorio: ln -s /opt/mirepositorio/ /var/www/mirepositorioweb. Esto lo hacemos para que nuestro repositorio esté disponible a través del servidor web.
        Para comprobarlo, podemos abrir un navegador y en el URL ponemos http://ip-del-servidor/mirepositorioweb.

Configurar el cliente

    Vamos a la máquina que será nuestro cliente de actualizaciones.
    Tenemos que incorporar nuestro repositorio personal a la lista de repositorios del equipo cliente para que encuentre los paquetes por la red.
    Primero vamos a vaciar los ficheros deb que se hayan quedado en la caché: apt-get clean
    Modificamos el fichero /etc/apt/sources.list, comentando todas las líneas para desactivar los respositorios actuales.
    Añadiremos la definición de nuestro repositorio personal al fichero sources.list: deb http://ip-del-servidor/mirepositorioweb nombre-del-alumno main
    Vamos a tratar de actualizar la lista de paquetes disponibles en nuestro repositorio: apt-get update
    Instalamos alguno de los paquetes del repositorio:
        apt-get install tree (Al no estar el paquete firmado en el repositorio, se nos pide confirmación)
        apt-get -y --force-yes install tree (Al no estar el paquete firmado en el repositorio, se nos pide confirmación pero de forma predeterminada decidimos responder Yes a todas las preguntas del instalador)
    Hay que notar que al no estar los paquetes firmados se nos solicita autorización para su instalación.

Enlaces de interés:

    http://enavas.blogspot.com.es/2012/10/crear-nuestro-propio-repositorio-de.html?m=1
    https://davehall.com.au/blog/dave/2010/02/06/howto-setup-private-package-repository-reprepro-nginx
    http://santi-bassett.blogspot.com.es/2014/07/setting-up-apt-repository-with-reprepro.html
    http://wiki.canaima.softwarelibre.gob.ve/wiki/Haciendo_repositorios_de_paquetes_binarios_con_reprepro
