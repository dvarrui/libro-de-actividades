class Foto < ActiveRecord::Base
  validates_presence_of :fichero

  has_many :diapositivas
  has_and_belongs_to_many :categorias
end
