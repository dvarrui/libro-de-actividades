# Objetivos
# * Crear directorios y establecer permisos
# * Código limpio y ordenado

require 'rainbow'

def show_title
  puts "Ejecutando el programa #{Rainbow($0).bg(:blue)}..."
end

def create_dirs
  if File.exist? 'private'
    puts "[W] Directorio private ya existe!"
  else
    system('mkdir private')
    system('chmod 755 private')
  end

  if File.exist?('group')
    puts "[W] Directorio group ya existe!"
  else
    system('mkdir group')
    system('chmod 750 group')
  end

  if File.exist? 'public'
    puts "[W] Directorio public ya existe!"
  else
    system('mkdir public')
    system('chmod 755 public')
  end
end

def delete_dirs
  if File.exist? 'private'
    system('rmdir private')
  else
    puts "[W] Directorio private ya borrado!"
  end

  if File.exist?('group')
    system('rmdir group')
  else
    puts "[W] Directorio group ya borrado!"
  end

  if File.exist? 'public'
    system('rmdir public')
  else
    puts "[W] Directorio public ya borrado!"
  end
end
