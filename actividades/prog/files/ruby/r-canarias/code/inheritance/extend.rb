#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'

class MyExtendedTest < Test::Unit::TestCase
  def run(*args)
    puts "About to run."
    super
    puts "Done running."
  end

  def test_extension
    puts "I will be run verbosely."
  end
end
