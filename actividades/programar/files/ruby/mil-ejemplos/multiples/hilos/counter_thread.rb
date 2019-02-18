#!/usr/bin/ruby -w
# counter_thread.rb

counter = 0
counter_thread = Thread.new do
  1.upto(1000000) {counter += 1}
end

counter_thread.join unless ARGV[0]
puts "El contador fue capaz de contar hasta #{counter}"

