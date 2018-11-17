#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---


def level3
  File.open("no-such-file")
end

def level2
  level3
end

begin                    # line 11
  level2
rescue Exception => ex   # line 13
  puts ex.message
end
