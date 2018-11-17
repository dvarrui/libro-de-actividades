class Aula < ActiveRecord::Base
  has_many :clase
  validates_presence_of :nombre
end
