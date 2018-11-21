#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
$:.unshift("../lib")
require 'test/unit'
require 's4t-utils'


class ErrorHandlingTests < Test::Unit::TestCase
  include S4tUtils

  def test_with_pleasant_exceptions_and_no_error
    result = with_pleasant_exceptions {
      "result"
    }
    assert_equal("result", result)
  end

  def test_with_pleasant_exceptions_prints_errors_to_stderr
    result = capturing_stderr {
      with_pleasant_exceptions {
        raise Exception.new("this is the message")
      }
    }
    assert_equal("this is the message\n", result)
  end

  def test_with_pleasant_exceptions_does_not_interfere_with_exit
    assert_wants_to_exit {
      with_pleasant_exceptions {
        exit
      }
    }
  end


  def test_without_pleasant_exceptions_prints_warning
    warning = capturing_stderr { 
      assert_raises(Exception) { 
        without_pleasant_exceptions { raise Exception.new }
      }
    }
    assert_match(/Note: exception handling turned off/, warning)
  end

end
