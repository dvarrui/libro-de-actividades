
```
Curso      : 201920, 201819, curso1718
Requisitos : vagrant y Virtualbox
Tiempo estimado :

```

---
# Vagrant con VirtualBox

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (3.3) Comprobar proyecto 1    | | | |
| (5.2) Comprobar proyecto 2    | | | |
| (6.1) Suministro Shell Script | | | |
| (6.2) Suministro Puppet       | | | |
| (7.2) Crear Box Vagrant       | | |. |

---
# 1. Introducción

Según *Wikipedia*:

_Vagrant es una herramienta para la creación y configuración de entornos de desarrollo virtualizados.

Originalmente se desarrolló para VirtualBox y sistemas de configuración tales como Chef, Salt y Puppet. Sin embargo desde la versión 1.1 Vagrant es capaz de trabajar con múltiples proveedores, como VMware, Amazon EC2, LXC, DigitalOcean, etc.2

Aunque Vagrant se ha desarrollado en Ruby se puede usar en multitud de
proyectos escritos en otros lenguajes._

> NOTA: Para desarrollar esta actividad se ha utilizado principalmente
la información del enlace anterior publicado por Jonathan Wiesel, el 16/07/2013.

---
# 2. Instalar Vagrant

> Enlaces de interés:
> * [Introducción a Vagrant](https://code.tutsplus.com/es/tutorials/introduction-to-vagrant--cms-25917)
> * [Cómo instalar y configurar Vagrant](http://codehero.co/como-instalar-y-configurar-vagrant/)
> * [Instalar vagrant en OpenSUSE 13.2](http://gattaca.es/post/running-vagrant-on-opensuse/)

La instalación vamos a hacerla en una máquina real.
* Instalar Vagrant.
* Si vamos a trabajar Vagrant con MV de VirtualBox, hay que comprobar que las versiones de ambos son compatibles entre sí.
    * `vagrant version`, para comprobar la versión actual de Vagrant.
    * `VBoxManage -v`, para comprobar la versión actual de VirtualBox.

---
# 3. Proyecto 1

## 3.1 Imagen, caja o box

Existen muchos repositorios desde donde podemos descargar la cajas de Vagrant (Imágenes o boxes). Incluso podemos descargarnos cajas con Windows, GNU/Linux con entorno gráfico, BSD, etc. Por ejemplo:
* [Vagrant Box List](http://www.vagrantbox.es)
* [HashiCorp's Atlas box catalog](https://atlas.hashicorp.com/boxes/search)

> OJO: Sustituir **BOXNAME** por `ubuntu/bionic64`

* `vagrant box add ubuntu/bionic64`, descargar la caja que necesitamos a través de vagrant.
* `vagrant box list`, lista las cajas/imágenes disponibles actualmente en nuestra máquina.

```
vagrant42-proyecto1> vagrant box list
ubuntu/bionic64 (virtualbox, 0)
```

> Si te preguntas... ¿Dónde se guardan las imágenes base? ¿Las cajas de vagrant que nos vamos descargando? ¿Están disponibles sólo para mi usuario o lo están para todos los usuarios de la misma máquina?
>
> ![vagrant-directory](./images/vagrant-directory.png)

## 3.2 Directorio

* Crear un directorio para nuestro proyecto vagrant (Donde XX es el número de cada alumno):

```
mkdir vagrantXX-proyecto1
cd vagrantXX-proyecto1
```

A partir de ahora vamos a trabajar dentro de esta carpeta.
* Crear el fichero `Vagrantfile` de la siguiente forma:
```
Vagrant.configure("2") do |config|
  config.vm.box = "BOXNAME"
  config.vm.provider "virtualbox"
end
```

> NOTA: Con `vagrant init` se crea un fichero `Vagranfile` con las opciones por defecto.

## 3.3 MV: Levantar y entrar

Vamos a crear una MV nueva y la vamos a iniciar usando Vagrant:
* Debemos estar dentro de `vagrantXX-proyecto1`.
* `vagrant up`, para iniciar una nueva instancia de la máquina.
* `vagrant ssh`: Conectar/entrar en nuestra máquina virtual usando SSH.

> **Otros comandos últiles de Vagrant son**:
> * `vagrant suspend`: Suspender la máquina virtual. Tener en cuenta que la MV en modo **suspendido** consume más espacio en disco debido a que el estado de la máquina virtual que suele almacenarse en la RAM se pasa a disco.
> * `vagrant resume` : Volver a despertar la máquina virtual.
> * `vagrant halt`: Apagarla la máquina virtual.
> * `vagrant status`: Estado actual de la máquina virtual.
> * `vagrant destroy`: Para eliminar la máquina virtual (No los ficheros de configuración).

---

# 4. TEORÍA

`NO ES NECESARIO hacer este apartado. Sólo es información.`

A continuación se muestran ejemplos de configuración Vagrantfile que NO ES NECESARIO hacer. Sólo es información.

> Enlace de interés [Tutorial Vagrant. ¿Qué es y cómo usarlo?](https://geekytheory.com/tutorial-vagrant-1-que-es-y-como-usarlo)

**Carpetas compartidas**

La carpeta del proyecto que contiene el `Vagrantfile` es visible
para el sistema el virtualizado, esto nos permite compartir archivos fácilmente entre los dos entornos.

Ejemplos para configurar las carpetas compartidas:
* `config.vm.synced_folder ".", "/vagrant"`: La carpeta del proyecto es accesible desde /vagrant de la MV.
* `config.vm.synced_folder "htdocs", "/var/www/html"`. La carpeta htdocs del proyecto es accesible desde /var/www/html de la MV.

**Redireccionamiento de los puertos**

Cuando trabajamos con máquinas virtuales, es frecuente usarlas para proyectos enfocados a la web, y para acceder a las páginas es necesario configurar el enrutamiento de puertos.

* `config.vm.network "private_network", ip: "192.168.33.10"`: Ejemplo para configurar la red.

**Conexión SSH**: Ejemplo para personalizar la conexión SSH a nuestra máquina virtual:

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

> ¿Cómo podríamos crear una MV Windows usando vagrant en GNU/Linux?

---

# 5. Proyecto2 (redirección de puertos)

## 5.1 Creamos proyecto 2

* Crear carpeta `vagrantXX-proyecto2`. Entrar en el directorio.
* Crear proyecto Vagrant.
* Configurar Vagrantfile para usar nuestra caja BOXNAME.
* Modificar el fichero `Vagrantfile`, de modo que el puerto 4567 del sistema anfitrión sea enrutado al puerto 80 del ambiente virtualizado.
  * `config.vm.network :forwarded_port, host: 4567, guest: 80`
* `vagrant ssh`, entramos en la MV
* Instalamos apache2.
* Cuando la MV está iniciada y queremos recargar el fichero de configuración si ha cambiado hacemos `vagrant reload`.

## 5.2 Comprobamos proyecto 2

Para confirmar que hay un servicio a la escucha en 4567, desde la máquina real
podemos ejecutar los siguientes comandos:
* En el HOST-CON-VAGRANT (Máquina real). Comprobaremos que el puerto 4567 está a la escucha.
    * `vagrant port` para ver la redirección de puertos de la máquina Vagrant.
* En HOST-CON-VAGRANT, abrimos el navegador web con el URL `http://127.0.0.1:4567`. En realidad estamos accediendo al puerto 80 de nuestro sistema virtualizado.

![vagrant-forward-example](./images/vagrant-forward-example.png)

---

# 6. Suministro

Una de los mejores aspectos de Vagrant es el uso de herramientas de suministro. Esto es, ejecutar *"una receta"* o una serie de scripts durante el proceso de arranque del entorno virtual para instalar, configurar y personalizar un sin fin de aspectos del SO del sistema anfitrión.

* `vagrant halt`, apagamos la MV.
* `vagrant destroy` y la destruimos para volver a empezar.

## 6.1 Proyecto 3 (Suministro mediante shell script)

Ahora vamos a suministrar a la MV un pequeño script para instalar Apache.
* Crear directorio `vagrantXX-proyecto3` y dentro un proyecto Vagrant.
* Crear el script `install_apache.sh`, dentro del proyecto con el siguiente
contenido (_hay que arreglarlo porque puede tener fallos_):

```
#!/usr/bin/env bash

apt-get update
apt-get install -y apache2
rm -rf /var/www
ln -fs /vagrant /var/www
echo "<h1>Vagrant Proyecto 3</h1>" > /var/www/index.html
echo "<p>Curso201819</p>" >> /var/www/index.html
echo "<p>Nombre-del-alumno</p>" >> /var/www/index.html
```

> Poner permisos de ejecución al script. ¿No crees?

Vamos a indicar a Vagrant que debe ejecutar el script anterior
(`install_apache.sh`) dentro del entorno virtual.
* Modificar Vagrantfile y agregar la siguiente línea a la configuración:
`config.vm.provision :shell, :path => "install_apache.sh"`

> Si usamos los siguiente `config.vm.provision "shell", inline: '"echo "Hola"'`, ejecutaría directamente el comando especificado. Es lo que llamaremos provisión inline.

* Volvemos a crear la MV.

> Podemos usar `vagrant reload`, si está en ejecución, para que coja los cambios de configuración.

Podremos notar, al iniciar la máquina, que en los mensajes de salida se muestran mensajes que indican cómo se va instalando el paquete de Apache que indicamos.

* Para verificar que efectivamente el servidor Apache ha sido instalado e iniciado, abrimos navegador en la máquina real con URL `http://127.0.0.1:4567`.

## 6.2 Proyecto 4 (Suministro mediante Puppet)

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
* Crear directorio `vagrantXX-proyecto4` como nuevo proyecto Vagrant.
* Modificar el archivo Vagrantfile de la siguiente forma:

```
Vagrant.configure(2) do |config|
  ...
  config.vm.provision "puppet" do |puppet|
    puppet.manifest_file = "default.pp"
  end
 end
```

* Ahora hay que crear el fichero `manifests/default.pp`, con las órdenes/instrucciones puppet para instalar el programa `PACKAGENAME`. Ejemplo:

```
package { 'PACKAGENAME':
  ensure => 'present',
}
```

> El Puppet lo veremos en profundidad en otra actividad. Pero por ahora necesitamos usarlo con Vagrant...

Para que se apliquen los cambios de configuración, tenemos dos formas:
* (A) Parar la MV, destruirla y crearla de nuevo (`vagrant halt`, `vagrant destroy` y `vagrant up`).
* (B) Con la MV encendida recargar la configuración (`vagrant reload`), y volver a ejecutar la provisión (`vagrant provision`).

---

# 7. Proyecto 5 (Nuestra caja)

En los apartados anteriores hemos descargado una caja/box de un repositorio de Internet, y luego la hemos provisionado para personalizarla. En este apartado vamos a crear nuestra propia caja/box personalizada a partir de una MV de VirtualBox que tengamos.

## 7.1 Preparar la MV VirtualBox

> Enlace de interés:
>
> * Indicaciones de [¿Cómo crear una Base Box en Vagrant a partir de una máquina virtual](http://www.dbigcloud.com/virtualizacion/146-como-crear-un-vase-box-en-vagrant-a-partir-de-una-maquina-virtual.html) para preparar la MV de VirtualBox.

### Buscar una máquina virtual

Lo primero que tenemos que hacer es preparar nuestra máquina virtual con una configuración por defecto, por si queremos publicar nuestro Box, ésto se realiza para seguir un estándar y que todo el mundo pueda usar dicho Box.

* Crear una MV VirtualBox nueva o usar una que ya tengamos.
* Instalar OpenSSH Server en la MV.


### Crear usuario

Vamos a crear el usuario `vagrant`. Esto lo hacemos para poder acceder a la máquina virtual por SSH desde fuera con este usuario. Y luego, a este usuario le agregamos una clave pública para autorizar el acceso sin clave
desde Vagrant. Veamos cómo:

* Ir a la MV de VirtualBox.

```
su
useradd -m vagrant
su - vagrant
mkdir .ssh
wget https://raw.githubusercontent.com/mitchellh/vagrant/master/keys/vagrant.pub -O .ssh/authorized_keys
chmod 700 .ssh
chmod 600 .ssh/authorized_keys
```
* ¿Qué estamos haciendo? ¿Te suena de verlo con SSH?
* Poner clave `vagrant` al usuario vagrant y al usuario root.

Tenemos que conceder permisos al usuario vagrant para que pueda configurar la red, instalar software, montar carpetas compartidas, etc. para ello debemos configurar `/etc/sudoers` (visudo) para que no nos solicite la password de root, cuando realicemos estas operación con el usuario vagrant.

* Añadir `vagrant ALL=(ALL) NOPASSWD: ALL` a /etc/sudoers.

> Hay que comprobar que no existe una linea indicando requiretty si existe la comentamos.

### VirtualBox Guest Additions

* Debemos asegurarnos que tenemos instalado las VirtualBox Guest Additions
con una versión compatible con el host anfitrion.

```
root@hostname:~# modinfo vboxguest
filename:       /lib/modules/3.13.0-32-generic/updates/dkms/vboxguest.ko
version:        4.3.20
license:        GPL
description:    Oracle VM VirtualBox Guest Additions for Linux Module
author:         Oracle Corporation
srcversion:     22BF504734255C977E4D805
alias:          pci:v000080EEd0000CAFEsv00000000sd00000000bc*sc*i*
depends:        
vermagic:       3.13.0-32-generic SMP mod_unload modversions

root@hostname:~# modinfo vboxguest |grep version
version:        4.3.20
```
* Apagamos la MV.

## 7.2 Crear caja Vagrant

Una vez hemos preparado la máquina virtual ya podemos crear el box.

* Vamos a crear una nueva carpeta `vagrantXX-proyecto5`, para este nuevo proyecto vagrant.
* Localizar el nombre de la máquina VirtualBox que ya hemos preparado anteriormente.
    * `VBoxManage list vms`, comando de VirtualBox que lista las MV que tenemos.
* Nos aseguramos que la MV esté apagada.
* Crear la caja `package.box` a partir de la MV.

![vagrant-package](./images/vagrant-package.png)

* Comprobamos que se ha creado el fichero `package.box` en el directorio donde
hemos ejecutado el comando.

![vagrant-package_box_file](./images/vagrant-package_box_file.png)

* Muestro la lista de cajas disponibles, pero sólo tengo 1 porque todavía
no he incluido la que acabo de crear. Finalmente, añado la nueva caja creada
por mí, al repositorio local de cajas vagrant.

![vagrant-package](./images/vagrant-2-boxes.png)

* Levantamos la MV.
* Nos conectamos sin problemas.

>
> **Vagrant y SSH**
>
> * Recordar que podemos cambiar los parámetros de configuración del acceso SSH. Mira la teoría...
> * Vagrant genera un par de llaves para cada máquina.
> * Ejecuta `vagrant ssh-config`, para averiguar donde está la llave privada para cada máquina.

---

# ANEXO

## A.2 Windows

Ampliar esta práctica para comprobar el funcionamiento de Vagrant bajo Windows y usar cajas/boxes vagrant con Windows.

## A.3 Cambios próximo Curso

* Probar Vagrant dentro de una MV...y luego con VBox dentro de la MV.
* Arreglar warning que Apache2 "Fully qualified. domain name".
* Añadir ejemplo de provisión con "inline"
* Duda: ¿El comando vagrant package XXX debe ejecutarse en $HOME? Porque parece que se crea un directorio .vagrant.
* Cambiar los nombres "mivagrantXX"y "mivagrantXXconmicaja".
* Recomendar usa Shutter en las capturas y marcar/resaltar la parte donde hay que fijarse.

## A.4 VBoxManage

https://www.garron.me/es/gnu-linux/controla-maquinas-virtuales-virtualbox.html

VBoxManage showvminfo VMNAME | grep State
