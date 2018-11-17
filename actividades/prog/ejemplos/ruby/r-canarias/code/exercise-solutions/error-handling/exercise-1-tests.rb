#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'exercise-1'


# Test::Unit has a built-in assertion called assert_raise. It checks
# that the right class of Exception is raised. I often want to also
# check the exception's message, so I've added this method to the
# stock assertions. It compares the message against a regular
# expression.
module Test
  module Unit
    module Assertions
      def assert_raise_with_matching_message(exception_class, message, &block)
        exception = assert_raise(exception_class, &block)
        assert_match(message, exception.message)
      end
    end
  end
end

class TestChecking < Test::Unit::TestCase
  def test_zero_arguments_is_an_error
    assert_raise_with_matching_message(RuntimeError,
                                       /Exactly one argument is required/) {
      check_args([])
    }
  end

  def test_more_than_one_argument_is_an_error
    assert_raise_with_matching_message(RuntimeError,
                                       /Exactly one argument is required/) {
      check_args([1,2])
    }
  end

  def test_one_argument_is_just_right
    check_args(['1'])
  end

  def test_integers_are_all_digits
    assert_raise_with_matching_message(RuntimeError,
                                       /'1d' is not an integer/) {
      check_args(['1d'])
    }

    assert_raise_with_matching_message(RuntimeError,
                                       /'x303' is not an integer/) {
      check_args(['x303'])
    }

    # You may have heard of "integer overflow bugs", which happen in
    # some languages when an integer value is too big to fit in a
    # "word" (roughly, the computer's unit of calculation). Ruby
    # integers can never overflow. You can try that out with
    # ruby exercise-1.rb 1361129467683753853853498429727072845824
    # That number's too big to fit on any machine I know of.
    # Although, check_args doesn't do any integer conversion (that
    # happens later), it can't hurt to try a big number.
    check_args(["1361129467683753853853498429727072845824"])
  end
end
