module PesosHelper

=begin
  def activar_menu_nav(p)
    s='<div id="nav">'
    s=s+'<b>Usted está aquí:</b> '
    
    if (p==:pesos) then
      s=s+'<a href="/menus/curso">Inicio del Curso</a> > '
      s=s+'<a href="/cursos">Cursos</a> > '
      s=s+'<a href="/programas">Programaciones</a> > '
      s=s+'Pesos'
    elsif (p.class.to_s=="Peso") then
      s=s+'<a href="/menus/curso">Inicio del Curso</a> > '
      s=s+'<a href="/cursos">Cursos</a> > '
      s=s+'<a href="/cursos/'+p.programa.curso_id.to_s+'">Curso: '+p.programa.curso.codigo+'</a> > '
      s=s+'<a href="/programas">Programaciones</a> > '
      s=s+'<a href="/programas/'+p.programa_id.to_s+'">Programación: '+p.programa.ref+'</a> > '
      s=s+'Peso: '+p.codigo
    end
    
    s=s+'</div>'
    return s
  end
=end

end
