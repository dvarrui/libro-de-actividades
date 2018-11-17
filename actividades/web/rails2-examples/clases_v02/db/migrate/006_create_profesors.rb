class CreateProfesors < ActiveRecord::Migration
  def self.up
    create_table :profesors do |t|
      t.column :nombre, :string
      t.column :apellidos, :string
      t.column :dni, :string
      t.column :email, :string
      t.column :telefono_sms, :string
      t.column :telefono_otro, :string
      t.column :activo, :boolean
      t.column :usuario, :string
      t.column :clave, :string
    end

    p=Profesor.new
    p.nombre='David'
    p.apellidos='Vargas Ruiz'
    p.dni='45443506Z'
    p.email='davidvargas.tenerife@gmail.com'
    p.telefono_sms='657176183'
    p.telefono_otro='922382086'
    p.activo=true
    p.save
  end

  def self.down
    drop_table :profesors
  end
end
