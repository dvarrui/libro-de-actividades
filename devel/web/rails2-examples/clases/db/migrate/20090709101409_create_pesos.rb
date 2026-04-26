class CreatePesos < ActiveRecord::Migration
  def self.up
    create_table :pesos do |t|
      t.column :programacion_id, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :valor, :integer
      t.column :valor_min, :integer
    end
  end

  def self.down
    drop_table :pesos
  end
end
