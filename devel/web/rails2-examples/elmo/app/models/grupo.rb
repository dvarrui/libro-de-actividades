class Grupo < ActiveRecord::Base
	belongs_to :curso
	belongs_to :asignatura
	has_many :matriculas, :dependent=>:destroy
	has_many :faltas, :through => :matriculas
	has_many :notas, :through => :matriculas
	
	def codigo
		return asignatura.codigo+'-'+letra
	end
	
	def num_hora_inicio
		num = (hora_inicio.hour-15)*2
		num=num+1 if (hora_inicio.min>=30)
		return num-1
	end
	
	def num_total_intervalos_clase
	   num = ((hora_fin-hora_inicio)/3600)*2
	   return num.to_i
	end
	
end
