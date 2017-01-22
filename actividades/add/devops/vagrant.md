*(Actividad creada en el curso 201516)*

# 1. Introducción

Según *Wikipedia*:

```
    Vagrant es una herramienta para la creación y configuración de entornos
    de desarrollo virtualizados.

    Originalmente se desarrolló para VirtualBox y sistemas de configuración tales
    como Chef, Salt y Puppet. Sin embargo desde la versión 1.1 Vagrant es
    capaz de trabajar con múltiples proveedores, como VMware, Amazon EC2, LXC,
    DigitalOcean, etc.2

    Aunque Vagrant se ha desarrollado en Ruby se puede usar en multitud de
    proyectos escritos en otros lenguajes.
```

Enlaces de interés:
* [Cómo instalar y configurar Vagrant](http://codehero.co/como-instalar-y-configurar-vagrant/)
* [Instalar vagrant en OpenSUSE 13.2](http://gattaca.es/post/running-vagrant-on-opensuse/)  

> Para desarrollar esta actividad se ha utilizado la información del
enlace anterior publicado por: Jonathan Wiesel, El 16/07/2013.

---

# 2. Primeros pasos

## 2.1 Instalar

La instalación debemos hacerla en una máquina real.
* Hay varias formas de instalar Vagrant:
    * `apt-get install vagrant` o
    * Usando un paquete [Vagrant-deb](http://172.20.1.2/~general/software/varios/vagrant_1.7.2_x86_64.deb)
Disponible para descargar del servidor Leela.

## 2.2. Proyecto

* Crear un directorio para nuestro proyecto vagrant (Donde XX es el número de cada alumno):
```
    mkdir mivagrantXX
    cd mivagrantXX
    vagrant init
```

![vagrant-init](./images/vagrant-init.png)

## 2.3 Imagen, caja o box

* Ahora necesitamos obtener una imagen(caja, box) de un sistema operativo. Vamos,
por ejemplo, a conseguir una imagen de un `Ubuntu Precise de 32 bits`:
```
vagrant box add micajaXX_ubuntu_precise32 http://files.vagrantup.com/precise32.box
```

![vagrant-box-add](./images/vagrant-box-add.png)

* `vagrant box list`: Lista las cajas/imágenes disponibles actualmente en nuestra máquina.

Para usar una caja determinada en nuestro proyecto, modificamos el fichero `Vagrantfile`
(dentro de la carpeta de nuestro proyecto).
* Cambiamos la línea `config.vm.box = "base"` por  `config.vm.box = "micajaXX_ubuntu_precise32"`.
* Es más cómodo trabajar con el fichero si eliminamos todas las líneas de comentarios.

![vagrantfile](./images/vagrantfile.png)

> Existen muchos repositorios para descargar imágenes/cajas/boxes. Por ejemplo:
>
> * [Vagrant Box List](http://www.vagrantbox.es)
> * [HashiCorp's Atlas box catalog](https://atlas.hashicorp.com/boxes/search)
>
> Incluso podemos descargarnos cajas con Windows, GNU/Linux con entorno gráfico, BSD, etc.

## 2.4 Iniciar una nueva máquina

Vamos a iniciar una máquina virtual nueva usando Vagrant:
* `cd mivagrantXX`
* `vagrant up`: comando para iniciar una nueva instancia de la máquina.

![vagrant-up](./images/vagrant-up.png)

* `vagrant ssh`: Conectar/entrar en nuestra máquina virtual usando SSH.
* Otros comandos últiles de Vagrant son:
    * `vagrant suspend`: Suspender la máquina virtual. Tener en cuenta que la
    MV en modo **suspendido** consume más espacio en disco debido a que el
    estado de la máquina virtual que suele almacenarse en la RAM se pasa a disco.
    * `vagrant resume` : Volver a despertar la máquina virtual.
    * `vagrant halt`: Apagarla la máquina virtual.
    * `vagrant status`: Estado actual de la máquina virtual.
    * `vagrant destroy`: Para eliminar la máquina virtual (No los ficheros de configuración).

> **Ejemplos**
>
> Crear un proyecto vagrant con Windows7 e iniciar la MV:
> * `vagrant init opentable/win-7-professional-amd64-nocm`
> * `vagrant up --provider virtualbox`
>
> Crear un proyecto vagrant con MV OpenSUSE 42.1 e iniciar la MV:
> * `vagrant init opensuse/openSUSE-42.1-x86_64`
> * `vagrant up --provider virtualbox`

---

# 3. Configuración del entorno virtual

## 3.1 Carpetas sincronizadas

> La carpeta del proyecto que contiene el `Vagrantfile` es visible
para el sistema el virtualizado, esto nos permite compartir archivos fácilmente
entre los dos entornos.

* Para identificar las carpetas compartidas dentro del entorno virtual, hacemos:
```
vagrant up
vagrant ssh
ls /vagrant
```

> Esto nos mostrará que efectivamente el directorio `/vagrant` dentro del entorno
virtual posee el mismo `Vagrantfile` que se encuentra en nuestro sistema anfitrión.

## 3.2 Redireccionamiento de los puertos

Cuando trabajamos con máquinas virtuales, es frecuente usarlas para proyectos
enfocados a la web, y para acceder a las páginas es necesario configurar el
enrutamiento de puertos.

* Entramos en la MV e instalamos apache.
    * `vagrant ssh`
    * `apt-get install apache2`
* Modificar el fichero `Vagrantfile`, de modo que el puerto 4567 del sistema anfitrión sea enrutado al puerto 80 del ambiente virtualizado.
  * `config.vm.network :forwarded_port, host: 4567, guest: 80`
* Luego iniciamos la MV (si ya se encuentra en ejecución lo podemos refrescar
con `vagrant reload`)

> Para confirmar que hay un servicio a la escucha en 4567, desde la máquina real
podemos ejecutar los siguientes comandos:
> * `nmap -p 4500-4600 localhost`
> * `netstat -ntap`

* En la máquina real, abrimos el navegador web con el URL `http://127.0.0.1:4567`. En realidad estamos accediendo al puerto 80 de nuestro
sistema virtualizado.

![vagrant-forward-example](./images/vagrant-forward-example.png)

---

# 4. Ejemplos de configuración Vagrantfile

A continuación se muestran ejemplos de configuración Vagrantfile que NO ES NECESARIO hacer. Sólo es información.

> Enlace de interés [Tutorial Vagrant. ¿Qué es y cómo usarlo?](https://geekytheory.com/tutorial-vagrant-1-que-es-y-como-usarlo)

Ejemplo para configurar la red:
```
config.vm.network "private_network", ip: "192.168.33.10"
```

Ejemplo para configurar las carpetas compartidas:
```
config.vm.synced_folder "htdocs", "/var/www/html"
```

Ejemplo para configurar la conexión SSH de vagrant a nuestra máquina virtual:
```
config.ssh.username = 'root'
config.ssh.password = 'vagrant'
config.ssh.insert_key = 'true'
```

Ejemplo para configurar la ejecución remota de aplicaciones gráficas instaladas en la máquina virtual, mediante SSH:
```
config.ssh.forward_agent = true
config.ssh.forward_x11 = true
```

---

# 5.Suministro

Una de los mejores aspectos de Vagrant es el uso de herramientas de suministro.
Esto es, ejecutar *"una receta"* o una serie de scripts durante el proceso de
arranque del entorno virtual para instalar, configurar y personalizar un sin fin
de aspectos del SO del sistema anfitrión.

* `vagrant halt`, apagamos la MV.
* `vagrant destroy` y la destruimos para volver a empezar.

## 5.1 Suministro mediante shell script

Ahora vamos a suministrar a la MV un pequeño script para instalar Apache.
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

> Poner permisos de ejecución al script.

Vamos a indicar a Vagrant que debe ejecutar dentro del entorno virtual un archivo `install_apache.sh`.

* Modificar Vagrantfile y agregar la siguiente línea a la configuración:
`config.vm.provision :shell, :path => "install_apache.sh"`

> Si usamos los siguiente `config.vm.provision "shell", inline: '"echo "Hola"'`, ejecuta
directamente el comando especificado.

* Volvemos a crear la MV.

> Podemos usar `vagrant reload` si está en ejecución para que coja el cambio de la configuración.

Podremos notar, al iniciar la máquina, que en los mensajes de salida se muestran
mensajes que indican cómo se va instalando el paquete de Apache que indicamos.

* Para verificar que efectivamente el servidor Apache ha sido instalado e iniciado,
abrimos navegador en la máquina real con URL `http://127.0.0.1:4567`.

## 5.2 Suministro mediante Puppet

> Enlace de interés:
> * [Crear un entorno de desarrollo con vagrant y puppet](http://developerlover.com/crear-un-entorno-de-desarrollo-con-vagrant-y-puppet/)
> * `friendsofvagrant.github.io -> Puppet Provisioning`
>
> Veamos imágenes de ejemplo (de Aarón Gonźalez Díaz) con Vagrantfile configurado con puppet:
>
> ![vagranfile-puppet](./images/vagrantfile-puppet.png)
>
> Fichero de configuración de puppet `mipuppet.pp`:
>
> ![vagran-puppet-pp-file](./images/vagrant-puppet-pp-file.png)

Se pide hacer lo siguiente.

* Modificar el archivo el archivo Vagrantfile de la siguiente forma:

```
Vagrant.configure(2) do |config|
  ...
  config.vm.provision "puppet" do |puppet|
    puppet.manifest_file = "default.pp"
  end
 end
```

* Crear un fichero `manifests/default.pp`, con las órdenes/instrucciones puppet para instalar el programa `nmap`. Ejemplo:

```
package { 'nmap':
  ensure => 'present',
}
```

Para que se apliquen los cambios de configuración, tenemos dos formas:
* (A) Parar la MV, destruirla y crearla de nuevo (`vagrant halt`, `vagrant destroy` y `vagrant up`).
* (B) Con la MV encendida recargar la configuración y volver a ejecutar la provisión
    (`vagrant reload`, `vagrant provision`).

---

# 6. Nuestra caja personalizada

En los apartados anteriores hemos descargado una caja/box de un repositorio de Internet,
y luego la hemos provisionado para personalizarla. En este apartado vamos a crear
nuestra propia caja/box personalizada a partir de una MV de VirtualBox.

## 6.1 Preparar la MV VirtualBox

Lo primero que tenemos que hacer es preparar nuestra máquina virtual con una configuración por defecto, por si queremos publicar nuestro Box, ésto se realiza para seguir un estándar y que todo el mundo pueda usar dicho Box.

* Crear una MV VirtualBox nueva o usar una que ya tengamos.
* Instalar OpenSSH Server.

> Indicaciones de [¿Cómo crear una Base Box en Vagrant a partir de una máquina virtual](http://www.dbigcloud.com/virtualizacion/146-como-crear-un-vase-box-en-vagrant-a-partir-de-una-maquina-virtual.html) para preparar la MV de VirtualBox.

* Crear el usuario Vagrant, para poder acceder a la máquina virtual por SSH.
A este usuario le agregamos una clave pública para autorizar el acceso sin clave
desde Vagrant.
```
useradd -m vagrant
su - vagrant
mkdir .ssh
wget https://raw.githubusercontent.com/mitchellh/vagrant/master/keys/vagrant.pub -O .ssh/authorized_keys
chmod 700 .ssh
chmod 600 .ssh/authorized_keys
```

* Poner clave `vagrant` al usuario vagrant y al usuario root.

Tenemos que conceder permisos al usuario vagrant para que pueda configurar la red,
instalar software, montar carpetas compartidas, etc. para ello debemos configurar
`/etc/sudoers` (visudo) para que no nos solicite la password de root, cuando
realicemos estas operación con el usuario vagrant.

* Añadir `vagrant ALL=(ALL) NOPASSWD: ALL` a /etc/sudoers.

> Hay que comprobar que no existe una linea indicando requiretty si existe la comentamos.

* Debemos asegurarnos que tenemos instalado las VirtualBox Guest Additions,
para conseguir mejoras en el S.O o poder compartir carpetas con el anfitrión.
```
root@dbigcloud:~# modinfo vboxguest
filename:       /lib/modules/3.13.0-32-generic/updates/dkms/vboxguest.ko
version:        4.3.20
license:        GPL
description:    Oracle VM VirtualBox Guest Additions for Linux Module
author:         Oracle Corporation
srcversion:     22BF504734255C977E4D805
alias:          pci:v000080EEd0000CAFEsv00000000sd00000000bc*sc*i*
depends:        
vermagic:       3.13.0-32-generic SMP mod_unload modversions
```

## 6.2 Crear la caja vagrant

Una vez hemos preparado la máquina virtual ya podemos crear el box.

* Vamos a crear una nueva carpeta `mivagrantXXconmicaja`, para este nuevo proyecto vagrant.
* Ejecutamos `vagrant init` para crear el fichero de configuración nuevo.
* Localizar el nombre de nuestra máquina VirtualBox (Por ejemplo, `v1-opensuse132-xfce`).
* Crear la caja `package.box` a partir de la MV.

![vagrant-package](./images/vagrant-package.png)

* Comprobamos que se ha creado la caja `package.box` en el directorio donde
hemos ejecutado el comando.

![vagrant-package_box_file](./images/vagrant-package_box_file.png)

* Muestro la lista de cajas disponibles, pero sólo tengo 1 porque todavía
no he incluido la que acabo de crear. Finalmente, añado la nueva caja creada
por mí al repositorio de vagrant.

![vagrant-package](./images/vagrant-2-boxes.png)

* Al levantar la máquina con esta nueva caja obtengo este error.
Probablemente por tener mal las GuestAdittions.

![vagrant-package](./images/vagrant-error-mybox.png)

* Pero haciendo `vagrant ssh` nos conectamos sin problemas con la máquina.

> Recordar que podemos cambiar los parámetros de configuración de acceso SSH.
> Ver ejemplo:
>
>     config.ssh.username = 'root'
>     config.ssh.password = 'vagrant'
>     config.ssh.insert_key = 'true'
>

---

# ANEXO

## A.1 Directorios
¿Dónde se guardan las imágenes base? ¿Las cajas de vagrant que nos vamos descargando?

![vagrant-directory](./images/vagrant-directory.png)

## A.2 Windows

Ampliar esta práctica para comprobar el funcionamiento de Vagrant bajo Windows
y usar cajas/boxes vagrant con Windows.
