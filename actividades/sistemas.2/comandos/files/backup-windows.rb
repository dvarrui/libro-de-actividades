
require 'fileutils'



# Crea el directorio backup si no existe

FileUtils.mkdir('c:\backup') unless File.exist?('c:\backup')


# Lee los nombres de los archivos que buscamos

nombres = Dir.glob(File.join('c:', '**', '*.exe')
) + 
          Dir.glob(File.join('c:', '**', '*.txt'))
 + 
          Dir.glob(File.join('c:', '**', '*.rb'))



# Para cada nombre de archivo hacemos lo siguiente:


nombres.each do |i|
  
  # Nombre del fichero destino
  
  dest = File.join('C:','backup', File.basename(i))
  
  # Copia source en dest
  
  FileUtils.cp(i, dest)      
  print '.'                  
  # Muestra un punto en pantalla

end



puts "Fin del script."
