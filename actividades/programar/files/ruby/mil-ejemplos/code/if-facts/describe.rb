#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
def describe(inhabitant)
  if inhabitant == "sophie"
    puts 'gender: female'
    puts 'height: 145'
  elsif inhabitant == "paul"
    puts 'gender: male'
    puts 'height: 145'
  elsif inhabitant == "dawn"
    puts 'gender: female'
    puts 'height: 170'
  elsif inhabitant == "brian"
    puts 'gender: male'
    puts 'height: 180'
  else
    puts 'species: Trachemys scripta elegans'
    puts 'height: 6'
  end
end
