#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit' 
require 'formatter'

class FormatterNormalUseTests < Test::Unit::TestCase

  def setup
    formatter = Formatter.new
    formatter.report_range(Time.local(2005, 1, 1), Time.local(2005, 2, 1))
    formatter.use_subsystem_with_change_count('sub1', 30)
    formatter.use_subsystem_with_change_count('sub2', 39)
    @output_lines = formatter.output.split("\n")  
  end

  def test_header_comes_before_subsystem_lines 
    assert_match(/Changes between/, @output_lines[0])
  end

  def test_both_lines_are_present_in_descending_change_count_order 
    assert_match(/sub2.*39/, @output_lines[1])
    assert_match(/sub1.*30/, @output_lines[2])
  end

  def test_nothing_else_is_present 
    assert_equal(3, @output_lines.size)
  end
end


class FormatterPrivateMethodTests < Test::Unit::TestCase
  
  def setup                            
    @formatter = Formatter.new
  end

  def test_header_format
    @formatter.report_range(Time.local(2001, 3, 3),
                            Time.local(2002, 2, 2))
    assert_equal("Changes between March 3, 2001, and February 2, 2002:",
                 @formatter.header)
  end

  def test_normal_subsystem_line_format
    assert_equal('         audit ********* (45)',
                 @formatter.subsystem_line("audit", 45))
  end

  def test_asterisks_for_divides_by_five
    assert_equal('****', @formatter.asterisks_for(20))
  end

  def test_asterisks_for_rounds_up_and_down
    assert_equal('****', @formatter.asterisks_for(18))
    assert_equal('***', @formatter.asterisks_for(17))
  end

  def test_churn_line_to_int_extracts_parenthesized_change_count
    assert_equal(19, @formatter.churn_line_to_int("    churn2 **** (19)"))
    assert_equal(9, @formatter.churn_line_to_int("    churn ** (9)"))
  end


  def test_lines_are_ordered_by_descending_change_count
    @formatter.use_subsystem_with_change_count("a count matters for sorting, not a name", 1)
    @formatter.use_subsystem_with_change_count("inventory", 0)
    @formatter.use_subsystem_with_change_count("churn", 12)
    expected = [ "         churn ** (12)",
                 "all that really matters is the number in parens - (1)",
                 "     inventory  (0)" ]

    actual = @formatter.lines_ordered_by_descending_change_count
    assert_match(/churn/, actual[0])
    assert_match(/a count matters/, actual[1])
    assert_match(/inventory/, actual[2])
  end

end
