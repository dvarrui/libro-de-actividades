class Grupo < ActiveRecord::Base
  belongs_to :curso
  has_and_belongs_to_many :alumnos, :order => 'nombre'
  has_many :clases
end
