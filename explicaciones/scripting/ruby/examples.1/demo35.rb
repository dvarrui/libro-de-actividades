#!/usr/bin/env ruby
# encoding: utf-8

if ARGV[0].nil?
  puts "Usage:"
  puts "  #{$0} NUMBER, First argument must be a number!"
  exit 1
end

table = ARGV[0].to_i

(1..10).each do |i|
  puts "#{table} * #{i} = #{table*i}"
end
