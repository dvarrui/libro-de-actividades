class Asignatura < ActiveRecord::Base
	belongs_to :curso
	has_many :unidades, :dependent=>:destroy, :order=>:numero 
	has_many :pesos, :dependent=>:destroy, :order => :codigo 
	has_many :grupos, :dependent=>:destroy
	
   def can_be_deleted?
	  return true if (grupos.size==0)
	  false
	end

end
