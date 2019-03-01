#!/bin/bash

echo "[INFO] Actualizando repositorios"
#cp virtualbox.repo /etc/zypp/repos.d
#wget -q https://www.virtualbox.org/download/oracle_vbox.asc -O- | rpm --import -

cp visualcode.repo /etc/zypp/repos.d
rpm --import https://packages.microsoft.com/keys/microsoft.asc

sh -c 'echo -e "[Atom]\nname=Atom Editor\nbaseurl=https://packagecloud.io/AtomEditor/atom/el/7/\$basearch\nenabled=1\ntype=rpm-md\ngpgcheck=0\nrepo_gpgcheck=1\ngpgkey=https://packagecloud.io/AtomEditor/atom/gpgkey" > /etc/zypp/repos.d/atom.repo'
zypper --gpg-auto-import-keys refresh

zypper addrepo https://download.opensuse.org/repositories/Education/openSUSE_Tumbleweed/Education.repo

zypper refresh

echo "[INFO] Desktop..."
zypper install -y patterns-mate-mate

echo "[INFO] Informática..."
zypper install -y vim tree geany fd
zypper install -y xournal evince
zypper install -y chromium midori wireshark
zypper install -y nmap traceroute ipcalc hexchat
zypper install -y gcc make kernel-devel
zypper install -y code atom
zypper install -y sqlite3 ruby2.6-rubygem-sqlite3
zypper install -y gns3 gns3-gui gns3-server

echo "[INFO] Multimedia..."
zypper install -y krita inkscape blender
zypper install -y vlc audacity
zypper install -y kdenlive openshot
zypper install -y simplescreenrecorder recordmydesktop gtk-recordMyDesktop
zypper in shutter
zypper install -n wine
zypper install -n fuse-exfat

echo "[INFO] Educativos..."
zypper in kgeography gnome-maps marble
zypper in chameleon openbabel
zypper install -n kicad  kicad-doc-es librecad dia
zypper install -n geogebra octave
zypper install -n genius kalgebra kmplot mathomatic nasc klogic
zypper install -n ngspice spice-gtk spice-up

#wget -q https://www.virtualbox.org/download/oracle_vbox.asc -O- | rpm --import -
#rpm --checksig PACKAGE_NAME

zypper update

exit 0

usermod alumno -G vboxusers
usermod alumno -G italc
usermod super -G vboxusers
usermod super -G italc

##############################################

add-apt-repository -y ppa:webupd8team/java
add-apt-repository -y ppa:didrocks/ubuntu-developer-tools-center
add-apt-repository -y ppa:paolorotolo/android-studio

# Ubuntu-developers-tools-center
apt-get -y install ubuntu-developer-tools-center
# Android SDK
apt-get -y install python-software-properties

# -- Sistemas de 64 bits
apt-get -y install aptitude
aptitude install ia32-libs
apt-get -y install lib32z1 lib32ncurses5 lib32bz2-1.0 lib32stdc++6
# Android Studio
apt-get -y install android-studio

# Virtualbox
wget http://download.virtualbox.org/virtualbox/4.3.30/Oracle_VM_VirtualBox_Extension_Pack-4.3.30-101610.vbox-extpack
VBoxManage extpack uninstall "Oracle VM VirtualBox Extension Pack"
VBoxManage extpack install Oracle_VM_VirtualBox_Extension_Pack-4.3.30-101610.vbox-extpack
#extfat

#italc 108
cp key108 /etc/italc/keys/public/teacher/key
mv /etc/italc/italc.conf /etc/italc/italc.conf.bak
cp italc.conf /etc/italc/italc.conf
#italc 109
#cp key109 /etc/italc/keys/public/teacher/key
chgrp -R italc /etc/italc/keys/public/

echo "exec /usr/bin/ica &">>/mnt/asir/home/.profile
echo "exec /usr/bin/ica &">>/mnt/daw/home/.profile

# Actualizaciones desde archivos descargados.
libreoffice LanguageTool-3.0.oxt &
virtualbox Oracle_VM_VirtualBox_Extension_Pack-*.vbox-extpack &
