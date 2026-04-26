class Programacion < ActiveRecord::Base
	belongs_to :curso
	belongs_to :modulo
	belongs_to :profesor
	belongs_to :aula
	has_many :pesos, :order => :codigo
	has_many :unidades, :order => :numero
	has_many :horarios, :order => :num_dia
	
	validates_presence_of :curso_id, :modulo_id, :profesor_id, :aula_id
	
	def codigo
		modulo.codigo
	end
	
	def ref
		modulo.codigo+":"+curso.ref
	end
	
end
