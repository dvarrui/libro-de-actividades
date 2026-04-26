class Curso < ActiveRecord::Base
	has_many :asignaturas, :dependent=>:destroy, :order => :codigo
	has_many :grupos, :through => :asignaturas, :order => :dia_semana
	has_many :matriculas, :through => :grupos
	
	def is_selected?
	  i = Parametro.get_parametro_curso
	  return (id==i)
	end
	
	def can_be_deleted?
	  return true if (grupos.size==0)
	  false
	end

end
