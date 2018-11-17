class CreatePases < ActiveRecord::Migration
  def self.up
    create_table :pases do |t|
      t.column "nombre", :string
      t.column "creado_en", :datetime
    end
  end

  def self.down
    drop_table :pases
  end
end
