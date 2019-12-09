#!/usr/bin/env ruby
# Target:
# * Show current IP configuration
# Changes:
# * Filter only real IP
# * Save IP into variable

ip = %x[ip a | grep 'inet ' | grep -v 'host lo']
puts "[INFO] Network configuration :"
puts " IP : #{ip}"
