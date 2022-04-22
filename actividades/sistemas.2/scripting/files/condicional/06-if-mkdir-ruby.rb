#!/usr/bin/env ruby

system("clear")
puts "[ INFO ] Ejecutando: #{$0}"
puts "         #{Time.now}"

dirname = "vargas.d"
filename = "readme.txt"
filepath = "#{dirname}/#{filename}"
actions = 0

if not File.exist? dirname
  puts " => Crear directorio #{dirname}"
  system("mkdir #{dirname}")
  actions += 1
end

if not File.exist? filepath
  puts " => Crear filepath #{filepath}"
  system("touch #{filepath}")
  actions += 1
end

if actions == 0
  puts "[ INFO ] Jajaja... no tuve que hacer nada!"
else
  puts "[ INFO ] Se han realizado #{actions} acciones. Fin"
end

exit 0
