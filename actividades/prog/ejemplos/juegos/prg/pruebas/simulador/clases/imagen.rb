=begin
Fichero: clases/imagen.rb
Autor: David Vargas <dvargas@canarias.org>
Fecha: 0.1.0 20080119
Descripción: Clase que representa cada una de las imágenes.
=end

require 'modulos/to_from_cadena'

class Imagen
  include ToFromCadena
  attr_reader :id, :nombre
  attr_accessor :imagen

  def initialize
    tofromcadena_campos :id, :nombre
    tofromcadena_tipos :to_i, :to_s       
    tofromcadena_modo :simple
  end
  
  def ancho
    @imagen.w
  end
  
  def alto
    @imagen.h
  end
end
