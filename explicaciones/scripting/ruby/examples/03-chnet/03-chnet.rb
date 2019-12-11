#!/usr/bin/env ruby

require 'rainbow'

def set_dinamic
  name = 'dinamic'
  puts "[INFO] Setting '#{Rainbow(name).bright}' configuration..."
  puts "ip link enp0s20u1 down"
  puts "ip link enp0s20u1 up"
end

def set_static_classroom109
  name = 'static_classroom109'
  puts "[INFO] Setting '#{Rainbow(name).bright}' configuration..."
  puts "ip addr add 172.19.42.31 dev enp0s20u1"
end

def set_static_myhome
  name = 'static_myhome'
  puts "[INFO] Setting '#{Rainbow(name).bright}' configuration..."
  puts "ip addr add 192.168.1.116/24 dev enp0s20u1"
end

def change_network_configuration
  puts "[INFO] CHange NETwork configuration"
  puts " 1. Dinamic"
  puts " 2. Static Classroom 109"
  puts " 3. Static My home"
  print " Select option: "
  option = gets.chop

  set_dinamic if option == '1'
  set_static_classroom109 if option == '2'
  set_static_myhome if option == '3'
end

change_network_configuration
