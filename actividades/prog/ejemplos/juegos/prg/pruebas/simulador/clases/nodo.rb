=begin
Fichero: clases/nodo.rb
Autor: David Vargas <dvargas@canarias.org>
Versión: 0.1.1 20080124
Descripción: Clase que representa a las entidades tipo nodo.
=end 

require 'modulos/to_from_cadena'

class Nodo
  include ToFromCadena
  attr_reader :tipo, :nombre, :cod_imagen
  attr_accessor :imagen
  
  def initialize
    tofromcadena_campos :tipo, :nombre, :cod_imagen
    tofromcadena_tipos :to_s, :to_s, :to_i
    tofromcadena_modo :simple
  end
  
  def pintar
    
  end
end
