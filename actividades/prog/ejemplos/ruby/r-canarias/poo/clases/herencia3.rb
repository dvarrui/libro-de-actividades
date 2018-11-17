
class Humano
  def identidad
    print "soy una persona.\n"
  end
  def tarifa_tren(edad)
    if edad < 12
      print "tarifa reducida.\n"
    else
      print "tarifa normal. \n"
    end
  end
end

Humano.new.identidad

class Estudiante<Humano
  def identidad
    print "soy un estudiante.\n"
  end
end

Estudiante.new.identidad

class Estudiante2<Humano
  def identidad
    super
    print "también soy un estudiante.\n"
  end
end

Estudiante2.new.identidad

