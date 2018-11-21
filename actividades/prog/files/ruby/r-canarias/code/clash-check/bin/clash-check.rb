#! /opt/local/bin/ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

if ARGV.empty? or ARGV[0] == '--help'
  puts 'Usage: ruby -S clash-check.rb library-name...'
  puts
  puts 'Each library-name is checked to see if it already exists in'
  puts 'the load path. If so, a warning message is printed. Silence '
  puts 'means the library-name is safe.'
end

ARGV.each do | library |
  begin
    require library
    puts "DANGER: A library named #{library} already exists."
  rescue LoadError
  rescue Exception => ex
    puts "? Unexpected Exception #{ex.inspect} for #{library}."
  end
end
