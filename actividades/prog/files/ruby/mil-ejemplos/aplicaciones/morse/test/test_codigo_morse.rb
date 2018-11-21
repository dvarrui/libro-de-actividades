# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'clases/codigo_morse'

class TestCodigoMorse < Test::Unit::TestCase
  def test_parametros
    m = CodigoMorse.new
    
    assert(false, 'Assertion was false.')
    flunk "TODO: Write test"
    # assert_equal("foo", bar)
  end
end
