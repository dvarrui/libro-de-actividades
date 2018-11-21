#!/usr/bin/env ruby
#divide.rb 

100.times do
  numerator = rand(100) 
  denominator = rand(10) 
  $stderr.puts "Dividing #{numerator} by #{denominator}" if $DEBUG 
  puts numerator / denominator 
end
