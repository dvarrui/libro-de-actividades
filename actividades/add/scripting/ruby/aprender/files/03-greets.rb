#!/usr/bin/ruby

if ARGV.empty?
  puts "Usage: #{__FILE__} <name>"
  puts "At least one argument is required"
  exit(2)
end

ARGV.sort.each do |a|
  puts "Hello #{ a.capitalize }!"
end #end do block
