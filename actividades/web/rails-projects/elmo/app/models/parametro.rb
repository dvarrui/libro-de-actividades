class Parametro < ActiveRecord::Base

  def self.get_parametro_curso
     Parametro.find_by_variable("curso_id").valor.to_i
  end
end
