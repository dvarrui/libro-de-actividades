#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
desire = case ARGV[0]
         when nil: '*tests.rb'
         when 'fast': '*-tests.rb'
         when 'slow': '*-slowtests.rb'
         end

Dir.glob(desire).each do | testfile |
  puts testfile
  require testfile
end
