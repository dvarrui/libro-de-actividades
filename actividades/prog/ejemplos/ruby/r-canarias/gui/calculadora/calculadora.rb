
class Calculadora
  attr_writer :a, :b

  def sumar
    @a + @b
  end

  def restar
    @a - @b
  end

  def multiplicar
    @a * @b
  end

  def dividir
    @a / @b
  end
end

