class Ciclo < ActiveRecord::Base
	belongs_to :centro
	has_many :modulos, :order => :codigo
	
	validates_presence_of :codigo, :message => 'El código es el nombre corto o abreviación del Ciclo'
	validates_presence_of :nombre
	
	def ref
		codigo+':'+centro.codigo
	end
end
