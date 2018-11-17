class CreateCategorias < ActiveRecord::Migration
  def self.up
    create_table :categorias do |t|
      t.column :nombre, :string
      # t.column :name, :string
    end
  end

  def self.down
    drop_table :categorias
  end
end
