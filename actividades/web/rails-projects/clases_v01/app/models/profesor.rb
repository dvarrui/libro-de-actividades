class Profesor < ActiveRecord::Base
  belongs_to :departamento
  has_many :clase

  validates_format_of :correo, :with => /^[\w\.]+@[\w\.]+$/
end
