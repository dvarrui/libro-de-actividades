#!/usr/bin/env ruby

print "Write your name: "
name = gets.chop

puts "Hello #{name.capitalize}!"

if name == 'david'
  puts "Ruby is the best!"
else
  puts "I'm thinking about programming languages..."
end

# Now we are using conditional! And looks just like pseudocode!
