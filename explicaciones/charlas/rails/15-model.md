
# RAILSINGER-Z

![](images/nave.png)

[Volver](README.md)

---

# Modelo para los mensajes

Vamos a crear modelos para los `messages`.

```
messenger> rails g model message title:string body:string
Running via Spring preloader in process 5922
      invoke  active_record
      create    db/migrate/20181209015709_create_messages.rb
      create    app/models/message.rb
      invoke    test_unit
      create      test/models/message_test.rb
      create      test/fixtures/messages.yml
messenger>
```

---

# Validators

```
class Message < ApplicationRecord
  validates :title, presence: true
end
```

```
class Tag < ApplicationRecord
  validates :name, presence: true
end
```

---

# Migraciones

* Tenemos una nueva migración.

```
messenger> more db/migrate/20181209015709_create_messages.rb
class CreateMessages < ActiveRecord::Migration[5.2]
  def change
    create_table :messages do |t|
      t.string :title
      t.string :body

      t.timestamps
    end
  end
end
```

* Hay que aplicar el cambio en la base de datos. Esto es, hacer las migraciones pendientes. `rails db:migrate`.

```
messenger> rails db:migrate
== 20181209015709 CreateMessages: migrating  =======================
-- create_table(:messages)
   -> 0.0013s
== 20181209015709 CreateMessages: migrated (0.0015s) ===============

```

* Para comprobar o que ha ocurrido en la base de datos `rails dbconsole`
(sqlite> .tables y sqlite> .schema messages)

```
messenger> rails dbconsole
SQLite version 3.25.2 2018-09-25 19:08:10
Enter ".help" for usage hints.

sqlite> .tables
  ar_internal_metadata  
  schema_migrations   
  messages              
  tags                

sqlite> .schema messages
CREATE TABLE IF NOT EXISTS "messages" (
  "id" integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  "title" varchar,
  "body" varchar,
  "created_at" datetime NOT NULL,
  "updated_at" datetime NOT NULL);

sqlite> .quit
```

* Revisar los campos que se han creado en la tabla `messages`.
* Crear algún mensaje de prueba con `rails console`.

```
messenger> rails console

Running via Spring preloader in process 6369
Loading development environment (Rails 5.2.2)

irb(main):001:0> m = Message.new
=> #<Message id: nil, title: nil, body: nil, created_at: nil, updated_at: nil>

irb(main):002:0> m.title = "First message"
=> "First message"

irb(main):003:0> m.body = "Hello Mazinger-Z!, How are you?"
=> "Hello Mazinger-Z!, How are you?"

irb(main):004:0> m.save
   (0.2ms)  begin transaction
  Message Create (0.4ms)  INSERT INTO "messages" (
    "title", "body", "created_at", "updated_at") VALUES (?, ?, ?, ?)
    [
      ["title", "First message"],
      ["body", "Hello Mazinger-Z!, How are you?"],
      ["created_at", "2018-12-09 02:05:17.154723"],
      ["updated_at", "2018-12-09 02:05:17.154723"]
    ]
   (128.7ms)  commit transaction
=> true

irb(main):005:0> Message.all
  Message Load (0.6ms)  SELECT  "messages".* FROM "messages" LIMIT ?  [["LIMIT", 11]]

=> #<ActiveRecord::Relation [
  #<Message id: 1,
    title: "First message",
    body: "Hello Mazinger-Z!, How are you?",
    created_at: "2018-12-09 02:05:17",
    updated_at: "2018-12-09 02:05:17">]>

irb(main):006:0>
```
* Ahora es el momento del... CRUD. Jajajaja.

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
