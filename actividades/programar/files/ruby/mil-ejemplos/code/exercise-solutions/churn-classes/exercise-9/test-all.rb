#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
Dir.glob("*tests.rb").each do | testfile |
  puts testfile
  require testfile
end
