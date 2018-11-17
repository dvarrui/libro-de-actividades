class CreateAsignaturas < ActiveRecord::Migration
  def self.up
    create_table :asignaturas do |t|
    	t.integer :curso_id
      t.string :codigo
      t.string :nombre
    end
  end

  def self.down
    drop_table :asignaturas
  end
end
