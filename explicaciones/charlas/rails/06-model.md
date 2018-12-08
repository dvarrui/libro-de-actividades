
# Rails

![](images/nave.png)

[Volver](README.md)

---

# sqlite 3 (Estado inicial)

* Vamos a echar un vistazo a la base de datos:
```
messanger> sqlite3 db/development.sqlite3
SQLite version 3.25.2 2018-09-25 19:08:10
Enter ".help" for usage hints.

sqlite> .databases
main: .../messenger/db/development.sqlite3

sqlite> .tables
ar_internal_metadata  schema_migrations   
sqlite>

sqlite> .schema
CREATE TABLE IF NOT EXISTS "schema_migrations" ("version" varchar NOT NULL PRIMARY KEY);
CREATE TABLE IF NOT EXISTS "ar_internal_metadata" ("key" varchar NOT NULL PRIMARY KEY, "value" varchar, "created_at" datetime NOT NULL, "updated_at" datetime NOT NULL);

sqlite> .quit
```
* Estamos en un estado inicial... no hay nada.

---

# Modelo (Es la M del MVC)

> Recordemos que estamos dentro de un MVC todo POO.

Para la capa de modelos se usa `ActiveRecord`.
Es un ORM (Object Relational Mapping) muy potente.
De esta forma no interactuamos con la base de datos, sólo con el ORM. Esto es, con `ActiveRecord`.

> * Convención vs configuración.
> * Usar el inglés, ¡por favor!

Convenciones de los Modelos:
* Model/Class: Singular Camel Case
* Table/Schema: Plural con underscores

| Model/Class | Table/Schema |
| ----------- | ------------ |
| Tag         | tags         |
| Message     | messages     |
| Profile     | profiles     |

Vamos a crear... los `tags`, por ejemplo. Hay varias formas de hacerlo.
Ahora vamos a usar los generadores de Rails.

```
messenger> rails g model Tag name:string

Running via Spring preloader in process 6796
      invoke  active_record
      create    db/migrate/20181206141757_create_tags.rb
      create    app/models/tag.rb
      invoke    test_unit
      create      test/models/tag_test.rb
      create      test/fixtures/tags.yml

messenger>
```

El directorio `db/migrate` no existía hasta ahora. Todos los cambios que se hagan en los Modelos (Y por tanto, en la base de datos que lo sustenta), se guardarán como "migraciones". Esto servirá para poder hacer cambios hacia adelante y/o hacia atrás en la estructura de forma sencilla. ¡Guau!

La idea es que cambiar... en cualquier momento del proceso de desarrollo no suponga un problema. Que sea todo natural y sencillo.

Veamos nuestro modelo `Tag`... ¡qué bonito ha quedado! (Todo son objetos)
Es un DSL (Domain Specific Language) escrito en Ruby para interactuar con la base de datos.

```
messenger> more db/migrate/20181206141757_create_tags.rb

class CreateTags < ActiveRecord::Migration[5.2]
  def change
    create_table :tags do |t|
      t.string :name

      t.timestamps
    end
  end
end
```

* Hay que aplicar el cambio en la base de datos. Esto es, hacer las migraciones pendientes. `rails db:migrate`.

```
messenger> rails db:migrate
== 20181206141757 CreateTags: migrating =======================================
-- create_table(:tags)
   -> 0.0013s
== 20181206141757 CreateTags: migrated (0.0015s) ==============================
```

* Para comprobar o que ha ocurrido en la base de datos, vamos a sqlite3:
```
messenger> sqlite3 db/development.sqlite3
SQLite version 3.25.2 2018-09-25 19:08:10
Enter ".help" for usage hints.
sqlite> .tables
ar_internal_metadata  schema_migrations     tags                
sqlite> select * from schema_migrations;
20181206141757

sqlite> .schema tags
CREATE TABLE IF NOT EXISTS "tags" ("id" integer PRIMARY KEY AUTOINCREMENT NOT NULL, "name" varchar, "created_at" datetime NOT NULL, "updated_at" datetime NOT NULL);

sqlite>
```

* Revisar los campos que se han creado en la tabla `tags`:
    * id
    * name
    * created_at
    * updated_at

> Nuevamente "Convención vs configuración". La magia de verdad no existe... pero impresiona cuando ves el espectáculo.

* Además tenemos la herramienta `rails dbconsole` para entrar/conectar con el gestor de bases de datos, sin pensar qué base de datos tenemos instalada:
```
messenger> rails dbconsole
SQLite version 3.25.2 2018-09-25 19:08:10
Enter ".help" for usage hints.
sqlite> .tables
ar_internal_metadata  schema_migrations     tags                
sqlite> .schema tags
CREATE TABLE IF NOT EXISTS "tags" ("id" integer PRIMARY KEY AUTOINCREMENT NOT NULL, "name" varchar, "created_at" datetime NOT NULL, "updated_at" datetime NOT NULL);
sqlite>
```

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
