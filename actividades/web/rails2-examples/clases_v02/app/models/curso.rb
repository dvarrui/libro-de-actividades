class Curso < ActiveRecord::Base
  validates_presence_of :nombre, :fecha_trim1

  has_many :ciclos

  def codigo
    nombre
  end
end
