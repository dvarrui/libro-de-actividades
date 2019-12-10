#!/usr/bin/env ruby
# Target:
# * Show current network configuration: IP, mask, interface name
# Changes:
# * Execute command wiht system(command)
# * Filter only real IP with grep and grep -v
# * Save command output using %x[command]
# * Pretty and clear output
# * It's easy then show if_name
# * Print colors using Rainbow gem (gem install rainbow)
# * Show gateway and verify Internet access

require 'rainbow'

# Get network information
output = %x[ip a | grep 'inet ' | grep -v 'host lo']
items = output.split()
ip = items[1]
if_name = items.last

items = %x[ip route | grep default].split
gateway = items[2]

ok = system('ping -c1 8.8.4.4 > /dev/null')
internet = (ok ? 'Ok' : Rainbow('No access').red)

# Display Network information
puts "[INFO] Showing network configuration"
puts "  IF name  : #{Rainbow(if_name).bright}"
puts "  IP/mask  : #{Rainbow(ip).bright}"
puts "  Gateway  : #{Rainbow(gateway).bright}"
puts "  Internet : #{Rainbow(internet).bright}"
