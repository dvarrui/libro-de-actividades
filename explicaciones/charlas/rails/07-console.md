
# Rails

![](images/mazinger-y-afrodita.png)

# console

> Veamos el espectáculo entre bambalinas...

```
messenger> rails console
Running via Spring preloader in process 4221
Loading development environment (Rails 5.2.2)
irb(main):001:0> a = Tag.all
  Tag Load (0.8ms)  SELECT  "tags".* FROM "tags" LIMIT ?  [["LIMIT", 11]]
=> #<ActiveRecord::Relation []>
irb(main):002:0> a.class
=> Tag::ActiveRecord_Relation
irb(main):003:0>

```
---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
