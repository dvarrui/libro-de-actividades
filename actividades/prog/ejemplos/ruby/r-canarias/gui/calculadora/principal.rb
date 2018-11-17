#!/usr/bin/env ruby
#
# This file is gererated by ruby-glade-create-template 1.1.3.
#
require 'libglade2'
require 'calculadora'

class CalculadoraGlade
  include GetText

  attr :glade
  
  def initialize(path_or_data, root = nil, domain = nil, localedir = nil, flag = GladeXML::FILE)
    bindtextdomain(domain, localedir, nil, "UTF-8")
    @glade = GladeXML.new(path_or_data, root, domain, localedir, flag) {|handler| method(handler)}
   #Creado por DVR 20071203
   @entNumero1 = @glade.get_widget("entNumero1")
   @entNumero2 = @glade.get_widget("entNumero2")
   @lblResultado = @glade.get_widget("lblResultado")
   @calculadora = Calculadora.new
  end
 
  def leer_numeros
    @calculadora.a= @entNumero1.text.to_f
    @calculadora.b= @entNumero2.text.to_f
  end

  def poner_resultado(n)
    @lblResultado.set_text(n.to_s)
  end
 
  def on_btnSalir_clicked(widget)
    Gtk.main_quit
  end
  def on_btnDividir_clicked(widget)
    leer_numeros
    poner_resultado @calculadora.dividir
  end
  def on_btnMultiplicar_clicked(widget)
    leer_numeros
    poner_resultado @calculadora.multiplicar
  end

  def on_btnSumar_clicked(widget)
    leer_numeros
    poner_resultado @calculadora.sumar
  end

  def on_btnReset_clicked(widget)
    puts "on_btnReset_clicked() is not implemented yet."
  end
  def on_btnRestar_clicked(widget)
    leer_numeros
    poner_resultado @calculadora.restar
  end
end

# Main program
if __FILE__ == $0
  # Set values as your own application. 
  PROG_PATH = "calculadora.glade"
  PROG_NAME = "YOUR_APPLICATION_NAME"
  Gtk.init
  CalculadoraGlade.new(PROG_PATH, nil, PROG_NAME)
  Gtk.main
end
