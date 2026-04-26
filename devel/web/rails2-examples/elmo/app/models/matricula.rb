class Matricula < ActiveRecord::Base
	belongs_to :grupo
	belongs_to :alumno
	belongs_to :tutor
	has_many :notas, :dependent=>:destroy
	has_many :faltas, :dependent=>:destroy, :order => :numdia
end
