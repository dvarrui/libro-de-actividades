class CreateCentros < ActiveRecord::Migration
  def self.up
    create_table :centros do |t|
      t.column :codigo, :string
      t.column :nombre, :string
      t.column :cif, :string
      t.column :municipio_id, :integer
      t.column :localidad, :string
      t.column :domicilio, :string
      t.column :cod_postal, :string
      t.column :latitud, :float
      t.column :longitud, :float
      t.column :tlf_fijo, :string
      t.column :tlf_movil, :string
      t.column :fax, :string
      t.column :email, :string
      t.column :web, :string
      t.column :horario, :string
      t.column :activo, :boolean
    end
    
    Centro.new do |r|
      r.codigo='Puerto de la Cruz'
      r.nombre='IES Puerto de la Cruz'
      r.cif='1'
      r.municipio_id=4
      r.localidad='Puerto de la Cruz'
      r.domicilio='Calle de las Cabezas'
      r.cod_postal='38400'
      r.tlf_fijo='922 380 112'
      r.web='www.arteinformatica.es'
      r.horario='h1=08:00, h2=09:00, h3=10:00, d3=11:00, h4=11:30, h5=12:30, h6=13:30'
      r.activo=true
      r.save
    end
    
    Centro.new do |r|
      r.codigo='Escuela de Música'
      r.nombre='Escuela de Música y Danza de la Villa de la Orotava'
      r.cif='2'
      r.municipio_id=3
      r.horario='h1=15:30, h2=16:30, h3=17:30, d3=18:30, h4=19:00, h5=20:00, h6=21:00'
      r.activo=true
      r.save
    end
  end

  def self.down
    drop_table :centros
  end
end
