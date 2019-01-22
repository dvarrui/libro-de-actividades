#!/usr/bin/env ruby
#
# Programación Secuencial
# * `` o %x()
# * puts
# * Usar variables para guardar datos
#
# Objetivo:
# * Mostrar información del sistema en pantalla
# * Pero además de forma bonita (más humana)


# Recopilo información...
mi_nombre  = `whoami`       # ¿Quién soy?
mi_sitio   = `pwd`          # ¿Dónde estoy?
la_fecha   = %x(date)       # ¿Qué día es?
el_arbol   = %x(which tree) # ¿Dónde encuentro árboles?

# Muestro la información
puts("¡Hola! Me llamo <#{mi_nombre}>")
puts "Y resulta que me encuentro en <#{mi_sitio}>"

puts('Miro el reloj y es <' + la_fecha + '>')
puts 'Creo que hay un árbol en ' + el_arbol + '>'

# Problemilla: Olvidamos el "\n" del final (chomp)
#
# % irb                                                                              19-01-19 - 13:32:45
# irb(main):001:0> a = `whoami`
# => "david\n"
# irb(main):002:0> quit
# %
