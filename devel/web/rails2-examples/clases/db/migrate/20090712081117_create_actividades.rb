class CreateActividades < ActiveRecord::Migration
  def self.up
    create_table :actividades do |t|
      t.column :unidad_id, :integer
      t.column :peso_id, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :url, :string
      t.column :fecha_inicio, :date
      t.column :fecha_fin, :date
      t.column :valor_max, :integer
    end
  end

  def self.down
    drop_table :actividades
  end
end
