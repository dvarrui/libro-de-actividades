class Diapositiva < ActiveRecord::Base
  belongs_to :pase
  belongs_to :foto
  acts_as_list :scope => "pase_id"
end
