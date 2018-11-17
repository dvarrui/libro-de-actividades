class Curso < ActiveRecord::Base
	belongs_to :centro
	has_many :festivos, :order => :fecha_inicio
	has_many :programaciones
	has_many :horarios, :through => :programaciones
	
	validates_presence_of :codigo, :message => 'Por ejemplo para el curso académico 2009-2010 se puede poner como código "2009-10", "2009-2010", "Curso de David", etc.'
	validates_presence_of :inicio_clases
	
	def validate
		if (inicio_clases>fin_eval1 || fin_eval1>fin_eval2 || fin_eval2>fin_eval3) then
			errors.add('Fechas','Las fechas deben ser correlativas en el tiempo.')
		end
	end
	
	def ref
		codigo+':'+centro.codigo
	end
end
