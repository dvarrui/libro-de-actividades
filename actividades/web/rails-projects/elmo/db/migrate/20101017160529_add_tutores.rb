class AddTutores < ActiveRecord::Migration
  def self.up
    add_column "tutores", "especialidad", :string
  end

  def self.down
    remove_column "tutores", "especialidad"
  end
end
