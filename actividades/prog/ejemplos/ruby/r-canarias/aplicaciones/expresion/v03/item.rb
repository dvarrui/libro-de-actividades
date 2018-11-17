#
# Autor: David Vargas <dvargas@canarias.org>
# Versión: 20070827
# 

class Item
  attr_accessor :valor, :nivel

  def initialize(p_valor='',p_nivel=0)
    @valor = p_valor
    @nivel = p_nivel
  end

  def to_f
    @valor.to_f
  end

  def to_s
    @valor
  end
end
