#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module S4tUtils

  module_function

  def with_pleasant_exceptions
    yield
  rescue SystemExit
    raise
  rescue Exception => ex
    $stderr.puts(ex.message)
  end

  # with_pleasant_exceptions swallows the stack trace, which you
  # want to see during debugging. The easy way to see it is to add
  # 'out' to the method, giving this:
  def without_pleasant_exceptions
    $stderr.puts "Note: exception handling turned off."
    yield
  end
end
