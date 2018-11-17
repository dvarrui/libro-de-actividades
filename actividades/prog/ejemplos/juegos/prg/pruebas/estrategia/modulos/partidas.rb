require 'clases/terreno'
require 'clases/mapa'
require 'clases/personaje'
require 'clases/otros_personajes'
 

module Partidas
  class FalsoGame
    attr_accessor :mapa
  end

  class FalsoJugador
    attr_accessor :game
  end
    
  def self.iniciar_partida1
      
    puts "Iniciando la partida1..."

    #Definiendo Tipos de terrenos
    bosque = Terreno.new("Bosque")
    hierba = Terreno.new("Hierba")
    montana = Terreno.new("Montana")
    tierra = Terreno.new("Tierra")
    agua = Terreno.new("Agua")

    #Creando un mapa concreto (sistema 1)
=begin
mapa = Mapa.new(20,20)
mapa.terreno[0,0] = bosque
mapa.terreno[1,0] = bosque
mapa.terreno[2,0] = tierra
mapa.terreno[3,0] = bosque
=end

    #Creando un mapa concreto (sistema 2)
    terreno_clave = {
      "b" => bosque,
      "h" => hierba,
      "m" => montana,
      "t" => tierra,
      "a" => agua
    }

    mapa = Mapa.new terreno_clave, <<-END
      hhhhhhhhhh
      hhhhhhhaaa
      hhhhhhaabb
      hhhttttttt
      hhtthhabtb
      hhthhhaabb
    END

    #puts mapa.terreno[0,0].nombre
    #puts mapa.terreno.rep

    jugador = FalsoJugador.new
    jugador.game = FalsoGame.new
    jugador.game.mapa = mapa
    dixie = Personaje.new(jugador,"Dixie")
    jugador.game.mapa.ubicar(dixie, 0, 0)
    dixie.mover(1, 0)

    trex = TRex.new(jugador,"Johan")
    puts trex.rep


  end
end
