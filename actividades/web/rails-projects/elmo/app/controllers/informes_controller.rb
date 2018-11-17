class InformesController < ApplicationController
	def all_grupos
	  @curso = Curso.find(Parametro.get_parametro_curso)
	  @dia_semana=['Día 0','Lunes','Martes','Miércoles','Jueves','Viernes']
	end
end
