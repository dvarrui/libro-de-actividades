#!/usr/bin/env ruby

puts "Ruta      : " + __FILE__
puts "Basename  : " + File.basename(__FILE__)
puts "Dirname   : " + File.dirname(__FILE__)
puts "Dirname x2: " + File.dirname(File.dirname(__FILE__))

