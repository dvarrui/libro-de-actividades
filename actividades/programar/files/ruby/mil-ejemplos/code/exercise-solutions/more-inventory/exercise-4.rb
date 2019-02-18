#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def check_usage
  if ARGV[0].nil? 
    puts "Usage: differences.rb old-inventory new-inventory"
    exit
  end
end

def boring?(line, boring_words)
  boring_words.any? do | a_boring_word |
    contains?(line, a_boring_word)
  end
end

def contains?(line, a_boring_word)
  line.chomp.split('/').include?(a_boring_word)
end

def inventory_from(filename)
  inventory = File.open(filename)
  downcased = inventory.collect do | line | 
    line.downcase
  end
  downcased.reject do | line |
    boring?(line)       # (1) 
  end
end

def compare_inventory_files(old_file, new_file)
  old_inventory = inventory_from(old_file)
  new_inventory = inventory_from(new_file)

  puts "The following files have been added:"
  puts new_inventory - old_inventory

  puts ""
  puts "The following files have been deleted:"
  puts old_inventory - new_inventory
end

if $0 == __FILE__
  check_usage 
  compare_inventory_files(ARGV[0], ARGV[1]) 
end
