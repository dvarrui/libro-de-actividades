#!/usr/bin/env ruby

# PASO 1: Comprobar que somos el usuario root

usuario = `whoami`.chop
if usuario != "root"
  puts "[ERROR] Tienes que ejecutar el script como root."
  exit 1
end

# PASO 2: Quitar la IP actual

a = `ip a | grep eth0 | grep inet`
b = a.split(" ")
ip_actual = b[1]

puts "[INFO] Quitando la IP #{ip_actual}"
system("ip addr del #{ip_actual} dev eth0")

# PASO 3: Poner una nueva IP

ip_nueva = "172.19.42.32"
puts "[INFO] Configurando la IP #{ip_nueva}"
system("ip addr add #{ip_nueva}/16 dev eth0")


