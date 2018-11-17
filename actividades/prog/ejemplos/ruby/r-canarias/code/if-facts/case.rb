#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def description_of(inhabitant)
  case inhabitant
  when "sophie"
    ['gender: female', 'height: 145']
  when "paul"
    ['gender: male', 'height: 145']
  when "dawn"
    ['gender: female', 'height: 170']
  when "brian"
    ['gender: male', 'height: 180']
  else
    ['species: Trachemys scripta elegans', 'height: 6']
  end
end
