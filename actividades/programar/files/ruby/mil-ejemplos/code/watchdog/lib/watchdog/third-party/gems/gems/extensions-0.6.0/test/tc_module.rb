#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/module'

class TC_Module < Test::Unit::TestCase

  def test_deep_const_get
    assert_equal(String, Object.const_get("String"))
    assert_equal(String, Object.const_get(:String))
    assert_equal(String, Object.deep_const_get("String"))
    assert_equal(String, Object.deep_const_get(:String))
    assert_equal(Process::Sys, Object.deep_const_get("Process::Sys"))
    assert_equal(Regexp::IGNORECASE, Object.deep_const_get("Regexp::IGNORECASE"))
    assert_equal(Regexp::MULTILINE, Object.deep_const_get("Regexp::MULTILINE"))
    assert_equal(Test::Unit::Assertions, Test.deep_const_get("Unit::Assertions"))
    assert_equal(Test::Unit, Test.deep_const_get("::Test::Unit"))
    assert_equal(Regexp::MULTILINE, Test.deep_const_get("::Regexp::MULTILINE"))
  end

  def test_class_by_name
    assert_equal(Test::Unit, Class.by_name("Test::Unit"))
    assert_equal(Test::Unit, Class.by_name("::Test::Unit"))
    assert_equal(Test::Unit::Assertions, Class.by_name("Test::Unit::Assertions"))
    assert_equal(Test::Unit::Assertions, Class.by_name("::Test::Unit::Assertions"))
    assert_equal(String, Class.by_name("String"))
    assert_equal(String, Class.by_name("::String"))
  end

  def test_class_by_name_bad_input
    assert_raise(ArgumentError) { Class.by_name("Regexp::IGNORECASE") }
    assert_raise(NameError) { Class.by_name("not-a-class") }
    assert_raise(NoMethodError) { Class.by_name(1) }  # We call to_str on argument.
    assert_raise(NameError) { Class.by_name("StrauPlunkt123") }
  end

  def test_basename
    assert_equal("String", String.basename)
    assert_equal("Unit", Test::Unit.basename)
    assert_equal("Assertions", Test::Unit::Assertions.basename)
  end

end  # class TC_Class

