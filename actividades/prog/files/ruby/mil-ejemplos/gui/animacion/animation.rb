# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

class Animation
  attr_reader :frame, :width, :height, :objects
  
  def initialize(width, height)
    @width = width
    @height = @height
    @objects = []
    @frame = 0
    @step_callbacks = []
    @at_callbacks == Hash.new { |hash, key| hash[key] = [] }
  end
  
  def add(obj)
    @objects.push(obj)
  end
end
