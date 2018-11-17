class CreatePesos < ActiveRecord::Migration
  def self.up
    create_table :pesos do |t|
      t.integer :asignatura_id
      t.string :codigo
      t.string :nombre
      t.integer :valor
    end
  end

  def self.down
    drop_table :pesos
  end
end
