class CreateFestivos < ActiveRecord::Migration
  def self.up
    create_table :festivos do |t|
      t.column "curso_id", :integer
      t.column "fecha_inicio", :datetime
      t.column "fecha_fin", :datetime
      t.column "descripcion", :string
    end
  end

  def self.down
    drop_table :festivos
  end
end
