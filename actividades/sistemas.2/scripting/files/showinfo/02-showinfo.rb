#!/usr/bin/env ruby

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

####
# Mostrar los datos por pantalla

puts "[-] Executing #{appname}..."
sleep sleep_time
puts "[+] IP y máscara     : " + ip
puts "[+] Puerta de enlace : " + gateway

exit 0
