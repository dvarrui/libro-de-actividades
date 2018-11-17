#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module Gem
  @sources = ["http://gems.rubyforge.org"]
  def self.sources
    @sources
  end
end
