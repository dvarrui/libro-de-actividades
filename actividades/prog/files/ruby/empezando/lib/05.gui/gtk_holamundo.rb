#!/usr/bin/ruby -w
#gtk_holamundo.rb


=begin
ubuntu 7.10 Gutsy Gibbon

El procedimiento es simple, solo hace falta instalar un metapaquete que contienen todo lo necesario

sudo aptitude install ruby-gnome2

Para ver la version de ruby instalada se puede ejecutar:

ubuntu@ubuntu:~/ruby$ ruby -v
ruby 1.8.6 (2007-06-07 patchlevel 36) [x86_64-linux]

para ver la version de gtk2 instalada se puede ejecutar:

ubuntu@ubuntu:~/ruby$ ruby -rgtk2 -e 'p Gtk::VERSION'
[2, 12, 0]
=end


require 'gtk2'

Gtk.init
window=Gtk::Window.new 'GTK Ruby - HolaMundo'
label=Gtk::Label.new 'Hola Mundo!'
window.add label
window.signal_connect('destroy') {Gtk.main_quit}
window.show_all
Gtk.main

