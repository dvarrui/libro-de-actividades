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
gateway_ip = items[2]
internet_ok = system("ping 1.1.1.1 -c 1 > /dev/null")

# DNS
dns_ip = "?"
dns_ok = system("host www.nba.com > /dev/null")


####
# Mostrar los datos por pantalla

puts "[-] Executing...       ".white + appname.light_cyan

sleep sleep_time
print "[+] IP y máscara     : ".white
puts ip
print "[+] Puerta de enlace : ".white
puts gateway_ip
print "[+] Acceso a Internet: ".white
puts internet_ok
print "[+] Servidor DNS1    : ".white
puts dns_ip
print "[+] Servicio DNS     : ".white
puts dns_ok


exit 0
