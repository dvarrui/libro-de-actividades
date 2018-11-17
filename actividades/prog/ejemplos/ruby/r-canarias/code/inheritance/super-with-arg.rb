#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

class Parent
  def arglist_taker(one_arg)
    puts "Parent: I was given #{one_arg}." 
  end
end

class Child < Parent
  def arglist_taker(one_arg, another)
    puts "Child: I have two arguments: #{one_arg} and #{another}."
    super(one_arg)   # (1) 
  end
end


Child.new.arglist_taker(1, 2)
