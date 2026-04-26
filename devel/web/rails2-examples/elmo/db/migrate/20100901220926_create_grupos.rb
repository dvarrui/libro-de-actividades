class CreateGrupos < ActiveRecord::Migration
  def self.up
    create_table :grupos do |t|
      t.integer :curso_id
      t.integer :asignatura_id
      t.string :letra
      t.integer :dia_semana
      t.time :hora_inicio
      t.time :hora_fin
    end
  end

  def self.down
    drop_table :grupos
  end
end
