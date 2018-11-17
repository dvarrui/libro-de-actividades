#!/usr/bin/env ruby
## Inicializa Ruby/GTK2, como siempre.
require 'gtk2'

Gtk.init

# Pulsar un botón llamará a este método.
def callback(widget)
  # Muestra la propiedad 'label' del componente.
  # Puedes ver el API para más información.
  puts "Hola de nuevo - #{widget.label}(#{widget}) fue pulsado."
end

# Crea la ventana.
window = Gtk::Window.new

# Especifica el título y el borde de la ventana.
s = "Hola Mundo4!"
window.title = s
window.border_width = 10

# El programa terminará directamente con el evento 'delete_event'.
window.signal_connect('delete_event') do
  Gtk.main_quit
  false
end

# Creamos una caja para almacenar los componentes dentro.  
# Este proceso se describe detalladamente en la siguiente sección.
# La caja en realidad no es visible, tan solo es usada para ordenar los componentes. 
box1 = Gtk::HBox.new(false, 0)

# Vamos a colocar la caja en la ventana principal.
window.add(box1)

# Crea un nuevo botón con la etiqueta "Boton 1".
boton1 = Gtk::Button.new("Botón 1")

# Ahora cuando pulsemos el botón, llamaremos al método de respuesta
# con una referencia a "botón 1" como argumento.
boton1.signal_connect( "clicked" ) do |w|
  callback(w)
end

# En lugar de usar window.add, vamos a empaquetar este botón en la caja
# invisible, que a su vez está empaquetada dentro de la ventana.
box1.pack_start(boton1, true, true, 0)

# Sigamos los mismos pasos para crear un segundo botón.
boton2 = Gtk::Button.new("Botón 2")

# Llama al mismo método de respuesta con diferente argumento,
# pasando en su lugar una referencia a "botón 2".
boton2.signal_connect("clicked") do |w|
  callback(w)
end

# De la misma forma empaquetamos el segundo botón en la caja.
box1.pack_start(boton2, true, true, 0)

# Se puede llamar al método 'show' de cada componente así:
boton1.show
boton2.show
box1.show
window.show 
window.show_all
Gtk.main
