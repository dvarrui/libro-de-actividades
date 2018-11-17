#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/hash'

class TC_Hash < Test::Unit::TestCase
  def test_select!
      # Empty hash.
    a = {}
    assert_equal(nil, a.select! {false}, "return nil if hash not changed")
    assert_equal({}, a, "hash is not changed")

    a = {}
    assert_equal(nil, a.select! {true}, "return nil if hash not changed")
    assert_equal({}, a, "hash is not changed")

      # Non-empty hash.
    a = {0 => 'a', 1 => 'b', 2 => 'c', 3 => 'd'}
    assert_equal({0 => 'a', 2 => 'c'}, a.select! {|x,y| x % 2 == 0}, "select even numbers")
    assert_equal({0 => 'a', 2 => 'c'}, a, "select even numbers")

    a = {0 => 'a', 1 => 'b', 2 => 'c', 3 => 'd'}
    assert_equal({1 => 'b'}, a.select! {|x,y| y == 'b'}, "select one member")
    assert_equal({1 => 'b'}, a, "select one member")

    a = {0 => 'a', 1 => 'b', 2 => 'c', 3 => 'd'}
    assert_equal(nil, a.select! {true}, "return nil if hash not changed")
    assert_equal({0 => 'a', 1 => 'b', 2 => 'c', 3 => 'd'}, a, "select all")

    a = {0 => 'a', 1 => 'b', 2 => 'c', 3 => 'd'}
    assert_equal({}, a.select! {false}, "select none")
    assert_equal({}, a, "select none")
  end
end  # class TC_Hash

