#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'extensions/continuation'

class TC_Continuation < Test::Unit::TestCase

    # Test Continuation.create using the counter example from the documentation.
  def test_counter
    shadow_counter = 11
    continuation, counter = Continuation.create(10)   # (A)
      shadow_counter -= 1
      assert_equal(shadow_counter, counter)
    continuation.call(counter - 1) if counter > 0     # goto (A) if counter > 0
    assert_equal(0, counter)
    assert_equal(shadow_counter, counter)
  end

    # Test Continuation.create using the cc_inject example from the documentation.
  def test_cc_inject
    sum1_5 = [1,2,3,4,5].cc_inject { |acc, n| acc + n }
    assert_equal(15, sum1_5)
  end

    # Implement Array#cc_inject for the test_cc_inject method.
  class ::Array
    def cc_inject( value=nil )
      copy = self.clone
      cc, result, item = Continuation.create( value, nil )
      next_item = copy.shift 
      if result and item
        cc.call( yield(result, item), next_item ) 
      elsif next_item
        cc.call( next_item, result ) 
      end
      result 
    end 
  end

end  # class TC_Continuation
