
`EN CONSTRUCCIÓN!!!`

```
Curso       : 202122
Area        : Sistemas operativos, automatización, devops
Requisitos  : Docker, docker compose
Tiempo      : 6 sesiones
Descripción : Gestionar varios contenedores en local y con conexiones HTTPS
```

# Docker-compose Nginx: + Let's Encrypt

> Enlaces de interés:
> * [Docker Compose Nginx Tutorial](https://omarghader.github.io/docker-compose-nginx-tutorial/)
> * [Nginx and Let’s Encrypt with Docker in Less Than 5 Minutes](https://pentacent.medium.com/nginx-and-lets-encrypt-with-docker-in-less-than-5-minutes-b4b8a60d3a71)
> * [Certificación de dominio con Let’s Encrypt, NGINX y Docker Compose](https://medium.com/eudaimonia-ar/certificar-dominios-con-letsencrypt-y-nginx-con-docker-compose-a6a948f47f2f)

# 1. Nginx como balanceador de carga

Vamos a ver cómo usar un contenedor Nginx como balanceador de carga

Supongamos que tenemos 2 servidores web (server1 y server2). Vamos a configurar Nginx para repartir la carga entre ambos servidores.

```
request --> nginx ---> server1
              |    
              |
              |------> server2
```

## 1.1 Configuración de los servidores

Creamos el siguiente fichero de configuración `data/nginx.conf`:

```
events { worker_connections 1024; }

http {

    upstream app_servers {    # Create an upstream for the web servers
        server server1:80;    # the first server
        server server2:80;    # the second server
    }

    server {
        listen 80;

        location / {
            proxy_pass         http://app_servers;  # load balance the traffic
        }
    }
}
```

Vamos a crear páginas web diferentes para cada servidor web:
* Creamos el fichero `data/server1.html` con el siguiente contenido: `<html><body><h1>alumnoXX_web-server1</h1></body></html>`
* Creamos el fichero `data/server2.html` con el siguiente contenido: `<html><body><h1>alumnoXX_web-server2</h1></body></html>`


## 1.2 Docker compose

Creamos el fichero `docker-compose.yaml`:

```
version: '3'

services:
  # The load balancer
  nginx:
    image: nginx:1.16.0-alpine
    volumes:
      - ./nginx.conf:/etc/nginx/nginx.conf:ro
    ports:
      - "8080:80"

  # The web server1
  server1:
    image: nginx:1.16.0-alpine
    volumes:
      - ./server1.html:/usr/share/nginx/html/index.html

  # The web server2
  server2:
    image: nginx:1.16.0-alpine
    volumes:
      - ./server2.html:/usr/share/nginx/html/index.html
```

## 1.3 Comprobamos

* Iniciamos los contenedores configurados con Compose `docker-compose up -d`.
* Iniciamos un navegador web y vamos al URL http://localhost:8080.
* Cada vez que refresquemos nos aparecerá alternativamente server1, server2, etc.
