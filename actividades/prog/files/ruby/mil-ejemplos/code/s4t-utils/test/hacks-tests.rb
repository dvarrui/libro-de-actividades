#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
$:.unshift("../lib")
require 'test/unit'
require 's4t-utils'


class ClaimsTests < Test::Unit::TestCase
  include S4tUtils

  # The name 'prog1' comes from Lisp. Couldn't think of a better one.
  def test_prog1_returns_argument_after_executing_block
    block_result = nil
    prog1_result = prog1(1) {
      block_result = 2
    }
    assert_equal(1, prog1_result)
    assert_equal(2, block_result)
  end

  def test_prog1_is_also_a_module_method
    block_result = nil
    prog1_result = S4tUtils.prog1(1) {
      block_result = 2
    }
    assert_equal(1, prog1_result)
    assert_equal(2, block_result)
  end

  def test_arg_forwarder_forwards_one_arg
    array = []
    forwarder = ArgForwarder.new(array, 5)
    forwarder.push
    assert_equal([5], array)
  end

  def test_arg_forwarder_forwards_multiple_args
    hash = { 1 => 'one', 2 => 'two', 3 => 'three' }
    forwarder = ArgForwarder.new(hash, 1, 2)
    forwarder.values_at
    assert_equal(["one", "two", "three"], forwarder.values_at(3))
  end

end
