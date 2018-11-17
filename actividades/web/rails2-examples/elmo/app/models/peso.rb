class Peso < ActiveRecord::Base
	belongs_to :asignatura
	has_many :tareas, :dependent=>:destroy
end
