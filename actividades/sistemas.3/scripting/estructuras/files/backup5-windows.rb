# Ejecutar este script como:
#  runas /user:administrador "ruby backup-windows.rb"
#
# Descripción:
#  Buscar todos los archivos png, jpg y jpeg y copiarlos en c:\backup

require 'fileutils'

# Crea el directorio backup si no existe
FileUtils.mkdir('c:\backup') unless File.exist?('c:\backup')

# Lee los nombres de los archivos que buscamos
nombres = Dir.glob(File.join('c:', '**', '*.png'))
nombres << Dir.glob(File.join('c:', '**', '*.jpg'))
nombres << Dir.glob(File.join('c:', '**', '*.jpeg'))

# Para cada nombre de archivo hacemos lo siguiente:
nombres.each do |i|
  dest = File.join('C:', 'backup', File.basename(i)) # Nombre del fichero destino
  FileUtils.cp(i, dest)    # Copia source en dest
  print '.'                # Muestra un punto en pantalla
end

puts "Fin del script!"
