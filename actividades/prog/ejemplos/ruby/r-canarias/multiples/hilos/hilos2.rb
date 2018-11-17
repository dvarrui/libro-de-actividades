#!/usr/bin/ruby
#

x=0

Thread.new do
  while x < 5
    x -= 1
    puts "DEC: Disminuyendo la x a #{x}\n"
  end
  puts "DEC: la x es muy grande. Lo dejo!"
end

while x < 5
  x += 3
  puts "INC: Aumentando a x a #{x}\n"
end

sleep(1)

