#!/usr/bin/env ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'test/unit'
require 'rubygems/cmd_manager'
require 'rubygems/user_interaction'
require 'test/mockgemui'

class TestCheckCommand < Test::Unit::TestCase
  include Gem::DefaultUserInteraction

  def setup
    @cm = Gem::CommandManager.new
    @cmd = @cm['check']
  end

  def test_create
    assert_equal "check", @cmd.command
    assert_equal "gem check", @cmd.program_name
    assert_match /Check/, @cmd.summary
  end

  def test_invoke_help
    use_ui(MockGemUi.new) do 
      assert ! @cmd.invoke('--help')
      assert_match /Usage:/, ui.output
    end
  end
end
