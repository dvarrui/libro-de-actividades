#!/usr/bin/env ruby 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 
require 'clases/alfabeto'
require 'clases/simbolo'


s1 = Simbolo.new
s1.from_cadena!("0:----")
puts s1.to_cadena
s2=Simbolo.new
s2.from_cadena!("1:---.")
puts s2.to_cadena


require 'modulos/comprobacion_de_tipos'

class TestTipos
  def hola(n, s, f)
    n.times {f.write "hola #{s}!\n"}
  end
  
  extend ComprobacionDeTipos
  fichero = lambda do |x|
    x.respond_to?('write') and x.respond_to?('closed') #and not x.closed?
  end
  
  definir_reglas(:fichero => fichero,
    :positivo => lambda {|x| x>=0})
  
  aplicar_reglas_a :hola, [:positivo,:string]
  
end

t = TestTipos.new
t.hola(2,'mundo', $stdout)

t.hola(-1,'mundo', $stdout)

#t.hola(2,3001, $stdout)

f=open('file.txt','w') {}
#t.hola(2,'mundo',f)
