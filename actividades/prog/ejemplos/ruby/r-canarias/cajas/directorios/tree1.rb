#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
#

class Tree

  def initialize(p=".",n=0)
    @directorio=p
    @nivel=0
  end

  def mostrar
    d = Dir.new @directorio

    d.sort.each do |e|
      print(e,tipo_fichero(e),"\n") if (e[0]!=46)
    end
  end

  def tipo_fichero(nombre)
    ruta=@directorio+'/'+nombre
    return '(d)' if File.directory?(ruta)
    return '(x)' if File.executable?(ruta)
    return '(c)' if File.chardev?(ruta)
    return '(?)'
  end

  def tabulador
    
  end

end

t = Tree.new(ARGV[0]||'.')
t.mostrar

