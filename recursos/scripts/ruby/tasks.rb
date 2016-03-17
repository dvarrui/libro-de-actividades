#!/usr/bin/ruby
# encoding: utf-8

=begin
Author      : David Vargas
Date        : 17-03-2016
Description : Actions repository
Disclaimer  : Sorry about my english! I'm Spanish speaker.
=end

def set_password(ip,password)
  system("ssh root@#{ip} 'hostname -f'") #show remote hostname
  system("ssh root@#{ip} \"echo 'root:#{password}' | chpasswd") # set root password
end

def delete_vhds(ip)
  system("ssh root@#{ip} 'mount /dev/sda2 /mnt/entorno2; rm -r /mnt/entorno2/VHDs/*'") #remove VHDs files
end

def instalar_minicom(ip)
  puts "* host[#{ip}]: Instalando minicom..."
  system("ssh root@#{ip} 'apt-get install minicom -y --force-yes'")
  system("ssh root@#{ip} 'adduser asir tty'")
  system("ssh root@#{ip} 'chmod 660 /dev/tty8'")
  puts "* host[#{ip}]: Minicom OK!"
end

