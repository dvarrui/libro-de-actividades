class Profesor < ActiveRecord::Base
  has_many :modulos
  has_many :horarios

  validates_presence_of :nombre, :email, :telefono_sms

  def codigo
    nombre+' '+apellidos
  end
end
