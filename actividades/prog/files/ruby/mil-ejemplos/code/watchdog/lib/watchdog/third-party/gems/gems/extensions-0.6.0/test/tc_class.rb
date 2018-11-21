#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/class'

class TC_Class < Test::Unit::TestCase

  class ClassExample1
    attr_reader :a, :b, :c
    autoinit(:a, :b) do
      @c = @a + @b
    end
  end

  # Test autoinit with a block.
  def test_autoinit_1
    eg = ClassExample1.new(15, 3)
    assert_equal(15, eg.a)
    assert_equal(3,  eg.b)
    assert_equal(18, eg.c)
  end

  class ClassExample2
    attr_reader :x
    autoinit(:x)
  end

  # Test autoinit without a block.
  def test_autoinit_2
    eg = ClassExample2.new("Hello")
    assert_equal("Hello", eg.x)
  end

end  # class TC_Class

