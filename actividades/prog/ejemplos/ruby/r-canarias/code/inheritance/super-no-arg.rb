#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---


class Parent
  def arglist_taker(*args)
    puts "Parent: I was given #{args.inspect}." 
  end
end

class Child < Parent
  def arglist_taker(one_arg, another)
    puts "Child: I have two arguments: #{one_arg} and #{another}."
    super()   # (1) 
  end
end



Child.new.arglist_taker(1, 2)
