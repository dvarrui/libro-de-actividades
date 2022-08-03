#!/usr/bin/env ruby

puts "Choose action:"
puts "  -c  Create users"
puts "  -d  Delete users"

action = gets.chomp

if action == "-c"
  system "10-cristian-create.bat"
elsif action == "-d"
  system "11-cristian-delete.bat"
end

exit 0

