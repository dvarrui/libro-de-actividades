#!/bin/bash

#   sudo apt-add-repository ppa:qucs/qucs
#      sudo apt-get update
#     sudo apt-get install qucs

apt-get update
apt-get upgrade -y

echo "[INFO] Software general"
apt-get install -y synaptic
apt-get install -y firefox filezilla midori

apt-get install -y libreoffice libreoffice-templates
apt-get install -y libreoffice-style-oxygen libreoffice-dmath libreoffice-nplsolver
apt-get install -y libreoffice-help-es

apt-get install -y p7zip unrar p7zip-full
apt-get install -y openssh-server smbclient 
apt-get install -y pdfchain impressive xdot ardesia
apt-get install -y evince xournal pdftk
apt-get install -y impressive dia

echo "[INFO] Programas para matemáticas"
apt-get install -y geogebra
apt-get install -y octave r-base r-base-dev r-recommended

echo "[INFO] Programas para ciencias"
apt-get install -y avogadro

echo "[INFO] Programas Multimedia"
apt-get install -y openclipart openclipart-libreoffice
apt-get install -y kdenlive gimp gimp-help-es 
apt-get install -y krita inkscape blender create-resources
apt-get install -y inkscape scribus 
apt-get install -y kdenlive avidemux
apt-get install -y vlc audacity
apt-get install -y recordmydesktop gtk-recordmydesktop gnome-screenshot

echo "[INFO[ Programas para Informática"
apt-get install -y tree ruby irb ri
apt-get install -y sqlite3 sqlite3-doc ruby-sqlite3
apt-get install -y vim geany gedit
apt-get install -y nmap traceroute ipcalc hexchat
apt-get install -y wget curl htop
apt-get install -y git make smb4k
apt-get install -y libapache2-mod-php5 
apt-get install -y gkrellm
apt-get install -y gsmartcontrol
apt-get install -y eclipse netbeans glassfish-javaee glassfish-appserv
apt-get install -y xmlcopyeditor

echo "[INFO] Programas Educativos"
apt-get install -y scratch

echo "[INFO] Programas Diseño, Electricidad, Circuitos, etc."
apt-get install -y easyspice ngspice electric glogic
apt-get install -y kicad kicad-doc-es librecad
apt-get install -y arduino

echo "[INFO] Quitar programas que no hacen falta"
apt-get remove -y abiword gnumeric gnumeric-common
apt-get remove -y ace-of-penguins


apt-get autoremove

