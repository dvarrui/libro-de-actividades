class CreateCursos < ActiveRecord::Migration
  def self.up
    create_table :cursos do |t|
      t.column :nombre, :string
      t.column :fecha_trim1, :date
      t.column :fecha_trim2, :date
      t.column :fecha_trim3, :date
      t.column :fecha_fin, :date
    end

    c = Curso.new
    c.nombre='default'
    c.fecha_trim1 = Date.new
    c.save
  end

  def self.down
    drop_table :cursos
  end
end
