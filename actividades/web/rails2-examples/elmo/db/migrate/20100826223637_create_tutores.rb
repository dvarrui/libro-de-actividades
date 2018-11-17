class CreateTutores < ActiveRecord::Migration
  def self.up
    create_table :tutores do |t|
      t.string :nombre
      t.string :apellidos
      t.string :email
      t.boolean :baja

    end
  end

  def self.down
    drop_table :tutores
  end
end
