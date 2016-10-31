#!/usr/bin/env ruby

number = 3

(1..10).each do |i|
  puts number.to_s+" * #{i.to_s}  = "+(number*i).to_s
end
