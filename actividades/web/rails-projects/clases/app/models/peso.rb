class Peso < ActiveRecord::Base
	belongs_to :programacion
	has_many :actividades
	
	def ref
		codigo+":"+programa.ref
	end
end
