#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
begin
  File.open("no-such-file")
  puts "You will never see me."
rescue Exception => ex
  puts ex.message
end

puts "End of script"
