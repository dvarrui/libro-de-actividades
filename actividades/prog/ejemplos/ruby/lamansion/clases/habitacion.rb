# 
# Autor: David Vargas <dvargas@canarias.org>
#

class Habitacion
  attr_accessor :id, :descripcion, :norte, :sur, :este, :oeste
  @@SEP=":"

  def self.new_from_cadena(str)
    h = Habitacion.new
    h.from_cadena!(str)
    return h
  end

  def from_cadena!(str)
    i = str.split(@@SEP)
    @id = i[1].to_i
    @descripcion = i[2]
    @norte = i[3].to_i
    @sur   = i[4].to_i
    @este  = i[5].to_i
    @oeste = i[6].to_i
  end

  #<p>Devuelve un objeto <b>Habitacion</b> como un String con todos los
  #atributos del objeto concatenados.</p>
  def to_cadena
    str = (self.class.to_s) + @@SEP+ @id.to_s+@@SEP+@descripcion
    str = str+@@SEP+@norte.to_s+@@SEP+@sur.to_s
    str = str+@@SEP+@este.to_s+@@SEP+@oeste.to_s
    return str
  end
end 
