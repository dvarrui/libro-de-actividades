class CreateProgramaciones < ActiveRecord::Migration
  def self.up
    create_table :programaciones do |t|
      t.column :curso_id, :integer
      t.column :modulo_id, :integer
      t.column :profesor_id, :integer
      t.column :aula_id, :integer
      t.column :etiqueta, :string
      t.column :url, :string
      t.column :modo_evaluar, :string
    end
  end

  def self.down
    drop_table :programaciones
  end
end
