#!/usr/bin/env ruby
# encoding: utf-8

name = ARGV[0]

if name.nil?
  puts "Usage:"
  puts "  Please first argument must be your name!"
  exit 1
end

puts "Hello #{name}!"
