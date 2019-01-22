#!/usr/bin/env ruby
#
# Objetivos
# * Crear directorios y establecer permisos
# * El método system() devuelve true/false
# * Hacer comprobaciones previas con if-then

if not File.exist? 'private'
  system('mkdir private')
  system('chmod 700 private')
end

if !File.exist?('group')
  system('mkdir group')
  system('chmod 750 group')
end

unless File.exist? 'public'
  system('mkdir public')
  system('chmod 755 public')
end
