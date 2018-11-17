# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'modulos/to_from_cadena'


class TestToFromCadena < Test::Unit::TestCase
  
  
  def setup
    @s1 = PruebaModoSimple.new
    @s1.id = 1
    @s1.nombre = "David"
    @s1.edad = 30 
    
    @s2 = PruebaModoSimple2.new
    @s2.codigo = 3
    @s2.nombre = "Vargas Ruiz"
    @s2.edad = 33.5
    
    @c1 = PruebaModoClase.new
    @c1.id = 2
    @c1.nombre = "Desiree"
    @c1.edad = 20
    
    @c2 = PruebaModoClase2.new
    @c2.codigo = 4
    @c2.nombre = "Jorge Cejas"
    @c2.edad = 24.5
  end
  
  def test_definicion
    def_s1="PruebaModoSimple:id(to_s):nombre(to_s):edad(to_s)"
    def_s2="PruebaModoSimple2:codigo(to_i):nombre(to_s):edad(to_f)"
    def_c1="PruebaModoClase:id(to_s):nombre(to_s):edad(to_s)"
    def_c2="PruebaModoClase2:codigo(to_i):nombre(to_s):edad(to_f)"
    assert_equal(def_s1,@s1.tofromcadena_definicion)
    assert_equal(def_s2,@s2.tofromcadena_definicion)
    assert_equal(def_c1,@c1.tofromcadena_definicion)
    assert_equal(def_c2,@c2.tofromcadena_definicion)
  end
  
  def test_to_cadena
    assert_equal(@s1.to_cadena, "1:David:30")
    assert(@s1.id.instance_of?(Fixnum))
    assert(@s1.nombre.instance_of?(String))
    assert(@s1.edad.instance_of?(Fixnum))
    assert_equal(@s2.to_cadena, "3:Vargas Ruiz:33.5")
    assert(@s2.codigo.instance_of?(Fixnum))
    assert(@s2.nombre.instance_of?(String))
    assert(@s2.edad.instance_of?(Float))
    #TODO: ojo con los nobres de clases defindas dentro de un módulo
    assert_equal(@c1.to_cadena, "PruebaModoClase:2:Desiree:20")
    assert(@c1.id.instance_of?(Fixnum))
    assert(@c1.nombre.instance_of?(String))
    assert(@c1.edad.instance_of?(Fixnum))
    assert_equal(@c2.to_cadena, "PruebaModoClase2:4:Jorge Cejas:24.5")
    assert(@c2.codigo.instance_of?(Fixnum))
    assert(@c2.nombre.instance_of?(String))
    assert(@c2.edad.instance_of?(Float))
  end
  
  def test_from_cadena
    @s1.from_cadena!("11:Vargas:31")
    assert_equal(@s1.to_cadena,"11:Vargas:31")
    assert(@s1.id.instance_of?(String))
    assert(@s1.nombre.instance_of?(String))
    assert(@s1.edad.instance_of?(String))
    assert_equal(@s1.id,"11")
    assert_equal(@s1.nombre,"Vargas")
    assert_equal(@s1.edad,"31")
    
    @s2.from_cadena!("12:Ruiz:22.3")
    assert_equal(@s2.to_cadena,"12:Ruiz:22.3")
    assert(@s2.codigo.instance_of?(Fixnum))
    assert(@s2.nombre.instance_of?(String))
    assert(@s2.edad.instance_of?(Float))
    assert_equal(@s2.codigo,12)
    assert_equal(@s2.nombre,"Ruiz")
    assert_equal(@s2.edad,22.3)
    
    @c1.from_cadena!("PruebaModoClase:13:Jorge:33")
    assert_equal(@c1.to_cadena,"PruebaModoClase:13:Jorge:33")
    assert(@c1.id.instance_of?(String))
    assert(@c1.nombre.instance_of?(String))
    assert(@c1.edad.instance_of?(String))
    assert_equal(@c1.id,"13")
    assert_equal(@c1.nombre,"Jorge")
    assert_equal(@c1.edad,"33")
    
    @c2.from_cadena!("PruebaModoClase2:14:Gutierrez:44.1")
    assert_equal(@c2.to_cadena,"PruebaModoClase2:14:Gutierrez:44.1")
    assert(@c2.codigo.instance_of?(Fixnum))
    assert(@c2.nombre.instance_of?(String))
    assert(@c2.edad.instance_of?(Float))
    assert_equal(@c2.codigo,14)
    assert_equal(@c2.nombre,"Gutierrez")
    assert_equal(@c2.edad,44.1)
  end
end

class Class
  #<p>Si por ejemplo el nombre de la clase es Personajes::Dinosaurio
  #devuelve sólo Dinosaurio.</p>
  def nombre_corto
    name().gsub(/^.*:/,'')
  end
end

class PruebaModoSimple
  include ToFromCadena
  attr_accessor :id, :nombre, :edad
  def initialize
    tofromcadena_campos :id, :nombre, :edad
    tofromcadena_modo :simple
  end
end

class PruebaModoSimple2
  include ToFromCadena
  attr_accessor :codigo, :nombre, :edad
  def initialize
    tofromcadena_campos :codigo , :nombre, :edad
    tofromcadena_tipos :to_i, :to_s, :to_f
    tofromcadena_modo :simple
  end
end

class PruebaModoClase
  include ToFromCadena
  attr_accessor :id, :nombre, :edad
  def initialize
    tofromcadena_campos :id, :nombre, :edad
    tofromcadena_modo :clase
  end
end

class PruebaModoClase2
  include ToFromCadena
  attr_accessor :codigo, :nombre, :edad
  def initialize
    tofromcadena_campos :codigo, :nombre, :edad
    tofromcadena_tipos :to_i, :to_s, :to_f
    tofromcadena_modo :clase
  end
end
