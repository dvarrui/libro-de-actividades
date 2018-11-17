#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
#
# This file defines a $log variable for logging, and a time() method for recording timing
# information.
#

$log = Object.new
def $log.debug(str)
  STDERR.puts str
end

def time(msg, width=25)
  t = Time.now
  return_value = yield
  elapsed = Time.now.to_f - t.to_f
  elapsed = sprintf("%3.3f", elapsed)
  $log.debug "#{msg.ljust(width)}: #{elapsed}s"
  return_value
end

