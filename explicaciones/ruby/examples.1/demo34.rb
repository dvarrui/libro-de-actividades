#!/usr/bin/env ruby
# encoding: utf-8

name = ARGV[0]

if name.nil?
  puts "Usage:"
  puts "  #{$0} NAME, First argument must be your name!"
  exit 1
end

puts "Hello #{name}!"
exit 0

# Using exitcode:
# * =0 -> ok
# * >0 -> error
