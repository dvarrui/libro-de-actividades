#!/usr/bin/ruby
# Autor: David Vargas <dvargas@canarias.org>
# 

$vg=0 #Variable global, accesible por todos los hilos

def codigo_del_hilo(id)
  vl=0
  50.times do
    puts "Hilo[#{id}]: vl=#{vl}, vg=#{$vg}"
    vl += 1
    $vg += 1
    sleep(0.01)
  end
  puts "Hilo[#{id}] terminado."
end

def crear_hilos_y_esperar(a)
  t = []
  a.each do |i| 
    t << Thread.new { codigo_del_hilo(i) } #Crear un hilo que ejecuta el método
  end
  t.each { |i| i.join} #Esperar por cada hilo creado
end

crear_hilos_y_esperar([1,2,3,4,5])


