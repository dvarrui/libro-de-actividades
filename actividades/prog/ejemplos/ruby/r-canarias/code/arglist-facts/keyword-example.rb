#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

def center(field_width, keys={})
  spread = keys.fetch(:spread, 0)
  padding = keys.fetch(:padding, ' ')
  # ...


  puts "  field_width = #{field_width.inspect}"
  puts "  spread = #{spread.inspect}"
  puts "  padding = #{padding.inspect}"
  puts "  -----------------------------------"
end


if $0 == __FILE__
  puts "center(20) produces:"
  center(20)
  
  puts "center(20, :spread => 3) produces:"
  center(20, :spread => 3)
  
  puts "center(20, :padding => '-') produces:"
  center(20, :padding => '-')
  
  puts "center(20, :padding => '-', :spread => 3) produces:"
  center(20, :padding => '-', :spread => 3)
end
  
