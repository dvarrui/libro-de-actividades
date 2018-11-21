#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/symbol'

class TC_Symbol < Test::Unit::TestCase
  def test_to_proc
    x = (1..10).inject(&:*)
    assert_equal(3628800, x)

    x = %w{foo bar qux}.map(&:reverse)
    assert_equal(%w{oof rab xuq}, x)

    x = [1, 2, nil, 3, nil].reject(&:nil?)
    assert_equal([1, 2, 3], x)

    x = %w{ruby and world}.sort_by(&:reverse)
    assert_equal(%w{world and ruby}, x)
  end
end  # class TC_Symbol

