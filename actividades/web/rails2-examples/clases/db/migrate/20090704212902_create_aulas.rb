class CreateAulas < ActiveRecord::Migration
  def self.up
    create_table :aulas do |t|
      t.column :centro_id, :integer
      t.column :codigo, :string
      t.column :activo, :boolean
    end
    
    Aula.new do |r|
      r.centro_id=1
      r.codigo='aula108'
      r.activo=true
      r.save
    end
    
    Aula.new do |r|
      r.centro_id=1
      r.codigo='aula109'
      r.activo=true
      r.save
    end
    
    Aula.new do |r|
      r.centro_id=2
      r.codigo='Núm.19'
      r.activo=true
      r.save
    end    
  end

  def self.down
    drop_table :aulas
  end
end
