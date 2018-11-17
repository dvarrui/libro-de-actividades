#
#Definiciones de funciones
#
def mostrar_titulo(titulo,ancho=50,letra="*")
  ancho.times do print letra end
  puts
  puts titulo.center(ancho)
  ancho.times do print letra end
  puts
end

def leer_diccionario(fichero)
  palabras = []
  f = File.new(fichero)
  f.each_line do |linea|
    palabras << linea unless linea.rstrip.rindex('#')==0
  end
  return palabras
end

def elegir_palabra_al_azar(palabras)
   i = rand(palabras.length)
   palabras[i].split(':')
end
