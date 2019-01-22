#!/usr/bin/env ruby

require_relative '13-doundo-lib'

def show_use
  puts "Usage:"
  puts "* #{$0} -c, create directories"
  puts "* #{$0} -d, delete directories"
  puts "* #{$0} -s, show directory status"
end

show_title
dirlist = read_data_from('13-dirlist.txt')

if ARGV.empty?
  show_use
elsif ARGV[0]=='-c'
  create_dirs dirlist
elsif ARGV[0]=='-d'
  delete_dirs dirlist
elsif ARGV[0]=='-s'
  show_dir_status dirlist
else
  show_use
end
