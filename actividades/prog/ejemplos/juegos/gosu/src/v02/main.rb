#!/usr/bin/ruby
require 'rubygems'
require 'gosu'

require 'game/game'

class GameWindow < Gosu::Window

  def initialize
    super(800, 600, false)
    self.caption = "IES Puerto de la Cruz"

    Game::init(self)
  end

  def update
    #Este debe ser el main loop
    Game::update
  end

  def draw
    Game::render
  end

  def button_down(id)
    if id == Gosu::Button::KbEscape
      game_over
    end
  end
  
  def game_over
    close
  end
end

window = GameWindow.new
window.show
