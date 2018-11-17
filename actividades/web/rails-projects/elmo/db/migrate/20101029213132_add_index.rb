class AddIndex < ActiveRecord::Migration
  def self.up
    add_index(:cursos,:codigo)
    add_index(:alumnos,:nombre)
    add_index(:alumnos,:apellidos)
    add_index(:alumnos,:numexp)
    add_index(:tutores,:nombre)
    add_index(:tutores,:apellidos)
    add_index(:tutores,:baja)
    add_index(:asignaturas,:codigo)
    add_index(:asignaturas,:curso_id)
    add_index(:grupos,:asignatura_id)
    add_index(:matriculas,:alumno_id)
    add_index(:matriculas,:grupo_id)
    add_index(:faltas, :numdia)
    add_index(:faltas, :matricula_id)
    add_index(:notas, :matricula_id)
  end

  def self.down
    remove_index(:cursos, :codigo)
    remove_index(:alumnos, :nombre)
    remove_index(:alumnos, :apellidos)
    remove_index(:alumnos, :numexp)
    remove_index(:tutores, :nombre)
    remove_index(:tutores, :apellidos)
    remove_index(:tutores, :baja)
    remove_index(:asignaturas, :codigo)
    remove_index(:asignaturas, :curso_id)
    remove_index(:grupos, :asignatura_id)
    remove_index(:matriculas, :alumno_id)
    remove_index(:matriculas, :grupo_id)
    remove_index(:faltas, :numdia)
    remove_index(:faltas, :matricula_id)
    remove_index(:notas, :matricula_id)
  end
end
