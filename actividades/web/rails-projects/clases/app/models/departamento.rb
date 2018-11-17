class Departamento < ActiveRecord::Base
	belongs_to :centro
	has_many :profesores, :order => :apellidos
	
	validates_presence_of :codigo
	validates_presence_of :nombre
	
	def ref
		codigo+':'+centro.codigo
	end
end
