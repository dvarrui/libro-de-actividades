class Profesor < ActiveRecord::Base
	belongs_to :departamento
	has_many :programaciones, :order => :curso_id
	
	validates_presence_of :departamento_id, :dni, :nombre, :tlf_movil, :email
	
	def nombre_completo
		apellidos+", "+nombre
	end
	
	def codigo
		nombre
	end
end
