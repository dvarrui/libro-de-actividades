class AddFotoColumna < ActiveRecord::Migration
  def self.up
    add_column "fotos", "fecha_creacion", :datetime
    add_column "fotos", "thumbnail", :string
    add_column "fotos", "descripcion", :string
  
    Foto.find(:all).each do |f|
      f.update_attribute :fecha_creacion, Time.now
      f.update_attribute :thumbnail, f.fichero.gsub('.','_m')
    end
  end

  def self.down
    remove_column "fotos", "fecha_creacion"
    remove_column "fotos", "thumbnail"
    remove_column "fotos", "descripcion"
  end
end
