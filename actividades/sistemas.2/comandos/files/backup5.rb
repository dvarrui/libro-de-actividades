#!/usr/bin/env ruby

require 'fileutils'

# Dir.chdir('C:/')
Dir.chdir('/home/profesor')
items = Dir.glob("**/*.png")

items.each do |i|
  source = File.join('/home', 'profesor', i)
  dest = File.join('backup', File.basename(i))
#  puts source
#  puts dest
#  FileUtils.cp(source, dest)
  #puts "/home/profesor/#{i}", "backup/#{File.basename(i)}"
  puts "copy #{source} #{dest}"
  system("copy #{source} #{dest}")
end
