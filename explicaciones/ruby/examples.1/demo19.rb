#!/usr/bin/env ruby

require 'pry-byebug'

number = 3
i=1

while i<=10
  binding.pry
  puts number.to_s+" * #{i.to_s}  = "+(number*i).to_s
  i=i+1
end

# Classic loop. No iterator.
# This isn't the ruby way.
