class CreateNotas < ActiveRecord::Migration
  def self.up
    create_table :notas do |t|
      t.integer :tarea_id
      t.integer :matricula_id
      t.integer :valor_n
      t.integer :valor_r
      t.boolean :evaluado

      t.timestamps
    end
  end

  def self.down
    drop_table :notas
  end
end
