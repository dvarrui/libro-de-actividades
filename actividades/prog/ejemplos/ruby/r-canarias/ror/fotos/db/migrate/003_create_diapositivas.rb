class CreateDiapositivas < ActiveRecord::Migration
  def self.up
    create_table :diapositivas do |t|
      t.column "posicion", :integer
      t.column "foto_id", :integer
      t.column "pase_id", :integer
    end
  end

  def self.down
    drop_table :diapositivas
  end
end
