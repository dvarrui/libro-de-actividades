#!/usr/bin/env ruby

text = <<-TEXT
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
total = lines.size
lines.each_with_index do |line, index|
  print '=' * 10 
  print " [ INFO ] Step #{index + 1}/#{total}" 
  putps '=' * 10 
  system(line)
end

