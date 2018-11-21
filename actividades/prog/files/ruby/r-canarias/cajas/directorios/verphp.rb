#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
#

class VerFuncionesPHP

  def initialize(p=".",n=0)
    @directorio=p
    @nivel=0 #para recursividad futura
  end

  def mostrar
    d = Dir.new @directorio

    d.sort.each do |f|
      #Se procesa el contenido del fichero si es un .php
      if (f[-4..f.size]==".php")
        num_metodos = revisar_contenido f
        print "   (Puede ser un fichero con variables globales)\n" if num_metodos==0
        print "\n"
      end
    end
  end

  def revisar_contenido(nombre)
    puts " - Fichero : #{nombre}"
    lista = []
    ruta=@directorio+'/'+nombre
    File.new(ruta).each do |s|
      if s.include? "function"
        s.strip!
        lista << s[s.index("function")+8, s.size-9]
      end
    end

    lista.sort.each {|s| print "   * ",s,"\n"}
    return lista.size 
  end

end

t = VerFuncionesPHP.new(ARGV[0]||'/home/david/web/david/rec/php')
t.mostrar

