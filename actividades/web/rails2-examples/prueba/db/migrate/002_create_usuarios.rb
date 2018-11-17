class CreateUsuarios < ActiveRecord::Migration
  def self.up
    create_table :usuarios do |t|
      # t.column :name, :string
      t.column :nombre, :string
      t.column :clave, :string
    end
  end

  def self.down
    drop_table :usuarios
  end
end
