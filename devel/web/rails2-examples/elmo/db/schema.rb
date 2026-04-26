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

ActiveRecord::Schema.define(:version => 20101029213132) do

  create_table "alumnos", :force => true do |t|
    t.integer  "numexp"
    t.string   "nombre"
    t.string   "apellidos"
    t.string   "tlf_fijo"
    t.string   "tlf_movil"
    t.string   "email"
    t.date     "fecha_nac"
    t.text     "observaciones"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "alumnos", ["numexp"], :name => "index_alumnos_on_numexp"
  add_index "alumnos", ["apellidos"], :name => "index_alumnos_on_apellidos"
  add_index "alumnos", ["nombre"], :name => "index_alumnos_on_nombre"

  create_table "asignaturas", :force => true do |t|
    t.integer "curso_id"
    t.string  "codigo"
    t.string  "nombre"
  end

  add_index "asignaturas", ["curso_id"], :name => "index_asignaturas_on_curso_id"
  add_index "asignaturas", ["codigo"], :name => "index_asignaturas_on_codigo"

  create_table "cursos", :force => true do |t|
    t.string  "codigo"
    t.boolean "fin_trim1"
    t.boolean "fin_trim2"
    t.boolean "fin_trim3"
    t.date    "fecha_fin_trim1"
    t.date    "fecha_fin_trim2"
    t.date    "fecha_fin_trim3"
  end

  add_index "cursos", ["codigo"], :name => "index_cursos_on_codigo"

  create_table "faltas", :force => true do |t|
    t.integer  "matricula_id"
    t.integer  "numdia"
    t.date     "fecha"
    t.string   "valor"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "faltas", ["matricula_id"], :name => "index_faltas_on_matricula_id"
  add_index "faltas", ["numdia"], :name => "index_faltas_on_numdia"

  create_table "grupos", :force => true do |t|
    t.integer "curso_id"
    t.integer "asignatura_id"
    t.string  "letra"
    t.integer "dia_semana"
    t.time    "hora_inicio"
    t.time    "hora_fin"
  end

  add_index "grupos", ["asignatura_id"], :name => "index_grupos_on_asignatura_id"

  create_table "matriculas", :force => true do |t|
    t.integer  "grupo_id"
    t.integer  "alumno_id"
    t.integer  "tutor_id"
    t.boolean  "baja"
    t.date     "fecha_baja"
    t.integer  "nota_trim1"
    t.integer  "nota_trim2"
    t.integer  "nota_trim3"
    t.integer  "nota_final"
    t.boolean  "evaluado_trim1"
    t.boolean  "evaluado_trim2"
    t.boolean  "evaluado_trim3"
    t.boolean  "evaluado_final"
    t.text     "boletin_trim1"
    t.text     "boletin_trim2"
    t.text     "boletin_trim3"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "matriculas", ["grupo_id"], :name => "index_matriculas_on_grupo_id"
  add_index "matriculas", ["alumno_id"], :name => "index_matriculas_on_alumno_id"

  create_table "notas", :force => true do |t|
    t.integer  "tarea_id"
    t.integer  "matricula_id"
    t.integer  "valor_n"
    t.integer  "valor_r"
    t.boolean  "evaluado"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "notas", ["matricula_id"], :name => "index_notas_on_matricula_id"

  create_table "parametros", :force => true do |t|
    t.string "variable"
    t.string "valor"
  end

  create_table "pesos", :force => true do |t|
    t.integer "asignatura_id"
    t.string  "codigo"
    t.string  "nombre"
    t.integer "valor"
  end

  create_table "tareas", :force => true do |t|
    t.integer  "unidad_id"
    t.integer  "peso_id"
    t.string   "codigo"
    t.string   "nombre"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "tutores", :force => true do |t|
    t.string  "nombre"
    t.string  "apellidos"
    t.string  "email"
    t.boolean "baja"
    t.string  "especialidad"
  end

  add_index "tutores", ["baja"], :name => "index_tutores_on_baja"
  add_index "tutores", ["apellidos"], :name => "index_tutores_on_apellidos"
  add_index "tutores", ["nombre"], :name => "index_tutores_on_nombre"

  create_table "unidades", :force => true do |t|
    t.integer "asignatura_id"
    t.integer "numero"
    t.string  "codigo"
    t.string  "nombre"
    t.integer "trim"
  end

end
