# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'modulos/to_from_cadena'

class TestToFromCadena < Test::Unit::TestCase
  def test_setup
    include ToFromCadena
    self.tofromcadena_setup :campo1, :campo2, :campo3
  end
end
