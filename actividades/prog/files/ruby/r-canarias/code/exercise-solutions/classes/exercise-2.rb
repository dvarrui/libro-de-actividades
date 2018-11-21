#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
  class Counter
    def self.counted_new
      self.count += 1
      new
    end

    def self.count
      @count = 0 if @count.nil?
      @count
    end

    def self.count=(value)
      @count = value
    end


    def self.reset
      self.count = nil
    end


  end

