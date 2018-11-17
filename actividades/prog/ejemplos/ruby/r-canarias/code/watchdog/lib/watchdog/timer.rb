#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module Watchdog

  def self.time
    start = Time.now
    retval = yield
    duration = Time.now - start
    [duration, retval]
    # As shorthand, the above could be written "return duration, retval"
  end
end
