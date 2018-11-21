
require 'modulos/to_from_cadena'

class Simbolo
  include ToFromCadena
  attr_reader :simbolo, :codigo
  
  def initialize    
    tofromcadena_setup :simbolo, :codigo
    tofromcadena_modo :simple
  end
end