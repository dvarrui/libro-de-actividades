class Alumno < ActiveRecord::Base
  validates_presence_of :nombre, :apellidos
end
