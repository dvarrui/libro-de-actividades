class Categoria < ActiveRecord::Base
  has_and_belongs_to_many :fotos
  acts_as_tree
end
