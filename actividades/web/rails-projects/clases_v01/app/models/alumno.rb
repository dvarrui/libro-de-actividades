class Alumno < ActiveRecord::Base
  has_and_belongs_to_many :grupo

  validates_format_of :correo, :with => /^[\w\.]+@[\w\.]+$/
end
