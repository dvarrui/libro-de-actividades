#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

  def with_pleasant_exceptions
    begin
      yield
    rescue SystemExit   #(1)
      raise
    rescue Exception => ex #(2)
      $stderr.puts(ex.message)  #(3)
    end
  end

