
# Rails

![](images/mazinger-y-afrodita.png)

# console

> Veamos el espectáculo entre bambalinas...

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

irb(main):010:0> quit
```


* Tag.find_by_name("CFGS")
* Tag.where( name: "CFGS")
* Tag.last
* Tag.first
* Tag.all.limit(2)

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
