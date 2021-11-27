
```
Estado      : EN DESARROLLO
Curso       :
Area        : Sistemas operativos, automatización, devops
Requisitos  : Se recomienda usar Docker con SSOO GNU/Linux
Tiempo      : 6 sesiones
Descripción : Gestionar granjas de contenedores
```

---
# 1. Docker-compose

Existen varias herramientas muy conocidas para trabajar con granjas de contenedores: Kubernetes, Swarm, Docker-compose.

Docker Compose es una herramienta que permite simplificar el uso de Docker. Facilitando el diseño y la construcción de servicios o de aplicaciones con múltiples componentes.

> Enlaces de interés:
> * [Aprendiendo a utilizar Docker Compose](https://dockertips.com/utilizando-docker-compose)
> * [Empezando con docker-compose](https://riptutorial.com/es/docker-compose)
> * [Getting started](https://docs.docker.com/compose/gettingstarted/)
> * [Taller de introducción a Docker](https://github.com/sergarb1/TallerIntroduccionDocker)
> * https://aulasoftwarelibre.github.io/taller-de-docker/docker-compose/

# 2. Gestionar un contenedor con volumen

docker images
docker search nginx
docker pull nginx
docker images

/usr/share/nginx/html

## 2.1 Volúmenes

https://dockertips.com/volumenes

A continuación vamos lanzar un contenedor
`docker --name=app1nginx -d -p 8081:80 -v html:/usr/share/nginx/html -t nginx`

docker port
docker volume


Buscar imagen contenedor Nginx en hub.docker.com

# 3. Gestionar dos contenedores


---
# ANEXO

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
