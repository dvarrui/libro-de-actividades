# -*- coding: utf-8 -*-
begin
  # En caso de que usemos Gosu via rubygems
  require 'rubygems'
rescue LoadError
  # En caso de que no...
end
require 'gosu'


# Esto lo utilizamos para darle un orden a los elementos gráficos del
# juego durante el dibujado en pantalla
module ZOrder
  # El tablero se dibuja primero, después las piezas, y por último la
  # interfaz de usuario (el texto, por ejemplo)
  Board, Pieces, UI = *0..2
end


# Constantes para configurar nuestro juego
module GameConstants
  # Textos
  module Text
    Caption = "Me-tris"
    Score = "Puntos"
    GameOver = "Juego finalizado"
    NextPiece = "Pieza siguiente"
    Info = "Presiona <Enter> para reiniciar o <Esc> para terminar"
  end
  
  
  # Gráficos
  module Graphics
    # Para los bloques
    Tile = "media/square.png"
    # Para el pivote
    Pivot = "media/circle.png"
  end

  #Sonidos
  module Sounds
    # Para desaparecer un renglón
    CompletedRow = "media/dialog-information.ogg"
    # Para cuando se deja caer una pieza
    StorePiece = "media/dialog-warning.ogg"
    # Para cuando termina el juego
    GameOver = "media/dialog-error.ogg"
    # Para cuando inicia el juego
    GameStart = "media/desktop-logout.ogg"
    # Para cuando no se pueda rotar o mover la pieza
    InvalidMove = "media/button-pressed.ogg"
  end
  

  # Configuración del juego
  # Jugar en pantalla completa?
  FullScreen = false
  # Ancho de la pantalla en pixeles
  ScreenWidth = 800
  # Alto de la pantalla en pixeles
  ScreenHeight = 600
  # Ancho del tablero (en bloques)
  BoardWidth = 12
  # Alto del tablero (en bloques)
  BoardHeight = 17
  # Longitud de los lados de un bloque (en pixeles)
  BlockSize = 25.0
  # Separación entre bloques (en pixeles)
  BlockSeparation = 5.0
  # Longitud de los lados de la imagen (en pixeles)
  ImageSize = 30 + BlockSeparation
  # Factor para ajustar tamaño del texto y la imagen de los bloques en
  # caso de que el tamaño de GameConstants::BlockSize no corresponda
  # con el de GameConstants::ImageSize
  Factor = BlockSize/ImageSize

  # Jugabilidad
  # Bajar la pieza cada X número de milisegundos
  DescendTime = 1000
  # Bajar la pieza rápido cada X número de milisegundos
  BoostedDescendTime = 25
  # Repetir el movimiento con la tecla presionada cada X número de
  # milisegundos
  RepeatTime = 200
end


# Clase para crear las diferentes figuras que puede tener una pieza en
# el juego
class ShapeFactory
  def self.shapes
    # Aquí definimos las figuras que queremos que aparezcan en el
    # juego, los 0s representan espacios en blanco, los 1s representan
    # bloques sólidos y el 2 es para indicar el pivote (opcional)
    [
     [
      [1,1],
      [1,1]
     ],
     [
      [1,1,2,1,1]
     ],
     [
      [1,1,1,2],
      [0,0,0,1]
     ],
     [
      [0,2,1],
      [1,1,0]
     ],
     [
      [0,1,0],
      [1,2,1]
     ],
     [
      [0,1,0],
      [1,1,1],
      [0,1,0]
     ],
     [
      [2,1,1],
      [1,1,0],
      [1,0,0]
     ],
     [
      [1,1,2],
      [0,1,1]
     ]
    ]
  end

  # Método para obtener una figura al azar
  def self.shape
    shapes[rand(shapes.size)]
  end
end


