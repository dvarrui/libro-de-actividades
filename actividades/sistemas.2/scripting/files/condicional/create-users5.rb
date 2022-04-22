#!/usr/bin/env ruby
# * Script para crear usuarios de forma masiva
# * SO Windows
# * Version 1 (usando Ruby each)

# PASO1: Leer el fichero con los nombres de los usuarios
nombres = File.read('nombres.txt').split

# PASO2: Para cada nombre de usuario hacer lo siguiente:
nombres.each do |i|
  puts "=> Leyendo el nombre de #{i}" # Mostrar mensaje en pantalla
  system("net user #{i} 123456 /add") # Añadir línea al fichero de salida
end
