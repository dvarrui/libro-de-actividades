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

ROOT_PASSWORD="change-this"

def changes_departamento
  # Apply this actions to PC's into departamento
  (1..5).each do |ip|
    IP="172.31.#{ip.tos}.0"
    puts "* host[#{IP}]"
    system("ssh root@#{IP} 'hostname -f'") #show remote hostname
    system("ssh root@#{IP} \"echo 'root:#{ROOT_PASSWORD}' | chpasswd" # set root password
  end 
end

def changes_aula109() {
  # Apply this actions to PC's into aula109
  (1..28).each do |i|
    IP="172.19.#{i}.0"
    puts  "* host[$IP]"
    #ssh root@$IP 'hostname -f' #show remote hostname
    #ssh root@$IP "echo \"root:$ROOT_PASSWORD\" | chpasswd" # set root password
    system("ssh root@#{IP} 'mount /dev/sda2 /mnt/entorno2; rm -r /mnt/entorno2/VHDs/*'") #remove VHDs files
  end
end

changes_aula109