# Esta clase nos servirá para dar seguimiento al estado de una pieza
# (figura y posición)
class PieceState
  # Deseamos tener acceso a la posición (x,y) y figura (shape)
  attr_accessor :x, :y, :shape
  
  def initialize
    # Seleccionamos una pieza al azar de nuestro repertorio
    @shape = ShapeFactory.shape
    # Le metemos variedad a las piezas obteniendo la matriz
    # transpuesta con 50% de probabilidad de que quede como estaba
    # originalmente y 50% de que quede en espejo
    @shape = @shape.transpose if(rand(2) == 0)
  end
  
  # Método para obtener la anchura
  def w
    @shape.first.size
  end
  
  # Método para obtener la altura
  def h
    @shape.size
  end
end


# Esta clase representa una pieza en el tablero
class Piece
  # Necesitamos tener acceso al color de la pieza para que el tablero
  # pueda almacenar los bloques con el color correspondiente
  attr_reader :color
  
  def initialize(window, board)
    # Obtenemos una referencia a la ventana del juego uso interno
    @window = window
    # Obtenemos una referencia al tablero para uso interno
    @board = board

    # Creamos un color para los bloques sólidos de las piezas
    @color = Gosu::Color.new(0xff000000)
    @color.red = rand(255-20) + 20
    @color.green = rand(255-20) + 20
    @color.blue = rand(255-20) + 20
    
    # Tomamos el color anterior y creamos un uno semitransparente para
    # los representar los bloques vacíos de las piezas
    @colorb = Gosu::Color.new(@color.argb)
    @colorb.alpha = 0x66
    
    # Tendremos dos estados, el actual (válido) y uno tentativo (puede
    # quedar en estado inválido) que nos servirá para identificar si
    # un movimiento es válido, por ejemplo:
    # ¿Después de rotar tenemos una figura válida? ¿no colisiona con
    # nada? ¿no se sale de la pantalla?
    @current_state = PieceState.new
    
    # Al tener una pieza nueva, la centramos en el tablero y la
    # ponemos por encima de él (además de crear el estado tentativo)
    move_to_center
  end
  
  # Coloca la pieza al lado del tablero
  def move_to_side
    @current_state.x = @board.w + 2
    @current_state.y = 2
    @next_state = @current_state.dup
  end
  
  # Poner la pieza lista para comenzar a descender
  def move_to_center
    @current_state.x = (@board.w - @current_state.w)/2
    @current_state.y = -@current_state.h
    @next_state = @current_state.dup
  end
  
  # Método que nos genera un arreglo vacío con las dimensiones
  # del estado actual transpuestas, nos sirve a la hora de tratar de
  # hacer la rotación de una pieza
  def empty_rotated_shape
    Array.new(@current_state.w).map!{Array.new(@current_state.h, 0)}
  end
  
  # Posición en x (en bloques)
  def x
    @current_state.x
  end
  
  # Posición en y (en bloques)
  def y
    @current_state.y
  end
  
  # Altura (en bloques)
  def h
    @current_state.h
  end
  
  # Anchura (en bloques)
  def w
    @current_state.w
  end
  
  # Figura de la pieza
  def shape
    @current_state.shape
  end

  # Si al hacer una rotación o mover la pieza, queda en una posición
  # válida dentro del tablero, entonces llamamos a este método para
  # que la pieza realmente tome esa posición
  def to_next_state
    @current_state = @next_state.clone
  end
  
  # Si la pieza quedá en una posición no válida dentro del tablero,
  # entonces restauramos su estado
  def restore_state
    @next_state = @current_state.clone
    # Si este método es llamado es porque se trató de hacer algún
    # movimiento no válido, tocamos ese sonido
    @window.sounds[:invalid_move].play
  end

  # Método para operar con el estado siguiente
  def with_next_state
    yield @next_state
  end
  
  # Método para operar con ambos estados
  def with_states
    yield @current_state, @next_state
  end

  # Método para operar con cada una de las celdas de nuestra pieza
  def for_every_cell
    @current_state.shape.each_with_index do |row, i|
      row.each_with_index do |cell, j|
        yield(cell, i, j)
      end
    end
  end
  
  # Método que dibuja nuestra pieza en pantalla
  def draw
    # Recorremos cada celda de nuestra pieza y la ponemos en pantalla
    for_every_cell do |c,i,j|
      
      # Calculamos la posición de la pieza en la pantalla
      pos_x = @board.offset_x + (j+@current_state.x) * @board.b_size
      pos_y = @board.offset_y + (i+@current_state.y) * @board.b_size
      
      # El color depende de si es sólido o vacío
      @window.graphics[:square].
        draw(pos_x,
             pos_y,
             ZOrder::Pieces,
             GameConstants::Factor,
             GameConstants::Factor,
             c==0 ? @colorb : @color,
             :default)
      
      # Remarcamos el pivote (si existe)
      if c==2
        @window.graphics[:circle].
          draw(pos_x,
               pos_y,
               ZOrder::Pieces,
               GameConstants::Factor,
               GameConstants::Factor,
               @colorb,
               :additive)
      end
    end
  end
  
