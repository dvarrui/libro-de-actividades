class CreateDepartamentos < ActiveRecord::Migration
  def self.up
    create_table :departamentos do |t|
      t.column :centro_id, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :activo, :boolean
    end
    
    Departamento.new do |r|
      r.centro_id=1
      r.codigo='INF'
      r.nombre='Informática'
      r.activo=true
      r.save
    end

    Departamento.new do |r|
      r.centro_id=2
      r.codigo='FMC'
      r.nombre='Formación Musical Complementaria'
      r.activo=true
      r.save
    end
    
    Departamento.new do |r|
      r.centro_id=1
      r.codigo='ING'
      r.nombre='Inglés'
      r.activo=true
      r.save
    end
  end

  def self.down
    drop_table :departamentos
  end
end
