class Aula < ActiveRecord::Base
	belongs_to :centro
	has_many :programas
	
	validates_presence_of :codigo
	
	def ref
		codigo+':'+centro.codigo
	end
end
