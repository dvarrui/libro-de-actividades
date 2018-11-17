class Horario < ActiveRecord::Base
	belongs_to :programacion
	
	def ref
		'prueba'
	end
end
