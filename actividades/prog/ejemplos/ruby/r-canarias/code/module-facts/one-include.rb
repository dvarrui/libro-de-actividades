#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module Included
  Needed = "I am needed."    # (1)
  Clasher = "I would clash." # (2)
end

module Includer
  Clasher = "I should be preserved."

  Needed = Included::Needed
end

puts Includer::Clasher
puts Includer::Needed
