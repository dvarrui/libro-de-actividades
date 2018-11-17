class CreateModulos < ActiveRecord::Migration
  def self.up
    create_table :modulos do |t|
      t.column :nombre, :string
      t.column :desc, :string
      t.column :nivel, :integer
      t.column :ciclo_id, :integer
      t.column :profesor_id, :integer
      t.column :peso_eje, :integer
      t.column :peso_val, :integer
      t.column :peso_pob, :integer
    end

    m1=Modulo.new
    m1.nombre='DIR'
    m1.desc='Programación en Entornos Gráficos'
    m1.nivel=2
    m1.ciclo=Ciclo.find(:first)
    m1.peso_eje=30
    m1.peso_val=10
    m1.peso_pob=60
    m1.save

    m2=Modulo.new
    m2.nombre='SIM'
    m2.desc='Sistemas Operativos Multiusuario y en Red'
    m2.nivel=1
    m2.ciclo = Ciclo.find(:first)
    m2.peso_eje=30
    m2.peso_val=10
    m2.peso_pob=60
    m2.save
  end

  def self.down
    drop_table :modulos
  end

end
