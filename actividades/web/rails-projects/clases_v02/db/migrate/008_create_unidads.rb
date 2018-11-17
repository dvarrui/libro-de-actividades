class CreateUnidads < ActiveRecord::Migration
  def self.up
    create_table :unidads do |t|
      t.column :modulo_id, :integer
      t.column :numero, :integer
      t.column :nombre, :string
      t.column :fecha_ini, :date
    end
  end

  def self.down
    drop_table :unidads
  end
end
