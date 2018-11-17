#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---


module SomeModule

  class SomeClass
    def hello
      "hi"
    end
  end

  Constant = "I am a constant."

  def self.speak
    "I am a module method."
  end

  def speak
    "I am a mixin method."
  end

end





module SomeModule
  module InnerModule
    Constant = "I am an inner constant."

    def self.speak
      "I am an inner module method."
    end
      
    def speak
      "I am an inner mixin method."
    end

    def SomeClass
      def hello
        "an inner hello"
      end
    end
  end
end

