class Empresa < ActiveRecord::Base
	belongs_to :municipio
	belongs_to :centro
end
