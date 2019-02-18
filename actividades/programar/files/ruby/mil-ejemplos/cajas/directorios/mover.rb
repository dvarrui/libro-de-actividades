#!/usr/bin/ruby -w
#
# Autor: David Vargas <dvargas@canarias.org>
#

class Mover
  attr_accessor :modo

  def initialize(d1,d2)
    @dirbase1 = d1
    @dirbase2 = d2
    @modo = :lectura
    comprobar
  end

  def comprobar
    @error = false
    @error = true if !File.directory? @dirbase1
    @error = true if !File.directory? @dirbase2
    @error = true if @dirbase1 == @dirbase2
  end

  def mostrar
    print "@dir1  = ", @dirbase1, "\n"
    print "@dir2  = ", @dirbase2, "\n"
    print "@modo  = ", @modo, "\n"
    print "@error = ", @error, "\n"
  end

  def ejecutar(base1=@dirbase1,base2=@dirbase2)
    if (@error==true)
      puts "Error en los parámetros!"
      return false
    end

    Dir.new(base1).sort.each do |f|
      if (f!='.' && f!='..')
        ruta1 = base1+f
        ruta2 = base2+f
        if File.directory?(ruta1) && File.ftype(ruta1)=='directory'
          ejecutar(ruta1+'/',ruta2+'/')
          if @modo==:escribir
            `rmdir #{ruta1}`
          end
        elsif File.exist?(ruta1)
          print "mv #{ruta1} #{ruta2} \n"
          if @modo==:escribir
            `mv #{ruta1} #{ruta2}`
          end
        else
          print "ERROR:",ruta1,", ",ruta2,"\n"
        end
      end
    end 
  end

end

if ARGV.size<1
  puts "Forma de uso: #{__FILE__} directorio_origen directorio_destino"
else
  m = Mover.new(ARGV[0],ARGV[1])
  m.mostrar
  m.modo = :escribir
  m.ejecutar
end

