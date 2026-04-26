class Nota < ActiveRecord::Base
	belongs_to :tarea
	belongs_to :matricula
end
