#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070802
#

require 'operador'

class Expresion
  attr_reader :expr_inicio, :traza
  attr_accessor :expr_vector, :operadores

  def initialize(p)
    @expr_inicio = p
    @expr_vector = @expr_inicio.split
    @traza = []
    @traza << "Inicio => "+@expr_inicio
    
    @fin = false
    @operador = Operador.new
    @operadores = ['*','/','-','+']
  end

  def fin?
    return @fin
  end

  def expr_actual
    return @expr_vector.join(" ")
  end 
 
  #Devuelve el resultado de evaluar la expresión de inicio
  def evaluar()
    while(!@fin) do
      @fin = (evaluar1op ? false : true)
    end
    return expr_actual
  end

  #Realiza una única operación de la expresión
  def evaluar1op()
    return false if @fin

    @operadores.each do |op|
      @expr_vector.each_index do |i|
         if (@expr_vector[i]==op)
           salida = @operador.calcular(i,@expr_vector,@traza)
           return true if salida
         end
      end
    end
    
    return false
  end
end
