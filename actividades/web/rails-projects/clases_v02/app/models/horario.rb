class Horario < ActiveRecord::Base
  belongs_to :aula
  belongs_to :modulo
  belongs_to :profesor

  validates_presence_of :dia, :hora
end
