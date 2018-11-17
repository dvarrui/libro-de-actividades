
require 'app/Aula.rb'

class Poblacion
	attr_reader :aulas, :horarios, :profesores, :materias
	
	def initialize
		@aulas=[]
		@horarios=[]
		@profesores=[]
		@materias=[]
	end

public

	def load_aulas
		a = Aula.new
		a.from_cadena("1:201")
		puts a.inspect
	end
	
end

