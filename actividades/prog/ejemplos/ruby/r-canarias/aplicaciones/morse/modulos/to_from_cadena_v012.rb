=begin
Fichero: modulos/to_from_cadena.rb
Autor: David Vargas <dvargas@canarias.org>
Versión: 0.1.2 20080112
Contenido: Contiene la definición del módulo ToFromCadena
Descripción: 
=end

module ToFromCadena
  SEP=":" #Caracter que se utiliza como separador de campo
  
  #<p>Hay que ejecutar este método al incluir el módulo, porque
  #inicializa las variables utilizadas.</p>
  def tofromcadena_setup(*args)
    @hash_tofromcadena= Hash.new
    campos=[]
    args.each do |i| 
      campos << i
    end
    @hash_tofromcadena[:campos]=campos
    @hash_tofromcadena[:modo]=:simple
    @hash_tofromcadena[:comentarios]=["#","/",";"]
  end
  
  def tofromcadena_modo(t)
    @hash_tofromcadena[:modo]=t
    @hash_tofromcadena[:modo]=:simple if (t==:default)
  end

  #<p>Devuelve un objeto <b>String</b> con todos los campos concatenados,
  # y usando como separador la constante <b>SEP</b>.</p>
  def to_cadena
    modo = @hash_tofromcadena[:modo]
    campos = @hash_tofromcadena[:campos]
    s=""
    s+=self.class.name if (modo==:clase)
    campos.each_index do |index|
      s += SEP if (s.length>0||index>0)
      atributo=campos[index].to_s
      s += (self.instance_variable_get("@"+atributo)).to_s
    end
    return s
  end
  
  def from_cadena!(cadena)
    #Modo :simple, cadena "david:vargas" -> campos "nombre:apellidos2
    #Modo :clase, cadena "Clase:david:vargas" -> campos "nombre:apellidos2
    campos=@hash_tofromcadena[:campos]
    modo=@hash_tofromcadena[:modo]
    valores=cadena.split(SEP)
    
    #Ajuste para el modo :clase
    if (modo==:clase)
      #Comprobamos que el primer valor del Array valores contiene
      #el nombre de la clase
      if (valores[0]!=self.class.name) 
        raise "Parámetro incorrecto en el nombre de la clase. "+valores[0]+"!="+self.class.name
      end
      #Quitamos el primer valor del Array de valores 
      valores = valores[1..valores.size] if (modo==:clase)
    end
    #Comprobar que el número de valores coincida con los campos
    if (campos.size!=valores.size)
      raise "No coinciden los valores("+valores.size.to_s+") pasados con los campos("+campos.size.to_s+") esperados."
    end
    
    campos.each_index do |index|
      atributo=campos[index].to_s
      valor=valores[index]
      self.instance_variable_set("@"+atributo, valor)
    end
  end
  
  #<p>Devuelve un nuevo objeto de la clase, creándolo a partir
  #del contenido del <b>String</b> <u>cadena</u>.</p>
  #TODO:Pasar el método de instancia a la clase
  def new_from_cadena(cadena)
    c = self.class
    o = c.new
    o.from_cadena!(cadena)
    return o
  end
  
  def tofromcadena_definicion
    campos=@hash_tofromcadena[:campos]
    s=""+self.class.name
    campos.each do |c|
      s = s + SEP if s.length >0
      s = s + c.to_s
    end
    return s
  end

  #<p>Devuelve un <b>Array</b> de objetos a partir de los datos
  #leidos desde el archivo con nombre <i>fichero</i>.</p>
  def get_array_desde_fichero(fichero)
    comentarios = @hash_tofromcadena[:comentarios]
    f=File.new(fichero).readlines
    salida=[]
    
    f.each do |item|
      item.strip!
      if  (item.size>0 && !comentarios.member?(item[0].to_s))
        #La línea item no está vacía y no contiene comentarios
        r = self.class.new.from_cadena!(item)
        salida << r         
      end
    end
  end
  
  private
  #Por ahora no hay nada privado
end
