#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 'watchdog/kennel'
require 'watchdog/barkers'


class KennelTests < Test::Unit::TestCase
  include Watchdog

  class MuzzledBarker < Barker
    attr_reader :subject, :body
    def bark(subject, body)
      @subject = subject
      @body = body
    end
  end


  def test_kennel_forwards_work_on
    kennel = Kennel.new
    muzzled = MuzzledBarker.new 
    kennel.add(muzzled)
    kennel.bark('subject', 'body')
    
    assert_equal('subject', muzzled.subject)
    assert_equal('body', muzzled.body)
  end

  class WhiningBarker < Barker
    def name; "whiner"; end

    def bark(subject, body)
      raise StandardError.new(subject)
    end
  end
    
  def test_kennel_gathers_failures
    kennel = Kennel.new
    muzzled = MuzzledBarker.new
    kennel.add(WhiningBarker.new, muzzled,
                  WhiningBarker.new, WhiningBarker.new)
    kennel.bark('whine', 'or yelp')
  rescue MultiException => ex
    messages = ex.message.split("\n")
    # Three whining messages
    assert_equal(3, messages.grep(/Complaint from whiner: whine \(StandardError\)/).length)
    # But failures don't interfere with success.
    assert_equal('whine', muzzled.subject)
    assert_equal('or yelp', muzzled.body)
  else
    fail("No exception thrown.")
  end    

    
end
