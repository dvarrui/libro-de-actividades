#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070827
# 

class Operador
  attr_reader :traza, :operadores

  def initialize
    @metodos = { '(' => 'parentesis', ')' => 'parentesis', 'sen' => 'op_num', 'abs' => 'op_num', '*' => 'num_op_num', '/' => 'num_op_num', '-' => 'num_op_num', '+' => 'num_op_num'}
    @operadores = @metodos.keys
    @traza = []
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
 
  def parentesis?(p)
    return true if (p=='(' || p==')')
    false
  end
 
  def expr2str(expr)
    s = []
    expr.each {|e| s << e.valor }
    return s.join(" ")
  end

  def calcular(index,expresion,traza)
    @traza = traza
    if parentesis?(expresion[index].valor)
      salida = parentesis(index,expresion)
    else
      salida = send @metodos[expresion[index].valor], index, expresion
    end
    return salida
  end

  def constante(index,expr)
    begin
      if expr[index].valor=='pi'
        pi = 3.141516
        @traza << 'pi => '+pi.to_s
        expr[index] = pi.to_s
        return true
      end
    rescue
      @traza << 'Error: operación=pi, index='+index.to_s+', expr -> '+expr.to_s
      return false
    end
    return false
  end

  def num_op_num(index,expr)
    begin
      op = expr[index].valor
      return false if (index==0 || index>expr.size-2 || expr.size<3)
      if (numero?(expr[index-1].valor) && numero?(expr[index+1].valor))
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
        n = expr[index].nivel
        expr[index-1..index+1] = Item.new(valor.to_s, n)
        @traza << n1.to_s+op+n2.to_s+' = '+valor.to_s+" , expr -> "+expr2str(expr)
        return true
      end
    rescue
      @traza << 'Error: operación='+op+', index='+index.to_s+', expr='+expr2str(expr)
      return false
    end
    return false
  end

  def op_num(index,expr)
    begin
      op = expr[index].valor
      return false if (index>expr.size-2 || expr.size<2)
      if (numero?(expr[index+1].valor))
        num = expr[index+1].to_f
        case op
          when 'sen'
            valor = Math.sin num
          when 'cos'
            valor = Math.cos num
          when 'tan+'
            valor = Math.tan num
          when 'abs'
            valor = num.abs
          else
            valor = 0
        end
        n = expr[index].nivel
        expr[index..index+1] = Item.new(valor.to_s, n)
        @traza << op+num.to_s+' = '+valor.to_s+" , expr -> "+expr2str(expr)
        return true
      end
    rescue
      @traza << 'Error: operación='+op+', index='+index.to_s+', expr='+expr2str(expr)
      return false
    end
    return false
  end

  def parentesis(index,expr)
    begin
      return false if (index>expr.size-3 || expr.size<3)
      if (expr[index].valor=='(' && expr[index+2].valor==')')
        expr[index+1].nivel -= 1
        expr[index..index+2] = expr[index+1]
        @traza << '('+expr[index].valor+') = '+expr[index].valor+', expr -> '+expr2str(expr)
        return true
      end
    rescue 
      @traza << 'Error: operación=paréntesis, index='+index.to_s+', expr='+expr2str(expr)
      return false
    end
    return false
  end

end
