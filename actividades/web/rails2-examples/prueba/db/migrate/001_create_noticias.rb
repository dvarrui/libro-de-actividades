class CreateNoticias < ActiveRecord::Migration
  def self.up
    create_table :noticias do |t|
      # t.column :name, :string
      t.column :titulo, :string
      t.column :contenido, :text
    end
  end

  def self.down
    drop_table :noticias
  end
end
