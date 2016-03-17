#!/usr/bin/ruby
# encoding: utf-8

=begin
Author      : David Vargas
Date        : 13-16-2016
Description :
    This script execute remotely commands on several hosts at a time.

    It's necesary generate on your local PC the SSH key pairs (ssh-keygen), and
    copy the public key on every remote hosts (ssh-copy-id). So you can execute
    the script without write the password to access the remote host.

Disclaimer  : Sorry about my english! I'm Spanish speaker.
Remember    :
* Use `ssh-keygen` on your local PC to generate SSH key pair
* Use `ssh-copy-id` on your local PC to copy your plubic key into remote host

=end

def departamento
  equipos=[]
  (1..5).each { |i| equipos << "172.31.#{i}.0" }
  return equipos
end

def aula109
  equipos=[]
  (1..28).each { |i| equipos << "172.19.#{i}.0" }
  return equipos
end

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

equipos=aula109

threads=[]
equipos.each do |ip| 
  threads << Thread.new{ instalar_minicom(ip) } 
end
threads.each { |t| t.join }
 
