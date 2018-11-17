
class Pepe
 
  def metodo(*n)
    print "nº args=",n.size,"\n"
    n.each do |i|
      print "- ",i
    end
    print "\n"
  end

end

p = Pepe.new
p.metodo "kaka"
p.metodo "caca","ruta"
p.metodo "2", "3", "ddd"

