#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'cs-valid'
include CsValid    # A top-level inclusion

# CsValid's Reader class can be seen everywhere:
puts Reader.new.hello

module MyModule
  class Viewer
    def hello
      "I can see Reader too: " + Reader.new.hello
    end
  end
end

puts MyModule::Viewer.new.hello
