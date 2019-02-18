
class Hora
	attr_reader :id, :dia, :numhora
	
	def from_cadena(c)
		item=c.split(":");
		@id=item[0].to_i
		@dia=item[1] 
		@numhora=item[2].to_i 
	end
	
end
