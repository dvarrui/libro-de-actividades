#!/usr/bin/env ruby

text = <<-TEXT
  apt update
  apt install -y vim tree nmap
  apt install -y git
TEXT

#apt install -y figlet
#figlet ubuntu > /etc/motd
#echo "" >> /etc/motd
#echo "David Vargas Ruiz" >> /etc/motd
#echo "https://github.com/dvarrui" >> /etc/motd

#echo "# Adding more alias" >> /home/vagrant/.bashrc
#echo "alias c='clear'" >> /home/vagrant/.bashrc
#echo "alias v='vdir'" >> /home/vagrant/.bashrcCOMMANDS

#lsb_release -d

print "¿Empezamos? "
gets

lines = text.split("\n")
for line in lines do
  puts line
end

