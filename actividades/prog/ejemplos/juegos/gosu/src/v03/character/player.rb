
require 'game/game'
require 'graphic/graphic'

class Player
  attr_accessor :floor, :x, :y, :score
  
  def initialize
    @window = Game::window
    @image = {}
    @image[:stop] = []
    @image[:stop][1] = Gosu::Image.new(@window, "data/img/jugador-01.png", false)
    @image[:right] = []
    @image[:right][1] = Gosu::Image.new(@window, "data/img/jugador-der-01.png", false)
    @image[:right][2] = Gosu::Image.new(@window, "data/img/jugador-der-02.png", false)
    @image[:right][3] = Gosu::Image.new(@window, "data/img/jugador-der-03.png", false)
    @image[:right][4] = Gosu::Image.new(@window, "data/img/jugador-der-04.png", false)
    @image[:left] = []
    @image[:left][1] = Gosu::Image.new(@window, "data/img/jugador-izq-01.png", false)
    @image[:left][2] = Gosu::Image.new(@window, "data/img/jugador-izq-02.png", false)
    @image[:left][3] = Gosu::Image.new(@window, "data/img/jugador-izq-03.png", false)
    @image[:left][4] = Gosu::Image.new(@window, "data/img/jugador-izq-04.png", false)
    @beep = Gosu::Sample.new(@window, "data/snd/beep.wav")
    @explosion = Gosu::Sample.new(@window, "data/snd/explosion.wav")

    @x = @y = @vel_y = @acel_y= 0.0
    @vel_x = 4.5
    @state = :floor
    @state_look_at = :right
    @floor = 0
    @score = 0
    @actual_frame = 1
    @next_frame = 0
  end

  def position(x,y)
    @x, @y = x, y
  end
  
  def left
    @x -= @vel_x
    @x %= 800
    @state_look_at = :left
    inc_frame
  end
  
  def right
    @x += @vel_x
    @x %= 800
    @state_look_at = :right
    inc_frame
  end
  
  def inc_frame
    @next_frame +=1
    if @next_frame>6 then
		@next_frame=0
		@actual_frame += 1
		if @actual_frame>4 then
			@actual_frame=1
		end
	end
  end
  
  
  def up
	if @state==:floor then
		@state=:jumping_up
		@vel_y = -8
		@acel_y = 0.92
		@beep.play
		@score +=10
	end
  end
  
  def update
    if @window.button_down? Gosu::Button::KbLeft or @window.button_down? Gosu::Button::GpLeft then
      left
    end
    if @window.button_down? Gosu::Button::KbRight or @window.button_down? Gosu::Button::GpRight then
      right
    end
    if @window.button_down? Gosu::Button::KbUp or @window.button_down? Gosu::Button::GpButton0 then
      up
    end

    if @state==:jumping_up then
      @y = @y + @vel_y
      @vel_y *= @acel_y
      if (@vel_y.abs<0.8) then
	@state=:jumping_down
	@vel_y=+4
	@acel_y=+1.05
      end
    elsif @state==:jumping_down then
      @y += @vel_y
      @vel_y *= @acel_y
      if (@y>=@floor) then
	@y=@floor
	@state=:floor
	@explosion.play
      end
    end
  end

  def render
    @image[@state_look_at][@actual_frame].draw(@x, @y, ZOrder::PLAYER)
  end
end
