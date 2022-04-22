#!/usr/bin/env ruby

puts "[ INFO ] Ejecutando (#{$0})"

dirname = "vargas.d"
filename = "readme.txt"
filepath = "#{dirname}/#{filename}"

if not File.exist? dirname
  puts " => Crear directorio #{dirname}"
  system("mkdir #{dirname}")
end

if not File.exist? filepath
  puts " => Crear filepath #{filepath}"
  system("touch #{filepath}")
end

puts "[ INFO ] Fin del script!"
exit 0
