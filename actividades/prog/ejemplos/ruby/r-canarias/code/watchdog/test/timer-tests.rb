#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 'watchdog/timer'

class TestName < Test::Unit::TestCase

  def test_timer
    duration, result = Watchdog.time {
      sleep 2
      5
    }
    # Rough check because time is inaccurate.
    assert_true(duration >= 1.5)
    assert_equal(5, result)
  end
end
