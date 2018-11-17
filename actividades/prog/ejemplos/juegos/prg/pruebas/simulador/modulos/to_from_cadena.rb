=begin
Fichero: modulos/to_from_cadena.rb
Autor: David Vargas <dvargas@canarias.org>
Versión: 0.1.6 20080124
Contenido: Contiene la definición del módulo ToFromCadena
Descripción: 
Forma de uso:
  require 'modulos/to_from_cadena'
  
  class Simbolo
    include ToFromCadena
    attr_reader :simbolo, :codigo
  
    def initialize
      tofromcadena_campos :simbolo, :codigo
      tofromcadena_modo :simple
    end
 end

=end

module ToFromCadena
  SEP=":" #Caracter que se utiliza como separador de campo
  
  #<p>Hay que ejecutar este método al incluir el módulo, porque
  #inicializa las variables utilizadas.</p>
  def tofromcadena_campos(*args)
    @hash_tofromcadena= Hash.new
    @hash_tofromcadena[:modo]=:simple
    @hash_tofromcadena[:comentarios]=["#","//",";","/*"]
    campos = []
    tipos = {}

    #Se pasan como argumentos todos los campos necesarios
    #y se asume que por defecto son de tipo string
    #Para definir otros tipos usar em método tofromcadena_tipos
    args.each do |item| 
      campos << item
      tipos[item] = :to_s
    end

    @hash_tofromcadena[:campos] = campos
    @hash_tofromcadena[:tipos] = tipos
  end
  
  def tofromcadena_tipos(*args)
    campos = @hash_tofromcadena[:campos]
    tipos = {}
    
    campos.each_index do |index|
      tipos[campos[index]] = args[index]
    end
    @hash_tofromcadena[:tipos] = tipos
  end
  
  def tofromcadena_modo(t)
    @hash_tofromcadena[:modo]=t
    @hash_tofromcadena[:modo]=:simple if (t==:default)
  end

  #<p>Devuelve un <b>String</b> con el formato especificado
  #para dicho objeto según el módulo <i>ToFromCadena</i>.</p>
  def tofromcadena_definicion
    campos=@hash_tofromcadena[:campos]
    tipos=@hash_tofromcadena[:tipos]
    s=""+self.class.name
    campos.each do |c|
      s = s + SEP if s.length >0
      s = s + c.to_s + "("+tipos[c].to_s+")"
    end
    return s
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
    tipos=@hash_tofromcadena[:tipos]
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
      valor=(valores[index]).send(tipos[atributo.to_sym].to_s)
      self.instance_variable_set("@"+atributo, valor)
    end
    
  end
  
  #<p>Devuelve un nuevo objeto de la clase, creándolo a partir
  #del contenido del <b>String</b> <u>cadena</u>.</p>
  #TODO:Pasar el método de instancia a la clase
  def self.new_from_cadena(cadena)
    c = self.class
    o = c.new
    o.from_cadena!(cadena)
    return o
  end
  
  #<p>Devuelve un <b>Array</b> de objetos a partir de los datos
  #leidos desde el archivo con nombre <i>fichero</i>.</p>
  def get_array(fichero)
    comentarios = @hash_tofromcadena[:comentarios]
    f=File.new(fichero).readlines
    salida=[]
    
    f.each do |item|
      item.strip!
      if  (item.size>0)
        #La línea item no está vacía 
        es_comentario=false
        comentarios.each do |c|
          es_comentario=true if (item[0,c.length]==c)
        end
        if !es_comentario 
          #La línea no contiene comentarios
          r = self.class.new
          r.from_cadena!(item)
          salida << r         
        end
      end
    end
    return salida
  end
  
  private
  #Por ahora no hay nada privado
end
