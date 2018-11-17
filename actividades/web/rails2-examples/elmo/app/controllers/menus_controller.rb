class MenusController < ApplicationController
	layout 'with2cols'
	
  def index
    redirect_to(:controller => 'cursos')
  end

  def inicio
    redirect_to( :controller => 'cursos', :action => 'index')
  end
  
  def diario
    @curso = Curso.find(Parametro.get_parametro_curso)
    @grupos = @curso.grupos.sort_by{|p| p.dia_semana}
    @horario = crear_horario_con @grupos
  end

  def cierre
    render :template=>'menus/cierre'
  end

  def acercade
    render :template=>'menus/acercade'
  end

private
  def crear_horario_con(grupos)
    horario=[]
    5.times do |i|
      horario << []
    	11.times do |j|
    	  if j==6 then
    	    horario[i]<<'<td class="descanso">Descanso</td>'
    	  else
    	    horario[i]<<'<td>'+i.to_s+','+j.to_s+'</td>'
    	  end 
    	end
    end
    
    grupos.each do |g|
      i=g.dia_semana-1
      j=g.num_hora_inicio
      k=g.num_total_intervalos_clase
      s='<td rowspan="'+k.to_s+'" class="ocupado"><b>'+g.codigo+'</b>'
      s=s+'<br><a href="'+url_for(:controller=>'faltas',:action=>'show_grupo',:id=>g)+'">Faltas</a>, '
      s=s+'<a href="'+url_for(:controller=>'notas',:action=>'show_grupo',:id=>g)+'">Notas</a>'
      s=s+'</td>'
      horario[i][j]=s
      
      if (k>1)
        horario[i][j+1]=''
      end
      if (k>2)
        horario[i][j+2]=''
      end
      if (k>3)
        horario[i][j+3]=''
      end
    end
        
    return horario
  end
  
end
