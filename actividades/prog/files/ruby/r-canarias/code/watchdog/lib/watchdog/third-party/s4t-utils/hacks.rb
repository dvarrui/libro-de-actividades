#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module S4tUtils

  module_function

  def prog1(retval)
    yield
    retval
  end


  class ArgForwarder
    def initialize(target, *added_args)
      @target = target
      @added_args = added_args
    end

    def method_missing(method, *args)
      @target.send(method, *(@added_args + args))
    end
  end

  
  
end
