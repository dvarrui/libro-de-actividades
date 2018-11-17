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
#Version 2
#Creamos variables globales, constantes y métodos/funciones

#
#Variables globales
#
VERSION_JUEGO="2"
palabras=[]

#
#Definiciones de funciones
#
def mostrar_titulo(titulo)
  40.times do print "*" end
  puts
  puts titulo.center(40)
  40.times do print "*" end
  puts
end

def leer_diccionario
  palabras = []
  f = File.new("ahorcado/diccionario.txt","r")
  f.each_line do |linea|
    palabras << linea unless linea.rstrip.rindex('#')==0
  end
  return palabras
end

def elegir_palabra_al_azar(palabras)
   i = rand(palabras.length)
   palabras[i].split(':')
end

#
#Ejecución del programa
#
mostrar_titulo("Juego del ahorcado Versión "<<VERSION_JUEGO)
palabras = leer_diccionario
palabra_secreta,ayuda_secreta = elegir_palabra_al_azar(palabras)


puts palabra_secreta
puts ayuda_secreta

