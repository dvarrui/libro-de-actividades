#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 'watchdog/../../bin/watchdog'

class TestName < Test::Unit::TestCase
  include Watchdog

  class TestWatchdogCommand < WatchdogCommand
    # Erase behaviors of no interest
    def initialize; end
  end
    

  def test_command_name_ignores_ruby
    dog = TestWatchdogCommand.new
    assert_equal('echo', dog.command_name(['echo', 'foo']))
    assert_equal('echo.rb', dog.command_name(['ruby', 'echo.rb', 'foo']))
  end

  def test_command_name_is_just_basename
    dog = TestWatchdogCommand.new
    assert_equal('echo.rb', dog.command_name(['ruby', '/usr/bin/echo.rb', 'foo']))
  end

end
