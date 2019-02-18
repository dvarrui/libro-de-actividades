
module AntGame
  class Ant
    attr_accessor :x, :y, :direction, :next_action
    def initialize(x, y)
      @x = x; @y = y
      @direction = :north
      @next_action = Actions::WAIT
    end
  end
  module Actions
    WAIT = :wait
    TURN_LEFT = :turn_left
    TURN_RIGHT = :turn_right
    GO = :go
  end
end
AntGame::Ant.new(4, 5)
include AntGame
Ant.new(1, 2)
