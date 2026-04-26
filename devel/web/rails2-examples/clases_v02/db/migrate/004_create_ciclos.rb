class CreateCiclos < ActiveRecord::Migration
  def self.up
    create_table :ciclos do |t|
      t.column :nombre, :string
      t.column :desc, :string
      t.column :curso_id, :integer
    end

    c1 = Ciclo.new
    c1.nombre='ASI'
    c1.desc='Administración de Sistemas'
    c1.curso= Curso.find_by_nombre('default')
    c1.save

    c2 = Ciclo.new
    c2.nombre='DAI'
    c2.desc='Desarrollo de Aplicaciones'
    c2.curso= Curso.find_by_nombre('default')
    c2.save
  end

  def self.down
    drop_table :ciclos
  end
end
