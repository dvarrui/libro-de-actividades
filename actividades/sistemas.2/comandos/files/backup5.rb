#!/usr/bin/env ruby

require 'fileutils'

# Crea el directorio backup si no existe
FileUtils.mkdir('backup') unless File.exist? 'backup'
# Lee los nombres de los archivos que buscamos
nombres = Dir.glob("/home/profesor/**/*.{jpg,jpeg,png}")

# Para cada nombre de archivo hacemos lo siguiente:
nombres.each do |i|
  source = i                      # Nombre del fichero origen
  dest = File.join('.','backup', File.basename(i)) # Nombre del fichero destino
  FileUtils.cp(source, dest)      # Copia source en dest
  print '.'                       # Muestra un punto en pantalla
end

puts "\nFin del script."
