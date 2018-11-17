#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'thread'

class Queue
  def to_a
    result = []
    until empty?
      result << self.pop
    end
    result
  end
end

module Watchdog
  class MultiException < StandardError

    attr_reader :traces
    
    def initialize(exceptions)
      messages = exceptions.collect { | ex | "#{ex.to_s} (#{ex.class})" }
      super(messages.join("\n"))
      @traces = exceptions.collect { | ex | ex.backtrace }
    end

    def self.reraise_with_combined_backtrace
      yield
    rescue MultiException => ex
      ex.traces.each_with_index { | trace, index | 
        trace.unshift("Trace number #{index}:\n")
      }
      ex.set_backtrace(ex.traces.flatten)
      raise
    end
      
    
  end
  
end
  
