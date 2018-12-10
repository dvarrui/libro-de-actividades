#!/usr/bin/ruby

a = ARGV[0].to_i
b = ARGV[1].to_i
c = ARGV[2].to_i
discriminante = b ** 2 - 4 * a * c

puts 'Calculadora de Ecuaciones de Segundo Grado'
puts "Coeficiente cuadrático (a) ----> #{a}"
puts "Coeficiente lineal (b) --------> #{b}"
puts "Término independiente (c) -----> #{c}"
puts "Ecuación: #{a} x^2 + #{b} x + #{c}"

puts('Pulse ENTER para ver la solución')
#p = gets

if a == 0
  x = -c / b
  puts 'Los valores introducidos no se corresponden con una ecuación de segundo grado'
  puts "La solución es: x = #{x}"
elsif discriminante < 0
  puts 'La ecuación no tiene solución real'
else
  x1 = (-b + Math.sqrt(discriminante.to_f)) / (2 * a)
  x2 = (-b - Math.sqrt(discriminante.to_f)) / (2 * a)
  puts 'Soluciones: '
  puts "x1 = #{x1}"
  puts "x2 = #{x2}"
end
