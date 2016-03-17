#!/usr/bin/ruby
# encoding: utf-8

=begin
Author      : David Vargas
Date        : 17-03-2016
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

require_relative 'hosts'
require_relative 'tasks'

equipos=aula109

threads=[]
equipos.each do |ip| 
  threads << Thread.new{ instalar_minicom(ip) } 
end
threads.each { |t| t.join }
 
