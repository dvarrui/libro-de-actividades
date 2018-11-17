#!/usr/bin/ruby

class HolaMundo5
  def saludar(n=5)
    n.times {puts "Hola Mundo 5!"}
  end
end

h = HolaMundo5.new
h.saludar
