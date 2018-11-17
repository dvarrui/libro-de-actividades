class Alumno < ActiveRecord::Base
	has_many :matriculas, :dependent=>:destroy

	def nombre_completo
		return apellidos+', '+nombre
	end
	
	def can_be_deleted?
	  return true if (matriculas.size==0)
	  false
	end
end
