#!/usr/bin/ruby

def saludar(nombre,numero)
  numero.times do
    print "Hola ",nombre,"!\n"
  end
end

saludar(ARGV[0], ARGV[1].to_i) if ARGV.length>1
