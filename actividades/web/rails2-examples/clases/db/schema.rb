# This file is auto-generated from the current state of the database. Instead of editing this file, 
# please use the migrations feature of Active Record to incrementally modify your database, and
# then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your database schema. If you need
# to create the application database on another system, you should be using db:schema:load, not running
# all the migrations from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20090714225623) do

  create_table "actividades", :force => true do |t|
    t.integer "unidad_id",    :limit => 11
    t.integer "peso_id",      :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.string  "url"
    t.date    "fecha_inicio"
    t.date    "fecha_fin"
    t.integer "valor_max",    :limit => 11
  end

  create_table "alumnos", :force => true do |t|
    t.string  "expediente"
    t.string  "dni"
    t.string  "apellidos"
    t.string  "nombre"
    t.date    "fecha_nac"
    t.boolean "sexo"
    t.string  "tlf_fijo"
    t.string  "tlf_movil"
    t.string  "email"
    t.integer "municipio_id",  :limit => 11
    t.string  "localidad"
    t.string  "domicilio"
    t.string  "cod_postal"
    t.float   "latitud"
    t.float   "longitud"
    t.text    "observaciones"
  end

  create_table "aulas", :force => true do |t|
    t.integer "centro_id", :limit => 11
    t.string  "codigo"
    t.boolean "activo"
  end

  create_table "centros", :force => true do |t|
    t.string  "codigo"
    t.string  "nombre"
    t.string  "cif"
    t.integer "municipio_id", :limit => 11
    t.string  "localidad"
    t.string  "domicilio"
    t.string  "cod_postal"
    t.float   "latitud"
    t.float   "longitud"
    t.string  "tlf_fijo"
    t.string  "tlf_movil"
    t.string  "fax"
    t.string  "email"
    t.string  "web"
    t.string  "horario"
    t.boolean "activo"
  end

  create_table "ciclos", :force => true do |t|
    t.integer "centro_id", :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.boolean "activo"
  end

  create_table "cursos", :force => true do |t|
    t.integer "centro_id",     :limit => 11
    t.string  "codigo"
    t.date    "inicio_clases"
    t.date    "fin_eval1"
    t.date    "fin_eval2"
    t.date    "fin_eval3"
  end

  create_table "departamentos", :force => true do |t|
    t.integer "centro_id", :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.boolean "activo"
  end

  create_table "empresas", :force => true do |t|
    t.integer "centro_id",            :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.string  "cif"
    t.integer "municipio_id",         :limit => 11
    t.string  "localidad"
    t.string  "domicilio"
    t.string  "cod_postal"
    t.float   "latitud"
    t.float   "longitud"
    t.string  "tlf_fijo"
    t.string  "tlf_movil"
    t.string  "fax"
    t.string  "email"
    t.string  "nif_representante"
    t.string  "nombre_representante"
    t.string  "cargo_representante"
    t.string  "nif_tutor"
    t.string  "nombre_tutor"
    t.string  "horario_empresa"
    t.string  "horario_practicas"
    t.text    "observaciones"
    t.boolean "activo"
  end

  create_table "festivos", :force => true do |t|
    t.integer "curso_id",     :limit => 11
    t.date    "fecha_inicio"
    t.date    "fecha_fin"
    t.string  "motivo"
  end

  create_table "horarios", :force => true do |t|
    t.integer "programacion_id", :limit => 11
    t.integer "num_dia",         :limit => 11
    t.integer "num_hora",        :limit => 11
  end

  create_table "modulos", :force => true do |t|
    t.integer "ciclo_id", :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.integer "curso",    :limit => 11
    t.boolean "activo"
  end

  create_table "municipios", :force => true do |t|
    t.string "nombre"
    t.float  "latitud"
    t.float  "longitud"
  end

  create_table "pesos", :force => true do |t|
    t.integer "programacion_id", :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.integer "valor",           :limit => 11
    t.integer "valor_min",       :limit => 11
  end

  create_table "profesores", :force => true do |t|
    t.integer "departamento_id", :limit => 11
    t.string  "dni"
    t.string  "apellidos"
    t.string  "nombre"
    t.string  "tlf_fijo"
    t.string  "tlf_movil"
    t.string  "email"
    t.boolean "activo"
  end

  create_table "programaciones", :force => true do |t|
    t.integer "curso_id",     :limit => 11
    t.integer "modulo_id",    :limit => 11
    t.integer "profesor_id",  :limit => 11
    t.integer "aula_id",      :limit => 11
    t.string  "etiqueta"
    t.string  "url"
    t.string  "modo_evaluar"
  end

  create_table "unidades", :force => true do |t|
    t.integer "programacion_id", :limit => 11
    t.integer "numero",          :limit => 11
    t.string  "codigo"
    t.string  "nombre"
    t.string  "url"
    t.integer "peso",            :limit => 11
  end

end
