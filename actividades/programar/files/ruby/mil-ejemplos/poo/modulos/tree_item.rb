
class TreeItem
  attr_accessor :left, :right, :item
  include Enumerable
  
  def initialize(item)
    self.item = item
  end
  
  def each(&block)
    #block.call(self.item)
    left.each(&block) if left
    block.call(self.item)
    right.each(&block) if right
  end
end   
                                     #» nil
p root = TreeItem.new("root")                #<TreeItem:0xb7e2fad8 @item="root">
p root.to_a.join(' | ')                      # "root"
p root.left = TreeItem.new("left")           #<TreeItem:0xb7e28224 @item="left">
p root.to_a.join(' | ')                      #» "root | left"
p root.right = TreeItem.new("right")         #<TreeItem:0xb7e20448 @item="right">
p root.to_a.join(' | ')                      #» "root | left | right"
p root.left.left = TreeItem.new("left-left") #<TreeItem:0xb7e1965c @item="left-left">
p root.to_a.join(' | ')                      #» "root | left | left-left | right"
