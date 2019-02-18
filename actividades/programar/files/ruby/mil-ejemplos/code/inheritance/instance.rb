#---
# Excerpted from "Everyday Scripting in Ruby"
# We make no guarantees that this code is fit for any purpose. 
# Visit http://www.pragmaticprogrammer.com/titles/bmsft for more book information.
#---

class NoteTaker
  attr_reader :commentary
  
  def initialize(title)         # (1) 
    @commentary = [title]
  end

  def note(notation)      # (2)
    @commentary << "Note: #{notation}"    # (3) 
  end
end

class TimingNoteTaker < NoteTaker

  def timestamp                 # (4)
    note(boundary('-'))       # (5)
    @commentary << Time.now.to_s   # (6)
  end

  def boundary(character)      # (7)
    character * 20
  end
end

child = TimingNoteTaker.new("May 1")  # (8)
child.timestamp                       # (9)
child.note("coffee")                  # (10)
child.note("bagels")
child.timestamp
child.note('email')
puts child.commentary
