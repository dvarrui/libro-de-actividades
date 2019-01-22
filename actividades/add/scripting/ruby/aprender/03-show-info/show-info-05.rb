#!/usr/bin/env ruby
#
# Programación Secuencial
# * `` o %x()
# * puts
# * Usar variables para guardar datos
# * Usar método chmop para quitar "\n"
# * Más información en :https://ruby-doc.org/core-2.6/String.html
#
# Objetivo:
# * Mostrar información del sistema en pantalla
# * Pero además de forma bonita (más humana)
# * Código más limpio y ordenado

# Recopilo información...
mi_nombre  = `whoami`.chomp       # ¿Quién soy?
mi_sitio   = `pwd`.chomp          # ¿Dónde estoy?
la_fecha   = %x(date).chomp       # ¿Qué días es?
el_arbol   = %x(which tree).chomp # ¿Dónde encuentro árboles?

# Muestro la información
puts("* Me llamo   <#{mi_nombre}>")
puts "* Estoy en   <#{mi_sitio}>"
puts('* Fecha/hora <' + la_fecha + '>')
puts '* Comando    <' + el_arbol + '>'
