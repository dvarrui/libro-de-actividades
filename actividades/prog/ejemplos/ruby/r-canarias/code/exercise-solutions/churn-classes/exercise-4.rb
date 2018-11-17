#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit' 
require 'exercise-7'     


class SubversionRepositoryTests < Test::Unit::TestCase

  def setup
    @repository = SubversionRepository.new('root')
  end

  def test_date
    assert_equal('2005-03-04',
                 @repository.date(Time.local(2005, 3, 4)))
  end

  def test_subversion_log_can_have_no_changes
    assert_equal(0, @repository.extract_change_count_from("------------------------------------------------------------------------\n"))
  end
  
  def test_subversion_log_with_changes
    assert_equal(2, @repository.extract_change_count_from("------------------------------------------------------------------------\nr2531 | bem | 2005-07-01 01:11:44 -0500 (Fri, 01 Jul 2005) | 1 line\n\nrevisions up through ch 3 exercises\n------------------------------------------------------------------------\nr2524 | bem | 2005-06-30 18:45:59 -0500 (Thu, 30 Jun 2005) | 1 line\n\nresults of read-through; including renaming mistyping to snapshots\n------------------------------------------------------------------------\n"))
  end

end


class FormatterNormalUseTests < Test::Unit::TestCase

  def setup
    formatter = Formatter.new
    formatter.use_date('1960-02-19')
    formatter.add_subsystem_change_count('sub1', 30)
    formatter.add_subsystem_change_count('sub2', 39)
    @output_lines = formatter.output.split("\n")  #(1)
  end

  def test_header_comes_before_subsystem_lines #(2)
    assert_match(/Changes since 1960-02-19/, @output_lines[0])
  end

  def test_both_lines_are_present_in_descending_change_count_order #(3)
    assert_match(/sub2.*39/, @output_lines[1])
    assert_match(/sub1.*30/, @output_lines[2])
  end

  def test_nothing_else_is_present #(4)
    assert_equal(3, @output_lines.size)
  end
end


class FormatterPrivateMethodTests < Test::Unit::TestCase
  
  def setup                            
    @formatter = Formatter.new
  end

  def test_header_format
    assert_equal("Changes since 2005-08-05:",
                 @formatter.header('2005-08-05'))
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


  def test_order_by_descending_change_count
    original = [ "all that really matters is the number in parens - (1)",
                 "     inventory  (0)",
                 "         churn ** (12)" ]

    expected = [ "         churn ** (12)",
                 "all that really matters is the number in parens - (1)",
                 "     inventory  (0)" ]

    actual = @formatter.order_by_descending_change_count(original)

    assert_equal(expected, actual)
  end

end


class ChurnTests < Test::Unit::TestCase 

  def test_month_before_is_28_days
    assert_equal(Time.local(2005, 1, 1),
                 month_before(Time.local(2005, 1, 29)))
  end

end
