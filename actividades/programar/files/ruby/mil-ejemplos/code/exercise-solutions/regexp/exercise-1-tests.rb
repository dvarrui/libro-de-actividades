#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit' 
require 'exercise-1'

class ChurnTests < Test::Unit::TestCase 

  # Note to book readers: 
  # The current implementation depends less on the fact documented 
  # in the following two tests than the previous one did. But, since
  # they're still true, I'm leaving the tests.

  def test_subsystem_line_surrounds_asterisks_with_spaces
    assert_match(/ \* \(3\)/, subsystem_line("ui", 3))
    assert_match(/ \*\* \(10\)/, subsystem_line("ui", 10))
  end

  def test_subsystem_line_surrounds_even_no_asterisks_with_spaces
    # ... so interesting() can depend on this, if it needs to.
    assert_match(/  \(0\)/, subsystem_line("ui", 0))
  end    


  def test_interesting_lines_contain_at_least_one_asterisk
    boring_line = subsystem_line("inventory", 0)
    interesting_line = subsystem_line("ui", 3)
    big_line = subsystem_line('util', 61)

    original = [interesting_line, boring_line, big_line]
    expected = [interesting_line, big_line]

    assert_equal(expected, interesting(original))
  end

  def test_interesting_lines_subsystem_can_have_asterisk_at_end
    boring_line = subsystem_line('inventory*', 0)
    interesting_line = subsystem_line('ui*', 3)

    original = [interesting_line, boring_line]
    expected = [interesting_line]

    assert_equal(expected, interesting(original))
  end


  def test_interesting_lines_subsystem_can_have_asterisk_anywhere
    weird_but_boring = subsystem_line('+ and *** (3) and -', 0)

    original = [weird_but_boring]
    expected = []

    assert_equal(expected, interesting(original))
  end

end
