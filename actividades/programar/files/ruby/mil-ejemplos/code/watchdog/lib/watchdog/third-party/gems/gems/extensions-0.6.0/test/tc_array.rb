#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/array'

class TC_Array < Test::Unit::TestCase
  def test_select!
      # Empty array.
    a = []
    assert_equal(nil, a.select! {false}, "return nil if array not changed")
    assert_equal([], a, "array is not changed")
    a = []
    assert_equal(nil, a.select! {true}, "return nil if array not changed")
    assert_equal([], a, "array is not changed")

      # Non-empty array.
    a = [0,1,2,3]
    assert_equal([0,2], a.select! {|x| x % 2 == 0}, "select even numbers")
    assert_equal([0,2], a, "select even numbers")
    a = [0,1,2,3]
    assert_equal(nil, a.select! {true}, "return nil if array not changed")
    assert_equal([0,1,2,3], a, "select all")
    a = [0,1,2,3]
    assert_equal([], a.select! {false}, "select none")
    assert_equal([], a, "select none")
  end

  def test_only
    assert_equal(5,   [5].only)
    assert_equal(nil, [nil].only)
    assert_raise(IndexError) { [].only }
    assert_raise(IndexError) { [1,2,3].only }
  end

  def test_rand
    array = [1,2,3]
    seen = Hash.new { |hash, key| hash[key] = 0 }
    1000.times do
      n = array.rand
      seen[n] += 1
    end
    assert_equal([1,2,3], seen.keys.sort)
    assert(seen[1] > 100)
    assert(seen[2] > 100)
    assert(seen[3] > 100)
  end
end  # class TC_Array

