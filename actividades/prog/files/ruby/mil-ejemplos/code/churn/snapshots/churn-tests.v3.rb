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
                  header(month_before(Time.local(2005, 9, 2))))
  end

  def test_normal_subsystem_line_format
    assert_equal('         audit ********* (45)',
                 subsystem_line("audit", 45))
  end


  def test_asterisks_for_divides_by_five
    assert_equal('****', asterisks_for(20))
  end

end
