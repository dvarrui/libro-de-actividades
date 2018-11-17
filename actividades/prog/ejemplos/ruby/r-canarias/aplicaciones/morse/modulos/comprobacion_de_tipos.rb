=begin
Fichero: comprobacion_de_tipos.rb
Autor: David Vargas <dvargas@canarias.org>
Versión: 0.1.0 20081214
Descripción: Módulo para comprobar la validez de los tipos de variables
=end 

module ComprobacionDeTipos
  def definir_reglas(inputs={}.freeze)
    @definiciones_de_usuario ||={}
    inputs.each do |tipo, regla|
      @definiciones_de_usuario[tipo] = regla if (regla.respond_to? :call)
    end
  end
    
  def aplicar_reglas_a(metodo, *tipos)
    @reglas ||={}
    @reglas[metodo] = tipos
    method_added(metodo)
  end
    
  def method_added(metodo)
    #Cuando se añade el método se activan sus reglas de tipos
    inputs = @reglas[metodo]
    activar_reglas_sobre(metodo,inputs) if inputs
  end
   

  def activar_reglas_sobre(metodo, tipos)
    @reglas[metodo]=nil
    metodo_old = "__#{metodo}".intern
    condiciones=""
    tipos.flatten.each_with_index do |item,index|
      condiciones << %{
          if not self.class.get_regla_del_tipo(#{item.inspect}).call(args[#{index}])
            raise ViolacionDeTipos, "argumento #{index+1} del método '#{metodo}'"+
              " debe cumplir la regla '#{item}'",caller
          end
      }
    end
      
    class_eval %{
        alias_method #{metodo_old.inspect}, #{metodo.inspect}
        def #{metodo}(*args)
          #{condiciones}
          return #{metodo_old}(*args)
        end
    }
  end

  def get_regla_del_tipo(tipo)
    if @definiciones_de_usuario and @definiciones_de_usuario[tipo]
      @definiciones_de_usuario[tipo]
    else
      case tipo
      when :numero
        lambda { |x| x.is_a? Numeric }
      when :string
        lambda { |x| x.respond_to? :to_str }
      when :anything
        lambda { |x| true }
      else
        lambda { |x| false }
      end
    end
  end
  
  class ViolacionDeTipos < StandardError
      
  end

end
