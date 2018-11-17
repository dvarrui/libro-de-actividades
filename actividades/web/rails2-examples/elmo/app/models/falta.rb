class Falta < ActiveRecord::Base
	belongs_to :matricula
	
	def numdia_y_fecha
	  return 'D'+numdia.to_s+':'+fecha.strftime("%Y/%m/%d")
	end
end
