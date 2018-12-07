
# Rails

![](images/mazinger-y-afrodita.png)

# Arma secreta de Railsinger-Z: ActiveRecord

Ahora tendríamos que rellenar la tabla `tags` con contenido. Pero no lo vamos a hacer con SQL... debemos montar algo en la aplicación web para hacerlo:
* CRUD: Create-Read-Update-Delete functions.
* ORM:Object Relational Mapping (ActiveRecord)

El camino que hemos seguido hasta ahora es:
1. Crear las bases de datos.
1. Crear rutas.
1. Generar controladores.
1. Generar modelos.
1. Crear algún registro.
1. Ahora tocan las vistas.

---

# Veamos el espectáculo entre bambalinas...

* Enlace de interés [How to use the Rails console](https://www.youtube.com/watch?v=CDSRGdH7Lnk)

```
messenger> rails console
Running via Spring preloader in process 4948
Loading development environment (Rails 5.2.2)
irb(main):001:0> Tag.all
  Tag Load (0.7ms)  SELECT  "tags".* FROM "tags" LIMIT ?  [["LIMIT", 11]]
=> #<ActiveRecord::Relation []>

irb(main):002:0> a = Tag.new
=> #<Tag id: nil, name: nil, created_at: nil, updated_at: nil>
irb(main):003:0> a.class
=> Tag(id: integer, name: string, created_at: datetime, updated_at: datetime)
irb(main):004:0> a.name
=> nil
irb(main):005:0> a.name ="David"
=> "David"

irb(main):006:0> a.save
   (0.2ms)  begin transaction
  Tag Create (0.9ms)  INSERT INTO "tags" ("name", "created_at", "updated_at") VALUES (?, ?, ?)  [["name", "David"], ["created_at", "2018-12-07 04:51:50.046053"], ["updated_at", "2018-12-07 04:51:50.046053"]]
   (386.6ms)  commit transaction
=> true

irb(main):007:0> b = Tag.all
  Tag Load (0.3ms)  SELECT  "tags".* FROM "tags" LIMIT ?  [["LIMIT", 11]]
=> #<ActiveRecord::Relation [#<Tag id: 1, name: "David", created_at: "2018-12-07 04:51:50", updated_at: "2018-12-07 04:51:50">]>

irb(main):008:0> b.class
=> Tag::ActiveRecord_Relation
irb(main):009:0> b[0]
  Tag Load (0.5ms)  SELECT "tags".* FROM "tags"
=> #<Tag id: 1, name: "David", created_at: "2018-12-07 04:51:50", updated_at: "2018-12-07 04:51:50">
```

Vamos creando más Tags...

```
rb(main):015:0> Tag.all
  Tag Load (0.6ms)  SELECT  "tags".* FROM "tags" LIMIT ?  [["LIMIT", 11]]
=> #<ActiveRecord::Relation [#<Tag id: 1, name: "David", created_at: "2018-12-07 04:51:50", updated_at: "2018-12-07 04:51:50">, #<Tag id: 2, name: "CFGS", created_at: "2018-12-07 05:06:10", updated_at: "2018-12-07 05:06:10">, #<Tag id: 3, name: "asir", created_at: "2018-12-07 05:06:25", updated_at: "2018-12-07 05:06:25">, #<Tag id: 4, name: "daw", created_at: "2018-12-07 05:06:37", updated_at: "2018-12-07 05:06:37">]>
irb(main):016:0> Tag.all.count
   (0.4ms)  SELECT COUNT(*) FROM "tags"
=> 4
```

```
irb(main):020:0> Tag.last
  Tag Load (0.5ms)  SELECT  "tags".* FROM "tags" ORDER BY "tags"."id" DESC LIMIT ?  [["LIMIT", 1]]
=> #<Tag id: 4, name: "daw", created_at: "2018-12-07 05:06:37", updated_at: "2018-12-07 05:06:37">
irb(main):021:0> Tag.last.name
  Tag Load (0.3ms)  SELECT  "tags".* FROM "tags" ORDER BY "tags"."id" DESC LIMIT ?  [["LIMIT", 1]]
=> "daw"
irb(main):022:0> Tag.all.limit(2)
  Tag Load (0.4ms)  SELECT  "tags".* FROM "tags" LIMIT ?  [["LIMIT", 2]]
=> #<ActiveRecord::Relation [#<Tag id: 1, name: "David", created_at: "2018-12-07 04:51:50", updated_at: "2018-12-07 04:51:50">, #<Tag id: 2, name: "CFGS", created_at: "2018-12-07 05:06:10", updated_at: "2018-12-07 05:06:10">]>
irb(main):023:0>
```

Más métodos:
* Tag.find_by_name("CFGS")
* Tag.where( name: "CFGS")
* Tag.last
* Tag.first
* Tag.all.limit(2)

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
