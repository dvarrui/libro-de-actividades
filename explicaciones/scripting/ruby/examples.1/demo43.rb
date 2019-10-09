#!/usr/bin/ruby

subjects =  ['add', 'srd', 'ade', 'sgy']

subjects.each do |name|
  `rmdir curso1516/#{name}`
  puts "[INFO] Folder #{name} deleted!"
end

`rmdir curso1516`
puts "Finish!"
