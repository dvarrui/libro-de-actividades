class Modulo < ActiveRecord::Base
  belongs_to :ciclo
  belongs_to :profesor
  has_many :horarios
  has_many :unidads

  validates_presence_of :nombre, :desc, :nivel, :ciclo_id

  def codigo
    nombre+'_'+ciclo.codigo
  end
end