end

# Esta clase representa el tablero de juego
class Board
  # Necesitamos acceso a los atributos: altura, anchura, tamaño del
  # bloque, desplazamiento en x, desplazamiento en y, gráficas, si
  # está lleno el tablero
  attr_reader :h, :w, :b_size, :offset_x, :offset_y, :graphics, :full

  def initialize(window)
    # Obtenemos una referencia a ventana para uso interno
    @window = window
    
    # La altura del tablero (en bloques)
    @h = GameConstants::BoardHeight
    # La anchura del tablero (en bloques)
    @w = GameConstants::BoardWidth
    # El ancho/alto de los bloques (en pixeles)
    @b_size = GameConstants::BlockSize
    
    # Centramos el tablero en la ventana
    @offset_x = (window.w - self.pixel_w)/2
    @offset_y = (window.h - self.pixel_h)/2
    
    # Color para los espacios vacíos en el tablero
    @color = Gosu::Color.new(0xff222222)
    
    # Ponemos el tablero en su estado inicial
    reset
  end
  
  # Para inicializar el tablero
  def reset
    # La pieza que actualmente está descendiendo
    @current_piece = Piece.new(@window, self)
    
    # La pieza que sigue
    @next_piece = Piece.new(@window, self)
    
    # Movemos la pieza siguiente al lado del tablero para que la pueda
    # ver el jugador
    @next_piece.move_to_side
    
    # Generamos un arreglo de arreglos para representar nuestro
    # tablero
    @board = Array.new(@h).map!{Array.new(@w, 0)}
    # Lo mismo para representar el color de los bloques en el tablero
    @board_colors = Array.new(@h).map!{Array.new(@w,0)}
    
    # Nos servirá para saber si el juego ha terminado
    @full = false
    
    # Ponemos la puntuación del jugador en cero
    @score = 0

    # Tocamos un sonido al iniciar el juego
    @window.sounds[:game_start].play
  end
  
  
  # Sirve para revisar si el estado tentativo es válido y si el
  # tablero lo puede "tomar"
  def can_take_it?
    # Vamos a trabajar con el estado siguiente para validarlo
    @current_piece.with_next_state do |n|
      n.shape.each_with_index do |row, i|
        row.each_with_index do |cell, j|
          # Nos aseguramos de trabajar con los renglones de la pieza
          # que están dentro del tablero
          if n.y + i >= 0
            # Checamos si hay colisión
            if @board[i+n.y][j+n.x] != 0 && n.shape[i][j] != 0
              # Si hayamos una colisión regresamos falso para avisar
              # que el tablero no puede "tomar" la piezao
              return false
            end
          end
        end
      end
    end
    
    # Si no hay colisión entonces devolvemos verdadero, con esto
    # informamos que el tablero si puede "tomar" la pieza
    true
  end
  
  # Almacena los bloques de la pieza que ha llegado al fondo del
  # tablero
  def store_piece
    @current_piece.shape.each_with_index do |row, i|
      row.each_with_index do |cell, j|
        # Solo copia los bloques llenos (diferentes a cero)
        @board[i+@current_piece.y][j+@current_piece.x] = cell if cell != 0
        # Copiamos los colores de la pieza al tablero de colores
        @board_colors[i+@current_piece.y][j+@current_piece.x] = @current_piece.color if cell != 0
      end
    end
    # Tocamos un sonido cuando una pieza baja
    @window.sounds[:store_piece].play
  end
  
  # Método que elimina los renglones llenos
  def compact_board
    # Necesitamos saber que renglones vamos a eliminar
    to_delete = []
    
    # Vamos a revisar renglón por renglón
    @board.each_with_index do |row,i|
      # Sólo si el renglón NO incluye algún cero...
      unless row.include?(0)
        # Registramos si este renglón necesita ser eliminado
        to_delete << {:row => row, :index => i}
      end
    end
    
    to_delete.each do |r|
      # Eliminamos el renglón del tablero
      @board.delete(r[:row])
      # Y eliminamos el renglón en el tablero de colores (para que
      # esté sincronizado con nuestro tablero de bloques)
      @board_colors.delete_at(r[:index])
      # Insertamos un nuevo renglón lleno de ceros (vacío) al inicio
      # de cada tablero (el de bloques y el de colores)
      @board.unshift(Array.new(@w,0))
      @board_colors.unshift(Array.new(@w,0))
    end

    # Aumentamos la puntuación del jugador
    @score += (10 * to_delete.size)

    # Y tocamos un sonido si se eliminaron algunos renglones
    @window.sounds[:completed_row].play if to_delete.size > 0
  end
    
  # Anchura del tablero en pixeles
  def pixel_w
    @w * @b_size
  end
  
  # Altura del tablero en pixeles
  def pixel_h
    @h * @b_size
  end

  # Intenta de mover la pieza actual a la izquierda
  def move_piece_left
    # Con el estado tentativo
    @current_piece.with_next_state do |s|
      # Lo movemos a la izquierda
      s.x -= 1
      # Checamos si es válida su posición y si no colisiona con nada
      if s.x >= 0 && can_take_it?
        # Si es así, actualizamos el estado
        @current_piece.to_next_state
      else
        # Si no, restauramos el estado
        @current_piece.restore_state
      end
    end
  end

  # Trata de mover la pieza actual a la derecha
  def move_piece_right
    # Con el estado tentativo
    @current_piece.with_next_state do |s|
      # Lo movemos a la derecha
      s.x += 1
      # Checamos si es válida su posición y si no colisiona con nada
      if s.x + s.w <= self.w && can_take_it?
        # Si es así, actualizamos el estado
        @current_piece.to_next_state
      else
        # Si no, restauramos el estado
        @current_piece.restore_state
      end
    end
  end

  # Trata de mover la pieza actual hacia abajo
  def move_piece_down
    # Con el estado tentativo
    @current_piece.with_next_state do |s|
      # Lo movemos hacia abajo
      s.y += 1
      # Checamos si es válida su posición y si no colisiona con nada
      if s.y + s.h <= self.h && can_take_it?
        # Si es así, actualizamos el estado
        @current_piece.to_next_state
      # Si la pieza está completamente dentro del tablero...
      elsif s.y > 0
        # Guardamos la pieza
        store_piece
        # Revisamos si hay renglones llenos y los quitamos
        compact_board
        # Ponemos en juego la siguiente pieza
        @current_piece = @next_piece
        # Y la ponemos lista para entrar al tablero
        @current_piece.move_to_center
        # Generamos la pieza que vendrá a continuación
        @next_piece = Piece.new(@window, self)
        # La ponemos al lado del tablero
        @next_piece.move_to_side
      # Si no está completamente dentro, significa que el tablero está
      # lleno
      else
        # Avisamos que el tablero está lleno
        @full = true
        # Y tocamos un sonido para avisar que el juego terminó
        @window.sounds[:game_over].play
      end
    end
  end
  
  # Trata de rotar la pieza a la izquierda
  def rotate_piece_left
    # Necesitaremos esto para mantener el pivote en su lugar cuando
    # hagamos la rotación de la pieza
    adjust_by = [0,0]
    # Necesitamos los datos de ambos estados
    # c = current_state, n = next_state
    @current_piece.with_states do |c, n|
      n.shape = @current_piece.empty_rotated_shape
      c.shape.each_with_index do |row, i|
        row.each_with_index do |cell, j|
          # Vemos si hay que realizar algún ajuste por el pivote
          adjust_by = [j-i, i-(row.size-j-1)] if c.shape[i][j] == 2
          # Copiamos la pieza rotada
          n.shape[row.size-j-1][i] = c.shape[i][j]
        end
      end

      # Hacemos los ajustes pertinentes
      n.x += adjust_by[0]
      n.y += adjust_by[1]

      # Checamos que esté dentro del tablero y que no colisione con
      # nada al rotar
      if n.x >= 0 &&
          n.x + n.shape.first.size <= self.w &&
          n.y + n.shape.size <= self.h &&
          can_take_it?
        # Si es así, la rotamos
        @current_piece.to_next_state
      else
        # Si no, la dejamos como estaba originalmente
        @current_piece.restore_state
      end
    end
  end
  
  # Trata de rotar la pieza a la derecha
  def rotate_piece_right
    # Necesitaremos esto para mantener el pivote en su lugar cuando
    # hagamos la rotación de la pieza
    adjust_by = [0,0]
    # Necesitamos los datos de ambos estados
    # c = current_state, n = next_state
    @current_piece.with_states do |c, n|
      n.shape = @current_piece.empty_rotated_shape
      c.shape.each_with_index do |row, i|
        row.each_with_index do |cell, j|
          # Vemos si hay que realizar algún ajuste por el pivote
          adjust_by = [j-(c.shape.size-i-1), i-j] if c.shape[i][j] == 2
          # Copiamos la pieza rotada
          n.shape[j][c.shape.size-i-1] = c.shape[i][j]
        end
      end

      # Hacemos los ajustes pertinentes
      n.x += adjust_by[0]
      n.y += adjust_by[1]

      # Checamos que esté dentro del tablero y que no colisione con
      # nada al rotar
      if n.x >= 0 &&
          n.x + n.shape.first.size <= self.w &&
          n.y + n.shape.size <= self.h &&
          can_take_it?
        # Si es así, la rotamos
        @current_piece.to_next_state
      else
        # Si no, la dejamos como estaba originalmente
        @current_piece.restore_state
      end
    end
  end  
  
  # Método para facilitarnos el iterar a través de todas las celdas
  # que contiene el tablero
  def for_every_cell
    @board.each_with_index do |row,i|
      row.each_with_index do |cell,j|
        yield cell, i, j
      end
    end
  end
  
  # Para dibujar el marcador en pantalla
  def draw_score
    # Dibujamos la palabra "Puntos" en la pantalla
    @window.font.
      draw(GameConstants::Text::Score,
           offset_x - @window.font.text_width(GameConstants::Text::Score, GameConstants::Factor*1.5) - @b_size*2,
           offset_y,
           ZOrder::UI,
           GameConstants::Factor*1.5,
           GameConstants::Factor*1.5,
           0xffffffff)

    # Dibujamos los puntos que lleva el jugador
    @window.font.
      draw(@score,
           offset_x - @window.font.text_width(@score, GameConstants::Factor) - @b_size*2,
           offset_y + @b_size *2,
           ZOrder::UI,
           GameConstants::Factor*1.0,
           GameConstants::Factor*1.0,
           0xffcc3300)
  end
  
  # Para dibujar las piezas en pantalla (la actual y la siguiente)
  def draw_pieces
    # Dibujamos la pieza en juego
    @current_piece.draw
    # Dibujamos la pieza que sigue
    @next_piece.draw
    # Dibujamos las palabras "Siguiente pieza" en la pantalla
    @window.font.
      draw(GameConstants::Text::NextPiece,
           offset_x + w * @b_size + @b_size*2,
           offset_y,
           ZOrder::UI,
           GameConstants::Factor*0.9,
           GameConstants::Factor*0.9,
           0xffffffff)
  end
  
  # Para dibujar el tablero en la pantalla
  def draw_board
    # Dibujamos el tablero iterando cada celda que lo compone
    for_every_cell do |c, i, j|
      @window.graphics[:square].
        draw(@offset_x + j*@b_size,
             @offset_y + i*@b_size,
             ZOrder::Board,
             GameConstants::Factor,
             GameConstants::Factor,
             @board[i][j] == 0 ? @color : @board_colors[i][j],
             :default)
    end
  end
  
  # Dibujamos el tablero, el marcador y las piezas en pantalla
  def draw
    draw_score
    draw_pieces
    draw_board
  end
