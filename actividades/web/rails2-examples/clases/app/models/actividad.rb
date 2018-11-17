class Actividad < ActiveRecord::Base
	belongs_to :unidad
	belongs_to :peso
	
	validates_presence_of :codigo, :message => 'Identificador como por ejemplo E1.1, A2.4, etc.'
	validates_presence_of :nombre, :message => 'Descripción breve de la actividad'
	validates_numericality_of :valor_max
	
	def ref
		codigo+':'+unidad.ref
	end
	
	def validate
		if (fecha_inicio>fecha_fin) then
			errors.add('Intervalo de fechas:','Las fechas están desordenadas.')
		end
	end
end
