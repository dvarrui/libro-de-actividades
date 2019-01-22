# Objetivos
# * Crear directorios y establecer permisos
# * Código limpio y ordenado

require 'rainbow'

def show_title
  puts "Ejecutando el programa #{Rainbow($0).bg(:blue)}..."
end

def create_dirs
  if File.exist? 'private'
    puts "[WARN] Directorio private ya existe!"
  else
    system('mkdir private')
    system('chmod 755 private')
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
end
