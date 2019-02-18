# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 
require 'lib/dsl_helper'

class MyDsl
  dsl_accessor :name, :parameter
  
  def self.load(filename)
    dsl = new
    dsl.instance_eval(File.read(filename),filename)
    dsl
  end
  
  def define_parameters
    yield self
  end  
end
