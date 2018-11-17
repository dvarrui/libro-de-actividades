#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 'watchdog'
include Watchdog

# Hope to discover that site-default tests are incorrect.

class SiteDefaultTests < Test::Unit::TestCase

  class Revealer < WatchdogCommand
    attr_reader :user_choices
  end

  def test_xml_config_file
    with_command_args('command to run') {
      dog = Revealer.new
      errors = [
        JabberBarker.new(dog.user_choices).errors,
        SmtpBarker.new(dog.user_choices).errors,
        StdoutBarker.new(dog.user_choices).errors,
      ]
      assert_equal([], errors.flatten.compact)
    }
  end
end
