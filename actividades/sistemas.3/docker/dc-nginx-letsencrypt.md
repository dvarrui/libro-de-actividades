
`EN CONSTRUCCIÓN!!!`

```
Curso       : 202122
Area        : Sistemas operativos, automatización, devops
Requisitos  : Docker, docker compose, Nginx, Let's Encrypt
Tiempo      : 1 sesión
Descripción : Gestionar varios contenedores con conexiones HTTPS
```

# Docker-compose Nginx: + Let's Encrypt

> Enlaces de interés:
> * [Nginx and Let’s Encrypt with Docker in Less Than 5 Minutes](https://pentacent.medium.com/nginx-and-lets-encrypt-with-docker-in-less-than-5-minutes-b4b8a60d3a71)
> * [Certificación de dominio con Let’s Encrypt, NGINX y Docker Compose](https://medium.com/eudaimonia-ar/certificar-dominios-con-letsencrypt-y-nginx-con-docker-compose-a6a948f47f2f)

El objetivo es usar docker-compose para:
1. Lanzar contenedor con un servidor web que responde a las conexiones HTTPS (nginx).
2. Donde el certificado se obtendrá de otro contenedor (letsencrypt).

# 1. Configuración

**docker-compose.yaml**

```
version: '3'
services:
  nginx:
    image: nginx:1.15-alpine
    ports:
      - "80:80"
      - "443:443"
    volumes:
      - ./data/nginx:/etc/nginx/conf.d
  certbot:
    image: certbot/certbot
```

**data/nginx/app.conf**

A continuación, la configuración de Nginx para redirigir las peticiones HTTPS.

```
server {
    listen 80;
    server_name SERVERNAME;    location / {
        return 301 https://$host$request_uri;
    }    
}

server {
    listen 443 ssl;
    server_name SERVERNAME;

    location / {
        proxy_pass http://SERVERNAME;
    }
}
```

* Personalizar SERVERNAME con el nombre de dominio de nuestro servidor.

> WARNING: Todavía no podemos ejecutar `docker-compose up` porque el servidor Nginx todavía no tiene el certificado. Así que seguimos...

# 2. Enlazando nginx y certbot

Let’s Encrypt lleva a cabo la validación del dominio de la siguiente forma:
1. Certbot escribe unos datos en el URL "well-known" del servidor.
2. Let's Encrypt mediante consulta al URL "well-known" del dominio lee los datos.
3. Si la respuesta recibida responde correctamente, entonces se considera el dominio validado.

Vamos a necesitar los siguientes volúmenes:
1. Uno para la validación.
2. Y otro para los certificados.

* Modificar el fichero docker-compose.yaml, y añadir la siguiente configuración de volúmenes para los contenedores:

```
# Volúmenes para nginx:
  volumes:
   - ./data/nginx:/etc/nginx/conf.d
   - ./data/certbot/conf:/etc/letsencrypt
   - ./data/certbot/www:/var/www/certbot
```

```
# Volúmenes para certbot:
  volumes:
   - ./data/certbot/conf:/etc/letsencrypt
   - ./data/certbot/www:/var/www/certbot
```

En el fichero de configuración del servidor web (nginx) permitimos el acceso a la carpeta donde se guardarán los datos de respuesta para validar el dominio, en la sección server-port-80.

```
location /.well-known/acme-challenge/ {
    root /var/www/certbot;
}
```

Añadimos las referencias a los certificados HTTPS en la sección server-port-443 del fichero de configuración del servidor web (nginx):

```
ssl_certificate /etc/letsencrypt/live/NAMESERVER/fullchain.pem;
ssl_certificate_key /etc/letsencrypt/live/NAMESERVER/privkey.pem;
include /etc/letsencrypt/options-ssl-nginx.conf;
ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;
```

# 3. ¿El huevo o la gallina?

Necesitamos Nginx para ejecutar la validación de Let's Encrypt y obtener los certificados, pero Nginx no se puede ejecutar porque no tiene los certificados.

Para resolver este problema:
1. Se crean unos certificados temporales
2. Se inicia Nginx
3. Se borran los certificados temporales
4. Se solicita el certificado real

Podemos realizar todos estos pasos manualmente o usar un script que nos lo hago por nosotros de forma automática.

* Descargar el script `curl -L https://raw.githubusercontent.com/wmnnd/nginx-certbot/master/init-letsencrypt.sh > init-letsencrypt.sh`
* Editar el script y modificar los nombres de dominio y cuenta de correo.
* `chmod +x init-letsencrypt.sh`
* `sudo ./init-letsencrypt.sh`

# 4. Comprobamos

* Iniciar los contenedores con docker compose.
* Comprobar que funciona correctamente la conexión HTTPS.

# 5. Renovación automática del certificado

Los certificados caducarán y entonces tendremos que volver a repetir el proceso. ¿Podríamos automatizar el proceso de renovación antes de que se caduque?

* Añadiremos lo siguiente a la sección del `certbot` dentro del docker-compose.yaml, para que el certificado se renueve automáticamente cada 12 horas:

```
entrypoint: "/bin/sh -c 'trap exit TERM; while :; do certbot renew; sleep 12h & wait $${!}; done;'"
```
* En la sección `nginx`, nos aseguraremos que se recarga la configuración y los certificados cada 6 horas en background y se lanza el servidor Nginx en foreground.

```
command: "/bin/sh -c 'while :; do sleep 6h & wait $${!}; nginx -s reload; done & nginx -g \"daemon off;\"'"
```

# 6. Comprobación

* Comprobamos que funciona correctamente.
