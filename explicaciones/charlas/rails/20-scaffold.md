
# RAILSINGER-Z

![](images/super-brazo.png)

[Volver](README.md)

# Scaffold

> Enlaces de interés:
> * [Generating a Scaffold](http://www.xyzpub.com/en/ruby-on-rails/3.2/scaffold_anlegen.html)

Hasta ahora hemos visto cómo ir construyendo nuestra aplicación con la estructura MVC. De alguna manera, a base de hacer y hacer podríamos ver que hay un patrón que se repite una y otra vez... ¿y sí automatizamos ese patrón de trabajo?...

* `rails new flipante`, crea un nuevo proyecto.
* `rails generate scaffold product name 'price:decimal{7,2}'`, crea el "andamiaje" para:
    * Modelo `product`
    * Atributo `name` (por defecto string)
    * Atributo `price` de tipo decimal.
    * Con su controlador y..
    * Con sus vistas CRUD. Un completo!!!

```
Running via Spring preloader in process 14240
      invoke  active_record
      create    db/migrate/20181218001944_create_products.rb
      create    app/models/product.rb
      invoke  resource_route
       route    resources :products
      invoke  scaffold_controller
      create    app/controllers/products_controller.rb
      invoke    erb
      create      app/views/products
      create      app/views/products/index.html.erb
      create      app/views/products/edit.html.erb
      create      app/views/products/show.html.erb
      create      app/views/products/new.html.erb
      create      app/views/products/_form.html.erb
      invoke  assets
      invoke    coffee
      create      app/assets/javascripts/products.coffee
      invoke    scss
      create      app/assets/stylesheets/products.scss
      invoke  scss
      create    app/assets/stylesheets/scaffolds.scss
```

```
flipante> more db/migrate/20181218001944_create_products.rb
class CreateProducts < ActiveRecord::Migration[5.2]
  def change
    create_table :products do |t|
      t.string :name
      t.decimal :price, precision: 7, scale: 2

      t.timestamps
    end
  end
end
```

* `rake db:migrate`

```
flipante> rake db:migrate
== 20181218001944 CreateProducts: migrating ===================================
-- create_table(:products)
   -> 0.0018s
== 20181218001944 CreateProducts: migrated (0.0020s) ==========================
```

---

# Resumen Scaffold

* `rails new flipante`
* `rails generate scaffold product name 'price:decimal{7,2}'`
* `rake db:migrate`
* `rails server`
* URL => "http://localhost:3000/products"

¡TA-CHAN!

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
