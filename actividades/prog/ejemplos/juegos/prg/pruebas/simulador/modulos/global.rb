=begin
Fichero: modulos/definición.rb
Autor: David Vargas <dvargas@canarias.org>
Versión: 0.1.0 20080118
Descripción: Variables globales del proyecto
=end

module Global
  @definiciones = { 
    :RUTA_TTF => 'rec/ttf/',
    :RUTA_IMG => 'rec/img/',
    :ANCHO => 640,
    :ALTO => 480,
    :titulo => 'Simulador de pruebas...'
    }
  
  #<p>Devuelve el valor asociado a la <b>clave</b> introducida.</p>
  def self.get(clave)
    @definiciones[clave]
  end

end
