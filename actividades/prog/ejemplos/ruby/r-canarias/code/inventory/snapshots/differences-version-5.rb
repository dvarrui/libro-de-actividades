#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
unless ARGV.length == 2 
  puts "Usage: differences.rb old-inventory new-inventory"
  exit
end


def inventory_from(filename) #(1)
  File.open(filename).collect do | line | 
    line.downcase
  end
end

old_inventory = inventory_from(ARGV[0]) #(2)
new_inventory = inventory_from(ARGV[1]) #(3)

puts "The following files have been added:"
puts new_inventory - old_inventory

puts ""
puts "The following files have been deleted:"
puts old_inventory - new_inventory
