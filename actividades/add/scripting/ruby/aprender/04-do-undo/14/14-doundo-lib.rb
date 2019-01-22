# Objetivos
# * Crear directorios y establecer permisos
# * Código limpio y ordenado

require 'rainbow'

def show_title
  puts "Ejecutando el programa #{Rainbow($0).bg(:blue)}..."
end

def read_data_from(filename)
  content = `cat #{filename}`
  lines = content.split("\n")
  data = []
  lines.each do |line|
    items = line.split(':') # Dividir cada línea para obtener los campos
    data << { :name => items[0], :mode => items[1] }
  end
  return data
end

def create_dirs(dirlist)
  dirlist.each do |dirdata|
    dirname = dirdata[:name]
    dirmode = dirdata[:mode]
    if File.exist? dirname
      puts "[W] Directorio #{Rainbow(dirname).bright} ya existe!"
    else
      system "mkdir #{dirname}"
      system "chmod #{dirmode} #{dirname}"
    end
  end
end

def delete_dirs(dirlist)
  dirlist.each do |dirdata|
    dirname = dirdata[:name]
    if File.exist? dirname
      system "rmdir #{dirname}"
    else
      puts "[W] Directorio #{Rainbow(dirname).bright} ya borrado!"
    end
  end
end

def show_dir_status(dirlist)
  dirlist.each do |dirdata|
    dirname = dirdata[:name]
    if File.exist? dirname
      puts "* Directorio #{Rainbow(dirname).green.bright} existe!"
    else
      puts "* Directorio #{Rainbow(dirname).red.bright} borrado!"
    end
  end
end
