#!/usr/bin/env ruby

require_relative '10-doundo-lib'

def show_use
  puts "Usage:"
  puts "* #{$0} -c, create directories"
  puts "* #{$0} -d, delete directories"
  puts "* #{$0} -s, show directory status"
end

show_title

if ARGV.empty?
  show_use
elsif ARGV[0]=='-c'
  create_dirs
elsif ARGV[0]=='-d'
  delete_dirs
elsif ARGV[0]=='-s'
  show_dir_status
else
  show_use
end
