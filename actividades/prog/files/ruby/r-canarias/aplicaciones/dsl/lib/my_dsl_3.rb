# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

class MyDsl
    
  def self.load(filename)
    dsl = new
    dsl.instance_eval(File.read(filename),filename)
    dsl
  end
  
  def define_parameters
    yield self
  end
  
  def name(*val)
    if val.empty?
      @name
    else
      @name = val.size == 1 ? val[0] : val
    end
  end
  
   def parameter(*val)
    if val.empty?
      @parameter
    else
      @parameter = val.size == 1 ? val[0] : val
    end
  end
end
