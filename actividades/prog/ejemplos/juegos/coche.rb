#!/usr/bin/ruby

def pintar_cabecera
  system('clear')
  puts "Juego de carreras (z = izquierda, x = derecha, q = Salir)"
end

def inicializar
  @desp_world = 30
  @desp_carretera = []
  10.times { @desp_carretera << 0 }
  @desp_coche = 0
  @pos_coche = 2
  @pista_vacia = 'X     X'
  @pista_coche = ['XA    X', 'X A   X', 'X  A  X', 'X   A X', 'X    AX']
end

def pintar_carretera
  @desp_carretera.each do |i|
    puts ' ' * (i+@desp_world) + @pista_vacia
  end
end

def pintar_coche
  puts ' ' * (@desp_world + @desp_coche) + @pista_coche[@pos_coche]
end

def read_key
  system("stty raw -echo")
  char = STDIN.read_nonblock(1) rescue nil
  system("stty -raw echo")
  char
end

def play_game
  inicializar
  loop do
    pintar_cabecera
    pintar_carretera
    pintar_coche
    char = read_key
    break if /q/i =~ char
  end
  puts "Fin del juego!"
end

play_game
