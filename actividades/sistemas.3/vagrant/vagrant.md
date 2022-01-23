
```
Curso       : 202122, 202021, 201920, 201819, 201718
Area        : Sistemas Operativos, automatización, Devops
Descripción : Uso de la herramienta de automatización Vagrant
Requisitos  : Vagrant y Virtualbox
Tiempo      : 6 sesiones
```

# Vagrant con VirtualBox

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (3.3) Comprobar proyecto 1    | | | |
| (5.2) Comprobar proyecto 2    | | | |
| (6.1) Suministro Shell Script | | | |
| (6.2) Suministro Puppet       | | | |
| (7.2) Crear Box Vagrant       | | |. |

## Introducción

Según *Wikipedia*:

Vagrant es una herramienta para la creación
y configuración de entornos de desarrollo virtualizados.

Originalmente se desarrolló para VirtualBox y sistemas de configuración
tales como Chef, Salt y Puppet. Sin embargo desde la versión 1.1 Vagrant es capaz de trabajar con múltiples proveedores, como VMware, Amazon EC2, LXC, DigitalOcean, etc.2

Aunque Vagrant se ha desarrollado en Ruby se puede usar en multitud de
proyectos escritos en otros lenguajes.

> NOTA: Para desarrollar esta actividad se ha utilizado principalmente
la información del enlace anterior publicado por Jonathan Wiesel, el 16/07/2013.

## Instalar Vagrant

