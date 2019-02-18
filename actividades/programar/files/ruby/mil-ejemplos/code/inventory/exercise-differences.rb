#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def check_usage   # (1)
  unless ARGV.length == 2 
    puts "Usage: differences.rb old-inventory new-inventory"
    exit
  end
end

def boring?(line)
  line.split('/').include?('temp') or
    line.split('/').include?('recycler')
end

def inventory_from(filename)
  inventory = File.open(filename)
  downcased = inventory.collect do | line | 
    line.chomp.downcase
  end
  downcased.reject do | line |
    boring?(line)
  end
end

def compare_inventory_files(old_file, new_file) # (2)
  old_inventory = inventory_from(old_file)
  new_inventory = inventory_from(new_file)

  puts "The following files have been added:"
  puts new_inventory - old_inventory

  puts ""
  puts "The following files have been deleted:"
  puts old_inventory - new_inventory
end

if $0 == __FILE__ # (3)
  check_usage 
  compare_inventory_files(ARGV[0], ARGV[1]) 
end
