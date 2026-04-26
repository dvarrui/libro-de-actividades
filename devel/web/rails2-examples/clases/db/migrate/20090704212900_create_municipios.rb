class CreateMunicipios < ActiveRecord::Migration
  def self.up
    create_table :municipios do |t|
      t.column :nombre, :string
      t.column :latitud, :float
      t.column :longitud, :float
    end
    
    Municipio.new do |r|
      r.nombre='Guancha, La'
      r.save
    end
    Municipio.new do |r|
      r.nombre='Icod de los Vinos'
      r.save
    end
    Municipio.new do |r|
      r.nombre='Orotava, La'
      r.latitud=28.394617
      r.longitud=-16.524153
      r.save
    end
    Municipio.new do |r|
      r.nombre='Puerto de la Cruz'
      r.latitud=28.418251
      r.longitud=-16.548078
      r.save
    end
    Municipio.new do |r|
      r.nombre='Realejos, Los'
      r.save
    end
    Municipio.new do |r|
      r.nombre='Santa Úrsula'
      r.save
    end
    Municipio.new do |r|
      r.nombre='Sauzal, El'
      r.save
    end
  end

  def self.down
    drop_table :municipios
  end
end
