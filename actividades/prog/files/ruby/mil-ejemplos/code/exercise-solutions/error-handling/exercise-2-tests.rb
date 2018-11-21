#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'exercise-2'

class EachTests < Test::Unit::TestCase
  
  def test_each_array_element_found
    found = []
    [1, 2, 3].my_each do | elt | 
      found << elt
    end
    assert_equal([1, 2, 3], found)
  end

  def test_empty_arrays_do_nothing
    [].my_each do | elt | 
      fail("Why did I get #{elt}?")
    end
  end

  def test_each_returns_original_array
    array = [1, 2, 3]
    result = array.my_each do
      # nothing
    end
    assert_equal(array.object_id, result.object_id)
  end
end

