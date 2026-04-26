class Unidad < ActiveRecord::Base
	belongs_to :programacion
	has_many :actividades
	
	def ref
		'U'+numero.to_s+':'+programacion.ref
	end
end
