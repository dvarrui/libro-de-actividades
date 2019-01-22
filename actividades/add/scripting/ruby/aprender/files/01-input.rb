#!/usr/bin/ruby

print "Enter your name..."
STDOUT.flush
name = gets.chomp
puts "Hello #{name.capitalize}!"
