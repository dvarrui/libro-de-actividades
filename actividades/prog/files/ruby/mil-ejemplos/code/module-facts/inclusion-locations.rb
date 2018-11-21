#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---
module OuterProvider
  class GlobalClass
    def hello; 'global hi'; end
  end
end

module ModuleProvider
  class ModuleClass
    def hello; 'module hi'; end
  end
end

module ClassProvider
  class ClassClass
    def hello; 'class hi'; end
  end
end

include OuterProvider

puts GlobalClass.new.hello

def big_hello
  puts GlobalClass.new.hello
end

module M
  include ModuleProvider

  def self.big_hello
    puts GlobalClass.new.hello
    puts ModuleClass.new.hello
  end

  def big_hello
    puts GlobalClass.new.hello
    puts ModuleClass.new.hello
  end    

  class C
    include ClassProvider
    def self.big_hello
      puts GlobalClass.new.hello
      puts M::ModuleClass.new.hello
      puts ClassClass.new.hello
    end

    def big_hello
      puts GlobalClass.new.hello
      puts M::ModuleClass.new.hello
      puts ClassClass.new.hello
    end
  end
end

M.big_hello
M::C.big_hello
M::C.new.big_hello

class C2
  include M
end

C2.new.big_hello


