#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'center'
require 'test/unit'

class CenterTest < Test::Unit::TestCase
  def test_center_in_field_that_allows_one_space
    assert_equal(" hi ", "hi".center(4))
  end

  def test_center_in_field_with_odd_number_of_spaces_puts_extra_on_right
    assert_equal("  hi   ", "hi".center(7))
  end

  def test_center_in_too_small_field_does_nothing
    assert_equal("hi", "hi".center(1))
  end

  def test_center_in_just_right_field_does_nothing
    assert_equal("hi", "hi".center(2))
  end

  def test_center_second_argument_is_used_as_the_padding_character
    assert_equal("berzerk-", "berzerk".center(8, '-'))
    assert_equal("!!obstreperous!!", "obstreperous".center(16, '!'))
  end

  def test_padding_character_can_actually_be_multiple_characters
    assert_equal("<><>a<><>", 'a'.center(9, '<>'))
  end

  def test_padding_strings_that_are_too_long_are_truncated
    assert_equal("123ab1234", 'ab'.center(9, '12345'))
  end

end