end


# Esta es la clase que controla la aplicación, aquí se checa la
# entrada de teclado y se lleva el registro de los tiempos para hacer
# que las piezas desciendan
class Metris < Gosu::Window
  # Ancho y alto de la pantalla, fuente a usar para el texto, gráficos
  # y sonidos
  attr_reader :w, :h, :font, :graphics, :sounds

  def initialize
    @w = GameConstants::ScreenWidth
    @h = GameConstants::ScreenHeight
    
    super(@w, @h, GameConstants::FullScreen)
    self.caption = GameConstants::Text::Caption
    
    # Esta variable nos servirá para llevar un control de los gráficos
    # que tenemos en el juego
    @graphics = Hash.new
    # La imagen que usamos para representar los bloques
    @graphics[:square] = Gosu::Image.new(self, GameConstants::Graphics::Tile, true)
    # Y la que usamos para el pivote en las piezas
    @graphics[:circle] = Gosu::Image.new(self, GameConstants::Graphics::Pivot, true)
    
    # Variable para tener fácil acceso a los sonidos
    @sounds = Hash.new
    # Sonido a tocar cuando un renglón este lleno
    @sounds[:completed_row] = Gosu::Sample.new(self, GameConstants::Sounds::CompletedRow)
    # Sonido a tocar cuando el juego termine
    @sounds[:game_over] = Gosu::Sample.new(self, GameConstants::Sounds::GameOver)
    # Sonido a tocar cuando una pieza haya tocado fondo
    @sounds[:store_piece] = Gosu::Sample.new(self, GameConstants::Sounds::StorePiece)
    # Sonido a tocar cuando comience el juego
    @sounds[:game_start] = Gosu::Sample.new(self, GameConstants::Sounds::GameStart)
    # Sonido a tocar cuando no se pueda rotar o mover la pieza a algún lugar
    @sounds[:invalid_move] = Gosu::Sample.new(self, GameConstants::Sounds::InvalidMove)
    
    # Creamos el tablero de juego
    @board = Board.new(self)
    # Creamos la fuente para poner texto en pantalla
    @font = Gosu::Font.new(self, Gosu::default_font_name, 30)
    
    # Inicializamos los contadores de tiempo y el tablero
    reset
  end
  
  # Para inicializar los tiempos y el tablero
  def reset
    @moving_time = @falling_time = Gosu::milliseconds
    @moving_delta = @falling_delta = 0
    @board.reset
  end
  
  
  # Este es el ciclo del juego (gameloop)
  def update
    # Si el tablero no está lleno, lo actualizamos
    update_board if(!@board.full)
  end
  
  # Para actualizar el tablero
  def update_board
    # Tomamos el tiempo actual
    @new_time = Gosu::milliseconds
    # La diferencia entre el tiempo actual y el tiempo de movimiento
    @moving_delta = @new_time - @moving_time
    # La diferencia entre el tiempo actual y el tiempo de caida
    @falling_delta = @new_time - @falling_time
    
    # Esto pregunta si ya pasaron X ms desde la última vez que la
    # pieza bajo un renglón
    if @falling_delta > GameConstants::BoostedDescendTime
      # Si es así, entonces checamos si está presionado el botón de
      # flecha abajo
      if button_down? Gosu::Button::KbDown
        # Hacemos que el tablero mueva la pieza un renglón abajo
        @board.move_piece_down
        # Reiniciamos la variable del tiempo de caída al tiempo más
        # reciente obtenido
        @falling_time = @new_time
      end
    end
    
    # Este código es el que hace que la pieza baje automáticamente
    # cada X número de milisegundos, funciona igual que el anterior
    if @falling_delta > GameConstants::DescendTime
      @board.move_piece_down
      @falling_time = @new_time
    end

    # Este código permite que el jugador mantenga pulsada una tecla y
    # la pieza se siga moviendo a los lados, o siga rotando
    if @moving_delta > GameConstants::RepeatTime
      if button_down? Gosu::Button::KbRight
        @board.move_piece_right
        @moving_time = @new_time
      end
      
      if button_down? Gosu::Button::KbLeft
        @board.move_piece_left
        @moving_time = @new_time
      end
      
      if button_down? Gosu::Button::KbUp
        @board.rotate_piece_right
        @moving_time = @new_time
      end
      
      if button_down? Gosu::Button::KbQ
        @board.rotate_piece_left
        @moving_time = @new_time
      end
      
      if button_down? Gosu::Button::KbW
        @board.rotate_piece_right
        @moving_time = @new_time
      end
    end
    
    
  end
  
  # Esto es para tener acción inmediata, si alguien presiona de manera
  # repetida las flechas hacia los lados se obtendrá un movimiento más
  # rápido que si la dejamos presionada
  def button_down(id)
    # Si se presiona la tecla Esc, salimos
    self.close if id == Gosu::Button::KbEscape
    # Si se presiona la tecla Enter, reiniciamos el juego
    reset if id == Gosu::Button::KbReturn
    
    # Si el tablero está lleno, no continuar
    return if @board.full

    # Movimientos y rotaciones
    if id == Gosu::Button::KbRight
      @board.move_piece_right
      @moving_time = @new_time
    end
    
    if id == Gosu::Button::KbLeft
      @board.move_piece_left
      @moving_time = @new_time
    end

    if id == Gosu::Button::KbQ
      @board.rotate_piece_left
      @moving_time = @new_time
    end

    if id == Gosu::Button::KbUp
      @board.rotate_piece_right
      @moving_time = @new_time
    end
    
    if id == Gosu::Button::KbW
      @board.rotate_piece_right
      @moving_time = @new_time
    end
  end
  
  # Dibujar el "game over"
  def draw_game_over
    @font.draw_rel(GameConstants::Text::GameOver,
                   @w/2,
                   40,
                   ZOrder::UI,
                   0.5,
                   0.5,
                   GameConstants::Factor*2.0,
                   GameConstants::Factor*2.0,
                   0xffff0000)
    @font.draw_rel(GameConstants::Text::Info,
                   @w/2,
                   @board.pixel_h + @board.offset_y + 10,
                   ZOrder::UI,
                   0.5,
                   0,
                   GameConstants::Factor*0.8,
                   GameConstants::Factor*0.8,
                   0xffffffff)
  end
  
  
  # Dibujar el tablero y el gameover de ser necesario
  def draw
    @board.draw
    
    # Dibujamos el game over si el tablero está lleno
    draw_game_over if @board.full
  end
end

# Creamos la aplicación y la mostramos
Metris.new.show
