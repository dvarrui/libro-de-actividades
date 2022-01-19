
```
```

# Script secuencial con Ruby y Vagrant

# 1. Vagrant con aprovisionamiento Shell script de Bash

## 1.1 Ficheros de partida

Te voy a dar un fichero Vagrantfile tal que así:

```
# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.define "alumnoXX-script-ruby-vagrant-opensuse" do |i|

    # Configure BOX
    i.vm.box = "generic/opensuse15"

    # Configure VM
    i.vm.hostname = "alumnoXX-script-ruby-vagrant-opensuse"

    # i.vm.network "public_network", bridge: [ "eth0" ]
    # i.vm.network "public_network", bridge: [ "wlan0" ]
    i.vm.network :forwarded_port, guest: 22, host: 2222
    i.vm.network :forwarded_port, guest: 22, host: 2231
    i.vm.synced_folder "./", "/vagrant"
    i.vm.provision "shell", path: 'install-software.sh'

    # Configure VIRTUALBOX
    i.vm.provider "virtualbox" do |v|
      v.name = 'alumnoXX-script-ruby-vagrant-opensuse'
      v.memory = 2048
    end
  end
end
```

Como habrás notado (Ya sabes Vagrant), el fichero configura lo necesario para crear una MV con GNU/Linux OpenSUSE, pero además para realizar el aprovisionamiento se utiliza un shell script de bash como el siguiente:

```
#!/usr/bin/env bash

zypper refresh

zypper install -y vim neovim nano
zypper install -y tree nmap git
zypper install -y neofetch lsb-release
zypper install -y figlet

figlet OpenSUSE > /etc/motd
echo "" >> /etc/motd
echo "David Vargas Ruiz" >> /etc/motd
echo "https://github.com/dvarrui" >> /etc/motd

echo "# Adding more alias" >> /home/vagrant/.bashrc
echo "alias c='clear'" >> /home/vagrant/.bashrc
echo "alias v='vdir'" >> /home/vagrant/.bashrc

lsb_release -d
```

## 1.2 Personalización y prueba

* Ahora debes personalizar estos ficheros con la información de "nombre-alumnoXX" que te corresponda.
* A continuación, probar los ficheros y asegurarte de que funcionan correctamente (Aunque te los pase el profesor, siempre es buena práctica comprobarlo todo por si acaso). ;-)

## 1.3 Resolver las dudas

No quiero que te quedes con dudas, así que aprende bien el contenido de los ficheros anteriores. Pero no me refiero a que te lo aprendas de memoria NO. Me refiero a que quiero que entiendas TODO lo que pone en dichos ficheros. Este conocimiento te hará falta para lo que viene a continuación. Además que caerán preguntas en los cuestionarios basadas en estos ficheros.

Entiende bien lo que hace el script! ¿Cómo?
1. Buscando información en Internet al respecto de tus dudas.
2. Probando de forma aislada cada elemento que quieras entender bien.
3. Consultando en clase y mirando los apuntes.
4. Preguntando (preguntando) al profesor. NO vale con decir: "Esto no me sale" Hay que preguntar diciendo al profesor todo lo que se ha hecho, cómo lo has hecho y explicar dónde estás trabad@ y qué no entiendes. La pregunta debe ser concreta.

Entender la utlizadad de todos los programas que se instalan. Entender todo lo que hace "echo", etc.

Las dudas que tengas, con su solución las escribes en el informe.

#
