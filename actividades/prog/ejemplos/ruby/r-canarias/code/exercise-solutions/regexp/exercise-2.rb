#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def rearrange(name)
  match = /(\w+), (\w+)( \w+)?/.match(name) # (1)

  last_name = match[1]
  first_name = match[2]
  if match[3]
    separator = "#{match[3][0,2]}. "  # (2)
  else
    separator = ' '                   # (3)
  end
  
  "#{first_name}#{separator}#{last_name}" # (4)
end

