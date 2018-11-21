#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---



def convert_to_integer(string)
  unless /^-?\d+$/ =~ string
    raise RuntimeError.new("'#{string}' is not an integer.") # (1)
  end
  string.to_i
end


begin
  raise RuntimeError.new("An argument is required.") unless ARGV[0]
  puts convert_to_integer(ARGV[0])
rescue Exception => ex
  puts ex.message
end
