class CreateCiclos < ActiveRecord::Migration
  def self.up
    create_table :ciclos do |t|
      t.column :centro_id, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :activo, :boolean
    end

    Ciclo.new do |r|
        r.centro_id=1
    	r.codigo='ASI'
    	r.nombre='Administración de Sistemas'
    	r.activo=true
    	r.save
    end

    Ciclo.new do |r|
    	r.centro_id=1
    	r.codigo='DAI'
    	r.nombre='Desarrollo de Aplicaciones'
    	r.activo=true
    	r.save
	end
	
    Ciclo.new do |r|
    	r.centro_id=1
    	r.codigo='BACH'
    	r.nombre='Bachillerato'
    	r.activo=true
    	r.save
    end
    
     Ciclo.new do |r|
     	r.centro_id=2
    	r.codigo='EMO'
    	r.nombre='Escuela de Música'
    	r.activo=true
    	r.save
    end
  end

  def self.down
    drop_table :ciclos
  end
end
