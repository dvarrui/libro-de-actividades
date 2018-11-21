#!/usr/bin/ruby

#ri String#size
if ARGV.size>1
  n=ARGV[1].to_i
  n.times do
    print "Hola ", ARGV[0], "!\n"
  end
elsif ARGV.size>0
  print "Cuantas veces le saludo ",ARGV[0],"\n"
else
  puts "No le conozco."
end
