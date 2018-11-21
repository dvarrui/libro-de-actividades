#!/usr/bin/ruby

class CarreraCoches
  def initialize
    @circuito=[15,15,15,15,15,15,15,15,15,15]
    @circuito_coche=15    
    @aspecto_carretera="I     I"
    @aspecto_coche=['IA    I','I A   I','I  A  I','I   A I','I    A']
    @posicion=2
  end

  def pintar
    puts "---"
    @circuito.each do |i|
      i.times { |j| print ' ' }
      print @aspecto_carretera,"\n"
    end
    @circuito_coche.times {|i| print ' '}
    print @aspecto_coche[@posicion],"\n"
  end

  def avanzar
    n=rand(3)-1
    c=Array.new
    c << (@circuito[0]+n)
    0..9.times { |i| c << @circuito[i] }
    @circuito = c
  end

  def empezar
    10.times do
      pintar
      avanzar
    end
  end
end

c=CarreraCoches.new
c.empezar
