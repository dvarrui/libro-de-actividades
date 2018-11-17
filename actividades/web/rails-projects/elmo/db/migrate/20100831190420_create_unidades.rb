class CreateUnidades < ActiveRecord::Migration
  def self.up
    create_table :unidades do |t|
      t.integer :asignatura_id
      t.integer :numero
      t.string :codigo
      t.string :nombre
      t.integer :trim

    end
  end

  def self.down
    drop_table :unidades
  end
end
