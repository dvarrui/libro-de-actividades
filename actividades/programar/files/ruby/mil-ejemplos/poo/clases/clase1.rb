
class Fruta
  def kind=(k)
    @kind = k
  end
  def kind
    @kind
  end
end

f2 = Fruta.new
f2.kind = "banana"
print " * Valor del atributo: ",f2.kind,"\n"


class Fruta
     def inspect
        "una fruta de la variedad " + @kind
     end
end

print " * Información del objeto: ",f2,"\n"

p f2

