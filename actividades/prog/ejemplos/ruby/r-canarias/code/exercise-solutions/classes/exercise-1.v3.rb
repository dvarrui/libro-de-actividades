#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
  class Counter
    def self.maybe_initialize  #(1)
      @count = 0 if @count.nil?
    end
    
    def self.counted_new
      maybe_initialize   #(2)
      @count += 1 
      new
    end

    def self.count
      maybe_initialize   #(3)
      @count
    end
  end

