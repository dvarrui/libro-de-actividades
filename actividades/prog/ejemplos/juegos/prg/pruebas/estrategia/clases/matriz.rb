 

class Matriz
  #Crea una matriz (número de columnas, número de filas)
  def initialize(columnas, filas)
    @columnas = columnas
    @filas = filas
    @datos = []
    
    @filas.times do |y|
      @datos[y] = Array.new(columnas)
    end
  end
  
  def [](x,y)
    @datos[y][x]
  end
  
  def []=(x,y,valor)
    @datos[y][x] = valor
  end
  
  def all_posiciones
    #Esto lo puse para ajustar el marjen superior del bucle
    filas = @filas-1
    columnas = @columnas-1
    (0..filas).collect do |y|
      (0..columnas).collect do |x|
        [x,y]
      end
    end.inject([]) {|a,b| a.concat b}
  end
  
  def rep
    @datos.collect do |fila|
      fila.collect do |item|
        item.rep
      end
    end
  end
end

class NilClass
  def rep
    nil
  end
end
