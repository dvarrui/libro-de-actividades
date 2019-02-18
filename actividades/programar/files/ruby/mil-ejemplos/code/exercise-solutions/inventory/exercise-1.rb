#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
old_inventory = File.open('old-inventory.txt').readlines
new_inventory = File.open('new-inventory.txt').readlines

puts "The following files have been added:"
puts new_inventory - old_inventory

puts ""
puts "The following files have been deleted:"
puts old_inventory - new_inventory

new_count = (new_inventory - old_inventory).length
deleted_count = (old_inventory - new_inventory).length

common_count = new_inventory.length - new_count 


puts ""
puts "New files added:"
puts new_count
puts "Old files deleted:"
puts deleted_count
puts "Files in common:"
puts common_count
