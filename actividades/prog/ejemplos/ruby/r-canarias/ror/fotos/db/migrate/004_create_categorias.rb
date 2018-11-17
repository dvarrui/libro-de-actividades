class CreateCategorias < ActiveRecord::Migration
  def self.up
    create_table :categorias do |t|
      t.column "nombre", :string
      t.column "parent_id", :integer
    end
    create_table ("categorias_fotos", :id=>false) do |t|
      t.column "categoria_id", :integer
      t.column "foto_id", :integer
    end
    c = Categoria.new
    c.nombre = "all"
    c.save

    Foto.find(:all).each do |f|
      f.categorias << c
      f.save
    end
  end

  def self.down
    drop_table :categorias
    drop_table "categorias_fotos"
  end
end
