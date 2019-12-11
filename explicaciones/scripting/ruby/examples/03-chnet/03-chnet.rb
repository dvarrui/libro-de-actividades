#!/usr/bin/env ruby

require 'rainbow'

def reset
  name = 'Reset'
  puts "[INFO] #{Rainbow(name).bright} configuration..."
  puts "ip link enp0s20u1 down"
  puts "ip link enp0s20u1 up"
end

def set_classroom109
  name = 'classroom109'
  puts "[INFO] Setting '#{Rainbow(name).bright}' configuration..."
  puts "ip addr add 172.19.42.31 dev enp2s0"
end

def set_myhome
  name = 'myhome'
  puts "[INFO] Setting '#{Rainbow(name).bright}' configuration..."
  puts "ip addr add 192.168.1.116/24 dev enp2s0"
end

def change_network_configuration
  puts "[INFO] CHange NETwork configuration"
  puts " r. Reset"
  puts " 1. Classroom 109"
  puts " 2. My home"
  print " Select option [Enter=exit]: "
  option = gets.chop

  reset if option == 'r'
  set_classroom109 if option == '1'
  set_myhome if option == '2'
end

change_network_configuration
