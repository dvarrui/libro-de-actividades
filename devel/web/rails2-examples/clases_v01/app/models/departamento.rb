class Departamento < ActiveRecord::Base
  has_many :profesors, :order => 'nombre'
end
