#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070830
#

require 'apps/expresion'


e = Expresion.new ARGV.join(" ")
if ARGV.size==0
  e = Expresion.new "2 * ( 3 + 4 )"
end

print "\nResultado:\n ", e.expr_inicio, " = ", e.evaluar, "\n"

print "\nVer la traza:\n"
e.traza.each {|l| print " ",l,"\n"}
puts
