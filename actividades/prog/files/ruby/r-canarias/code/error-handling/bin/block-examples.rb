#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---


def simple
  yield
  puts 'block done.'
end



def with_arg(arg)
  yield arg
end


