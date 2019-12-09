#!/usr/bin/env ruby

print "Write your age: "
input = gets.chop
age = input.to_i

puts "input.class #{input.class}"
puts "age.class #{age.class}"

if age > 18
  puts "You are adult"
else
  puts "You are younger..."
end

# Better with integers
