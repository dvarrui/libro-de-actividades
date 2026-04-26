class Curso < ActiveRecord::Base
  has_many :festivos
  has_many :grupos
  has_many :clases
end
