module AulasHelper

  def incluir_tabla_horarios_mostrar(tabla,num)
    s=s+'<p>Horas ocupadas('+num.to_s+'):</p>'
    s=s+'<table border=1><tr>'
    s=s+'<th>Hora</th><th>Lunes</th><th>Martes</th><th>Miércoles</th><th>Jueves</th><th>Viernes</th></tr>'
    
    12.times do |fila|
      s=s+'<tr>'
      6.times { |col| s=s+'<td>'+tabla[fila][col]+'</td>' }
      s=s+'</tr>'
      if (fila==2||fila==5||fila==8) then
        s=s+'<tr>'
        6.times { |col| s=s+'<td>Descanso</td>' }
        s=s+'</tr>'
      end
    end
    s=s+'</table>'
  end
  	
end
