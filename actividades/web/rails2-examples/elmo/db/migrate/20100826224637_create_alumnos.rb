class CreateAlumnos < ActiveRecord::Migration
  def self.up
    create_table :alumnos do |t|
      t.integer :numexp
      t.string :nombre
      t.string :apellidos
      t.string :tlf_fijo
      t.string :tlf_movil
      t.string :email
      t.date :fecha_nac
      t.text :observaciones

      t.timestamps
    end
  end

  def self.down
    drop_table :alumnos
  end
end
