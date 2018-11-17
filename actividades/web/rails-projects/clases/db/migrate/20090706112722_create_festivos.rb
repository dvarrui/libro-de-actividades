class CreateFestivos < ActiveRecord::Migration
  def self.up
    create_table :festivos do |t|
      t.column :curso_id, :integer
      t.column :fecha_inicio, :date
      t.column :fecha_fin, :date
      t.column :motivo, :string
    end
  end

  def self.down
    drop_table :festivos
  end
end
