class CreateHorarios < ActiveRecord::Migration
  def self.up
    create_table :horarios do |t|
      t.column :programacion_id, :integer
      t.column :num_dia, :integer
      t.column :num_hora, :integer
    end
  end

  def self.down
    drop_table :horarios
  end
end
