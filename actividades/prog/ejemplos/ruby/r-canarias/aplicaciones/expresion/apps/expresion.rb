#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070831
#
# Esta clase permite evaluar expresiones numéricas. 
# Veamos unos ejemplos:
# 
#   e = Expresion.new "( 3 + 4 ) * 2"
#   e.evaluar                           => "14.0"
# 
#

require 'apps/item'
require 'apps/operador'

class Expresion
  attr_reader :expr_inicio #Expresión inicial de entrada
  attr_reader :traza #Devuelve un vector con los pasos ejecutados para evaluar la expresión

  def initialize(expresionInicial)
    @expr_inicio = limpiar_entrada expresionInicial

    @traza = [] #Guarda información de log con los pasos realizados
    @traza << "Inicio -> "+@expr_inicio

    @fin = false
    @operador = Operador.new
    @operadores = ['(','sen','*','/','-','+']
  
    @expr_vector = crear_expr_vector
  end

  #Devuelve +true+ cuando se ha terminado de evaluar la expresión.
  #Este método se usa en combinación con evaluar1op. Como evaluar1op realiza la evaluación de una única operación, seguidamente podemos preguntar a fin? para averiguar si se ha terminado de evaluar la expresión completamente.
  def fin?
    return @fin
  end

  #Devuelve un String con el valor de la expresión simplificada hasta este instante.
  def expr_actual
    salida = []
    @expr_vector.each { |e| salida << e.valor }
    return salida.join(" ")
  end 

  #Devuelve un +String+ con el resultado de evaluar la expresión de inicio.
  #Las consultas al método fin? devolverán el valor +true+.
  def evaluar()
    while(!@fin) do
      @fin = !evaluar1op
    end
    return expr_actual
  end

  #Realiza una única operación de simplificación sobre la expresión.
  #El método evaluar evalua la expresión hasta calcular que se simplifica completamente, pero este método sólo realiza una única operación de simplificación.
  #Ejemplo:
  #  e = Expresion.new "( 4 + 3 ) * 2"
  #  e.evaluar1op                     # => true
  #  e.expr_actual                    # => "( 7.0 ) * 2"
  #  e.fin?                           # => false
  #  e.evaluar1op                     # => true
  #  e.expr_actual                    # => "7.0 * 2"
  #  e.fin?                           # => false
  #  e.evaluar1op                     # => true
  #  e.expr_actual                    # => "14.0"
  #  e.fin?                           # => true
  #  
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

  private  
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

end
