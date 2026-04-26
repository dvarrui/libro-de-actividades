class Unidad < ActiveRecord::Base
	belongs_to :asignatura
	has_many :tareas, :dependent=>:destroy
	
	def numero_y_codigo
		return 'U'+numero.to_s+': '+codigo
	end
end
