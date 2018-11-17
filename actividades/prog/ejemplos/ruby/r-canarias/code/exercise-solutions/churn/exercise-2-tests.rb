#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit' 
require 'exercise-2'

class ChurnTests < Test::Unit::TestCase 

  def test_month_before_is_28_days
    assert_equal(Time.local(2005, 1, 1),
                 month_before(Time.local(2005, 1, 29)))
  end

  def test_svn_date
    assert_equal('2005-03-04',
                 svn_date(Time.local(2005, 3, 4)))
  end

  def test_header_format
    assert_equal("Changes between 2005-08-05 and 2006-12-30:",
                 header(svn_date(month_before(Time.local(2005, 9, 2))),
                        svn_date(Time.local(2006, 12, 30))))
  end

  def test_normal_subsystem_line_format
    assert_equal('         audit ********* (45)',
                 subsystem_line("audit", 45))
  end


  def test_asterisks_for_divides_by_five
    assert_equal('****', asterisks_for(20))
  end

  def test_asterisks_for_rounds_up_and_down
    assert_equal('****', asterisks_for(18))
    assert_equal('***', asterisks_for(17))
  end

  def test_asterisks_for_zero_is_a_dash
    assert_equal('-', asterisks_for(0))
  end

  def test_asterisks_for_rounds_up_small_numbers
    assert_equal('*', asterisks_for(1))
    assert_equal('*', asterisks_for(2))
    # Just in case, check nearby boundaries.
    assert_equal('*', asterisks_for(5))
    assert_equal('*', asterisks_for(7))
    assert_equal('**', asterisks_for(8))
  end


  def test_subversion_log_can_have_no_changes
    assert_equal(0, extract_change_count_from("------------------------------------------------------------------------\n"))
  end
  
  def test_subversion_log_with_changes
    assert_equal(2, extract_change_count_from("------------------------------------------------------------------------\nr2531 | bem | 2005-07-01 01:11:44 -0500 (Fri, 01 Jul 2005) | 1 line\n\nrevisions up through ch 3 exercises\n------------------------------------------------------------------------\nr2524 | bem | 2005-06-30 18:45:59 -0500 (Thu, 30 Jun 2005) | 1 line\n\nresults of read-through; including renaming mistyping to snapshots\n------------------------------------------------------------------------\n"))
  end


end
