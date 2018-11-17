#require 'rubygems'
require 'gosu'

require 'character/player'
require 'graphic/graphic'

module Game

  def self.init(window)
    @window = window
    @background_image = Gosu::Image.new(@window, "data/img/fondo-01.png", true)
    @font = Gosu::Font.new(@window, Gosu::default_font_name, 20)

    @player = Player.new
    @player.floor=230
    @player.position(320, @player.floor)
    @finish = false
  end

  def self.update
    @player.update
  end

  def self.render
    @background_image.draw(0, 0, 0);
    @player.render
    @font.draw("Score: #{@player.score}", 10, 10, ZOrder::UI, 1.0, 1.0, 0xffffff00)
  end

  def self.finish?
    return @finish
  end
  
  def self.window
    @window
  end
end

