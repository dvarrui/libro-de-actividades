#!/usr/bin/ruby

dinero = ARGV[0].to_i

puts('Para el importe solicitado tienes: ')

if  (dinero/100) >= 1
  puts "#{ dinero / 100} billete/s de 100 euros"
  dinero = dinero % 100
end
if dinero / 50 >= 1
  puts "#{ (dinero / 50) } billete/s de 50 euros"
  dinero = dinero % 50
end
if  dinero / 20 >= 1
  puts "#{dinero / 20} billete/s de 20 euros"
  dinero = dinero % 20
end
if dinero / 10 >= 1
  puts "#{ (dinero / 10) } billete/s de 10 euros"
  dinero = dinero % 10
end
if  dinero / 5 >= 1
  puts "#{ dinero / 5 } billete/s de 5 euros"
  dinero = dinero % 5
end
if  dinero / 2 >= 1
  puts "#{ (dinero / 2) } moneda/s de 2 euros"
  dinero = dinero % 2
end
if  dinero / 1 >= 1
  puts "#{dinero / 1} moneda/s de 1 euro"
  dinero = dinero % 1
end

puts "Resto es #{dinero}"
