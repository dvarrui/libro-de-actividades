#!/usr/bin/env ruby

require 'colorize'

# Variables del script
appname = 'showinfo'
sleep_time = 0

####
# Ejecutar comandos y recuperar información
# ip
output = `ip a | grep "inet "| grep brd`
items = output.split(" ")
ip = items[1]

# Gateway
output = `ip route | grep default`
items = output.split(" ")
gateway = items[2]

# DNS
dns = 'unkown'

####
# Mostrar los datos por pantalla

puts "[-] Executing...       ".white + appname.light_cyan

sleep sleep_time
print "[+] IP y máscara     : ".white
puts ip
print "[+] Puerta de enlace : ".white
puts gateway
print "[+] Servidor DNS1    : ".white
puts dns 

exit 0
