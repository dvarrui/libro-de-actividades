#!/usr/bin/env ruby

require 'ahorcado/version.04/funciones'
#
#Variables globales
#
VERSION_JUEGO="4"
FILE_DICCIONARIO="ahorcado/diccionario.txt"

#
#Ejecución del programa
#
if $0 == __FILE__
  mostrar_titulo("Juego del ahorcado Versión "<<VERSION_JUEGO,40,"=")
  palabras = leer_diccionario(FILE_DICCIONARIO)
  palabra_secreta,ayuda_secreta = elegir_palabra_al_azar(palabras)

  puts palabra_secreta
  puts ayuda_secreta
end
