class CreateHorarios < ActiveRecord::Migration
  def self.up
    create_table :horarios do |t|
      t.column :modulo_id, :integer
      t.column :profesor_id, :integer
      t.column :aula_id, :integer
      t.column :dia, :integer
      t.column :hora, :integer
    end
  end

  def self.down
    drop_table :horarios
  end
end
