#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'test/unit'
require 'watir'
include Watir    # You don't need to understand this to understand the test.
                 # See the chapter on modules if you want to.

class WatirExample < Test::Unit::TestCase

  def test_marick_vanity
    ie = IE.new    # Launch Internet Explorer

    ie.goto('http://www.google.com')

    # If you view the HTML source, you can see that Google
    # names the search field 'q'.
    ie.text_field(:name, "q").set("scripting for testers")

    # 'btnI' is the name of the "I'm Feeling Lucky" button.
    ie.button(:name, "btnI").click

    # Case-insensitive search for my name.
    assert(ie.contains_text(/marick/i))
  end

end
