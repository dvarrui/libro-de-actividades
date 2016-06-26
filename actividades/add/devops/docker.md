
#1.Introducción


Enlaces de interés:
* [Docker installation on SUSE](https://docs.docker.com/engine/installation/linux/SUSE)
* [Docker for beginners](http://prakhar.me/docker-curriculum/)


#2. Instalación y configuración

> Se requiere versión del Kernel 3.10 o superior (`uname -a`)

Ejecutar como superusuario:
```
zypper in docker
docker version
systemctl start docker
usermod -a -G docker USERNAME
```

> Salir de la sesión y volver a entrar con nuestro usuario.

Ejecutar con nuestro usuario:
``` 
docker images
docker run hello-world
docker images
```

#ANEXO

Enlaces de interés:
* [getting-started-with-docker](http://www.linux.com/news/enterprise/systems-management/873287-getting-started-with-docker)

Docker es una tecnología contenedor de aplicaciones construida sobre LXC.


Instalación

* Instalamos docker: `wget -qO- https://get.docker.com/ | sh`

