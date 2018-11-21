#!/usr/bin/env ruby
## Inicializa Ruby/GTK2, como siempre.
require 'gtk2'

Gtk.init

# Crea la ventana.
window = Gtk::Window.new

# Especifica el título y el borde de la ventana.
s = "Hola Mundo3!"
window.title = s
window.border_width = 10

# El programa terminará directamente con el evento 'delete_event'.
window.signal_connect('delete_event') do
  Gtk.main_quit
  false
end

# Crea un nuevo botón 
boton = Gtk::Button.new(s)
window.add(boton)

# Ahora cuando pulsemos el botón, llamaremos al método de respuesta
boton.signal_connect( "clicked" ) do |w|
  puts s
end

window.show_all
Gtk.main
