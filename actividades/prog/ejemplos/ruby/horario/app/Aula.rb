
class Aula
	attr_reader :id, :nombre
	
	def from_cadena(c)
		item=c.split(":");
		@id=item[0].to_i
		@nombre=item[1] 
	end
	
end
