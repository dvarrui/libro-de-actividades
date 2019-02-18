#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 200708026
#

require 'item'
require 'operador'

class Expresion
  attr_reader :expr_inicio, :traza
  attr_reader :expr_vector, :operadores

  def initialize(p)
    @expr_inicio = limpiar_entrada p

    @traza = [] #Guarda información de log con los pasos realizados
    @traza << "Inicio -> "+@expr_inicio

    @fin = false
    @operador = Operador.new
    @operadores = ['(','sen','*','/','-','+']
  
    @expr_vector = crear_expr_vector
  end

  def limpiar_entrada(e)
    #TODO:Modificar la entrada, limpiando los errores que se detecten
    e.strip!
    #e = @expr_inicio
    #e.gsub!(/[*//+]-/,'+ -')
    return e
  end

  def crear_expr_vector
    entrada = @expr_inicio.split
    salida = []
    profundidad = 0
    entrada.each do |e|
      profundidad+=1 if e=='('
      salida << Item.new(e,profundidad)
      profundidad-=1 if e==')'
    end
    if profundidad!=0
      @fin=true
      @traza << "Error en los paréntesis (profundidad es #{profundidad} y no cero)"
    end
    return salida
  end

  def fin?
    return @fin
  end

  def expr_actual
    salida = []
    @expr_vector.each { |e| salida << e.valor }
    return salida.join(" ")
  end 

  #Devuelve el resultado de evaluar la expresión de inicio
  def evaluar()
    while(!@fin) do
      @fin = !evaluar1op
    end
    return expr_actual
  end

  #Realiza una única operación de la expresión
  def evaluar1op()
    return false if @fin

    @operadores.each do |op|
      @expr_vector.each_index do |i|
         if (@expr_vector[i].valor==op)
           salida = @operador.calcular(i,@expr_vector,@traza)
           return true if salida
         end
      end
    end
    
    return false
  end
end
