#!/usr/bin/env ruby

require 'fileutils'

# ESTRUCTURA CONDICIONAL
if not File.exist?('backup')
  # Crea el directorio backup si no existe
  FileUtils.mkdir('backup')
end

# Lee los nombres de los archivos que buscamos
nombres = Dir.glob("/home/profesor/**/*.{jpg,jpeg,png}")

# ESTRUCTURA ITERATIVA
for nombre in nombres do
  # Para cada nombre de archivo hacemos lo siguiente:
  # Nombre del fichero destino
  dest = File.join('.','backup', File.basename(nombre))
  FileUtils.cp(nombre, dest)   # Copia source en dest
  print '.'                    # Muestra un punto en pantalla
end

puts "\nFin del script."
