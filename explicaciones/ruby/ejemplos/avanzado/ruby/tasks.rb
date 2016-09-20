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

=begin
* Añadir asir al grupo dialout
    Con los dispositivos Cisco y el cable USB to Serial lo asocia a ttyUSB0
    al incorporar el usuario al grupo adquiere los permisos y ya se podrá trabajar

     dmesg | grep tty
     usb 3-1: pl2303 converter now attached to ttyUSB0
=end

def instalar_minicom(ip)
  puts "* host[#{ip}]: Instalando minicom..."
  r = system("ssh root@#{ip} 'apt-get install minicom -y --force-yes'")
  r = r and system("ssh root@#{ip} 'adduser asir dialout'")
  r = r and system("ssh root@#{ip} 'adduser asir tty'")
  r = r and system("ssh root@#{ip} 'chmod 660 /dev/tty8'")
  if r
    puts " [ OK! ] host<#{ip}>: Minicom OK!"
  else
    puts " [ERROR] host<#{ip}>"
  end
end


def instalar_software(ip)
  package = "shutter"
  puts "* host[#{ip}]: Instalando #{package}..."
  r = system("ssh root@#{ip} 'apt-get install "+package+" -y --force-yes'")
  r = system("ssh root@#{ip} 'apt-get autoremove -y'")
end
