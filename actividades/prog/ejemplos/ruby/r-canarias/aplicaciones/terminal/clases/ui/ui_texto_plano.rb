# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

class UITextoPlano
    
  def consola=(n)
    @shell = n
  end
  
  def iniciar
    print @shell.prompt
    while !@shell.fin?
      orden = gets.chomp!
      @shell.ejecutar orden
      puts @shell.salida
      print @shell.prompt
    end
  end
end
