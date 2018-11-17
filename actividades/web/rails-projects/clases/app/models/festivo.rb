class Festivo < ActiveRecord::Base
	belongs_to :curso
	
	validates_presence_of :fecha_inicio
	validates_presence_of :fecha_fin
	validates_presence_of :motivo
	
	def validate
		if (fecha_inicio>fecha_fin) then
			errors.add('Fechas','La fecha final debe ser posterior al inicio.')
		end
	end
	
	def codigo
		motivo
	end
	
	def dias
		fecha_fin-fecha_inicio+1
	end
end
