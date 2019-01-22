#!/usr/bin/env ruby

require_relative '08-doundo-lib'

def show_use
  puts "Usage:"
  puts "* #{$0} -c, create directories"
  puts "* #{$0} -d, delete directories"
end

show_title

if ARGV.empty?
  show_use
elsif ARGV[0]=='-c'
  create_dirs
elsif ARGV[0]=='-d'
  delete_dirs
else
  show_use
end
