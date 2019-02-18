#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 0.2.0 20080108
# 

class Polinomio

  def initialize(p_grado=2)
    @coeficientes = [0]
    p_grado.times { @coeficientes << 0 }
  end

  def grado
    return (@coeficientes.size-1)
  end

  #<p>Devuelve el valor del polinomio para x=argumento.</p>
  def fdex(x)
    f = 0
    @coeficientes.each_index do |i|
      f = f + @coeficientes[i] * elevar(x,i)
    end
    return f
  end

  def set_coeficiente(posicion,valor)
    @coeficientes[posicion]=valor
  end

  def set_coeficientes(valores)
    @coeficientes=[]
    s = valores.strip.split(",")
    s.each {|i| @coeficientes << i.to_i}
  end

  def get_coeficiente(posicion)
    return 0 if posicion>grado
    return @coeficientes[posicion]
  end

  def to_s
    s = ""
    @coeficientes.size.times do |i|
      exponente=i
      t=""
      if (@coeficientes[exponente]!=0 || exponente==0)
        t = "+" if @coeficientes[exponente]>0
        
        if exponente==0
          t = t + @coeficientes[exponente].to_s
        elsif exponente==1
          t = t + @coeficientes[exponente].to_s if (@coeficientes[exponente]!=1)
          t = t + "x"
        elsif exponente >1
          t = t + @coeficientes[exponente].to_s if (@coeficientes[exponente]!=1)
          t = t + "x^"+ exponente.to_s
        end
      end
      t = t + " " if (exponente>0 && @coeficientes[exponente]!=0)
      s =t + s
    end
    return s.strip
  end

  def get_coeficientes
    return @coeficientes.join(",")
  end

  def es_igual?(p)
    return true if p.to_s==self.to_s
    return false
  end

  def sumar(p)
    while (self.grado<p.grado) 
      @coeficientes << 0
    end
    @coeficientes.size.times do |i|
      @coeficientes[i]=@coeficientes[i]+p.get_coeficiente(i)
    end
  end

  def multiplicar(n)
    @coeficientes.each_index do |i|
      @coeficientes[i]=@coeficientes[i]*n
    end
  end

  private

  def elevar(x,exponente)
    r = 1
    exponente.times {|e| r=r*x }
    return r
  end

end
