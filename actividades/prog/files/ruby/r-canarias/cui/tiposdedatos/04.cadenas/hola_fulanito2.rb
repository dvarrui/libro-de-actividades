#!/usr/bin/ruby

print "Introduce nombre y apellidos:"
nombre_completo = gets.strip

nombres = nombre_completo.split
nombres.each { |nombre| nombre.capitalize!}

nombre_completo = nombres.join(" ")

puts "Hola #{nombre_completo}!"

