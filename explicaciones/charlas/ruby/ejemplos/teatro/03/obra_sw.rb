#!/usr/bin/ruby
# enconding: utf-8

require_relative 'actor'

puts "="*50
puts "Obra: La venganza de Don Mendo"
puts "="*50

luke  = Actor.new 'Luke Skywalker', :blue
vader = Actor.new '   Darth Vader'

vader.dice "¿Obiwan no te contó qué le pasó a tu padre?"
luke.dice_gritando  "Me dijo que tú lo mataste!"
vader.dice "No. Yo, soy tu padre"
luke.dice_gritando  "Nooooooo!!!"

luke.info
vader.info
