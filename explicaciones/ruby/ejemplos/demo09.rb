#!/usr/bin/env ruby

print "Write a word : "
word = gets.chop

puts "- Word       : #{word}"
puts "- Capitalize : #{word.capitalize}"
puts "- Upcase     : #{word.upcase}"
puts "- Downcase   : #{word.downcase}"
puts "- Reverse    : #{word.reverse}"
puts "- Bytes      : #{word.bytes}"
