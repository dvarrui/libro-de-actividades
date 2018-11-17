#!/usr/bin/env ruby
#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

require 'stringio'
require 'rubygems/user_interaction'

class MockGemUi < Gem::StreamUI
  class TermError < RuntimeError; end

  def initialize(input="")
    super(StringIO.new(input), StringIO.new, StringIO.new)
    @terminated = false
    @banged = false
  end
  
  def input
    @ins.string
  end

  def output
    @outs.string
  end

  def error
    @errs.string
  end

  def banged?
    @banged
  end

  def terminated?
    @terminated
  end

  def terminate_interaction!(status=1)
    @terminated = true 
    @banged = true
    fail TermError
  end

  def terminate_interaction(status=0)
    @terminated = true
    fail TermError
  end
end
