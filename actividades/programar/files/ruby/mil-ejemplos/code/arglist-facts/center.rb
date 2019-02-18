#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

class String
  def center(field_width, padding=" ")

    left_width = (field_width - self.length) / 2 
    left_width = 0 if left_width < 0

    right_width = field_width - left_width - self.length
    right_width = 0 if right_width < 0

    left = (padding * left_width)[0, left_width]
    right = (padding * right_width)[0, right_width]

    left + self + right
  end
end
