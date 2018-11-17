class CreateAulas < ActiveRecord::Migration
  def self.up
    create_table :aulas do |t|
      t.column :nombre, :string
      t.column :desc, :string
      t.column :foto1, :string
      t.column :foto2, :string
      t.column :plano, :string
    end

    a1 = Aula.new
    a1.nombre='Aula108'
    a1.save
    a2 = Aula.new
    a2.nombre='Aula109'
    a2.save
  end

  def self.down
    drop_table :aulas
  end
end
