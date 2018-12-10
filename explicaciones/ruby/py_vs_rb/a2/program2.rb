#!/usr/bin/ruby

x1 = ARGV[0].to_i
x2 = ARGV[2].to_i
x3 = ARGV[4].to_i
y1 = ARGV[1].to_i
y2 = ARGV[3].to_i
y3 = ARGV[5].to_i

d1 = Math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)
d2 = Math.sqrt((x1 - x3) ** 2 + (y1 - y3) ** 2)

if d2 > d1
  puts "El punto más cercano a (#{x1}, #{y1}) es (#{x2}, #{y2}) y está a una distancia #{d1}"
elsif d2 < d1
  puts "El punto más cercano a (#{x1}, #{y1}) es (#{x3}, #{y3}) y está a una distancia #{d2}"
end
