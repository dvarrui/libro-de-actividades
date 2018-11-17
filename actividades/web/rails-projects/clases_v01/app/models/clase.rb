class Clase < ActiveRecord::Base
  belongs_to :curso
  belongs_to :aula
  belongs_to :profesor
  belongs_to :modulo
  belongs_to :grupo
end
