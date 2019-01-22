#!/usr/bin/env ruby
#
# Objetivos
# * Crear directorios y establecer permisos
# * File.exist? (https://ruby-doc.org/core-2.6/File.html)
# * Hacer comprobaciones previas con if-then

if File.exist? 'private'
  puts "[WARN] Directorio private ya existe!"
else
  system('mkdir private')
  system('chmod 700 private')
end

if File.exist?('group')
  puts "[WARN] Directorio group ya existe!"
else
  system('mkdir group')
  system('chmod 750 group')
end

if File.exist? 'public'
  puts "[WARN] Directorio public ya existe!"
else
  system('mkdir public')
  system('chmod 755 public')
end
