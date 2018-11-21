#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def rearrange(string)
  match = /(\w+), (\w+) (\w+)/.match(string)  #(1)
  last_name = match[1]
  first_name = match[2]
  middle_name = match[3]
  "#{first_name} #{middle_name[0,1]}. #{last_name}" #(2)
end
