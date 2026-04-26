class Modulo < ActiveRecord::Base
	belongs_to :ciclo
	has_many :programas
	
	validates_presence_of :codigo
	validates_presence_of :nombre
	
	def ref
		codigo+":"+ciclo.ref
	end
end
