#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/object'
require 'set'

class TC_Object < Test::Unit::TestCase

  def test_singleton_class
    obj = "xyz"
    expected = class << obj; self; end
    actual = obj.singleton_class
    assert_same(expected, actual)
  end

  def test_in
    assert(2.in?([1,2,3]))
    assert(2.in?(Set[1,2,3]))
    assert("two\n".in?("one\ntwo\nthree\n"))
    assert(5.in?("one\ntwo\nthree\n") == false)
    a = [ [1,3], ["a","b"] ]
    assert(["a","b"].in?(a))
  end

  def test_pp_s
    # Can't so much, as the code is so simple; all the work is done by PP.
    # Any testing of its output would be rendered incorrect by changes to that
    # library.  Just do a sanity check with a simple value.
    assert_equal(%{"one"\n}, "one".pp_s)
  end

  def test_not_nil?
    assert_equal(true,  5.not_nil?)
    assert_equal(true,  :x.not_nil?)
    assert_equal(false, nil.not_nil?)
    assert_equal(true,  false.not_nil?)
  end

  def test_non_nil?
    assert_equal(true,  5.non_nil?)
    assert_equal(true,  :x.non_nil?)
    assert_equal(false, nil.non_nil?)
    assert_equal(true,  false.non_nil?)
  end

  def test_define_method_0
    o = Object.new
    o.define_method(:foobar) do |x, y|
      x + y
    end
    assert_equal(9, o.foobar(5,4))
    assert_raise(ArgumentError) { o.foobar() }
    assert_raise(ArgumentError) { o.foobar(1) }
    assert_raise(ArgumentError) { o.foobar(1,2,3) }
  end

  def test_define_method_1
    o = Object.new
    p = proc { |a,b,c| a + b + c }
    o.define_method(:foobar, p)
    assert_equal("xyz", o.foobar('x','y','z'))
    assert_raise(ArgumentError) { o.foobar(1,2) }
  end

  def test_define_method_2
    s = 'hello'
    s.define_method(:foobar, s.method(:reverse))
    assert_equal("olleh", s.foobar)
    assert_raise(ArgumentError) { s.foobar(1,2,3) }
  end

end  # class TC_Object

