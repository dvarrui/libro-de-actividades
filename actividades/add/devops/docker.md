
# 1. Introducción

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

Docker es una tecnología contenedor de aplicaciones construida sobre LXC.
---

# 2. Requisitos

Vamos a usar MV OpenSUSE.
Nos aseguraremos que tiene una versión del Kernel 3.10 o superior (`uname -a`).

---

# 3. Instalación y primeras pruebas

* Enlaces de interés:
    * [EN - Docker installation on SUSE](https://docs.docker.com/engine/installation/linux/SUSE)
    * [ES - Curso de Docker en vídeos](jgaitpro.com/cursos/docker/)
* Ejecutar como superusuario:
```
zypper in docker              # Instala docker
systemctl start docker        # Inicia el servicio
                              # "docker daemon" hace el mismo efecto
docker version                # Debe mostrar información del cliente y del servidor
```
* Salir de la sesión y volver a entrar con nuestro usuario.
* Ejecutar con nuestro usuario para comprobar que todo funciona:
```
docker images           # Muestra las imágenes descargadas hasta ahora
docker ps -a            # Muestra todos los contenedores creados
docker run hello-world  # Descarga y ejecuta un contenedor con la imagen hello-world
                        # Sólo se muestra unos mensajes en pantalla.
docker images
docker ps -a            # El contenedor está estado 'Exited'
```

---

# 4. Configuración de la red

**Habilitar el acceso a la red externa a los contenedores**

Si queremos que nuestro contenedor tenga acceso a la red exterior, debemos
activar la opción IP_FORWARD (`net.ipv4.ip_forward`). Lo podemos hacer en YAST.

¿Recuerdas lo que implica `forwarding` en los dispositivos de red?

* Para openSUSE13.2 (cuando el método de configuracion de red es Wicked).
`Yast -> Dispositivos de red -> Encaminamiento -> Habilitar reenvío IPv4`
* Cuando la red está gestionada por Network Manager, en lugar de usar YaST
debemos editar el fichero `/etc/sysconfig/SuSEfirewall2` y poner `FW_ROUTE="yes"`.
* Para openSUSE Tumbleweed `Yast -> Sistema -> Configuración de red -> Menú de encaminamiento`.

Reiniciar el equipo para que se apliquen los cambios.

## 4.1 Más comandos

Información sobre otros comandos útiles:

* `docker stop CONTAINERID`, parar un contenedor que estaba iniciado.
* `docker start CONTAINERID`, inicia un contenedor que estaba parado.
* `docker attach CONTAINERID`, conecta el terminal actual con el interior de contenedor.
* `docker ps`, muestra los contenedores en ejecución.
* `docker ps -a`, muestra todos los contenedores en ejecución o no.
* `docker rm CONTAINERID`, eliminar un contenedor.
* `docker rmi IMAGENAME`, eliminar una imagen.

---

# 5. Creación manual

Nuestro SO base es OpenSUSE, pero vamos a crear un contenedor Debian8,
y dentro instalaremos Nginx.

## 5.1 Crear una imagen manualmente

```
docker images          # Vemos las imágenes disponibles localmente
docker search debian   # Buscamos en los repositorios de Docker Hub
                       # contenedores con la etiqueta `debian`
docker pull debian:9   # Descargamos una imagen `debian:9` en local
docker images
docker ps -a           # Vemos todos los contenedores
docker ps              # Vemos sólo los contenedores en ejecución
```  

* Vamos a crear un contenedor con nombre `con_debian` a partir de la
imagen `debian:9`, y ejecutaremos `/bin/bash`:

```
docker run --name=con_debian -i -t debian:9 /bin/bash

(Estamos dentro del contenedor)
root@IDContenedor:/# cat /etc/motd            # Comprobamos que estamos en Debian
root@IDContenedor:/# apt-get update
root@IDContenedor:/# apt-get install -y nginx # Instalamos nginx en el contenedor
root@IDContenedor:/# apt-get install -y vim   # Instalamos editor vi en el contenedor
root@IDContenedor:/# /usr/sbin/nginx          # Iniciamos el servicio nginx
root@IDContenedor:/# ps -ef
```

* Creamos un fichero HTML (`holamundo.html`).

```
root@IDContenedor:/# echo "<p>Hola nombre-del-alumno</p>" > /var/www/html/holamundo.html
```

* Creamos tambien un script `/root/server.sh` con el siguiente contenido:

```
#!/bin/bash

echo "Booting Nginx!"
/usr/sbin/nginx &

echo "Waiting..."
while(true) do
  sleep 60
done
```

Recordatorio:
* Hay que poner permisos de ejecución al script para que se pueda ejecutar.
* La primera línea de un script, siempre debe comenzar por `#!/`, sin espacios.

> Este script inicia el programa/servicio y entra en un bucle, para permanecer
activo y que no se cierre el contenedor.
> Más adelante cambiaremos este script por la herramienta `supervisor`.

* Ya tenemos nuestro contenedor auto-suficiente de Nginx, ahora debemos
crear una nueva imagen con los cambios que hemos hecho, para esto
abrimos otra ventana de terminal y busquemos el IDContenedor:

```
david@camaleon:~/devops> docker ps
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS         PORTS  NAMES
7d193d728925   debian:8   "/bin/bash"   2 minutes ago    Up 2 minutes          con_debian
```

* Ahora con esto podemos crear la nueva imagen a partir de los cambios que realizamos sobre la imagen base:

```
docker commit 7d193d728925 dvarrui/nginx
docker images
```

> Los estándares de Docker estipulan que los nombres de las imagenes deben
seguir el formato `nombreusuario/nombreimagen`.
> Todo cambio que se haga en la imagen y no se le haga commit se perderá en cuanto se cierre el contenedor.

```
docker ps
docker stop con_debian  # Paramos el contenedor
docker ps
docker ps -a           # Vemos el contenedor parado
docker rm IDcontenedor # Eliminamos el contenedor
docker ps -a
```

## 5.2 Crear contenedor con Nginx

Bien, tenemos una imagen con Nginx instalado, probemos ahora la magia de Docker.

* Iniciemos el contenedor de la siguiente manera:
```
docker ps
docker ps -a
docker run --name=con_nginx -p 80 -t dvarrui/nginx /root/server.sh
Booting Nginx!
Waiting...
```
Los mensajes muestran que el script server.sh está en ejecución.
No parar el programa. Es correcto.

> * El argumento `-p 80` le indica a Docker que debe mapear el puerto especificado
del contenedor, en nuestro caso el puerto 80 es el puerto por defecto
sobre el cual se levanta Nginx.
> * El script `server.sh`nos sirve para iniciar el servicio y permanecer en espera.
Lo podemos hacer también con el prgorama `Supervisor`.

* Abrimos una nueva terminal.
* `docker ps`, nos muestra los contenedores en ejecución. Podemos apreciar
que la última columna nos indica que el puerto 80 del contenedor
está redireccionado a un puerto local `0.0.0.0.:NNNNNN->80/tcp`.
* Abrir navegador web y poner URL `0.0.0.0.:NNNNNN`. De esta forma nos
conectaremos con el servidor Nginx que se está ejecutando dentro del contenedor.

![docker-url-nginx.png](./files/docker-url-nginx.png)

* Comprobar el acceso a `holamundo.html`.
* Paramos el contenedor y lo eliminamos.
```
docker ps
docker stop con_nginx
docker ps
docker ps -a
docker rm con_nginx
docker ps -a
```

> Como ya tenemos una imagen docker, podemos crear nuevos contenedores
cuando lo necesitemos.

---

# 6. Crear un contenedor con `Dockerfile`

Ahora vamos a conseguir el mismo resultado del apartado anterior, pero
usando un fichero de configuración, llamado `Dockerfile`

## 6.1 Comprobaciones iniciales:

```
docker images
docker ps
docker ps -a
```

## 6.2 Preparar ficheros

* Crear directorio `/home/nombre-alumno/dockerXX`, poner dentro los siguientes ficheros:
    * Dockerfile
    * holamundo.html
    * server.sh
* Crear el fichero `Dockerfile` con el siguiente contenido:
```
FROM debian:8

MAINTAINER Nombre-del-Alumno 1.0

RUN apt-get update
RUN apt-get install -y apt-utils
RUN apt-get install -y nginx
RUN apt-get install -y vim

COPY holamundo.html /var/www/html
RUN chmod 666 /var/www/html/holamundo.html

COPY server.sh /root
RUN chmod +x /root/server.sh

EXPOSE 80

CMD ["/root/server.sh"]
```

> Los ficheros `server.sh` y `holamundo.html` que vimos en el apartado anterior,
tienen que estar en el mismo directorio del fichero `Dockerfile`.

## 6.3 Crear imagen desde el `Dockerfile`

El fichero [Dockerfile](./files/Dockerfile) contiene la información
necesaria para contruir el contenedor, veamos:

```
cd /home/nombre-del-alumno/dockerXX # Entramos al directorio del Dockerfile
docker images                       # Consultamos las imágenes disponibles
docker build -t dvarrui/nginx2 .    # Construye imagen a partir del Dockefile
                                    # ¡¡¡OJO el punto final es necesario!!!
docker images                       # Debe aparecer nuestra nueva imagen
```

## 6.4 Crear contenedor y comprobar

* A continuación vamos a crear un contenedor con el nombre `con_nginx2`,
a partir de la imagen `dvarrui/nginx2`, y queremos que este contenedor
ejecute el programa `/root/server.sh`.

```
docker run --name=con_nginx2 -p 80 -t dvarrui/nginx2 /root/server.sh
```

* Desde otra terminal hacer `docker...`, para averiguar el puerto de escucha
del servidor Nginx.
* Comprobar en el navegador URL: `http://localhost:PORTNUMBER`
* Comprobar en el navegador URL: `http://localhost:PORTNUMBER/holamundo.html`

---

# 7. Migrar las imágenes de docker a otro servidor

¿Cómo puedo llevar los contenedores docker a un nuevo servidor?

> Enlaces de interés
>
> * https://www.odooargentina.com/forum/ayuda-1/question/migrar-todo-a-otro-servidor-imagenes-docker-397
> * http://linoxide.com/linux-how-to/backup-restore-migrate-containers-docker/

Crear un imagen de contenedor:
* `docker ps`, muestra los contenedores que tengo en ejecución.
* `docker commit -p CONTAINERID nombre-alumno/backupXX`, grabar una imagen de nombre "nombre-alumno/backupXX" a partir del contenedor CONTAINERID.
* `docker images`comprobar que se ha creado la imagen "nombre-alumno/backupXX".

Exportar imagen docker a fichero:
* `docker save -o ~/backupXX.tar nombre-alumno/backupXX`, guardamos la imagen
"nombre-alumno/backupXX" en un fichero tar.

Importar imagen docker desde fichero:
* Nos llevamos el tar a otra máquina con docker instalado, y restauramos.
* `docker load -i ~/backupXX.tar`, cargamos la imagen docker a partir del fichero tar.
* `docker images`, comprobamos que la nueva imagen está disponible.
* Crear contenedor a partir de la nueva imagen.

---

# 8. Limpiar

Cuando terminamos con los contedores y ya no lo necesitamos, es buena idea
pararlos y/o destruirlos.

---

# ANEXO

## A.1 supervisord

Dentro del contenedor:
* Instalar el supervidor `apt-get install -y supervisor`
* Crear una configuración personalizado para Nginx con Supervidor. Crear `/etc/supervisor/conf.d/supervisord.conf` con el siguiente contenido:
```
[supervisord]
nodaemon=true

[program:nginx]
command = /usr/sbin/nginx -g "daemon off;"
username = www-data
autorestart = true
stdout_logfile = /dev/stdout
stdout_logfile_maxbytes = 0
stderr_logfile = /dev/stderr
stderr_logfile_maxbytes = 0
```
* Hacemos la imagen dvarrui/nginx.
* En la máquina real podemos invocar el contenedor de la siguiente forma:
`docker run --name=con_nginx -p 80 -t dvarrui/nginx /usr/bin/supervisord -c /etc/supervisor/conf.d/supervisord.conf`

## A.2 Enlaces de interés

* [Docker for beginners](http://prakhar.me/docker-curriculum/)
* [getting-started-with-docker](http://www.linux.com/news/enterprise/systems-management/873287-getting-started-with-docker)

## A.3 Kubernetes

Kubernetes (commonly referred to as "K8s") is an open source container cluster manager originally designed by Google and donated to the Cloud Native Computing Foundation. It aims to provide a "platform for automating deployment, scaling, and operations of application containers across clusters of hosts".[3] It usually works with the Docker container tool and coordinates between a wide cluster of hosts running Docker.

* https://www.adictosaltrabajo.com/tutoriales/primeros-pasos-con-kubernetes/
* http://www.javiergarzas.com/2016/02/kubernetes-for-dummies-explicado-en-10-minutos.html
