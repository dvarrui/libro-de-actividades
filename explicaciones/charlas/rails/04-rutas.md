
# RAILSINGER-Z

![](images/laboratorio.png)

[Volver](README.md)

---

# Hola Mundo!

Lo primero es saludar y presentarse... ¿verdad?

* Poner en un Web Browser el URL `camaleon:3000/hello`
_(camaleon es el nombre de mi máquina)_.

```
Routing Error
No route matches [GET] "/hello"

Rails.root: .../messenger
```

Esto es porque la ruta `/hello` no está definida.

---

# Las rutas

Los métodos HTTP son: POST, PUT, PATCH y GET. Cada método genera un evento que puede ser enrutado (rutas) a diferentes sitios (controlador#método).

> OJO: Mejor usar siempre minúsculas en los comandos.

* `rails g`, ver todos los generadores (Hay muchos).
* Comprobamos que por ahora sólo tenemos estos ficheros en controladores:

```
messenger> vdir app/controllers/
application_controller.rb
concerns
```

* `rails g controller welcome`, para generar un nuevo controlador y sus ficheros asociados.

```
messenger> rails g controller welcome
Running via Spring preloader in process 6387

    create  app/controllers/welcome_controller.rb
    invoke  erb
    create    app/views/welcome
    invoke  test_unit
    create    test/controllers/welcome_controller_test.rb
    invoke  helper
    create    app/helpers/welcome_helper.rb
    invoke    test_unit
    invoke  assets
    invoke    coffee
    create      app/assets/javascripts/welcome.coffee
    invoke    scss
    create      app/assets/stylesheets/welcome.scss
messenger>
```

* Editar `app/controllers/welcome_controller.rb` y añadir lo siguiente:

```
  def greet
  end
```

* Crear `app/views/welcome/greet.html.erb` y añadir lo siguiente:

```
<p>Hello! How are you?</p>
```

* Añadir ruta a `config/routes.rb`. Esto es, añadir la linea
de ruta `get '/hello', to: 'welcome#greet'`

```
get     => indica el método HTML.
/hello  => es la ruta en el URL.
welcome => es el controlador.
greet   => es un método del controlador.
```
* Probamos. ¿Te acuerdas? URL `camaleon:3000/hello`

![](images/04-route-hello.png)

> Para trabajar con Rails podemos usar el editor de texto que queramos. Las características de Ruby hacen que los IDE no sean de gran ayuda. Ruby es tan expresivo que basta con escribir lo que necesitas de forma casi natural.

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
