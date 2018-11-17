# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'clases/terreno'

class TestTerreno < Test::Unit::TestCase
  def setup
    @b = Terreno.new("Bosque")
    @h = Terreno.new("Hierba")
    @a = Terreno.new("Agua")       
  end

  def test_parametros
    assert_equal(@b.nombre,"Bosque")
    assert_not_equal(@b.nombre,"Hierba")
    assert_equal(@h.nombre,"Hierba")
    assert_equal(@a.nombre,"Agua")
  end
  
  def test_rep
    assert_equal(@b.rep,["Bosque"])
    assert_equal(@h.rep,["Hierba"])
    assert_equal(@a.rep,["Agua"])
  end
  
  def test_definicion
    assert_equal(1,@b.instance_variables.size)
    assert_equal(3,@b.methods.size-@b.class.methods.size)
  end
end
