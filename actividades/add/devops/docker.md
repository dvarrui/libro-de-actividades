
#1.Introducción

Enlaces de interés:
* [Docker installation on SUSE](https://docs.docker.com/engine/installation/linux/SUSE)
* [Cómo instalar y usar docker](http://codehero.co/como-instalar-y-usar-docker/)
* [Docker for beginners](http://prakhar.me/docker-curriculum/)

Es muy común que nos encontremos desarrollando una aplicación y llegue 
el momento que decidamos tomar todos sus archivos y migrarlos ya sea al 
ambiente de producción, de prueba o simplemente probar su comportamiento 
en diferentes plataformas y servicios. Para situaciones de este estilo 
existen herramientas que, entre otras cosas, nos facilitan el embalaje 
y despliegue de la aplicación, es aquí donde entra en juego Docker.

Esta herramienta nos permite crear lo que ellos denominan contenedores, 
lo cual son aplicaciones empaquetadas auto-suficientes, muy livianas
 que son capaces de funcionar en prácticamente cualquier ambiente, 
 ya que tiene su propio sistema de archivos, librerías, terminal, etc. 

#2. Preparativos

Nesitaremos una MV OpenSUSE 13.2.

> Se requiere versión del Kernel 3.10 o superior (`uname -a`)

#3. Instalación y configuración

Ejecutar como superusuario:
```
zypper in docker              # Instala docker
systemctl start docker        # Inicia el servicio
docker version                # Debe mostrar información del cliente y del servidor
usermod -a -G docker USERNAME # Añade permisos a nuestro usuario
```

> Salir de la sesión y volver a entrar con nuestro usuario.

Ejecutar con nuestro usuario para comprobar que todo funciona:
``` 
docker images           # Muestra las imágenes descargadas hasta ahora
docker ps -a            # Muestra todos los contenedores creados
docker run hello-world  # Descarga y ejecuta un contenedor con la imagen hello-world
docker images
docker ps -a
``` 

> **Habilitar el acceso a la red externa para los contenedores**
>
> If you want your containers to be able to access the external network, 
you must enable the net.ipv4.ip_forward rule. To do this, use YaST.
>
> * Para openSUSE13.2 (cuando el método de configuracion de red es Wicked).
`Yast -> Dispositivos de red -> Encaminamiento -> Habilitar reenvío IPv4`
> * Cuando la red está gestionada por Network Manager, en lugar de usar YaST 
debemos editar el fichero `/etc/sysconfig/SuSEfirewall2` y poner `FW_ROUTE="yes"`.
> * Para openSUSE Tumbleweed `Yast -> Sistema -> Configuración de red -> Menú de encaminamiento`.
>
> ¿Recuerdas lo que implica `forwarding` en los dispositivos de red?

#4. Montar un contenedor personalizado

```
docker images          # Vemos las imágenes disponibles localmente
docker search debian   # Buscamos en los repositorios de Docker Hub
                       # contenedores con la etiqueta `debian`
docker pull debian:8   # Descargamos contenedor `debian:8` en local
docker pull opensuse
docker ps -a           # Vemos todos los contenedores
docker ps              # Vemos sólo los contenedores en ejecución
``` 

Vamos a crear un contenedor con nombre `mv_debian` a partir de la imagen `debian:8`, y ejecutaremos `/bin/bash`: 
```
docker run --name=mv_debian -i -t debian:8 /bin/bash

(Estamos dentro del contenedor)
root@ab80213de486:/# cat /etc/motd   # Comprobamos que estamos en Debian
root@ab80213de486:/# exit            # Salimos del contedor
(Fuera del contenedor)

docker ps 
docker ps -a           # Vemos el contenedor parado
docker start mv_debian # Iniciamos el contenedor
docker ps 
docker stop mv_debian  # Paramos el contenedor
docker ps 
docker ps -a           # Vemos el contenedor parado
docker rm IDcontenedor # Eliminamos el contenedor
docker ps -a 
```

#ANEXO

Enlaces de interés:
* [getting-started-with-docker](http://www.linux.com/news/enterprise/systems-management/873287-getting-started-with-docker)

Docker es una tecnología contenedor de aplicaciones construida sobre LXC.

