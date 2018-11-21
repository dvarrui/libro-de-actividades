#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'

class MyNonTest < Test::Unit::TestCase
  def run(*ignore_all_arguments)
    puts "Nothing will be run because I override my ancestor."
  end

  def test_not
    puts "I will not be run."
  end
end
