#!/usr/bin/env ruby

puts "[ INFO ] Executing infonet..."

# IP/mask
output = `ip a | grep "inet "| grep brd`
items = output.split(" ")
puts "[ INFO ] IP y máscara: " + items[1]

# Gateway
puts "[ INFO ] Puerta de enlace"
output = `ip route`
puts output

