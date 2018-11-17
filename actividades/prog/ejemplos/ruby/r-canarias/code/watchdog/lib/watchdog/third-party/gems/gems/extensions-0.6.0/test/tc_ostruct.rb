#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/ostruct'

class TC_OStruct < Test::Unit::TestCase
  class Person < OpenStruct; end

  def test_1_old_functionality
    o = OpenStruct.new
    assert_nil(o.foo)
    o.foo = :bar
    assert_equal(:bar, o.foo)
    o.delete_field(:foo)
    assert_nil(o.foo)
    o1 = OpenStruct.new(:x => 1, :y => 2)
    assert_equal(1, o1.x)
    assert_equal(2, o1.y)
    o2 = OpenStruct.new(:x => 1, :y => 2)
    assert(o1 == o2)
  end

  def test_2_new_functionality
    person = OpenStruct.new do |p|
      p.name = 'John Smith'
      p.gender  = :M
      p.age     = 71
    end
    assert_equal('John Smith', person.name)
    assert_equal(:M, person.gender)
    assert_equal(71, person.age)
    assert_equal(nil, person.address)
    person = OpenStruct.new(:gender => :M, :age => 71) do |p|
      p.name = 'John Smith'
    end
    assert_equal('John Smith', person.name)
    assert_equal(:M, person.gender)
    assert_equal(71, person.age)
    assert_equal(nil, person.address)
  end

  def test_3_subclass
    person = Person.new do |p|
      p.name = 'John Smith'
      p.gender  = :M
      p.age     = 71
    end
    assert_equal('John Smith', person.name)
    assert_equal(:M, person.gender)
    assert_equal(71, person.age)
    assert_equal(nil, person.address)
    person = Person.new(:gender => :M, :age => 71) do |p|
      p.name = 'John Smith'
    end
    assert_equal('John Smith', person.name)
    assert_equal(:M, person.gender)
    assert_equal(71, person.age)
    assert_equal(nil, person.address)
  end
end  # class TC_Array

