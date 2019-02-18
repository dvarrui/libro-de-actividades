#!/usr/bin/env ruby
#

require 'tk'

root = TkRoot.new
3.times { |f|
  4.times { |c|
    TkLabel.new(root) {
      text "fila #{f}, columna #{c}"
    }.grid('row' => f, 'column' => c, 'padx' => 10, 'pady' => 10)
  }
}
Tk.mainloop

