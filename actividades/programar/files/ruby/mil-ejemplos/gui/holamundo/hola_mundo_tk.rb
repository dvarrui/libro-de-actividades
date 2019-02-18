require 'tk'
root = TkRoot.new { title "hola_mundo_tk con ruby" }
TkLabel.new(root) {
  text  'Hola, Mundo!'
  pack  { padx 15 ; pady 15; side 'left' }
}
Tk.mainloop

