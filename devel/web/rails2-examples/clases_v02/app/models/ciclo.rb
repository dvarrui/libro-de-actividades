class Ciclo < ActiveRecord::Base
  belongs_to :curso
  has_many :modulos

  validates_presence_of :nombre, :desc, :curso_id

  def codigo
    nombre+'_'+curso.nombre
  end
end
