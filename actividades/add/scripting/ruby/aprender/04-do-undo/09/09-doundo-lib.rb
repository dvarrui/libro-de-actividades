# Objetivos
# * Crear directorios y establecer permisos
# * Código limpio y ordenado

require 'rainbow'

def show_title
  puts "Ejecutando el programa #{Rainbow($0).bg(:blue)}..."
end

def create_dirs
  dirnames = ['private', 'group', 'public']

  dirnames.each do |dirname|
    if File.exist? dirname
      puts "[W] Directorio #{Rainbow(dirname).bright} ya existe!"
    else
      system "mkdir #{dirname}"
      system "chmod 750 #{dirname}"
    end
  end
end

def delete_dirs
  dirnames = ['private', 'group', 'public']

  dirnames.each do |dirname|
    if File.exist? dirname
      system "rmdir #{dirname}"
    else
      puts "[W] Directorio #{Rainbow(dirname).bright} ya borrado!"
    end
  end
end
