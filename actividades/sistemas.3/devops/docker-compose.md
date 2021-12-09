
```
Curso       : 2021
Area        : Sistemas operativos, automatización, devops
Requisitos  : Docker
Tiempo      : 6 sesiones
Descripción : Gestionar varios contenedores en un host local
```

# 1. Docker-compose

Existen varias herramientas muy conocidas para trabajar con granjas de contenedores: Kubernetes, Swarm, Docker-compose.

Docker Compose es una herramienta que permite simplificar el uso de Docker. Facilitando el diseño y la construcción de servicios o de aplicaciones con múltiples componentes.

> Enlaces de interés:
> * [Aprendiendo a utilizar Docker Compose](https://dockertips.com/utilizando-docker-compose)
> * [Empezando con docker-compose](https://riptutorial.com/es/docker-compose)
> * [Getting started](https://docs.docker.com/compose/gettingstarted/)
> * [Taller de introducción a Docker](https://github.com/sergarb1/TallerIntroduccionDocker)

## 1.1 Instalar docker-compose

* `zypper se docker-compose`, buscamos el paquete en los repositorios.
* `zypper in ...`, instalamos el paquete de *Compose*.
* `docker-compose -v`, comprobamos la versión que hemos instalado.

# 2. Gestionar un contenedor Nginx

## 2.1 Descargar imagen

* `docker images`, consultamos las imágenes instaladas.
* `docker search nginx`, buscamos contenedores nginx.
* Podemos ir a la web hub.docker.com, localizar la imagen Nginx y consultar su información.
* `docker pull ...`, descargamos el contenedor.
* `docker images`, comprobamos que tenemos la imagen que buscábamos.

## 2.2 Gestionar Nginx con volumen

Los volúmenes son como las carpetas compartidas de las máquinas virtuales.
Consultar la TEORIA sobre los [volumenes](https://dockertips.com/volumenes).

* Crear carpeta `composeXXnginx`
* Crear fichero `composeXXnginx/html/index.html`. Personalizar esta página web a nuestro gusto.
Incluir nombre-alumnoXX.
* Crear el fichero `composeXXnginx/docker-compose.yaml` de la siguiente forma:

```
version: "3.3"
services:
  web:
    image: "nginx"
    ports:
    - "8081:80"
    volumes:
    - ./html:/usr/share/nginx/html
```

Este fichero se encarga se suministrar la información necesaria para crear el contenedor con *Compose*.

| Parámetro | Descripción |
| --------- | ----------- |
| web       | Nombre del contenedor  |
| image     | Imagen que se usará como base para crear el contenedor |
| ports     | Redireccionamiento de puertos |
| volumes   | Definir carpeta compartida y/o volumen |

* `docker-compose config`, comprobamos que el fichero esté bien escrito.
* `docker-compose ps`, comprobamos que todavía no se ha creado el contenedor.
* `docker-compose up`, iniciamos *Compose* en primer plano (foreground).
* Abrimos otro terminal (sin cerrar el anterior).

En el terminal2:
* `docker-compose ps`, comprobamos que se ha creado el contenedor.
* `docker ps`, también lo comprobamos con el comando de Docker.
* Abrir navegador. URL localhost:8081 y comprobar que se ve nuestra página web.

## 2.3 Cambios en "caliente"

Ahora vamos a ver que si hacemos cambios en el contenido de los ficheros dentro del volumen, podemos hacer cambios en "caliente".
* Ir al terminal 2.
* Modificar "index.html"
* Abrir navegador y validar los cambios.

Ahora vamos a apagar los contenedores
* Ir al terminal 2.
* `docker-compose down`.
* `docker-compose ps`.
* `docker ps -a`
* Si volvemos al terminal 1 veremos que el comando se terminó y liberó el terminal.

Es más sencillo gestionar contenedores con *Compose* que con los comandos de Docker.

# 3. Gestionar un contenedor Mysql con volumen

* Consultar información:
    * Vamos a la web hub.docker.com y buscamos algún contenedor Mysql.
    * Consultar información sobre las variables de entorno.
* Crear directorio `composeXXmysql`.
* Crear directorio `composeXXmysql/data`. Usaremos este directorio para mantener la información de la base de datos.

Crear fichero `docker-compose.yaml` conlo siguiente:
* Servicio que se llama "database".
* Imagen basada en "mysql".
* Redirigir el puerto de mysql (3306).
* Incluir esta configuración `command: --default-authentication-plugin=mysql_native_password`.
* Poner la clave de Mysql ROOT como variable de Entorno.
* Definir un volumen para que los datos de la base de datos sean persistentes. Mapearemos a carpeta "./data" con el directorio "/var/lib/mysql" del contenedor".

En el terminal:
* `docker-compose config`, validar que está bien escrito el fichero.
* `docker-compose up -d`, levantar los contenedores en segundo plano (parámetro -d).
* Si queremos ver los "logs" generados hacemos `docker-compose logs`. Esto nos sirve para depurar o solucionar problemas que puedan surgir.
* `docker-compose ps`, comprobar que los contenedores están en ejecución.

Comprobamos el MySQL:
* `nmap -Pn localhost`, comprobar que el puerto (mysql) está abierto.
* Usar el cliente mysql para acceder a la base de datos que gestiona el contenedor, y vamos a escribir algo dentro.
```
$ mysql -h IP-DE-LA-MAQUINA -u root  -p
mysql> create database alumnoXX
mysql> show databases
mysql> quit
$
```
* `docker-compose down`, destruimos los contenedores.
* `docker-compose ps`, comprobamos.

Comprobar la persistencia de los datos en Mysql:
* `docker-compose up -d`, volvemos a levantar los contenedores.
* Usar el cliente Mysql para volver a entrar en la base de datos y confirmar que se mantiene la información que habíamos escrito.

# 4. Gestionar dos contenedores

Ahora vamos a hacer un nuevo proyecto de *Compose*, donde vamos a gestionar 2 contenedores al mismo tiempo. En este caso vamos a crear un contenedor para la aplicación Wordpress(PHP con Apache2) y otro contenedor para la base de datos MariaDB.

> Enlace de interés:
> * [Compose - Ejemplo Wordpress con Mariadb](https://aulasoftwarelibre.github.io/taller-de-docker/docker-compose/)

* Crear directorio `composeXXwp`
* Crear directorio `composeXXwp/data`. Este directorio contendrá los datos de la base de datos MariaDB.
* Crear directorio `composeXXwp/html`. Este directorio guardará los ficheros (html, css, php, etc.) de la herramienta Wordpress.
* Crear fichero `composeXXwp/docker-compose.yaml` siguiendo las instrucciones del enlace anterior, para crear una configuración de Compose para gestionar 2 contenedores: uno con Wordpress y el otro con la base de datos MariaDB.
* Configuración para el contenedor de MySQL:
    * Usar como volumen el directorio "./data".
    * Configurar variables de entorno según documentación.
* Configuración para el contenedor de Wordpress:
    * Este contenedor depende del anterior (base de datos).
    * Usar como volumen el directorio "./html".
    * Configurar variables de entorno según documentación.
    * Configurar la redirección de puertos.
* Comprobar que funciona correctamente.

Como vemos *Compose* es una buena herramienta para gestionar contenedores Docker dentro de un host local. Existen otras herramientas más potentes capaces de gestionar contenedores en varios hosts de nuestra red: Swarm y Kubernetes.

---
# ANEXO

> 'default_authentication_plugin' is deprecated and will be removed in a future release. Please use authentication_policy instead.

A continuación vamos lanzar un contenedor
`docker --name=app1nginx -d -p 8081:80 -v ./html:/usr/share/nginx/html -t nginx`

docker port
docker volume

## GitHub  Actions

https://docs.github.com/es/actions/creating-actions/creating-a-docker-container-action

## Kubernetes

> Enlaces de interés:
> * https://www.adictosaltrabajo.com/tutoriales/primeros-pasos-con-kubernetes/
> * http://www.javiergarzas.com/2016/02/kubernetes-for-dummies-explicado-en-10-minutos.html
> * [Kubernetes Services simply visually explained](https://medium.com/swlh/kubernetes-services-simply-visually-explained-2d84e58d70e5)

Kubernetes (commonly referred to as "K8s") is an open source container cluster manager originally designed by Google and donated to the Cloud Native Computing Foundation. It aims to provide a "platform for automating deployment, scaling, and operations of application containers across clusters of hosts".[3] It usually works with the Docker container tool and coordinates between a wide cluster of hosts running Docker.

* Instalar docker
 `systemctl start docker`, iniciar el servicio. NOTA: El comando `docker daemon` hace el mismo efecto.
* `docker run busybox echo "Hello world"`. Docker Hub registry at http://docker.io
* Listing 2.2 A simple Node.js app: app.js

```
const http = require('http');
const os = require('os');
console.log("Kubia server starting...");
var handler = function(request, response) {
console.log("Received request from " + request.connection.remoteAddress);
response.writeHead(200);
response.end("You've hit " + os.hostname() + "\n");
};
var www = http.createServer(handler);
www.listen(8080);
```
