class CreateAlumnos < ActiveRecord::Migration
  def self.up
    create_table :alumnos do |t|
      t.column :nombre, :string
      t.column :apellidos, :string
      t.column :dni, :string
      t.column :fecha_nac, :date
      t.column :foto, :string
      t.column :email, :string
      t.column :telefono_sms, :string
      t.column :telefono_otro, :string
      t.column :domicilio, :string
      t.column :localidad, :string
      t.column :expediente, :string
    end
  end

  def self.down
    drop_table :alumnos
  end
end
