class CreateCursos < ActiveRecord::Migration
  def self.up
    create_table :cursos do |t|
      t.column :centro_id, :integer
      t.column :codigo, :string
      t.column :inicio_clases, :date
      t.column :fin_eval1, :date
      t.column :fin_eval2, :date
      t.column :fin_eval3, :date
    end
  end

  def self.down
    drop_table :cursos
  end
end
