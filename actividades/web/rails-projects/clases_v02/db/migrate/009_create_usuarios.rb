class CreateUsuarios < ActiveRecord::Migration
  def self.up
    create_table :usuarios do |t|
      t.column :nombre, :string
      t.column :clave, :string
      t.column :activo, :boolean
      t.column :admin, :boolean
      t.column :profesor_id, :integer
      t.column :alumno_id, :integer
    end

    u=Usuario.new
    u.nombre='admin'
    u.clave='123456'
    u.activo=true
    u.admin=true
    u.save
  end

  def self.down
    drop_table :usuarios
  end
end
