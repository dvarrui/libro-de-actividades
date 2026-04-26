class CreateDepartamentos < ActiveRecord::Migration
  def self.up
    create_table :departamentos do |t|
      t.column "nombre", :string
    end
    Departamento.new do |r|
      r.nombre = "Informática"
      r.save
      Profesor.find(:all).each do |p|
        p.departamento_id = r.id
        p.save
      end
    end
  end

  def self.down
    drop_table :departamentos
  end
end
