class Tarea < ActiveRecord::Base
   belongs_to :unidad
   belongs_to :peso
   has_many :notas, :dependent=>:destroy
end
