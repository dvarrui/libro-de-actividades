#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

class Time
  def self.right_now
    new
  end
end


# I'm using "class Time" twice so the examples look better in the
# book. Both methods could just as well be in the same class block.


class Time
  def self.yesterday
    right_now - 24 * 60 * 60
  end
end

