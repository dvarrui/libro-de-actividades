class CreateFotos < ActiveRecord::Migration
  def self.up
    create_table :fotos do |t|
      t.column "fichero", :string
    end
  end

  def self.down
    drop_table :fotos
  end
end
