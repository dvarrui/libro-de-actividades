# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'clases/imagen'
require 'sdl'

class TestImagen < Test::Unit::TestCase
  def setup
    @a = Imagen.new
    @b = Imagen.new
  end
  
  def test_parametros
    
  end
end
