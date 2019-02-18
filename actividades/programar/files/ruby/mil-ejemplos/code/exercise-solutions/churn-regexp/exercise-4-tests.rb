#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'exercise-4'

class RearrangeTests < Test::Unit::TestCase

  def test_rearrange_with_middle_name
    assert_equal("Dawn E. Marick", rearrange("Marick, Dawn Elaine"))
  end

  def test_rearrange_without_middle_name
    assert_equal("Paul Marick", rearrange("Marick, Paul"))
  end

end
