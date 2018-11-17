module MenusHelper

=begin
  def activar_menu_nav(p)
    s='<div id="nav">'
    s=s+'<b>Usted está aquí Sr:</b> '
    if (p==:centro) then
      s=s+'Recursos del Centro'
    else
      s=s+'Inicio del Curso'
    end
    s=s+'</div>'
    return s
  end
=end
end
