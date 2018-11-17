# To change this template, choose Tools | Templates
# and open the template in the editor.

puts '(1) Yo soy un objeto de la clase = ' + self.class.to_s
puts '(2) Yo soy un objeto = ' + self.to_s
puts '(3) Los métodos privados son: '
puts self.private_methods.sort
puts '(4) Los métodos públicos son: '
puts self.public_methods.sort
puts '(5) Los métodos singleton son: '
puts self.singleton_methods.sort