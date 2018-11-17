class CreateModulos < ActiveRecord::Migration
  def self.up
	create_table :modulos do |t|
	  t.column :ciclo_id, :integer
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :curso, :integer
      t.column :activo, :boolean
    end
    
    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('ASI')
      r.codigo='SIT'
      r.nombre='Sistemas Operativos Monousuario y Multiusuario'
      r.curso=1
      r.activo=true
 	  r.save
    end
    
    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('ASI')
      r.codigo='IMN'
      r.nombre='Implantación de Aplicaciones Informáticas de Gestión'
      r.curso=2
      r.activo=true
      r.save
    end
    
    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('DAI')
      r.codigo='SIM'
      r.nombre='Sistemas Operativos Multiusuario y en Red'
      r.curso=1
      r.activo=true
 	  r.save
    end
    
    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('DAI')
      r.codigo='DIR'
      r.nombre='Diseño de Servicios de Presentación en Entornos Gráficos'
      r.curso=2
      r.activo=true
      r.save
    end
   
    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('EMO')
      r.codigo='ARM'
      r.nombre='Armonía'
      r.activo=true
      r.save
    end

    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('EMO')
      r.codigo='FMA'
      r.nombre='Formación Musical Avanzada'
      r.activo=true
      r.save
    end

    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('EMO')
      r.codigo='FMC'
      r.nombre='Formación Musical Complementaria'
      r.activo=true
      r.save
    end
    
    Modulo.new do |r|
      r.ciclo=Ciclo.find_by_codigo('EMO')
      r.codigo='LNM'
      r.nombre='Lenguaje Musical'
      r.activo=true
      r.save
    end

  end

  def self.down
    drop_table :modulos
  end
end
