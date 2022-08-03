#!/usr/bin/env ruby

system('dialog --inputbox "Enter your name:" 8 40 2 ')
name = `cat /tmp/answer`

puts "Your name is #{name}"

