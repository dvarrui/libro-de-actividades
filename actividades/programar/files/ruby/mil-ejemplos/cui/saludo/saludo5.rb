#!/usr/bin/ruby

def saludar(nombre)
  print "Hola ",nombre,"!\n"
end

if ARGV.size>1
  n=ARGV[1].to_i
  n.times do
    saludar ARGV[0]
  end
end
