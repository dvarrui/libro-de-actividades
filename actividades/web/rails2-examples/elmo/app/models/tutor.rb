class Tutor < ActiveRecord::Base
	has_many :matriculas, :dependent=>:nullify

	def nombre_completo
		return apellidos+', '+nombre
	end
end
