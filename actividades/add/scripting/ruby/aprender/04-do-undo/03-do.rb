#!/usr/bin/env ruby
#
# Objetivos
# * Crear directorios y establecer permisos
# * Clase#método => File.exist?
# * Hacer comprobaciones previas con if-then

if not File.exist? 'private'
  puts "[INFO] Creando directorio private..."
  system('mkdir private')
  system('chmod 755 private')
end

if !File.exist?('group')
  puts "[INFO] Creando directorio group..."
  system('mkdir group')
  system('chmod 750 group')
end

unless File.exist? 'public'
  puts "[INFO] Creando directorio public..."
  system('mkdir public')
  system('chmod 755 public')
end
