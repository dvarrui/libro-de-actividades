class Categoria < ActiveRecord::Base
  has_many :contactos
  validates_presence_of :nombre, :message => "Hay que poner el nombre!"
end
