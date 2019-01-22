#!/usr/bin/env ruby
#
# Objetivo
# * "Monitorizar" varios hosts
# * Sólo mostrar información necesaria

ips = [ '127.0.0.1', '192.168.1.1', '8.8.4.4', '172.16.1.1']

ips.each do |ip|
  state = system("ping -c2 #{ip} > /dev/null")
  if state
    puts "[ OK ] Host #{ip} is ON!"
  else
    puts "[ERRO] Host #{ip} is OFF!"
  end
end

# Problema:
# * La información útil se pierde entre tantas líneas
