#!/usr/bin/ruby 

class Extra
   #Esto crea los getter/setter
   attr_accessor :content
   
   def test
      #Método de instancia
      puts "Este objeto tiene el contenido:"+@content.to_s
   end

   def add_method(name)
      #Metaprogramación: crea un nuevo método sobre la marcha
      self.class.send :define_method, name.to_sym do
         puts "Soy el método "+name.to_s
      end
   end
end

e = Extra.new

e.content="RetroGamers"
e.test

e.add_method "saludar"
e.saludar

