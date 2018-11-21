#!/usr/bin/ruby -w
#

x=0
Thread.new do
  while x<5
    x -= 1
    puts "DEC: Decremento x a #{x}\n"
    sleep 1
  end
  puts "DEC: x es demasiado grande; abandono!\n"
end

while x<5
  x += 3
  puts "INC: Incremento x a #{x}\n"
  sleep 2
end

