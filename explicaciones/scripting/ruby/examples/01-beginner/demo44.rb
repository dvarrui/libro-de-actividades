#!/usr/bin/ruby

input = `cat carpetas.txt`
subjects = input.split("\n")

puts "[INFO] subjects = #{subjects}"
system("mkdir curso1516")

subjects.each do |name|
  system("mkdir curso1516/#{name}")
end
puts "Finish!"
