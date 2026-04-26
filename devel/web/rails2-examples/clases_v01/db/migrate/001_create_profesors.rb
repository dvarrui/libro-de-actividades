class CreateProfesors < ActiveRecord::Migration
  def self.up
    create_table :profesors do |t|
      t.column "nombre", :string
      t.column "apellidos", :string
      t.column "dni", :string
      t.column "correo", :string
      t.column "telefono", :string
      t.column "departamento_id", :integer
    end
    Profesor.new do |r|
      r.nombre = "David"
      r.apellidos = "Vargas Ruiz"
      r.dni = "45443506Z"
      r.correo = "dvargas@canarias.org"
      r.telefono = "657176183"
      r.save
    end
  end

  def self.down
    drop_table :profesors
  end
end
