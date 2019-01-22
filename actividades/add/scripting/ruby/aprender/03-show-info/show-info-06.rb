#!/usr/bin/env ruby
#
# Programación Secuencial
# * `` o %x()
# * puts
# * Usar variables para guardar datos
# * Usar método chmop para quitar "\n"
# * Más información en :https://ruby-doc.org/core-2.6/String.html
# * Usar gemas (bibliotecas Rubygems)
# * Rainbow gem(https://rubygems.org/gems/rainbow): gem install rainbow
#
# Objetivo:
# * Mostrar información del sistema en pantalla
# * Código limpio y ordenado
# * Más bonito con colores
# * Resaltar las partes diferentes

require 'rainbow'

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
