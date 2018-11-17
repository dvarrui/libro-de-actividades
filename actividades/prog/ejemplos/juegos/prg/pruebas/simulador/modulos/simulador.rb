
require 'modulos/graficos' 
require 'clases/nodo'

module Simulador
  
  def self.iniciar(n)
    iniciar_prueba if n==:prueba
  end
  
  def self.iniciar_prueba  
    Graficos.iniciar_sdl
    Graficos.cargar_imagenes "conf/imagenes.txt"
    
    cargar_configuracion
    
    Graficos.mostrar_todas_las_imagenes
    Graficos.pintar
    Graficos.esperar_y_salir
  end
  
  def self.cargar_configuracion
    @nodos = Nodo.new.get_array("conf/nodos.txt")
    @nodos.each do |item|
      item.imagen = Graficos.get_imagen item.cod_imagen
    end
  end
end
