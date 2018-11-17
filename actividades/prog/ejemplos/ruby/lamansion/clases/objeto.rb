# Clase objeto
# 
# Autor: David Vargas <dvargas@canarias.org>
#

class Objeto
  attr_accessor :nombre, :estado, :habitacion, :lotengo, :desc1, :desc2
  @@SEP=":"

  def self.new_from_cadena(str,habitaciones)
    o = Objeto.new
    o.from_cadena!(str,habitaciones)
    return o
  end

  def from_cadena!(str,habitaciones)
    i = str.split(@@SEP)
    @nombre = i[1]
    @estado = i[2].to_i
    @desc1 = i[3]
    if (i[4].lenght==0)
      @desc2 = @desc1
    else
      @desc2 = i[4]
    end
    @habitacion = get_by_id(i[5].to_i,habitaciones)
    @lotengo = i[6].to_i
  end

  def to_cadena
    str = @nombre+@@SEP+@estado.to_s
    str = str + @@SEP + @desc1 + @@SEP + @desc2
    str = str + @@SEP + @habitacion.id + @@SEP + @lotengo.to_s
    return str
  end
  
  def get_by_id(id,vector)
    vector.each do |v|
      return v if v.id == id
    end
    return nill
  end
end 
