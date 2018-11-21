# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

class Module
  def dsl_accessor(*symbols)
    symbols.each {|sym|
      class_eval %{
        def #{sym}(*val)
          if val.empty?
            @#{sym}
          else
            @#{sym} = val.size == 1 ? val[0] : val
          end
        end
      }
    }
  end
    
end
