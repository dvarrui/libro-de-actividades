#!/usr/bin/env ruby

text = <<-TEXT
  apt update
  apt install -y vim tree nmap
  apt install -y git

  apt install -y figlet
  figlet ubuntu > /etc/motd
  echo "" >> /etc/motd
  echo "David Vargas Ruiz" >> /etc/motd
  echo "https://github.com/dvarrui" >> /etc/motd

  echo "# Adding more alias" >> /home/vagrant/.bashrc
  echo "alias c='clear'" >> /home/vagrant/.bashrc
  echo "alias v='vdir'" >> /home/vagrant/.bashrc

  lsb_release -d
TEXT

lines = text.split("\n")
for line in lines do
  system(line)
end

