class CreateAulas < ActiveRecord::Migration
  def self.up
    create_table :aulas do |t|
      t.column "nombre", :string
      t.column "foto", :string
    end
    Aula.new do |r|
      r.nombre="Informática"
      r.foto="aula_informatica.jpg"
      r.save
    end
  end

  def self.down
    drop_table :aulas
  end
end
