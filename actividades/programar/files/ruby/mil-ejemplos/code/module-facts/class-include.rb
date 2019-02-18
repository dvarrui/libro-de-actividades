#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
require 'cs-valid'

module MyModule
  class Viewer
    include CsValid      # A class-level inclusion

    def hello
      "I can see Reader: " + Reader.new.hello
    end
  end

  class Oblivious
    # "This class does not include CsValid."
    # "So the following method, if used, will fail:"
    def hello
      "I can see Reader too... or can I?" + Reader.new.hello
    end
  end
      
end

puts "Reader can be seen within Viewer:"
puts MyModule::Viewer::new.hello

puts "Reader can't be seen outside the class..."
puts "...so the following will fail if uncommented:"
# puts Reader.new.hello

puts "So will this class that didn't include CsValid:"
# puts MyModule::Oblivious.new.hello
