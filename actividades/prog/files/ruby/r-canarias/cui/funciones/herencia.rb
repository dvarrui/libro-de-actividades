
class Mamifero
   def respira
           print "inhalar y exhalar\n"
   end
end

class Gato<Mamifero
   def maulla
           print "miau \n"
   end
end

akane = Gato.new
akane.respira
akane.maulla

