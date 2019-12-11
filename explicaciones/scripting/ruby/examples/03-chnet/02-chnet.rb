#!/usr/bin/env ruby

def set_dinamic
  puts "[INFO] Setting 'dinamic' configuration..."
end

def set_static_classroom109
  puts "[INFO] Setting 'static_classroom109' configuration..."
end

def set_static_myhome
  puts "[INFO] Setting 'static_myhome' configuration..."
end

def menu
  puts "[INFO] CHange NETwork configuration"
  puts " 1. Dinamic"
  puts " 2. Static Classroom 109"
  puts " 3. Static My home"
  print " Select option: "
  option = gets.chop

  set_dinamic if option == '1'
  set_static_classroom109 if option == '2'
  set_static_myhome if option == '3'
end

menu
