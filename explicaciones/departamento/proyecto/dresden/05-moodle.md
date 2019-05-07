
# Moodle con docker

* Crear `Dockerfile`

```
FROM ubuntu:16.04

MAINTAINER IES Puerto de la Cruz 0.1

ENV DEBIAN_FRONTEND=noninteractive

RUN echo "mysql-server mysql-server/root_password password pepito" | debconf-set-selections
RUN echo "mysql-server mysql-server/root_password_again password pepito" | debconf-set-selections
RUN apt-get update
RUN apt-get install -y apt-utils \
    nginx \
		mysql-server

CMD ["nginx", "-g", "daemon off;"]

```

* Fichero `makefile`
```
build:
	docker build -t iespuertodelacruz/moodle .
run:
	docker run --name=moodle -e DEBIAN_FRONTEND=noninteractive -p 80:80 iespuertodelacruz/moodle
bash:
	docker run --name=moodle_bash -p 80:80 -i -t iespuertodelacruz/moodle /bin/bash
clean:
	docker stop moodle moodle_bash
	docker rm moodle moodle_bash
	docker rmi $(docker images|grep '<none>'| tr -s ' ' ':'|cut -d ':' -f 3|xargs)
```
