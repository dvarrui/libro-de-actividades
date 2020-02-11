
```
Curso      : 201920, 201819, 201716
Area       : Sistemas operativos, automatización, devops
Requisitos : Se recomienda usar SSOO GNU/Linux
Tiempo
Descripción: Introducción a los contenedores con Docker
```

---
# 1. Contenedores con Docker

> Enlaces de interés
> * [Docker for beginners](http://prakhar.me/docker-curriculum/)
> * [getting-started-with-docker](http://www.linux.com/news/enterprise/systems-management/873287-getting-started-with-docker)

## 1.1 Introducción

Es muy común que nos encontremos desarrollando una aplicación, y llegue el momento que decidamos tomar todos sus archivos y migrarlos, ya sea al ambiente de producción, de prueba, o simplemente probar su comportamiento en diferentes plataformas y servicios.

Para situaciones de este estilo existen herramientas que, entre otras cosas, nos facilitan el embalaje y despliegue de la aplicación, es aquí donde entra en juego Docker.

Esta herramienta nos permite crear lo que ellos denominan contenedores, lo cual son aplicaciones empaquetadas auto-suficientes, muy livianas, que son capaces de funcionar en prácticamente cualquier ambiente, ya que tiene su propio sistema de archivos, librerías, terminal, etc.

Docker es una tecnología contenedor de aplicaciones construida sobre LXC.

## 1.2. Requisitos

* Vamos a usar MV OpenSUSE.
* Nos aseguraremos que tiene una versión del Kernel 3.10 o superior (`uname -a`).

## 1.3 Habilitar el acceso a la red externa a los contenedores

Si queremos que nuestro contenedor tenga acceso a la red exterior, debemos activar la opción IP_FORWARD (`net.ipv4.ip_forward`). Lo podemos hacer en YAST. ¿Recuerdas lo que implica `forwarding` en los dispositivos de red?

| Sistema operativo | Activar "forwarding" |
| ----------------- | -------------------- |
| OpenSUSE Leap (configuración de red es Wicked) | Yast -> Dispositivos de red -> Encaminamiento -> Habilitar reenvío IPv4 |
| Cuando la red está gestionada por Network Manager | En lugar de usar YaST debemos editar el fichero "/etc/sysconfig/SuSEfirewall2" y poner FW_ROUTE="yes" |
| OpenSUSE Tumbleweed  | Yast -> Sistema -> Configuración de red -> Menú de encaminamiento |

* Reiniciar el equipo para que se apliquen los cambios de configuración anteriores.

---
# 2. Instalación y primeras pruebas

> Enlaces de interés:
> * [EN - Docker installation on SUSE](https://docs.docker.com/engine/installation/linux/SUSE)
> * [ES - Curso de Docker en vídeos](jgaitpro.com/cursos/docker/)

## 2.1 Instalación

Ejecutar como superusuario:
* `zypper in docker`, instalar docker.
* `systemctl start docker`, iniciar el servicio. NOTA: El comando `docker daemon` hace el mismo efecto.

**Entregar:**
* `docker version`, muestra información de las versiones cliente y servidor.

Salir de la sesión y volver a entrar con nuestro usuario.

## 2.2 Primera prueba

* Ejecutar con nuestro usuario para comprobar que todo funciona:

```
docker images           # Muestra las imágenes descargadas hasta ahora
                        # No debe haber ninguna
docker ps -a            # Muestra todos los contenedores creados
                        # No debe haber ninguno
```

* Primera prueba:

```
docker run hello-world  # Descarga y ejecuta un contenedor
                        # con la imagen "hello-world"
                        # Sólo muestra un mensaje en pantalla.
docker images           # Vemos la nueva imagen
docker ps -a            # El contenedor está estado 'Exited'
docker stop IDContainer # Parar el conteneder
docker rm IDContainer   # Eliminar el contenedor
```

## 2.3 TEORIA: Información

Tabla de referencia para no perderse:

| Software   | Base   | Sirve para crear   | Aplicaciones |
| ---------- | ------ | ------------------ | ------------ |
| VirtualBox | ISO    | Máquinas virtuales | N |
| Vagrant    | Box    | Máquinas virtuales | N |
| Docker     | Imagen | Contenedores       | 1 |


Información sobre otros comandos útiles:

| Comando                     | Descripción |
| --------------------------- | ------------------- |
| `docker stop CONTAINERID`   | parar un contenedor |
| `docker start CONTAINERID`  | iniciar un contenedor |
| `docker attach CONTAINERID` | conectar el terminal actual con el interior de contenedor |
| `docker ps`                 | mostrar los contenedores en ejecución |
| `docker ps -a`              | mostrar todos los contenedores (en ejecución o no) |
| `docker rm CONTAINERID`     | eliminar un contenedor |
| `docker rmi IMAGENAME`      | eliminar una imagen |

---
# 3. Creación manual

Nuestro SO base es OpenSUSE, pero vamos a crear un contenedor Debian,
y dentro instalaremos Nginx.

## 3.1 Crear una imagen manualmente

```
docker images        # Vemos las imágenes disponibles localmente
docker search debian # Buscamos en los repositorios de Docker Hub
                     # contenedores con la etiqueta `debian`
docker pull debian:9 # Descargamos una imagen `debian:9` en local
docker images
docker ps -a         # Vemos todos los contenedores
docker ps            # Vemos sólo los contenedores en ejecución
```  

* Vamos a crear un contenedor con nombre `con_debian` a partir de la
imagen `debian:9`, y ejecutaremos `/bin/bash`:

```
docker run --name=con_debian -i -t debian:9 /bin/bash
```

## 3.2 Personalizar el contenedor

Ahora que estamos dentro del contenedor, vamos a personalizarlo a nuestro gusto:

```
root@IDContenedor:/# cat /etc/motd            # Comprobamos que estamos en Debian
root@IDContenedor:/# apt-get update
root@IDContenedor:/# apt-get install -y nginx # Instalamos nginx en el contenedor
root@IDContenedor:/# apt-get install -y vim   # Instalamos editor vi en el contenedor
root@IDContenedor:/# /usr/sbin/nginx          # Iniciamos el servicio nginx
root@IDContenedor:/# ps -ef                   # Si no se encuentra el comando
                                              # ¿lo podemos instalar?
```

* Creamos un fichero HTML (`holamundo.html`).

```
root@IDContenedor:/# echo "<p>Hola nombre-del-alumno</p>" > /var/www/html/holamundo.html
```

## 3.3 Crear una imagen a partir del contenedor

* Ya tenemos nuestro contenedor auto-suficiente de Nginx, ahora debemos crear una nueva imagen con los cambios que hemos hecho, para esto abrimos otra ventana de terminal y busquemos el IDContenedor:

```
> docker ps
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS         PORTS  NAMES
7d193d728925   debian:9   "/bin/bash"   2 minutes ago    Up 2 minutes          con_debian
```

* Ahora con esto podemos crear la nueva imagen a partir de los cambios que realizamos sobre la imagen base:

```
docker commit 7d193d728925 dvarrui/nginx
docker images
```

> NOTA:
>
> * Los estándares de Docker estipulan que los nombres de las imagenes deben seguir el formato `nombreusuario/nombreimagen`.
> * Todo cambio que se haga en la imagen, y no se le haga commit se perderá en cuanto se cierre el contenedor.

```
docker ps
docker stop con_debian  # Paramos el contenedor
docker ps
docker ps -a           # Vemos el contenedor parado
docker rm IDcontenedor # Eliminamos el contenedor
docker ps -a
```

---
# 4. Crear contenedor a partir de nuestra imagen

## 4.1 Crear contenedor con Nginx

Bien, tenemos una imagen con Debian/Nginx instalado, probemos ahora la magia de Docker.

* Iniciemos el contenedor de la siguiente manera:

```
docker ps
docker ps -a
docker run --name=con_nginx -p 80 -t dvarrui/nginx /usr/sbin/nginx -d
```

:eyes: El argumento `-p 80` le indica a Docker que debe mapear el puerto especificado del contenedor, en nuestro caso el puerto 80 es el puerto por defecto sobre el cual se levanta Nginx.

## 4.2 Buscar los puertos de salida

* Abrimos una nueva terminal.
* `docker ps`, nos muestra los contenedores en ejecución. Podemos apreciar que la última columna nos indica que el puerto 80 del contenedor está redireccionado a un puerto local `0.0.0.0.:NNNNNN->80/tcp`.
* Abrir navegador web y poner URL `0.0.0.0.:NNNNNN`. De esta forma nos
conectaremos con el servidor Nginx que se está ejecutando dentro del contenedor.

![docker-url-nginx.png](./images/docker-url-nginx.png)

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
# 5. Crear un contenedor a partir de un `Dockerfile`

Ahora vamos a conseguir el mismo resultado del apartado anterior, pero
usando un fichero de configuración, llamado `Dockerfile`.

## 5.1 Comprobaciones iniciales

```
docker images
docker ps
docker ps -a
```

## 5.2 Preparar ficheros

* Crear directorio `/home/nombre-alumno/dockerXX`.
* Entrar el directorio anterior.
* Poner copia del fichero `holamundo.html` que teníamos.
* Crear el fichero `Dockerfile` con el siguiente contenido:

```
FROM debian:9

MAINTAINER nombre-del-alumnoXX 1.0

RUN apt-get update
RUN apt-get install -y apt-utils
RUN apt-get install -y nginx

COPY holamundo.html /var/www/html
RUN chmod 666 /var/www/html/holamundo.html

EXPOSE 80

CMD ["/usr/sbin/nginx -d"]
```

## 5.3 Crear imagen a partir del `Dockerfile`

El fichero Dockerfile contiene la información
necesaria para contruir el contenedor, veamos:

```
cd dockerXX                      # Directorio del Dockerfile
docker images                    # Consultamos las imágenes
docker build -t dvarrui/super .  # Construye imagen a partir del Dockefile
                                 # OJO: el punto final es necesario
docker images                    # Debe aparecer nuestra nueva imagen
```

## 5.4 Crear contenedor y comprobar

A continuación vamos a crear un contenedor con el nombre `con_super`, a partir de la imagen `dvarrui/super`. Probaremos con:

* `docker run --name=con_super -t dvarrui/super`
* `docker run --name=con_super -t dvarrui/super -p 80`
* `docker run --name=con_super -t dvarrui/super -p 80 /usr/sbin/nginx -d`

Desde otra terminal:
* `docker...`, para averiguar el puerto de escucha del servidor Nginx.
* Comprobar en el navegador:
    * URL `http://localhost:PORTNUMBER`
    * URL `http://localhost:PORTNUMBER/holamundo.html`

---
# 6. Migrar las imágenes de docker a otro servidor

¿Cómo puedo llevar los contenedores Docker a un nuevo servidor?

> Enlaces de interés
>
> * https://www.odooargentina.com/forum/ayuda-1/question/migrar-todo-a-otro-servidor-imagenes-docker-397
> * http://linoxide.com/linux-how-to/backup-restore-migrate-containers-docker/

**Exportar** imagen Docker a fichero tar:
* `docker save -o ~/backupXX.tar nombre-alumno/super`, guardamos la imagen
"nombre-alumno/super" en un fichero tar.

**Importar** imagen Docker desde fichero:
* Nos llevamos el tar a otra máquina con docker instalado, y restauramos.
* `docker load -i ~/backupXX.tar`, cargamos la imagen docker a partir del fichero tar.
* `docker images`, comprobamos que la nueva imagen está disponible.

Ya podemos crear contenedores a partir de la nueva imagen.

---
# 7. Limpiar

Cuando terminamos con los contenedores, y ya no lo necesitamos, es buena idea pararlos y/o destruirlos.
* `ps -a`
* `docker stop ...`
* `docker rm ...`
* `docker images`
* `docker rmi ...`

---
# ANEXO
Crear un imagen de contenedor:
* `docker ps`, muestra los contenedores que tengo en ejecución.
* `docker commit -p CONTAINERID nombre-alumno/backupXX`, grabar una imagen de nombre "nombre-alumno/backupXX" a partir del contenedor CONTAINERID.
* `docker images`comprobar que se ha creado la imagen "nombre-alumno/backupXX".
