#!/usr/bin/env ruby
#
#Fichero     : ahorcado.rb
#Fecha       : 20070404
#Autor       : David Vargas Ruiz
#Descripción : Juego del ahorcado
#
#Versión 1
#Tenemos un diccionario y leemos todo su contenido y lo cargamos en un array.
#A continuación elegimos uno al azar.
#

40.times do print "*" end
puts
puts "Juego del ahorcado Versión 1".center(40)
40.times do print "*" end
puts


palabras=[]

f = File.new("ahorcado/diccionario.txt","r")
f.each_line do |linea|
  palabras << linea unless linea.rstrip.rindex('#')==0
end

puts "\n",palabras
print "\nNúmero de palabras   = ",palabras.length,"\n"
i = rand(palabras.length)
print "Palabra aleatoria[",i,"] = ",palabras[i],"\n"

