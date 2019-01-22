#!/usr/bin/env ruby

# Objetivo:
# * Mostrar información del sistema en pantalla
# * Más bonito con colores
# * Resaltar las partes diferentes
# * Código más limpio, ordenado y reutilizable

require 'rainbow'

def show_info
  puts "\nMOSTRANDO INFORMACION DEL SISTEMA\n\n"

  # Recopilo información...
  mi_nombre  = Rainbow(`whoami`.chomp).inverse
  mi_sitio   = Rainbow(`pwd`.chomp).underline
  la_fecha   = Rainbow(%x(date).chomp).blink
  el_arbol   = Rainbow(%x(which tree).chomp).underline

  # Muestro la información
  puts "* Me llamo   : #{mi_nombre}"
  puts "* Estoy en   : #{mi_sitio}"
  puts "* Fecha/hora : #{la_fecha}"
  puts "* Comando    : #{el_arbol}"
end

def show_title
  puts "="*40
  puts "Ejecutando el programa #{Rainbow($0).yellow.bright}"
  puts "="*40
end
