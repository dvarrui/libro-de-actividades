#!/usr/bin/env ruby

#
#Variables globales
#
VERSION_JUEGO="05-20070408"
$DIR_PROGRAMA="ahorcado/version.05"
FILE_DICCIONARIO="ahorcado/diccionario.txt"

require $DIR_PROGRAMA + '/Ahorcado'

#
#Ejecución del programa
#
if $0 == __FILE__
  puts VERSION_JUEGO
   
  juego = Ahorcado.new
  juego.inicializar(FILE_DICCIONARIO)
  juego.presentacion

  juego.jugar
end
