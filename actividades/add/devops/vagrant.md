

#1. Introducción

*Wikipedia*

Vagrant es una herramienta para la creación y configuración de entornos 
de desarrollo virtualizados.

Originalmente se desarrolló para VirtualBox y sistemas de configuración tales 
como Chef, Salt y Puppet. Sin embargo desde la versión 1.1 Vagrant es 
capaz de trabajar con múltiples proveedores, como VMware, Amazon EC2, LXC, 
DigitalOcean, etc.2

Aunque Vagrant se ha desarrollado en Ruby se puede usar en multitud de 
proyectos escritos en otros lenguajes.

Enlaces de interés:
* [Cómo instalar y configurar Vagrant](http://codehero.co/como-instalar-y-configurar-vagrant/)

*La información para esta actividad ha sido extraída del enlace anterior
publicado por: Jonathan Wiesel, El 16/07/2013*


#2. Primeros pasos

##2.1 Instalar

* Instalar vagrant.

##2.2. Crear nuestro proyecto
* Crear un directorio para nuestro proyecto vagrant.
```
$ mkdir vagrant_ejemplo
$ cd vagrant_ejemplo
$ vagrant init
```

##2.3 Las cajas
* Ahora necesitamos obtener una caja (box). Una caja es una imagen de un sistema.
```
$ vagrant box add mi_caja_ubuntu_precise_32 http://files.vagrantup.com/precise32.box
```
* Podemos listar las cajas instaladas en nuestro sistema: `vagrant box list`.

* Usar la caja en nuestro proyecto. Modificamos nuestro fichero `Vagrantfile`.
Cambiamos la línea `config.vm.box = "base"` por  `config.vm.box = "mi_caja_ubuntu_precise_32"`.

##2.4 Levantar el ambiente

* Ya estamos listos para iniciar nuestra máquina virtual, solo debemos 
ejecutar el siguiente comando y la instancia será levantada en breves 
instantes: `vagrant up`.

> Usaremos ssh para conectar con nuestra máquina.

* Otros comandos:
    * `vagrant suspend`: Si no queremos trabajar con el ambiente en un tiempo podemos suspenderlo.
    * `vagrant resume` : Levantar la MV en cuestión de pocos segundos.
    * `vagrant halt`: Apagarla la máquina.
    * `vagrant status`: Esto nos informará el estado actual del ambiente.
    * `vagrant destroy`: Para desechar y destruir cualquier rastro de la máquina puedes hacerlo saliendo de la sesión SSH y ejecutando este comando.

> Debemos tener en cuenta que tener el ambiente en modo **suspendido** consume espacio
 en disco debido a que el estado de la maquina virtual que suele almacenarse en RAM debe pasar a disco.

#3. Configuración

##3.1 Carpetas sincronizadas

La carpeta del proyecto que contiene el Vagrantfile comparte los archivos entre el 
sistema anfitrión y el virtualizado, esto nos permite compartir archivos 
fácilmente entre los ambientes.

Para identificar la carpeta compartida dentro del ambiente virtual volvamos a levantarlo:
```
$ vagrant up
…
$ vagrant ssh
…
ls /vagrant
```

Esto nos mostrará que efectivamente el directorio /vagrant dentro del ambiente 
virtual posee el mismo Vagrantfile que se encuentra en nuestro sistema anfitrión. 
Cualquier archivo que coloquemos en este directorio será accesible desde cualquiera de los 2 extremos. 

##3.2 Enrutamiento de puertos

Uno de los casos más comunes cuando tenemos una máquina virtual es la 
situación que estamos trabajando con proyectos enfocados a la web, 
y para acceder a las páginas no es lo más cómodo tener que meternos 
por terminal al ambiente virtual y llamarlas desde ahí, aquí entra en 
juego el enrutamiento de puertos, para esto localizaremos el Vagrantfile 
y le agregaremos una línea como esta:

`config.vm.network :forwarded_port, host: 4567, guest: 80`

> Esto indicará que el puerto 4567 del sistema anfitrión será enrutado al 
puerto 80 del ambiente virtualizado.

* Luego iniciamos el ambiente nuevamente, o si este ya se encuentra corriendo 
lo podemos reiniciar con: `vagrant reload`
* En nuestro sistema anfitrión nos dirigimos al explorador de internet y colocamos: `http://127.0.0.1:4567`.
En realidad estaremos accediendo al puerto 80 de nuestro sistema virtualizado. 

##3.3 Suministro

Quizás el aspecto con mayor beneficios del enfoque que usa Vagrant 
es el uso de herramientas de suministro, el cual consiste en correr 
una receta o una serie de scripts durante el proceso de levantamiento 
del ambiente virtual que permite instalar y configurar un sin fin 
piezas de software, esto con el fin de que el ambiente previamente 
configurado y con todas las herramientas necesarias una vez haya sido levantado.

Por ahora suministremos al ambiente virtual con un pequeño script que 
instale Apache.

* Copiemos las siguiente líneas en un archivo y guardémoslo en el 
directorio raíz del proyecto como `install_apache.sh`:

```
    #!/usr/bin/env bash

    apt-get update
    apt-get install -y apache2
    rm -rf /var/www
    ln -fs /vagrant /var/www
```

* Luego modifiquemos el Vagrantfile y agreguemos la siguiente línea a la configuración:
`config.vm.provision :shell, :path => "install_apache.sh"`

> Esto le indicará a Vagrant que debe usar la herramienta nativa shell 
para suministrar el ambiente virtual con el archivo `install_apache.sh`.

* Iniciamos el ambiente nuevamente, o si este ya se encuentra corriendo 
lo podemos reiniciar con: `vagrant reload`.

> Podremos notar en la salida del levantamiento del ambiente como se va instalando el paquete de Apache que indicamos:

* Para verificar que efectivamente el servidor Apache ha sido levantado 
podemos navegar a la siguiente ruta mediante un explorador web que configuramos anteriormente:
`http://127.0.0.1:4567`.

