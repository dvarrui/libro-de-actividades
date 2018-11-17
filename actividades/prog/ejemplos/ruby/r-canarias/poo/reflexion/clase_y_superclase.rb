# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

puts "Script ruby #{$0}"

class Class
  def jerarquia
    resultado = (superclass ? superclass.jerarquia : [])
    resultado << "-"
    resultado << self
  end
end

class MiArray < Array
  
end

definiciones = [Object,Module,Class,Method,String,Array,MiArray]
definiciones.each do |item| 
  print item," < ",item.superclass.to_s," [Tipo ", item.class.to_s,"]\n"
  print " * ",item.jerarquia.to_s,"\n"
  print " * ",item.ancestors.to_s,"\n"
end

modulos = [Kernel,Enumerable,Comparable]
modulos.each do |item| 
  print item," < No tiene superclase [Tipo ",item.class.to_s,"]\n"
  print " * No tiene jerarquía\n"
  print " * ",item.ancestors.to_s,"\n"
end
