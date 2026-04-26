class CreateCursos < ActiveRecord::Migration
  def self.up
    create_table :cursos do |t|
      t.string :codigo
      t.boolean :fin_trim1
      t.boolean :fin_trim2
      t.boolean :fin_trim3
      t.date :fecha_fin_trim1
      t.date :fecha_fin_trim2
      t.date :fecha_fin_trim3
    end
    
    c = Curso.new
    c.codigo='Prueba'
    c.save
    
  end

  def self.down
    drop_table :cursos
  end
end
