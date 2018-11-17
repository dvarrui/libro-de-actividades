#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def rearrange(string)
  has_middle_name = /(\w+), (\w+) (\w+)/.match(string) # (1)
  no_middle_name =  /(\w+), (\w+)/.match(string)  # (2)

  if has_middle_name           # (3)
    last_name = has_middle_name[1]
    first_name = has_middle_name[2]
    middle_name = has_middle_name[3]
    "#{first_name} #{middle_name[0,1]}. #{last_name}"
  elsif no_middle_name
    last_name = no_middle_name[1]
    first_name = no_middle_name[2]
    "#{first_name} #{last_name}"
  end
end
