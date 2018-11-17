#!/usr/bin/ruby -w
# ncurses_1.rb

require 'ncurses'

module Ncurses
  COLORS= [COLOR_BLACK, COLOR_RED, COLOR_GREEN, COLOR_YELLOW, COLOR_BLUE, COLOR_MAGENTA, COLOR_CYAN, COLOR_WHITE]

  def self.program
    stdscr = Ncurses.initscr
    
    at_exit do
      echo
      nocbreak
      curs_set(1)
      stdscr.keypad(0)
      endwin
    end

    noecho
    cbreak
    curs_set(0)
    stdscr.keypad(1)
    start_color

    COLORS[1...COLORS_size].each_with_index do |color,i|
      init_pair(i+1, color, COLOR_BLACK)
    end

    yield stdscr
  end
end

Ncurses.program do |scr|
  str = ARGV[0] || 'David Vargas Ruiz'
  max_y , max_x = [], []
  scr.getmaxyx(max_y, max_x)
  max_y = max_y[0]
  max_x = max_x[0]-str.size+1
  100.times do
    scr.mvaddstr(rand(max_y), rand(max_x), str)
  end
  scr.getch
end

