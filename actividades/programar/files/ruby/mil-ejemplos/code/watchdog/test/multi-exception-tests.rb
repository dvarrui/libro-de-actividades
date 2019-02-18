#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require "set-standalone-test-paths.rb" unless $started_from_rakefile
require 'test/unit'
require 's4t-utils'
include S4tUtils

require 'watchdog/multi-exceptions'

class QueueExtensionTests < Test::Unit::TestCase
  def test_to_a
    queue = Queue.new
    queue << 1
    assert_equal([1], queue.to_a)
  end
end

class MultiExceptionTests < Test::Unit::TestCase
  include Watchdog

  def two_exceptions
    retval = []
    [IndexError.new("index error"),
      RuntimeError.new("runtime error")].each do | ex | 
      begin
        raise ex  # You have to raise to get a backtrace.
      rescue Exception => caught
        retval << caught
      end
    end
    retval
  end

  def setup
    @ex = MultiException.new(two_exceptions)
  end


  def test_multi_exception_aggregates_messages
    messages = @ex.message.split("\n")
    assert_match(/index error/, messages[0])
    assert_match(/runtime error/, messages[1])
  end

  def test_multi_exception_includes_exception_classes_in_messages
    messages = @ex.message.split("\n")
    assert_match(/IndexError/, messages[0])
    assert_match(/RuntimeError/, messages[1])
  end

  def test_multi_exception_aggregates_backtraces_in_traces_field
    assert_equal(2, @ex.traces.length)
    assert_equal(1, @ex.traces[0].grep(/setup/).length)
    assert_equal(1, @ex.traces[1].grep(/setup/).length)
  end

  def test_combined_trace
    begin
      MultiException.reraise_with_combined_backtrace do
        raise @ex
      end
    rescue MultiException => ex
      assert_equal(1, ex.backtrace.grep(/Trace number 0:/).length)
      assert_equal(1, ex.backtrace.grep(/Trace number 1:/).length)
      # The raise in this method is wiped out.
      assert_equal(0, ex.backtrace.grep(/test_combined_trace/).length)
      # The raises in setup are preserved.
      assert_equal(2, ex.backtrace.grep(/setup/).length)
    end
  end

end
