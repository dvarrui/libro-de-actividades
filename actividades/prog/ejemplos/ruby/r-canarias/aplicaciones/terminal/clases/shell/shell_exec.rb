=begin
Fichero: clases/shell/ShellExec
Autor: David VArgas <dvargas@canarias.org>
Versión: 0.1.0 20080123
Descripción: Implementa una consola pasando los comandos
directamente al sistema.
=end
 

class ShellExec
  attr_reader :directorio, :prompt, :salida
  
  def initialize(*args)
    if (args.size>0)
      @directorio = args[0]
      ejecutar "cd "+@directorio
    else
      @directorio = "/home/david/tmp"
    end
    @fin = false
  end
  
  def prompt
    return "Consola Virtual ["+@directorio+"]$ "
  end
  
  def ejecutar(comando)
    if (comando=="exit")
      @fin = true
      @salida = "Saliendo de la consola virtual..."
    else
      orden = "`"+comando+"`"
      @salida = eval orden
      #@salida = @salida + prompt
    end
  end
  
  def fin?
    return @fin
  end
end
