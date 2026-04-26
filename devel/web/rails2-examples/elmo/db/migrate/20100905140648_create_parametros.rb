class CreateParametros < ActiveRecord::Migration
  def self.up
    create_table :parametros do |t|
      t.string :variable
      t.string :valor
    end
    
    p = Parametro.new
    p.variable="curso_id"
    p.valor="1"
    p.save
    
  end

  def self.down
    drop_table :parametros
  end
end