> Enlaces de interés:
> * [Introducción a Vagrant](https://code.tutsplus.com/es/tutorials/introduction-to-vagrant--cms-25917)
> * [Cómo instalar y configurar Vagrant](http://codehero.co/como-instalar-y-configurar-vagrant/)
> * [Instalar vagrant en OpenSUSE 13.2](http://gattaca.es/post/running-vagrant-on-opensuse/)
> * [Descargar y actualizar vagrant](https://www.vagrantup.com/downloads.html)

* Instalar Vagrant. La instalación vamos a hacerla en una máquina real.
* Hay que comprobar que las versiones de Vagrant y VirtualBox son compatibles entre sí.
    * `vagrant version`, para comprobar la versión actual de Vagrant.
    * `VBoxManage -v`, para comprobar la versión actual de VirtualBox.
* Crear el alias `va='vagrant'`.

# 1. Proyecto: Añadir cajas

## 1.1 Imagen, caja o box

Existen muchos repositorios desde donde podemos descargar la cajas de Vagrant (Imágenes o boxes). Incluso podemos descargarnos cajas de otros sistemas oprativos desde [VagrantCloud Box List](https://app.vagrantup.com/boxes/search?provider=virtualbox)

> OJO: Sustituir **BOXNAME** por `ubuntu/bionic64`

* `vagrant box add BOXNAME`, descargar la caja que necesitamos a través de vagrant.
* `vagrant box list`, lista las cajas/imágenes disponibles actualmente en nuestra máquina.

```
> vagrant box list
BOXNAME (virtualbox, 0)
```

## 1.2 Directorio

* Crear un directorio para nuestro proyecto. Donde XX es el número de cada alumno:

```
mkdir nombre-alumnoXX-va1box.d
cd nombrealumnoXX-vagrant1
```

A partir de ahora vamos a trabajar dentro de esta carpeta.
* Crear un fichero nuevo llamado `Vagrantfile` con el siguiente contenido:
```
Vagrant.configure("2") do |config|
  config.vm.box = "BOXNAME"
  config.vm.hostname = "nombre-alumnoXX-va1box"
  config.vm.provider "virtualbox"
end
```

> INFO: Si quisiéramos partir de un fichero Vagrantfile más complejo, podemos usar el comando `vagrant init` para crearlo.

## 1.3 Comprobar

Vamos a crear una MV nueva y la vamos a iniciar usando Vagrant:
* Debemos estar dentro de `nombre-alumnoXX-va1box.d`.
* `vagrant up`, para iniciar una nueva instancia de la máquina.
* `vagrant ssh`: Conectar/entrar en nuestra máquina virtual usando SSH.

## 1.4 Eliminamos la MV

* `exit` para salir fuera de la MV.
* Ahora estamos en la máquina real.
* `vagrant halt`, para apagar la máquina virtual.
* `vagrant status`, consultar el estado actual de la máquina virtual.
* `vagrant destroy`, para eliminar la máquina virtual (No los ficheros de configuración).

> **Otros comandos últiles de Vagrant son**:
> * `vagrant suspend`: Suspender la máquina virtual. Tener en cuenta que la MV en modo **suspendido** consume más espacio en disco debido a que el estado de la máquina virtual que suele almacenarse en la RAM se pasa a disco.
> * `vagrant resume` : Volver a despertar la máquina virtual.

# 2. TEORÍA

`NO ES NECESARIO hacer este apartado. Sólo es información.`

A continuación se muestran ejemplos de configuración Vagrantfile que NO ES NECESARIO hacer. Sólo es información.

> Enlace de interés [Tutorial Vagrant. ¿Qué es y cómo usarlo?](https://geekytheory.com/tutorial-vagrant-1-que-es-y-como-usarlo)

**Carpetas compartidas**

La carpeta del proyecto que contiene el `Vagrantfile` es visible
para el sistema el virtualizado, esto nos permite compartir archivos fácilmente entre los dos entornos.

Ejemplos para configurar las carpetas compartidas:
* `config.vm.synced_folder ".", "/vagrant"`: La carpeta del proyecto es accesible desde /vagrant de la MV.
* `config.vm.synced_folder "html", "/var/www/html"`. La carpeta htdocs del proyecto es accesible desde /var/www/html de la MV.

**Configuración de red**

Cuando trabajamos con máquinas virtuales, es frecuente usarlas para proyectos enfocados a la web, y para acceder a las páginas es necesario configurar la red.

* Ejemplo para redirigir los puertos: `config.vm.network :forwarded_port, host: 4567, guest: 80`
* Ejemplo para configurar la IP: `config.vm.network "private_network", ip: "192.168.33.10"`:

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

# 3. Proyecto: Redirección de puertos

Ahora vamos a hacer otro proyecto añadiendo redirección de puertos.

## 3.1 Creamos los ficheros

En la máquina real:
* Crear carpeta `nombre-alumnoXX-va3port.d`.
* Entrar en el directorio.
* Configurar Vagrantfile para usar nuestra caja BOXNAME y hostname = "nombre-alumnoXX-vagrant3".
* Modificar el fichero `Vagrantfile`, de modo que el puerto 42XX del sistema anfitrión sea enrutado al puerto 80 del ambiente virtualizado.
  * `config.vm.network :forwarded_port, host: 42XX, guest: 80`, sustituir XX por el número de cada uno. Por ejemplo el PC 1 será 01.

Incluir en el fichero `Vagrantfile` las configuraciones necesarias para:
* La MV de VirtualBox debe tener el nombre `vagrantXX-port`.
* La memoria RAM de la MV en VirtualBox debe ser de 2048 MiB.

Levantar la MV podemos hace `vagrant up` pero también `time vagrant up` para medir el tiempo que se tarda en levantar la MV. El añadir el comando `time COMMAND` delante de un comando nos calcula el tiempo que tarda en ejecutarse dicho comandos/programa (COMMAND).

## 3.2 Entramos en la MV

* Entramos en la MV en la máquina virtual (`vagrant ssh`).
* Instalamos Apache2.

> NOTA: Cuando la MV está iniciada y si el fichero de configuración ha cambiado y queremos recargarlo (que se vuelva a leer) hacemos `vagrant reload`.

## 3.3 Comprobar

Para confirmar que hay un servicio a la escucha en 4567:
* Ir a la máquina real.
* `vagrant port` para ver la redirección de puertos de la máquina Vagrant.
* Abrir el navegador web con el URL `http://127.0.0.1:42XX`. En realidad estamos accediendo al puerto 80 de nuestro sistema virtualizado.
* `nmap -Pn localhost`

## 3.4 Eliminar la MV

(Ya sabes cómo hacerlo)

> Si levantamos 2 MV con Vagrant...
> * ¿Pueden verse entre ellas? (ping)
> * En caso negativo ¿cómo podemos configurar la red para que se vean?

# 4. Proyecto: Suministro mediante shell script

Una de los mejores aspectos de Vagrant es el uso de herramientas de suministro. Esto es, ejecutar *"una receta"* o una serie de scripts durante el proceso de arranque del entorno virtual para instalar, configurar y personalizar un sin fin de aspectos del SO del sistema anfitrión.

* `vagrant halt`, apagamos la MV.
* `vagrant destroy` y la destruimos para volver a empezar.

## 4.1 Crear ficheros

Ahora vamos a suministrar a la MV un pequeño script para instalar Apache.

Ejemplo:
```
david42-va4script.d
├── html
│   └── index.html
├── install_apache.sh
└── Vagrantfile
```

* Crear directorio `nombre-alumnoXX-va4script.d` para nuestro proyecto.
* Entrar en dicha carpeta.
* Crear la carpeta `html` y crear fichero `html/index.html` con el siguiente contenido:

```
<h1>Proyecto Vagrant4 Scripting</h1>
<p>FECHA-ACTUAL</p>
<p>Nombre-del-alumnoXX</p>
```

* Crear el script `install_apache.sh`, dentro del proyecto con el siguiente
contenido:

```
#!/usr/bin/env bash

echo "[INFO] Script de instalación de apache2 de [nombre-alumnoXX]"
apt update
apt install -y apache2
echo "[INFO] Fin del script: $(date)"
```

## 4.2 Vagrantfile

Incluir en el fichero de configuración `Vagrantfile` lo siguiente:
* `config.vm.hostname = "nombre-alumnoXX-va4script"`
* `config.vm.provision :shell, :path => "install_apache.sh"`, para indicar a Vagrant que debe ejecutar el script `install_apache.sh` dentro del entorno virtual.
* `config.vm.synced_folder "html", "/var/www/html"`, para sincronizar la carpeta exterior `html` con la carpeta interior. De esta forma el fichero "index.html" será visible dentro de la MV.

Incluir en el fichero `Vagrantfile` las configuraciones necesarias para:
* La MV de VirtualBox debe tener el nombre `vagrantXX-script`.
* La memoria RAM de la MV en VirtualBox debe ser de 2048 MiB.

## 4.3 Comprobamos

* `vagrant up`, para crear la MV. Podremos notar, al iniciar la máquina, que en los mensajes de salida se muestran mensajes que indican cómo se va instalando el paquete de Apache que indicamos.
* Para verificar que efectivamente el servidor Apache ha sido instalado e iniciado, abrimos navegador en la máquina real con URL `http://127.0.0.1:42XX`.

![vagrant-forward-example](./images/vagrant-forward-example.png)

> NOTA: Podemos usar `vagrant reload`, si la MV está en ejecución, para que coja los cambios de configuración sin necesidad de reiniciar.

# 5. Proyecto: Suministro mediante Puppet

Puppet es un orquestador. Sirve para aprovisionar las máquinas locales o remotas sin usar scripting.

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

## 5.1 Preparativos

Se pide hacer lo siguiente.
* Crear directorio `nombre-alumnoXX-va5puppet.d` como nuevo proyecto Vagrant.
* Modificar el archivo `Vagrantfile` de la siguiente forma:

```
Vagrant.configure("2") do |config|
  ...
  config.vm.hostname = "nombre-alumnoXX-va5puppet"
  ...
  # Nos aseguramos de tener Puppet en la MV antes de usarlo.
  config.vm.provision "shell", inline: "sudo apt-get update && sudo apt-get install -y puppet"

  # Hacemos aprovisionamiento con Puppet
  config.vm.provision "puppet" do |puppet|
    puppet.manifest_file = "nombre-del-alumnoXX.pp"
  end
 end
```

> Cuando usamos `config.vm.provision "shell", inline: '"echo "Hola"'`, se ejecuta directamente el comando especificado en la MV. Es lo que llamaremos provisión inline.

* Crear la carpeta `manifests`. OJO: un error muy típico es olvidarnos de la "s" final.
* Crear el fichero `manifests/nombre-del-alumnoXX.pp`, con las órdenes/instrucciones Puppet necesarias para instalar el software que elijamos. Ejemplo:

```
package { 'neofetch':
  ensure => 'present',
}
```

> NOTA:
> * El Puppet es un gestor de infraestructura que veremos en profundidad otra actividad.
> * Podemos hacer el suministro con otros gestores de infraestructura como Salt-stack. Consultar enlace  [Salt Provisioner](https://www.vagrantup.com/docs/provisioning/salt.html).

## 5.2 Vagrantfile

Incluir en el fichero `Vagrantfile` las configuraciones necesarias para:
* La MV de VirtualBox debe tener el nombre `vagrantXX-puppet`.
* La memoria RAM de la MV en VirtualBox debe ser de 2048 MiB.

## 5.3 Comprobamos

Para que se apliquen los cambios de configuración tenemos 2 caminos:
* **Con la MV encendida**
    1. `vagrant reload`, recargar la configuración.
    2. `vagrant provision`, volver a ejecutar la provisión.
* **Con la MV apagada**:
    1. `vagrant destroy`, destruir la MV.
    2. `vagrant up` volver a crearla.

Ir a la MV:
* Comprobar que el software está instalado.

# 6. Proyecto: Caja personalizada

En los apartados anteriores hemos descargado una caja/box de un repositorio de Internet, y la hemos personalizado. En este apartado vamos a crear nuestra propia caja/box a partir de una MV de VirtualBox que tengamos.

## 6.1 Preparar la MV VirtualBox

> Enlace de interés:
> * Indicaciones de [¿Cómo crear una Base Box en Vagrant a partir de una máquina virtual](http://www.dbigcloud.com/virtualizacion/146-como-crear-un-vase-box-en-vagrant-a-partir-de-una-maquina-virtual.html) para preparar la MV de VirtualBox.

**Elegir una máquina virtual**

Lo primero que tenemos que hacer es preparar nuestra máquina virtual con una determinada configuración para poder publicar nuestro Box.

* Crear una MV VirtualBox nueva o usar una que ya tengamos.
* Configurar la red en modo automático o dinámico (DHCP).
* Instalar OpenSSH Server en la MV.

**Crear usuario con aceso SSH**

Vamos a crear el usuario `vagrant`. Esto lo hacemos para poder acceder a la máquina virtual por SSH desde fuera con este usuario. Y luego, a este usuario le agregamos una clave pública para autorizar el acceso sin clave desde Vagrant. Veamos cómo:

* Ir a la MV de VirtualBox.
* Crear el usuario `vagrant`en la MV.
    * `su`
    * `useradd -m vagrant`
* Poner clave "vagrant" al usuario vagrant.
* Poner clave "vagrant" al usuario root.
* Configuramos acceso por clave pública al usuario `vagrant`:
    * `mkdir -pm 700 /home/vagrant/.ssh`, creamos la carpeta de configuración SSH.
    * `wget --no-check-certificate 'https://raw.github.com/mitchellh/vagrant/master/keys/vagrant.pub' -O /home/vagrant/.ssh/authorized_keys`, descargamos la clave pública.
    * `chmod 0600 /home/vagrant/.ssh/authorized_keys`, modificamos los permisos de la carpeta.
    * `chown -R vagrant /home/vagrant/.ssh`, modificamos el propietario de la carpeta.

> NOTA:
> * Podemos cambiar los parámetros de configuración del acceso SSH. Mira la teoría...
> * Ejecuta `vagrant ssh-config`, para averiguar donde está la llave privada para cada máquina.

**Sudoers**

Tenemos que conceder permisos al usuario `vagrant` para que pueda hacer tareas privilegiadas como configurar la red, instalar software, montar carpetas compartidas, etc. Para ello debemos configurar el fichero `/etc/sudoers` (Podemos usar el comando `visudo`) para que no nos solicite la password de root, cuando realicemos estas operaciones con el usuario `vagrant`.

* Añadir `vagrant ALL=(ALL) NOPASSWD: ALL` al fichero de configuración `/etc/sudoers`. Comprobar que no existe una linea indicando requiretty si existe la comentamos.

**Añadir las VirtualBox Guest Additions**

* Debemos asegurarnos que tenemos instalado las VirtualBox Guest Additions con una versión compatible con el host anfitrión. Comprobamos:
```
root@hostname:~# modinfo vboxguest |grep version
version:        6.0.24
```
* Apagamos la MV.

## 6.2 Crear caja Vagrant

Una vez hemos preparado la máquina virtual ya podemos crear el box.

* Vamos a crear una nueva carpeta `nombre-alumnoXX-va6custom.d`, para este nuevo proyecto vagrant.
* `VBoxManage list vms`, comando de VirtualBox que muestra los nombres de nuestras MVs. Elegir una de las máquinas (VMNAME).
* Nos aseguramos que la MV de VirtualBox VMNAME está apagada.
* `vagrant package --base VMNAME --output nombre-alumnoXX.box`, parar crear nuestra propia caja.
* Comprobamos que se ha creado el fichero `nombre-alumnoXX.box` en el directorio donde hemos ejecutado el comando.
* `vagrant box add nombre-alumno/va6custom nombre-alumnoXX.box`, añadimos la nueva caja creada por nosotros, al repositorio local de cajas vagrant de nuestra máquina.
* `vagrant box list`, consultar ahora la lista de cajas Vagrant disponibles.


## 6.3 Vagrantfile

* Crear un nuevo fichero Vagrantfile para usar nuestra caja.
* Incluir en el fichero `Vagrantfile` las configuraciones necesarias para:
    * La MV de VirtualBox debe tener el nombre `vagrantXX-nombre-alumno`.
    * La memoria RAM de la MV en VirtualBox debe ser de 2048 MiB.

## 6.4 Usar la nueva caja

* Levantamos una nueva MV a partir del Vagranfile.
* Nos debemos conectar sin problemas (`vagant ssh`).

# 7. Caja Windows

## 7.1 Windows con vagrant

* Crear una MV Windows usando vagrant.
* Comprobar

## 7.2 Limpiar

Cuando terminemos la práctica, ya no nos harán falta las cajas (boxes) que tenemos cargadas en nuestro repositorio local. Por tanto, podemos borrarlas para liberar espacio en disco:

* `vagrant box list`, para consultar las cajas disponibles.
* `vagrant box remove BOXNAME`, para eliminar una caja BOXNAME de nuestro repositorio local.

---
# ANEXO

## Pendiente

* Ampliar esta práctica para comprobar el funcionamiento de Vagrant bajo Windows y usar cajas/boxes vagrant con Windows. Ver ejemplo:

```
# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.define "windows10" do |i|
    i.vm.box = "senglin/win-10-enterprise-vs2015community"
    i.vm.box_version = "1.0.0"
    i.vm.hostname = "profesor42w10"
    i.vm.network "public_network", bridge: [ "eth0" ]
    i.vm.network :forwarded_port, guest: 80, host: 8081
    i.vm.provider "virtualbox" do |v|
      v.name = "windows10-ent-vs2015"
      v.memory = 2048
    end
  end
end
```

## A.3 Cambios próximo Curso

* Arreglar warning que Apache2 "Fully qualified. domain name".
* Duda: ¿El comando vagrant package XXX debe ejecutarse en $HOME? Porque parece que se crea un directorio .vagrant.

## A.4 VBoxManage

https://www.garron.me/es/gnu-linux/controla-maquinas-virtuales-virtualbox.html

VBoxManage showvminfo VMNAME | grep State
