#!/usr/bin/env bash

zypper refresh

zypper install -y vim nano
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

