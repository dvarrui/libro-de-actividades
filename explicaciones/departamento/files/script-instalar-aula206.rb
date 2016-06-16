#!/usr/bin/ruby
# encoding: utf-8

# Actualización del SO XUbuntu 14.4 
# Aula 206
# Curso 2015-16
#
# Instrucciones.
# 1 - Con el comando "su" tomar el rol de "superusuario"
# 2 - Copiar fichero en /tmp
# 3 - Posicionarse en dicha carpeta.
# 4 - Comprobar los permisos de ejecución de root
# 5 - Ejecutar: ./NOMBRE-SCRIPT
#
# Fecha UM   : 20160608
# Usuario UM : David

def first_step
  system("apt-get update")
end

def last_step
  puts "[INFO] Actualizando el software..."
  system("apt-get -y autoremove")
  system("apt-get -y upgrade")
  system("apt-get -y clean")
end

def uninstall_software
  puts "[REMOVE] Quitar programas que no hacen falta"
  packages = []
  packages << [ "abiword","gnumeric","gnumeric-common","ace-of-penguins"]

  packages.flatten.each { |package| system("apt-get remove -y #{package}") }
end

def install_software_01_general
  puts "[INFO] Software general"
  packages = [ 'synaptic','firefox','filezilla',"midori"]
  packages << [ 'libreoffice','libreoffice-templates','libreoffice-style-oxygen']
  packages << [ 'libreoffice-dmath','libreoffice-nplsolver','libreoffice-help-es']
  packages << [ "myspell-es", "libreoffice-java-common"]
  packages << [ "p7zip","unrar","p7zip-full"]
  packages << [ "openssh-server","smbclient","puppet"]
  packages << [ "pdfchain","xdot","ardesia","evince","xournal","pdftk","impressive","dia"]
  packages << [ "exfat-fuse","wine","winetricks"]

  packages.flatten.each { |package| system("apt-get install -y #{package}") }
end

def install_software_02_multimedia
  puts "[INFO] Programas Multimedia"
  packages = []
  packages << [ "openclipart","openclipart-libreoffice"]
  packages << [ "kdenlive", "avidemux","pitivy"]
  packages << [ "inkscape","blender","create-resources"]
  packages << [ "gimp","gimp-help-es","krita","inkscape","scribus"]
  packages << [ "vlc","audacity","recordmydesktop","gtk-recordmydesktop","gnome-screenshot","shutter"]
  packages << [ "mencoder","gstreamer1.0-plugins-bad","gstreamer1.0-plugins-ugly","gstreamer1.0-libav","tumbler-plugins-extra" ]
  
  packages.flatten.each { |package| system("apt-get install -y #{package}") }
end

def install_software_03_ciencia
  puts "[INFO] Programas Científicos"
  packages = []
  packages << ["geogebra","octave","r-base","r-base-dev","r-recommended"]
  packages << [ "avogadro"]
  packages << [ "scratch" ]
  
  packages.flatten.each { |package| system("apt-get install -y #{package}") }
end


def install_software_04_electricidad
  puts "[INFO] Programas Diseño, Electricidad, Circuitos, etc."
  packages =[]
  packages << [ "easyspice","ngspice","electric glogic" ]
  packages << [ "kicad","kicad-doc-es","librecad" ]
  packages << [ "arduino" ]

  packages.flatten.each { |package| system("apt-get install -y #{package}") }
end

def install_software_05_informatica
  puts "[INFO[ Programas para Informática"
  packages = []
  packages << ["tree","fish","ruby","irb","ri" ]
  packages << ["sqlite3","sqlite3-doc","ruby-sqlite3" ]
  packages << [ "vim","geany"]
  packages << [ "nmap","traceroute","ipcalc","hexchat","putty","minicom"]
  packages << [ "wget","curl","htop"]
  packages << [ "git","make","smb4k"]
  packages << [ "libapache2-mod-php5" ]
  packages << [ "gkrellm","gsmartcontrol" ]
  packages << [ "eclipse","netbeans","glassfish-javaee","glassfish-appserv"]
  packages << [ "xmlcopyeditor","gedit" ]
  packages << [ "isomaster"]
  packages << [ "docker","vagrant"]
  packages << [ "xsltproc","fop","docbook-xsl-ns","docbook5-xml"]

  packages.flatten.each { |package| system("apt-get install -y #{package}") }
end

def install_software_emilio_dam

  repos =[]
  repos << [ "ppa:webupd8team/java","ppa:didrocks/ubuntu-developer-tools-center","ppa:paolorotolo/android-studio" ]
  repos.flatten.each { |repo| system("add-apt-repository -y #{repo}") }
  system("apt-get -y update")

  packages = []
  packages << [ "default-jre", "default-jdk" ]   # Java
  packages << [ "ubuntu-developer-tools-center" ] # Ubuntu-developers-tools-center
  packages << [ "python-software-properties" ] # Android SDK
  packages << [ "ia32-libs", "lib32z1","lib32ncurses5","lib32bz2-1.0","lib32stdc++6" ] #Sistemas de 64 bits
  packages << [ "android-studio" ] # Android Studio
  packages << [ "apt-show-versions" ] # Poder conocer las versiones instaladas

  packages.flatten.each { |package| system("apt-get install -y #{package}") }
end

def download_files
  #under development
end

first_step
uninstall_software
install_software_01_general
install_software_02_multimedia
install_software_03_ciencia
install_software_04_electricidad
install_software_05_informatica
last_step
