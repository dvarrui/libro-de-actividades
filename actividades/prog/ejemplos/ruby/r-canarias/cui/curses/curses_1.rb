#!/usr/bin/ruby -w
# curses_1.rb

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
  str = ARGV[0] || 'David Vargas Ruiz'
  max_x = scr.maxx-str.size+1
  max_y = scr.maxy
  100.times do
    scr.setpos(rand(max_y), rand(max_x))
    scr.addstr(str)
  end
  scr.getch
end


