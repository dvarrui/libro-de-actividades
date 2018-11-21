#!/usr/bin/ruby

def saludar(nombre,numero)
  numero.times do
    print "Hola ",nombre,"!\n"
  end
end

if ARGV.size>1
  saludar ARGV[0], ARGV[1].to_i
end
