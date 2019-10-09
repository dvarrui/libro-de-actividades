#!/usr/bin/ruby

system("mkdir curso1516")

subjects =  ['add', 'srd', 'ade', 'sgy']

subjects.each do |name|
  `mkdir curso1516/#{name}`
  puts "[INFO] Created folder #{name}."
end

puts "Finish!"
