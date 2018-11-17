#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'

class Inheritor < Test::Unit::TestCase
  def test_announcer
    puts "This class inherits TestCase's behavior."
  end
end
