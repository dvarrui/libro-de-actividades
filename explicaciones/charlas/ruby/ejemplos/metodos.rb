

class Persona
  def initialize(name)
    @name = name
  end

  def me_presento
    puts "Hola soy #{@name}!"
  end

  def insultar
    puts "Hola soy un tonto!"
  end

  def insultar!
    @name = "tonto"
    me_presento
  end

  def eres_tonto?
    return true if @name == "tonto"
    false
  end
end
