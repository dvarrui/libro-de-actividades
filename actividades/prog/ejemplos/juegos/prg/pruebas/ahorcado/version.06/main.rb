#!/usr/bin/env ruby

#
#Variables globales
#
VERSION_JUEGO="06-20070410"
$DIR_PROGRAMA="ahorcado/version.06"
FILE_DICCIONARIO="ahorcado/diccionario.txt"

require $DIR_PROGRAMA + '/Ahorcado'

#
#Ejecución del programa
#
if $0 == __FILE__
  puts VERSION_JUEGO
   
  juego = Ahorcado.new
  juego.inicializar(FILE_DICCIONARIO)
  juego.jugar
end
