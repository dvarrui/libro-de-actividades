#!/usr/bin/env ruby
#
# Objetivo
# * "Monitorizar" varios hosts
# * Sólo mostrar información necesaria
# * Dar color a la salida
# * Mantener un bucle constante de vigilancia

require 'rainbow'

ips = [ '127.0.0.1', '192.168.1.1', '8.8.4.4', '172.16.1.1']

def scan(ips)
  total = ips.size
  ips.each_with_index do |ip, index|
    state = system("ping -c2 #{ip} > /dev/null")
    if state
      puts "#{index+1}/#{total} [ ON ] Host #{Rainbow(ip).bright}"
    else
      puts "#{index+1}/#{total} [ #{Rainbow('OFF').red.bright}] Host #{Rainbow(ip).bright}"
    end
  end
end

while(true)
  system('clear')
  scan ips
  sleep 3
end
