
require 'clases/eleccion'

class Personaje
  attr_reader :nombre, :salud, :desplazamiento, :acciones
  attr_accessor :x, :y
  
  def initialize(jugador, nombre)
    @jugador = jugador
    @nombre = nombre
    @salud = 10
    @desplazamiento = 2
    @acciones = []
  end
  
  def herir(cantidad)
    return if muerto?
    @salud -= cantidad
    morir if muerto?
  end
  
  def muerto?
    return @salud <= 0
  end
  
  def vivo?
    return ! muerto?
  end
  
  def morir
    #TODO: pendiente del método game y message_all
    @jugador.game.message_all("#{@nombre} está muerto.")
  end
  
  def enemigo?(otro)
    #TODO: hay que definir el método jugador
    #FIXME:De momento no tenemos método jugador sino atributo
    (otro != nil) && (jugador != otro.jugador)
  end
  
  def amigo?(otro)
    #TODO: hay que definir el método jugador
    (otro != nil) && (jugador == otro.jugador)
  end
  
  #<p>Método done?.</p>
  def terminado?; @terminado; end
  def terminar; @terminado=true; end
  def nuevo_turno; @terminado=false; end
  
  def mover(x, y)
    @jugador.game.mapa.mover(@x,@y,x,y)
    @x = x
    @y = y
  end
  
  def rep
    [self.class.nombre_corto, nombre]
    #[self.class.name, nombre]
  end
  
  #<p>Método move_choices.</p>
  def posibles_movimientos
    mapa = @jugador.game.mapa
    all = mapa.all_posiciones
    cercanos = all.find_all {|x,y| mapa.esta_cerca?(@desplazamiento, @x, @y, x, y)}
    #Son válidos los movimientos a posiciones cercanas y no ocupadas por otros personajes
    validos = cercanos.find_all {|x,y| mapa.personajes[x,y].nil? }
    return validos.collect do |x,y|
      Eleccion.new("Mover",x,y) {self.mover(x, y)}
    end
  end
  
  #<p>Método action_choices.</p>
  def posibles_acciones
    return @acciones.collect do |accion|
      Eleccion.new(*accion.rep) { accion }
    end
  end
end

class Class
  #<p>Si por ejemplo el nombre de la clase es Personajes::Dinosaurio
  #devuelve sólo Dinosaurio.</p>
  def nombre_corto
    name().gsub(/^.*:/,'')
  end
end

