#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module S4tUtils

  module_function

  def user_claims(fact, &block)
    raise StandardError.new(block.call) unless fact
  end

  def user_disputes(fact, &block)
    user_claims(!fact, &block)
  end
end


