#!/usr/bin/env ruby

puts "============================"
puts "Script para comprobar la red"
puts "============================"

# PASO 1: comprobar la IP y la máscara de red
ip_mask = "172.19.42.31/16"

a  = `ip a | grep inet| grep eth0`
b = a.split(" ")

if ip_mask == b[1]
  puts "[ OK  ] IP/MASK #{ip_mask} correcta"
else
  puts "[ERROR] IP/MASK actual #{b[1]} es diferente a #{ip_mask}"
end

# PASO 2: Comprobar la puerta de enlace

a = system("ping 1.1.1.1 -c 1 > /dev/null")
if a
  puts "[ OK  ] Puerta de enlace correcta"
else
  puts "[ERROR] Hay un problema en la puerta de enlace"
end

# PASO 3: Comprobar servidor DNS

a = system("host www.nba.com > /dev/null")
if a
  puts "[ OK  ] Servidor DNS correcto"
else
  puts "[ERROR] Servidor DNS con problemas"
end


