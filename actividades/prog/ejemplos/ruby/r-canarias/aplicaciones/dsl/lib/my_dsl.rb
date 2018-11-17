# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

class MyDsl
  
  def self.load(filename)
    dsl = new
    dsl.instance_eval(File.read(filename),filename)
    dsl
  end
end
