#!/usr/bin/env ruby
#
# Versión     : 20070501
#               20070813, incorpora el modo2 y tiene forma de clase
# Autor       : David Vargas Ruiz <dvargas@canarias.org>
# Descripción : Convierte un grupo de imágenes al tamaño adecuado para
#               publicar en la web. Además gener un fichero php para
#               su manipulación.
#
# Es necesario instalar el paquete <imagemagick> para poder
# disponer del comando <convert>
# 

class AlbumWeb
  attr_accessor :modo
  
  def initialize
    @modo = :tipo1
  end
  
  def ejecutar
    if ARGV[0]=='-h'
      opcion_mostrar_ayuda
    elsif ARGV[0]=='-r1'
      opcion_revisar
    elsif ARGV[0]=='-c1'
      @modo = :tipo1
      opcion_crear_album
    elsif ARGV[0]=='-c2'
      @modo= :tipo2
      opcion_crear_album
    else
      puts "Uso: #{nombre_script} -h"
      puts
    end
  end

  def nombre_script
    nombres = $0.split('/')
    nombres[nombres.length-1]
  end

  def opcion_mostrar_ayuda
    puts "Uso: #{nombre_script} [-c1][-c2][-h][-r]"
    puts "\t-c1, crear un album web con las imágenes renombradas."
    puts "\t-c2, crear un album web con las imágenes sin renombrar."
    puts "\t-h , mostrar esta ayuda"
    puts "\t-r1, revisar las imágenes"
    puts
  end

  def opcion_revisar
    directorio = ARGV[1]||'.'
    imagenes = leer directorio
    convertir imagenes,false
  end

  def opcion_crear_album
    directorio = ARGV[1]||'.'
    imagenes = leer directorio
    convertir imagenes,true
  end

  def leer(directorio)
    img=[]
  
    Dir.new(directorio).each do |f| 
      img << f if imagen?(f)
    end
    img.sort!
  end

  def imagen?(fichero)
    return true if fichero.downcase.include? '.jpg'
    false
  end

  def convertir(imagenes, confirmado)
    convertir_modo1(imagenes, confirmado) if modo == :tipo1
    convertir_modo2(imagenes, confirmado) if modo == :tipo2
  end
  
  def convertir_modo1(imagenes,confirmado)
    i=0
    if confirmado
      `echo > album.cnt`
      `echo '<h1><? echo $descripcion; ?></h1>' >> album.cnt`
      `echo '<hr>' >> album.cnt`
      `echo '<?' >> album.cnt`
      `echo '  //Generador por <album-web.rb> versión 20070502' >> album.cnt `
      `echo '  //Autor: David Vargas <dvargas@canarias.org>' >> album.cnt`
      `echo '  $dir="poner-el-directorio/";' >> album.cnt`
    end

    imagenes.each do |f|
      nombre=i.to_s.rjust(2,'0')
      puts "Convirtiendo #{f} => imagen_#{nombre}.jpg ..."
      if confirmado
        `convert -resize 400x300 #{f} imagen_#{nombre}.jpg`
        `echo '  \$imagenes[#{nombre.to_i}]=\$dir."imagen_#{nombre}.jpg";' >> album.cnt`
      end
      i+=1
    end

    if confirmado
      `echo "?>" >> album.cnt`
      `echo "" >> album.cnt`
      `echo "<?" >> album.cnt`
      `echo '  include("rec/php/imagenes.php");' >> album.cnt`
      `echo '  echoAlbumDeFotos(\$imagenes);' >> album.cnt`
      `echo "?>" >> album.cnt`
    end
  end

  def convertir_modo2(imagenes,confirmado)
    i=0
    if confirmado
      `echo > album.cnt`
      `echo '<h1><? echo $descripcion; ?></h1>' >> album.cnt`
      `echo '<hr>' >> album.cnt`
      `echo '<?' >> album.cnt`
      `echo '  //Generador por <album-web.rb> versión 20070813' >> album.cnt `
      `echo '  //Autor: David Vargas <dvargas@canarias.org>' >> album.cnt`
      `echo '  $dir_fotos="directorio/fotos/";' >> album.cnt`
      `echo '  $dir_diapositivas="directorio/diapositivas/";' >> album.cnt`
    end

    imagenes.each do |f|
      f2='d_'+f
      puts "Convirtiendo #{f} => #{f2} ..."
      if confirmado
        `convert -resize 400x300 #{f} #{f2}`
        `echo '  \$imagenes[#{i}]="#{f}";' >> album.cnt`
      end
      i+=1
    end

    if confirmado
      `echo "?>" >> album.cnt`
      `echo "" >> album.cnt`
      `echo "<?" >> album.cnt`
      `echo '  include("rec/php/imagenes.php");' >> album.cnt`
      `echo '  echoAlbumDeFotos2(\$imagenes);' >> album.cnt`
      `echo "?>" >> album.cnt`
    end
  end
end

##################
if $0==__FILE__
  a = AlbumWeb.new
  a.ejecutar
end
