#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20071009
#

require_relative 'clases/polinomio'


#Crear un polinomio pasando los coeficientes como argumentos
p = Polinomio.new 
p.set_coeficientes ARGV.join(",")

print p.to_s,"\n"
(-2..2).each do |i|
  print "f(",i,")=", p.fdex(i), "\n"
end
