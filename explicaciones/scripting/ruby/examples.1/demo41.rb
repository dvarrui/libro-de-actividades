#!/usr/bin/ruby
# encoding: utf-8

users = [ 'gregorio', 'kevin', 'héctor']

users.each do |user|
  puts "[INFO] Building folder for #{user} user!"
  system("mkdir #{user}")
end
