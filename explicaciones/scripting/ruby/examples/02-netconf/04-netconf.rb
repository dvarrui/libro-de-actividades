#!/usr/bin/env ruby
# Target:
# * Show current IP configuration
# Changes:
# * Execute command wiht system(command)
# * Filter only real IP with grep and grep -v
# * Save command output using %x[command]
# * Pretty and clear output

output = %x[ip a | grep 'inet ' | grep -v 'host lo']
ip = output.split()[1]
puts "[INFO] Showing network configuration"
puts "  IP/mask : #{ip}"
