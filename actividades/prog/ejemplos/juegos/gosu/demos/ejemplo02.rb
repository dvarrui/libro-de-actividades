require 'rubygems'
require 'gosu'

class GameWindow < Gosu::Window
  def initialize
    super(640, 480, false)
    self.caption = "Gosu Tutorial Game"
    
    @background_image = Gosu::Image.new(self, "examples/media/Space.png", true)
  end

  def update
  end

  def draw
    @background_image.draw(0, 0, 0);
  end
end

window = GameWindow.new
window.show
