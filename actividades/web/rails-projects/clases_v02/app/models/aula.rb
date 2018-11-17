class Aula < ActiveRecord::Base
  has_many :horarios

  validates_presence_of :nombre
end
