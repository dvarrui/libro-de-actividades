#!/usr/bin/env ruby
#
# Versión     : 20070324
# Autor       : David Vargas Ruiz
# Descripción :

def ejecutar
  if ARGV[0]=='-h'
    opcion_mostrar_ayuda
  elsif ARGV[0]=='-r'
    opcion_revisar
  elsif ARGV[0]=='-c'
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
  puts "Uso: #{nombre_script} [-c][-h][-r]"
  puts "\t-c , crear un album web con las imágenes"
  puts "\t-h , mostrar esta ayuda"
  puts "\t-r , revisar las imágenes"
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
    img << f if es_imagen?(f)
  end
  img.sort!
end

def convertir(imagenes,confirmado)
  i=0
  `echo > album.cnt` if confirmado
  imagenes.each do |f|
    nombre=i.to_s.rjust(2,'0')
    puts "convert -resize 400x300 #{f} imagen_#{nombre}.jpg"
      `convert -resize 400x300 #{f} imagen_#{nombre}.jpg` if confirmado
    i+=1
  end
end

def es_imagen?(fichero)
  return true if fichero.downcase.include? '.jpg'
  false
end

if $0==__FILE__
  ejecutar
end
