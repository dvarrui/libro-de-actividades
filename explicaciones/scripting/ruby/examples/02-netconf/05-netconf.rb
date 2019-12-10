#!/usr/bin/env ruby
# Target:
# * Show current IP configuration
# Changes:
# * Execute command wiht system(command)
# * Filter only real IP with grep and grep -v
# * Save command output using %x[command]
# * Pretty and clear output
# * It's easy then show if_name

output = %x[ip a | grep 'inet ' | grep -v 'host lo']
items = output.split()
ip = items[1]
if_name = items.last
puts "[INFO] Showing network configuration"
puts "  IF name : #{if_name}"
puts "  IP/mask : #{ip}"
