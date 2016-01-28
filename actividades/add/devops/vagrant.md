
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
* Nosotros vamos a usar el paquete [Vagrant-deb](http://172.20.1.2/~david/vagrant_1.7.2_x86_64.deb) de Leela.

##2.2. Proyecto
* Crear un directorio para nuestro proyecto vagrant.
```
$ mkdir miproyecto-vagrantXX
$ cd miproyecto-vagrantXX
$ vagrant init
```

##2.3 Imagen, caja o box
* Ahora necesitamos obtener una imagen(caja, box) de un sistema operativo.
```
$ vagrant box add micajaXX_ubuntu_precise32 http://files.vagrantup.com/precise32.box
```
* Podemos listar las cajas instaladas en nuestro sistema: `vagrant box list`.

* Usar la caja en nuestro proyecto. Modificamos nuestro fichero `Vagrantfile`.
Cambiamos la línea `config.vm.box = "base"` por  `config.vm.box = "micajaXX_ubuntu_precise32"`.

##2.4 Iniciar la máquina

* `cd miproyecto-VagrantXX`
* `vagrant up`: comando para iniciar una la instancia de la máquina.

> Usaremos ssh para conectar con nuestra máquina.

* Otros comandos de Vagrant:
    * `vagrant suspend`: Suspender la máquina.
    * `vagrant resume` : Volver a despertar la máquina.
    * `vagrant halt`: Apagarla la máquina.
    * `vagrant status`: Estado actual de la máquina.
    * `vagrant destroy`: Para eliminar completamente la máquina.

> Debemos tener en cuenta que tener el ambiente en modo **suspendido** consume espacio
 en disco debido a que el estado de la maquina virtual que suele almacenarse en RAM debe pasar a disco.

#3. Configuración

##3.1 Carpetas sincronizadas

La carpeta del proyecto que contiene el Vagrantfile comparte los archivos entre el 
sistema anfitrión y el virtualizado, esto nos permite compartir archivos 
fácilmente entre los ambientes.

Para identificar la carpeta compartida dentro del ambiente virtual volvamos a levantarlo:
```
    vagrant up
    vagrant ssh
    ls /vagrant
```

> Esto nos mostrará que efectivamente el directorio /vagrant dentro del ambiente 
virtual posee el mismo Vagrantfile que se encuentra en nuestro sistema anfitrión. 
>
> Cualquier archivo que coloquemos en este directorio será accesible desde cualquiera de los 2 extremos. 

##3.2 Redireccionamiento de los puertos

Uno de los casos más comunes cuando tenemos una máquina virtual es la 
situación que estamos trabajando con proyectos enfocados a la web, 
y para acceder a las páginas no es lo más cómodo tener que meternos 
por terminal al ambiente virtual y llamarlas desde ahí, aquí entra en 
juego el enrutamiento de puertos.

* Modificar el fichero Vagrantfile, de modo que el puerto 4567 del 
sistema anfitrión será enrutado al puerto 80 del ambiente virtualizado.

`config.vm.network :forwarded_port, host: 4567, guest: 80`

* Luego iniciamos la MV (si ya se encuentra en ejecución lo podemos refrescar 
con `vagrant reload`)
* En nuestro sistema anfitrión nos dirigimos al explorador de internet,
 y colocamos: `http://127.0.0.1:4567`. En realidad estaremos accediendo 
 al puerto 80 de nuestro sistema virtualizado. 

##3.3 Suministro

Quizás el aspecto con mayor beneficios del enfoque que usa Vagrant 
es el uso de herramientas de suministro, el cual consiste en correr 
una receta o una serie de scripts durante el proceso de levantamiento 
del ambiente virtual que permite instalar y configurar un sin fin 
piezas de software, esto con el fin de que el ambiente previamente 
configurado y con todas las herramientas necesarias una vez haya sido levantado.

Por ahora suministremos al ambiente virtual con un pequeño script que 
instale Apache.

* Crear el script `install_apache.sh`, dentro del proyecto con el siguiente
contenido:

```
    #!/usr/bin/env bash

    apt-get update
    apt-get install -y apache2
    rm -rf /var/www
    ln -fs /vagrant /var/www
    echo "<h1>Actividad de Vagrant</h1>" > /var/www/index.html
    echo "<p>Curso201516</p>" >> /var/www/index.html
    echo "<p>Nombre-del-alumno</p>" >> /var/www/index.html
```

* Modificar Vagrantfile y agregar la siguiente línea a la configuración:
`config.vm.provision :shell, :path => "install_apache.sh"`

> Esto le indicará a Vagrant que debe usar la herramienta nativa shell 
para suministrar el ambiente virtual con el archivo `install_apache.sh`.

* Iniciamos la MV o `vagrant reload` si está en ejecución.

> Podremos notar en la salida del levantamiento del ambiente como se va instalando el paquete de Apache que indicamos:

* Para verificar que efectivamente el servidor Apache ha sido levantado 
podemos navegar a la siguiente ruta mediante un explorador web que configuramos anteriormente:
`http://127.0.0.1:4567`.

