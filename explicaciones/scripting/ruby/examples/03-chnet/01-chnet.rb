#!/usr/bin/env ruby

def menu
  puts "[INFO] CHange NETwork configuration"
  puts " r. Reset"
  puts " 1. Classroom 109"
  puts " 2. My home"
  print " Select option: "
  option = gets.chop

  reset if option == 'r'
  set_classroom109 if option == '1'
  set_my_home if option == '2'
end

menu
