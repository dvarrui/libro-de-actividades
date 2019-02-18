#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 2007082
# 

class Operador
  attr_reader :traza, :operadores

  def initialize
    @metodos = { '*' => 'num_op_num', '/' => 'num_op_num', '-' => 'num_op_num', '+' => 'num_op_num'}
    @operadores = @metodos.keys
  end

  def operador?(p)
    return true if @operadores.index(p)
    false
  end

  def numero?(p)
    return true if p.to_i.to_s == p
    return true if p.to_f.to_s == p
    false
  end
  
  def calcular(index,expresion,traza)
    @traza = traza
    salida = send @metodos[expresion[index]], index, expresion 
    return salida
  end

  def constante(index,expr)
    begin
      if expr[index]=='pi'
        pi = 3.141516
        @traza << 'pi => '+pi.to_s
        expr[index] = pi.to_s
        return true
      end
    rescue
      @traza << 'Error: operación=pi, index='+index+', expr -> '+expr.to_s
      return false
    end
    return false
  end

  def num_op_num(index,expr)
    begin
      op = expr[index]
      return false if (index==0 || index>expr.size-2 || expr.size<3)
      if (numero?(expr[index-1]) && numero?(expr[index+1]))
        n1 = expr[index-1].to_f
        n2 = expr[index+1].to_f
        case op
          when '*'
            valor = n1 * n2
          when '/'
            valor = n1 / n2
          when '+'
            valor = n1 + n2
          when '-'
            valor = n1 - n2
          else
            valor = 0
        end
        expr[index-1..index+1] = valor.to_s
        @traza << n1.to_s+op+n2.to_s+' => '+valor.to_s+" , expr -> "+expr.join()
        return true
      end
    rescue
      @traza << 'Error: operación='+op+', index='+index+', expr='+expr.to_s
      return false
    end
    return false
  end

  def parentesis(index,expr)
    begin
      return false if (index>expr.size-3 || expr.size<3)
      if (expr[index-1]=='(' && expr[index+1]==')')
        @traza << expr[index-1..index+1].to_s+' => '+expr[index]
        expr[index-1..index+1] = expr[index]
        return true
      end
    rescue
      @traza << 'Error: operación=paréntesis, index='+index+', expr='+expr.to_s
      return false
    end
    return false
  end

end
