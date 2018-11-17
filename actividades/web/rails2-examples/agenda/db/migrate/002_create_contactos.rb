class CreateContactos < ActiveRecord::Migration
  def self.up
    create_table :contactos do |t|
      t.column :nombre, :string
      t.column :apellidos, :string
      t.column :telefono, :string
      t.column :email, :string
      t.column :direccion, :text
      t.column :categoria_id, :integer
      # t.column :name, :string
    end
  end

  def self.down
    drop_table :contactos
  end
end
