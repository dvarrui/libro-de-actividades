#!/usr/bin/env ruby

def menu
  puts "[INFO] CHange NETwork configuration"
  puts " 1. Dinamic"
  puts " 2. Static Classroom 109"
  puts " 3. Static My home"
  print " Select option: "
  option = gets.chop

  set_dinamic if option == '1'
  set_static_classroom109 if option == '2'
  set_static_my_home if option == '3'
end

menu
