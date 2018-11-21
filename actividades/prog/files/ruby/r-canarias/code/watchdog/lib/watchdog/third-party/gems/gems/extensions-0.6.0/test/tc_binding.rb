#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'extensions/binding'

  #
  # Special test case for Binding#of_caller.
  #
class TC_Binding_of_caller < Test::Unit::TestCase
    #
    # Tests Binding.of_caller
    #
  def test_of_caller
    x = 5
    _foo        # This will increment x.
    assert_equal 6, x
  end

    #
    # Test the exception that should result if Binding.of_caller is used incorrectly.
    #
  def test_of_caller_exception
    x = 10
    assert_raises(Exception) do
      _bar
      puts x
    end
  end

    # Use Binding.of_caller correctly.
  def _foo
    Binding.of_caller do |b|
      eval "x += 1", b
    end
  end

    # Use Binding.of_caller incorrectly (dangling code).
  def _bar
    Binding.of_caller do |b|
      eval "x += 1", b
    end
    "foo".strip!        # This code not allowed to be here.
  end
end  # class TC_Binding_of_caller


  #
  # Test case for the other Binding methods.
  #
class TC_Binding < Test::Unit::TestCase

    # Test all the Binding methods except of_caller.
  def test_binding_methods
    x = y = 5
    b = binding
    assert_equal 5, b.eval('x')
    assert_equal ['b', 'x', 'y'], b.local_variables.sort
    assert_equal 5, b[:x]
    assert_equal 7, (b[:y] = 7)
    assert_equal 7, y
    assert_equal 'local-variable', b.defined?(:y)
  end

    # Test the Binding methods in the of_caller context.
  def test_binding_methods_with_of_caller
    x = "foo"
    y = 33
    _target
    assert_equal "FOO", x
    assert_equal -33, y
  end

    # Target method for #test_binding_methods_with_of_caller.
  def _target
    Binding.of_caller do |b|
      assert_equal "foo", b[:x]
      assert_equal 33, b[:y]
      assert_equal ['x', 'y'], b.local_variables.sort
      assert_equal "foo!", b.eval('x + y.chr')
      b[:x].upcase!
      b[:y] *= -1
      assert_equal "FOO", b[:x]
      assert_equal -33, b[:y]
      assert_equal 'local-variable', b.defined?(:y)
      assert_equal nil, b.defined?(:foobar)
    end
  end

end  # class TC_Binding
