
```
Curso       : 202122
Area        : Sistemas operativos, automatización, devops
Requisitos  : Docker, docker compose, Nginx
Tiempo      : 1 sesión
Descripción : Gestionar varios contenedores en local y con conexiones HTTPS
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


Let’s Encrypt performs domain validation by requesting a well-known URL from a domain. If it receives a certain response (the “challenge”), the domain is considered validated. This is similar to how Google Search Console establishes ownership of a website. The response data is provided by certbot, so we need a way for the nginx container to serve files from certbot.

Vamos a necesitar compartir dos volúmenes:
1. Uno para la validación.
2. Y otro para los certificados.

* En el fichero docker-compose.yaml, añadimos la siguiente configuración des volúmenes para los contenedores nginx y certbot:

```
- ./data/certbot/conf:/etc/letsencrypt
- ./data/certbot/www:/var/www/certbot
```


Now we can make nginx serve the challenge files from certbot! Add this to the first (port 80) section of our nginx configuration (data/nginx/app.conf):

location /.well-known/acme-challenge/ {
    root /var/www/certbot;
}

After that, we need to reference the HTTPS certificates. Add the soon-to-be-created certificate and its private key to the second server section (port 443). Make sure to once again replace example.org with your domain name.

ssl_certificate /etc/letsencrypt/live/example.org/fullchain.pem;
ssl_certificate_key /etc/letsencrypt/live/example.org/privkey.pem;

And while we’re at it: The folks at Let’s Encrypt maintain best-practice HTTPS configurations for nginx. Let’s also add them to our config file. This will score you a straight A in the SSL Labs test!

include /etc/letsencrypt/options-ssl-nginx.conf;
ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;

The Chicken or the Egg?

Now for the tricky part. We need nginx to perform the Let’s Encrypt validation But nginx won’t start if the certificates are missing.

So what do we do? Create a dummy certificate, start nginx, delete the dummy and request the real certificates.
Luckily, you don’t have to do all this manually, I have created a convenient script for this.

Download the script to your working directory as init-letsencrypt.sh:

curl -L https://raw.githubusercontent.com/wmnnd/nginx-certbot/master/init-letsencrypt.sh > init-letsencrypt.sh

Edit the script to add in your domain(s) and your email address. If you’ve changed the directories of the shared Docker volumes, make sure you also adjust the data_path variable as well.

Then run chmod +x init-letsencrypt.sh and sudo ./init-letsencrypt.sh.
