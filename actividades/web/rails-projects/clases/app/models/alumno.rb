class Alumno < ActiveRecord::Base
	belongs_to :municipio
	
	validates_presence_of :nombre
	validates_presence_of :apellidos
	validates_presence_of :tlf_movil
	validates_presence_of :email
	
	def nombre_completo
		apellidos+', '+nombre
	end
	
	def edad
		((Date.today-fecha_nac).to_f/365).to_i
	end
end
