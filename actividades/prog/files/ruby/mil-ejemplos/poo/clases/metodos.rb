#!/usr/bin/ruby
# metodos.rb

def cuadrado(x)
    return x*x
end

def triangulos
    "son diferentes de los cuadrados"
    #se puede omitir la palabra return
end

def metodo(uno, dos, *varios)
    "Me han pasado #{uno} , #{dos} , #{varios.join(', ')}"
end

def es_palindromo?(frase)
    frase = frase.dup   #hago una copia de frase
    frase.gsub!(" "," ").downcase! == frase.reverse
end

puts "Cuadrado de 2 = #{cuadrado 2}"
puts "Los triangulos son " + triangulos
puts metodo("cocaina", "hachis", "mariguana", "extasis")
puts metodo(*(1..3).to_a)

frase = "Dabale arroz a la zorra el abad"
if es_palindromo? frase
   puts "\"#{frase}\" es un palindromo"
else
   puts "\"#{frase}\" no es un palindromo"
end

