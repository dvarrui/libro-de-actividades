class CreateUnidades < ActiveRecord::Migration
  def self.up
    create_table :unidades do |t|
      t.column :programacion_id, :integer
      t.column :numero, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :url, :string
      t.column :peso, :integer
    end
  end

  def self.down
    drop_table :unidades
  end
end
