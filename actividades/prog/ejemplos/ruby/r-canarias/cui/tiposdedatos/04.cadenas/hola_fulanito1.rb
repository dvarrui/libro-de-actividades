#!/usr/bin/ruby
print " Introduce nombre y apellidos : "
nombre_completo = gets
nombre_completo.strip!
print "Hola "
nombre_completo.each(" ") { |palabra| print palabra.capitalize , " " }
print "\n"


