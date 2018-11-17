class Pase < ActiveRecord::Base
  has_many :diapositivas, :order => :posicion
  #has_many :fotos, :through => :diapositivas
end
