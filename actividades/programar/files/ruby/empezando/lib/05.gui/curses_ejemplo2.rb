#!/usr/bin/ruby
#

require 'curses'

module Curses
  def self.program
    main_screen = init_screen
    noecho
    cbreak
    curs_set(0)
    main_screen.keypad = true
    yield main_screen
  end
end

Curses.program do |scr|
  10.times do |i|
    scr.setpos(0,0)
    scr.addstr("Pulsa la tecla "+i.to_s+" de 10...")
    str = scr.getch.to_s
    max_x = scr.maxx-str.size+1
    max_y = scr.maxy
    10.times do
      scr.setpos(rand(max_y), rand(max_x))
      scr.addstr(str)
    end
  end
end


