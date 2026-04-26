class CreateClases < ActiveRecord::Migration
  def self.up
    create_table :clases do |t|
       t.column "curso_id", :integer
       t.column "aula_id", :integer
       t.column "profesor_id", :integer
       t.column "modulo_id", :integer
       t.column "grupo_id", :integer
       t.column "diasemana_id", :integer
       t.column "hora_id", :integer
    end
  end

  def self.down
    drop_table :clases
  end
end
