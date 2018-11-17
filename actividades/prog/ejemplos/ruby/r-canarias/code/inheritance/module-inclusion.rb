#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
class Threeer
  include Enumerable

  def each
    yield(1)
    yield(2)
    yield(3)
  end
end
