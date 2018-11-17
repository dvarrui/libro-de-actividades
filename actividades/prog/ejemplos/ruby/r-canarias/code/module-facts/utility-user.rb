#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'utility-provider'

module User
  include UtilityProvider

  class Main
    def hello
      "Main class says " + Utility.new.hello
    end
  end

  if $0 == __FILE__
    puts Main.new.hello
  end
end
