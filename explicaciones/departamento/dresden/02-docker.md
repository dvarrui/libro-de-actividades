
# Instalar docker

* `uname -a`, verificar la versión del núcleo > 3.10

Enlace de interés:
* https://docs.docker.com/engine/installation/linux/docker-ce/ubuntu/#install-using-the-repository

## Configurar repositorio

* `sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88`
* `sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"`
* `sudo apt-get update`

```
Hit:1 http://mirror.hetzner.de/ubuntu/packages xenial InRelease
Hit:2 http://mirror.hetzner.de/ubuntu/packages xenial-backports InRelease
Hit:3 http://mirror.hetzner.de/ubuntu/packages xenial-updates InRelease
Hit:4 http://mirror.hetzner.de/ubuntu/security xenial-security InRelease
Hit:5 http://de.archive.ubuntu.com/ubuntu xenial InRelease
Hit:6 http://de.archive.ubuntu.com/ubuntu xenial-updates InRelease
Hit:7 http://de.archive.ubuntu.com/ubuntu xenial-backports InRelease
Get:8 http://security.ubuntu.com/ubuntu xenial-security InRelease [102 kB]
Get:9 https://download.docker.com/linux/ubuntu xenial InRelease [29.5 kB]
Get:10 https://download.docker.com/linux/ubuntu xenial/stable amd64 Packages [1966 B]
Fetched 134 kB in 0s (169 kB/s)
Reading package lists... Done
```

## Instalar docker

* `sudo apt-get install docker-ce`
* `apt-cache madison docker-ce`
* `dpkg -l |grep docker`

## Comprobar

* `sudo docker run hello-world`, comprobar que funciona
* Añadir al grupo `docker` a los usuarios root, sdelquin y david.
