
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

Ejecutar con nuestro usuario para comprobar que todo funciona:
``` 
docker images
docker run hello-world
docker images
``` 

> **Enable external network access**
>
> If you want your containers to be able to access the external network, 
you must enable the net.ipv4.ip_forward rule. To do this, use YaST.
>
> * For openSUSE Tumbleweed and later, browse to the System -> Network Settings -> Routing menu.
> * For SUSE Linux Enterprise 12 and previous openSUSE versions, 
browse to Network Devices -> Network Settings -> Routing menu (f) and check the Enable IPv4 Forwarding box.
> * When networking is handled by the Network Manager, instead of YaST you 
must edit the /etc/sysconfig/SuSEfirewall2 file needs by hand to ensure 
the FW_ROUTE flag is set to yes like so: FW_ROUTE="yes"


#ANEXO

Enlaces de interés:
* [getting-started-with-docker](http://www.linux.com/news/enterprise/systems-management/873287-getting-started-with-docker)

Docker es una tecnología contenedor de aplicaciones construida sobre LXC.


Instalación

* Instalamos docker: `wget -qO- https://get.docker.com/ | sh`

