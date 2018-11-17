class CreateAlumnos < ActiveRecord::Migration
  def self.up
    create_table :alumnos do |t|
      t.column :expediente, :string
      t.column :dni, :string
      t.column :apellidos, :string
      t.column :nombre, :string
      t.column :fecha_nac, :date
      t.column :sexo, :boolean
      t.column :tlf_fijo, :string
      t.column :tlf_movil, :string
      t.column :email, :string
      t.column :municipio_id, :integer
      t.column :localidad, :string
      t.column :domicilio, :string
      t.column :cod_postal, :string
      t.column :latitud, :float
      t.column :longitud, :float
      t.column :observaciones, :text
    end
  end

  def self.down
    drop_table :alumnos
  end
end
