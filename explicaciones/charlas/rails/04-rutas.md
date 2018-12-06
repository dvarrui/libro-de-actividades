
# Rails

![](images/laboratorio.png)

# ¡Hola!

Lo primero es saludar y presentarse... ¿verdad?

* Poner en un Web Browser el URL `camaleon:3000/hello`
(camaleon es el nombre de mi máquina).

```
Routing Error
No route matches [GET] "/hello"

Rails.root: /home/david/proy/repos/curso1819-david/messenger
```

Esto es porque la ruta `/hello` no está definida.

---

# Las rutas

Los métodos HTTP son: POST, PUT, PATCH y GET. Cada método genera un evento que puede ser enrutado (rutas) a diferentes sitios.

* `rails g`, ver todos los generadores (Hay muchos).
```
messenger> vdir app/controllers/
application_controller.rb
concerns
```

> OJO: Mejor usar siempre minúsculas en los comandos.

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
* Probamos. ¿Te acuerdas? URL `camaleon:3000/hola`

![](images/04-route-hello.png)

> Para trabajar con Rails podemos usar el editor de texto que queramos. Las características de Ruby hacen que los IDE no sean de gran ayuda. Ruby es tan expresivo que basta con escribir lo que necesitas de forma casi natural.

---

# Resumen de órdenes

| Comando                 | Descripción                 |
| ----------------------- | --------------------------- |
| rails -v                | Muestra la version de Rails |
| rails -h                | Muestra la ayuda        |
| rails new NAME          | Crear un nuevo proyecto |
| rails db:drop           | Elimina la BBDD |
| rails db:create         | Crear la BBDD |
| rails db:migrate        | Migración de la BBDD |
| rails s, rails server   | Iniciar la aplicación |
| rails g, rails generate | Muestra los generadores |
| rails g controller NAME | Generar un controlador |
| rails d, rails destroy  | Muestra los destructores |
| rails d controller NAME | Destruye un controlador |
