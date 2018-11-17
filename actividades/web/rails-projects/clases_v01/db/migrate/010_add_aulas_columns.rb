class AddAulasColumns < ActiveRecord::Migration
  def self.up
    add_column "aulas", "descripcion", :string
    add_column "aulas", "fecha_um", :datetime
    Aula.find(:all).each do |a|
      a.update_attribute :fecha_um, Time.now
    end
  end

  def self.down
    remove_column "aulas", "descripcion"
    remove_column "aulas", "fecha_um"
  end
end
