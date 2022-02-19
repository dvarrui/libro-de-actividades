
```
Curso       : 202122
Area        : Sistemas operativos, comandos, scripting
Descripción : Usar script con estructura secuencial para solucionar problemas.
              Aprovisionar máquinas con scripts en Vagrant
Requisitos  : Bash, Ruby
Tiempo      : 4 sessiones
```

# Script secuencial con Ruby y Vagrant

# 1. Vagrant con aprovisionamiento Shell script de Bash

## 1.1 Ficheros de partida

Aquí tenemos un fichero Vagrantfile:

```
# File: Vagrantfile

Vagrant.configure("2") do |config|
  config.vm.define "alumnoXX-vagrant-bash" do |i|

    # Configure BOX
    i.vm.box = "generic/opensuse15"

    # Configure VM
    i.vm.hostname = "alumnoXX-vagrant-bash"

    # i.vm.network "public_network", bridge: [ "eth0" ]
    # i.vm.network "public_network", bridge: [ "wlan0" ]
    i.vm.network :forwarded_port, guest: 22, host: 2222
    i.vm.network :forwarded_port, guest: 22, host: 2231
    i.vm.synced_folder "./", "/vagrant"
    i.vm.provision "shell", path: 'install-softwareXX.sh'

    # Configure VIRTUALBOX
    i.vm.provider "virtualbox" do |v|
      v.name = 'alumnoXX-vagrant-bash'
      v.memory = 2048
    end
  end
end
```

Como habrás notado (Ya sabes Vagrant), el fichero configura lo necesario para crear una MV con GNU/Linux OpenSUSE, pero además para realizar el aprovisionamiento se utiliza un shell script de bash como el siguiente:

```
#!/usr/bin/env bash
# File: install-software.sh

zypper refresh

zypper install -y vim nano
zypper install -y tree nmap
zypper install -y neofetch lsb-release
zypper install -y figlet

figlet OpenSUSE > /etc/motd
echo "" >> /etc/motd
echo "NOMBRE-COMPLETO-DEL-ALUMNO (XX)" >> /etc/motd
echo "URL-REPOSITORIO-DEL-ALUMNO" >> /etc/motd

echo "# Adding more alias" >> /home/vagrant/.bashrc
echo "alias c='clear'" >> /home/vagrant/.bashrc
echo "alias v='vdir'" >> /home/vagrant/.bashrc
echo "alias yosoy='echo nombre-alumnoXX'" >> /home/vagrant/.bashrc

lsb_release -d

exit 0
```

## 1.2 Personalización y prueba

* Ahora debes personalizar los ficheros anteriores con la información que te corresponda.
* A continuación, probar los ficheros, y asegurarte de que funcionan correctamente (Aunque te los pase el profesor, siempre es buena práctica comprobarlo todo por si acaso). ;-)

## 1.3 Resolver las dudas

No quiero que te quedes con dudas, así que aprende bien el contenido de los ficheros anteriores.

Pero no me refiero a que te lo aprendas de memoria NO. Me refiero a que quiero que entiendas TODO lo que pone en dichos ficheros. Este conocimiento te hará falta para lo que viene a continuación. Además que caerán preguntas en los cuestionarios basadas en estos ficheros.

¡Entiende bien lo que hace el script! ¿Cómo?
1. Buscando información en Internet al respecto de tus dudas.
2. Probando de forma aislada cada elemento que quieras entender bien.
3. Consultando en clase y mirando los apuntes.
4. Preguntando (preguntando) al profesor. NO vale con decir: "Esto no me sale" Hay que preguntar diciendo al profesor todo lo que se ha hecho, cómo lo has hecho y explicar dónde estás trabad@ y qué no entiendes. La pregunta debe ser concreta.

Entender la utlizadad de todos los programas que se instalan. Entender todo lo que hace "echo", etc.

Las dudas que tengas, con su solución las escribes en el informe.

# 2. Vagrant con aprovisionamiento script de Ruby

Como vamos a tocar el fichero Vagrantfile... hazle una copia de seguridad (`Bagrantfile.bash`). Y seguimos.

## 2.1 Modificar Bash por Ruby

Vamos a modificar y adaptar el Vagranfile para trabajar con Ruby.
* Asegúrate (creo que es así) de que la MV que se crea tiene por defecto instalado Ruby. En caso contrario añade una línea de configuración de aprovisionamiento `...vm.provision "shell", inline: "COMANDO-PARA-INSTALAR-RUBY"` al Vagranfile. Ésta debe ser la primera orden de aprovisionamiento.
    1. Si lo entiendes... seguimos.
    2. Si no lo entiendes... vuelve al paso 1.
* Crear un script Ruby que realice exactamente la misma función que el script Bash anterior.

Pistas:
* La primera línea cambia con `.../bin/env ruby`.
* El resto de comentarios son iguales en Ruby.
* Los comandos del sistema se ejecutan así `system("COMANDO-DEL-SISTEMA")`.
* El exit es igual en Ruby.

## 2.2 Comprobar

Comprobar que el nuevo script funciona correctamente con el fichero Vagranfile adaptado.

# 3. Investigación

OBJETIVO: En este apartado te planteo un objetivo y habrá que "investigar un poco" para resolverlo.

# 3.1 Script Ruby con Vagrant Ubuntu (1 punto)

Vamos a crear un Vagrantfile + script Ruby para realizar una aprovisionamiento en una MV Ubuntu.

Vagrantfile:
* Crear el fichero Vagrantfile sin aprovisionamiento.
* Comprobar primero el fichero Vagrantfile para Ubuntu.
* Si funciona... seguimos.

Script Ruby para aprovisionar:
* El listado de paquetes que debemos aprovisionar es el siguiente: neofetch, neovim, git, nmap, tree, traceroute, tmate, figlet, cowsay. Como son tantos... deberías usar la estructura it* junto con Arrays de Ruby (IMHO).
* Investiga la utilidad de los paquetes instalados y aprende a usarlos.
* Modificar y personalizar el fichero /etc/motd de Ubuntu para que tenga el "nombre-alumnoXX".
* Comprobar.

---

# ANEXO: Script Ruby con Vagrant Windows

* Crear un Vagrantfile + script Ruby para realizar una aprovisionamiento en una MV Windows.
* Listado de paquetes que debemos aprovisionar: (elige 3 paquetes que se puedan aprovisionar con comandos). Recuerda que existe `chocolatey` que tiene el comando `choco` (Lo usamos en 1ASIR).
* Comprobar que los ficheros creados funcionan correctamente para crear la MV Windows anteior.
* Investiga la utilidad de los paquetes instalados y aprende a usarlos.
