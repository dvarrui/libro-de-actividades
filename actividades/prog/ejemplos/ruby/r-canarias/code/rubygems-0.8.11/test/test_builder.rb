#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'rubygems'
Gem::manage_gems

class TestBuilder < Test::Unit::TestCase
  def test_invalid_spec_does_not_build
    spec = Gem::Specification.new 
    builder = Gem::Builder.new(spec)
    assert_raises(Gem::InvalidSpecificationException) {
      builder.build
   }
  end
end
