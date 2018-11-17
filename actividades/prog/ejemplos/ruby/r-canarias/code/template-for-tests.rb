#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'   #(1)
require 'X'           #(2)

class  X < Test::Unit::TestCase #(3)

  def test_X  #(4)
    assert_equal('expected', 'actual')  #(5)
  end

end
