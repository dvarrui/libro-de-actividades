
# Crear contenedor para Wordpress

Enlaces de interés:
* http://www.josedomingo.org/pledin/2016/02/enlazando-contenedores-docker/


docker run —name wordpressdb -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=wordpress -d mysql
docker run -p 8080:80 —name wordpress —link wordpressdb:mysql wordpress

# docker-compose

Herramienta para facilitar el uso de contenedores enlazados.

Instalación de docker-compose

```
root@dresden ~ # curl -L https://github.com/docker/compose/releases/download/1.14.0/docker-compose-uname -s-uname -m > /usr/local/bin/docker-compose
% Total % Received % Xferd Average Speed Time Time Time Current
Dload Upload Total Spent Left Speed
100 617 0 617 0 0 958 0 —:--:— —:--:— —:--:— 959
100 8084k 100 8084k 0 0 1647k 0 0:00:04 0:00:04 —:--:— 1942k

root@dresden ~ # chmod +x /usr/local/bin/docker-compose
root@dresden ~ #
```
