#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070802
#

require 'expresion'

e = Expresion.new ARGV[0]

print "\nResultado:\n ", e.expr_inicio, " = ", e.evaluar, "\n"

print "\nVer la traza:\n"
e.traza.each {|l| print " ",l,"\n"}
puts
