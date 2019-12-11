#!/usr/bin/env ruby

def reset
  puts "[INFO] Reset configuration..."
end

def set_classroom109
  puts "[INFO] Setting 'classroom109' configuration..."
end

def set_myhome
  puts "[INFO] Setting 'myhome' configuration..."
end

def menu
  puts "[INFO] CHange NETwork configuration"
  puts " r. Reset"
  puts " 1. Classroom 109"
  puts " 2. My home"
  print " Select option: "
  option = gets.chop

  reset if option == 'r'
  set_classroom109 if option == '1'
  set_myhome if option == '2'
end

menu
