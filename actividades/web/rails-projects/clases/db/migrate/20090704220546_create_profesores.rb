class CreateProfesores < ActiveRecord::Migration
  def self.up
    create_table :profesores do |t|
      t.column :departamento_id, :integer
      t.column :dni, :string
      t.column :apellidos, :string
      t.column :nombre, :string
      t.column :tlf_fijo, :string
      t.column :tlf_movil, :string
      t.column :email, :string
      t.column :activo, :boolean
    end
  end

  def self.down
    drop_table :profesores
  end
end
