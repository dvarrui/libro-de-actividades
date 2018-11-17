class CreateAlumnos < ActiveRecord::Migration
  def self.up
    create_table :alumnos do |t|
      t.column "nombre", :string
      t.column "apellidos", :string
      t.column "dni", :string
      t.column "correo", :string
      t.column "telefono", :string
      t.column "foto", :string
    end
    create_table(:alumnos_grupos, :id=>false) do |t|
      t.column "grupo_id", :integer
      t.column "alumno_id", :integer
    end
  end

  def self.down
    drop_table :alumnos
    drop_table :alumnos_grupos
  end
end
