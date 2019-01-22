#!/usr/bin/env ruby

require_relative '14-doundo-lib'

def show_use
  puts "Usage:"
  puts "  #{$0} -c FILENAME, create directories"
  puts "  #{$0} -d FILENAME, delete directories"
  puts "  #{$0} -s FILENAME, show directory status"
  puts "  #{$0} --all FILENAME, delete->create->show status directory"
end

show_title

if ARGV.empty? or ARGV.size!=2
  show_use
  exit
elsif not File.exist? ARGV[1]
  puts "[ERROR] File <#{ARGV[1]}> dosn't exists!"
  exit
end

dirlist = read_data_from(ARGV[1])

if ARGV.empty?
  show_use
elsif ARGV[0]=='-c'
  create_dirs dirlist
elsif ARGV[0]=='-d'
  delete_dirs dirlist
elsif ARGV[0]=='-s'
  show_dir_status dirlist
elsif ARGV[0]=='--all'
  delete_dirs dirlist
  create_dirs dirlist
  show_dir_status dirlist
else
  show_use
end
