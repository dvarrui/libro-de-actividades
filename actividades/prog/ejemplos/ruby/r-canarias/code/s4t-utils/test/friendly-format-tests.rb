#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
$:.unshift("../lib")
require 'test/unit'
require 's4t-utils'


class FriendlyFormatTests < Test::Unit::TestCase
  include S4tUtils

  def test_friendly_list
    assert_equal('', friendly_list('and', []))
    assert_equal("'a'", friendly_list('and', ['a']))
    assert_equal("'zed' + 'alpha'", friendly_list('+', [:zed, :alpha]))
    assert_equal("'1', '2', or '20'", friendly_list('or', [1, 2, 20]))
  end
end
