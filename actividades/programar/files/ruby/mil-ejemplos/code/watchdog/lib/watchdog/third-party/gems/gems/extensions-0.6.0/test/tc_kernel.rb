#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'extensions/kernel'

class TC_Kernel < Test::Unit::TestCase

    # We test this method by loading a file relative to the test directory (that's the idea,
    # after all), and confirming that a global variable defined therein has the expected value.
    # We check first that the variable is nil.
  def test_require_relative_1
    assert_nil($kernel_require_relative_test_variable_1)
    require_relative 'data/kernel_test/global_var_1'
    assert_equal('done', $kernel_require_relative_test_variable_1)
  end

    # This is to see what happens when we give the .rb extension.  It should work.
  def test_require_relative_2
    assert_nil($kernel_require_relative_test_variable_2)
    require_relative 'data/kernel_test/global_var_2.rb'
    assert_equal('done', $kernel_require_relative_test_variable_2)
  end

    # Ensuring we get a LoadError when the file not found.  We want this method to behave like
    # <tt>require</tt> as much as possible.
  def test_require_relative_3
    assert_raise(LoadError) do
      require_relative 'data/kernel_test/non-existent-file'
    end
  end

    # If we pass a non-string-like object to require_relative, we should get a NoMethodError.
  def test_require_relative_4
    assert_raise(NoMethodError) do
      require_relative 123.456
    end
  end

end

