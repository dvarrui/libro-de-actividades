class CreateFaltas < ActiveRecord::Migration
  def self.up
    create_table :faltas do |t|
      t.integer :matricula_id
      t.integer :numdia
      t.date :fecha
      t.string :valor

      t.timestamps
    end
  end

  def self.down
    drop_table :faltas
  end
end
