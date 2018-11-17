class CreateMatriculas < ActiveRecord::Migration
  def self.up
    create_table :matriculas do |t|
      t.integer :grupo_id
      t.integer :alumno_id
      t.integer :tutor_id
      t.boolean :baja
      t.date :fecha_baja
      t.integer :nota_trim1
      t.integer :nota_trim2
      t.integer :nota_trim3
      t.integer :nota_final
      t.boolean :evaluado_trim1
      t.boolean :evaluado_trim2
      t.boolean :evaluado_trim3
      t.boolean :evaluado_final
      t.text :boletin_trim1
      t.text :boletin_trim2
      t.text :boletin_trim3

      t.timestamps
    end
  end

  def self.down
    drop_table :matriculas
  end
end
