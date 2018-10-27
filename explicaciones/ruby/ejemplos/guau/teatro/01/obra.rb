#!/usr/bin/ruby
# enconding: utf-8

require_relative 'actor'

puts "="*50
puts "Obra: La venganza de Don Mendo"
puts "="*50

amigo = Actor.new '    Amigo'
mendo = Actor.new 'Don Mendo'

amigo.dice "Vos, de vos os reís, como yo me río de vós."
mendo.dice "No comprendo que decís."
amigo.dice "Será porque no querís. Está claro vive Dios"
mendo.dice "Siempre fuisteis enigmático, epigramático, ático, gramático y simbólico. Y aunque os escuche flemático. Sabed que  amí lo hiperbólico. No me resulta simpático."
mendo.dice_gritando "¡Hablad claro, Marqués!"
mendo.dice "Que en esta carcel sombría. Cualquier claridad de día. Consuelo y alivio es"
