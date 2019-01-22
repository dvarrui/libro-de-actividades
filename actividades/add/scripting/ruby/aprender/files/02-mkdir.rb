#!/usr/bin/ruby
require 'fileutils'

rubydir = ENV['HOME'] + '/ruby'

unless File.directory?(rubydir)
  puts "Creating <#{rubydir}> directory..."
  FileUtils.mkdir(rubydir)
end
FileUtils.chdir(rubydir)

puts "Now we are into #{`pwd`}"
