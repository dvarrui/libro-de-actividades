#!/usr/bin/env ruby

FOLDER = "/home/profesor"

Dir.chdir('/')
items = Dir.glob("#{FOLDER}/**/*.png")

items.each do |item|
  puts "=> cp #{item}"
end
