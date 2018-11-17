
class Datos
	attr_reader :aula, :hora
	
	def load(contexto='default')
		@hora={}
		open('data/'+contexto+'/horas').readlines do |t|
			h=Hora.new
			h.from_cadena(t)
			@hora[h.id]=h
		end 
	end
	
end
