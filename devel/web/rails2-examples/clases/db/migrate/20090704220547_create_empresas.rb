class CreateEmpresas < ActiveRecord::Migration
  def self.up
    create_table :empresas do |t|
      t.column :centro_id, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :cif, :string
      t.column :municipio_id, :integer
      t.column :localidad, :string
      t.column :domicilio, :string
      t.column :cod_postal, :string
      t.column :latitud, :float
      t.column :longitud, :float
      t.column :tlf_fijo, :string
      t.column :tlf_movil, :string
      t.column :fax, :string
      t.column :email, :string
      t.column :nif_representante, :string
      t.column :nombre_representante, :string
      t.column :cargo_representante, :string
      t.column :nif_tutor, :string
      t.column :nombre_tutor, :string
      t.column :horario_empresa, :string
      t.column :horario_practicas, :string
      t.column :observaciones, :text
      t.column :activo, :boolean
    end
  end

  def self.down
    drop_table :empresas
  end
end
