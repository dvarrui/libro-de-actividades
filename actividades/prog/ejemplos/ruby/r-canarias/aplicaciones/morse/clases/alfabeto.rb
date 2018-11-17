=begin
Fichero: codigo_morse.rb
Autor: David Vargas <dvargas.canarias.org>
Versión: 0.1.2 20080112
=end

require 'clases/simbolo'

class Alfabeto
  attr_reader :alfabeto
  
  def initialize
    @alfabeto=Hash.new
  end
  
  #<p>Cargar al alfabeto Morse desde un fichero.<p>
  def cargar_codigo(fichero)
    s = SimboloMorse.new
    vector = s.get_array_desde_fichero(fichero)
    vector.each do |item|  
      @alfabeto[item.simbolo]=item.codigo
    end
  end
end
