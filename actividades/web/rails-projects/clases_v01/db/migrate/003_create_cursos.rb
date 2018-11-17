class CreateCursos < ActiveRecord::Migration
  def self.up
    create_table :cursos do |t|
      t.column "nombre", :string
      t.column "inicio_clases", :date
      t.column "fin_clases", :date
    end
    Curso.new do |r|
      r.nombre = "2007-2008"
      r.inicio_clases = "2007-09-14"
      r.fin_clases = "2008-06-15"
      r.save
    end
  end

  def self.down
    drop_table :cursos
  end
end
