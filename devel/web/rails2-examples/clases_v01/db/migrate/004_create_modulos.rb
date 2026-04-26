class CreateModulos < ActiveRecord::Migration
  def self.up
    create_table :modulos do |t|
      t.column "codigo", :string
      t.column "nombre", :string
      t.column "num_horas_total_boc", :integer
      t.column "num_horas_semanal_boc", :integer
    end
  end

  def self.down
    drop_table :modulos
  end
end
