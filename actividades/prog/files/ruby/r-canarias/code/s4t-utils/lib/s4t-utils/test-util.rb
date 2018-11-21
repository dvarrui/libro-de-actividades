#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

module S4tUtils
  module TestUtil

    module_function

    def script(name)
      File.join(PACKAGE_ROOT, 'bin', name)
    end
    
    def test(filename)
      File.join(PACKAGE_ROOT, 'test', filename)
    end
    
    def test_data(filename)
      File.join(PACKAGE_ROOT, 'test', 'data', filename)
    end
  end
end
