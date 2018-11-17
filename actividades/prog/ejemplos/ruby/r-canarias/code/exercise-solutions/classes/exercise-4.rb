#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
  class Counter

    # Class methods


    def self.counted_new
      self.count += 1
      new_counter = new
      new_counter.birth_order = self.count
      new_counter
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

    # Instance methods
    attr_accessor :birth_order

  end

