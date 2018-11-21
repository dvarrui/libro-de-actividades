#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit' 
require 'churn'     


class ChurnTests < Test::Unit::TestCase 

  def test_month_before_is_28_days
    assert_equal(Time.local(2005, 1, 1),
                 month_before(Time.local(2005, 1, 29)))
  end

  def test_header_format
    assert_equal("Changes since 2005-08-05:",
                  header(Time.local(2005, 8, 5)))
  end

  def test_header_format
    assert_equal("Changes since 2005-08-05:",
                  header(month_before(Time.local(2005, 9, 2))))
  end


  # Reading this file and wondering about the two tests with the same
  # name?  They're two alternate versions of the same idea. I've put
  # my choice second, because it will override the first
  # definition. So when the file is run, the first is ignored.
  #
  # That's not something I'd normally do. It's a side-effect of the
  # way code samples are included into the printed book.


end
