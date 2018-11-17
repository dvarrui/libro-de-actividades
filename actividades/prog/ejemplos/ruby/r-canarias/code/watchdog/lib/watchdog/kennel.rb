#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'watchdog/multi-exceptions'
require 'watchdog/barkers'

module Watchdog

  # A Kennel is a collection of barkers who all bark as one.
  class Kennel

    attr_reader :barkers
    def initialize
      @barkers = []
    end


    def add(*barkers)
      @barkers += barkers
    end


    def bark(subject, message)
      simultaneously do | barker | 
        barker.bark(subject, message)
      end
    end

    def annotate(exception, barker)
      # Note: cannot raise exception.class because on Windows the 
      # exception message for Net errors will contain duplicate errors.
      # This is annoying because with_pleasant_exceptions appends the
      # exception class. So we have to append it ourselves.
      annotated = StandardError.new("Complaint from #{barker.name}: #{exception.to_s} (#{exception.class})")
      annotated.set_backtrace(exception.backtrace)
      annotated
    end


    def simultaneously # execute block in thread
      Thread.abort_on_exception = false
      queue = Queue.new
      threads = @barkers.collect do | barker |
        Thread.new(barker) do | barker |
          begin
            yield(barker)
          rescue Exception => ex
            queue << annotate(ex, barker)
          end
        end
      end
      threads.each { | thread | thread.join }

      # This peculiarness is the way to set the exception's
      # backtrace with the combination of the backtraces of
      # all saved exceptions (if any)
      MultiException.reraise_with_combined_backtrace { 
        raise MultiException.new(queue.to_a) if queue.length > 0
      }
    end
  end               
end
