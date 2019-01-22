#!/usr/bin/env ruby

require_relative '11-doundo-lib'

def show_use
  puts "Usage:"
  puts "* #{$0} -c, create directories"
  puts "* #{$0} -d, delete directories"
  puts "* #{$0} -s, show directory status"
end

show_title
dirnames = ['private11', 'group11', 'public11']

if ARGV.empty?
  show_use
elsif ARGV[0]=='-c'
  create_dirs dirnames
elsif ARGV[0]=='-d'
  delete_dirs dirnames
elsif ARGV[0]=='-s'
  show_dir_status dirnames
else
  show_use
end
