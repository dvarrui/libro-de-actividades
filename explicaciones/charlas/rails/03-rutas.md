
# Rails

# Las rutas

* Los métodos HTTP son: POST, PUT, PATCH y GET.
Cada método genera un evento que es enrutado (rutas)
a diferentes sitios.
* `config/routes.rb`, fichero de configuración de las rutas.
* Podemos usar el editor de texto que queramos. Las caracterísicas de Ruby hacen que los IDE no sean de gran ayuda. Ruby es tan expresivo que basta con escribir lo que necesitas.
* Ejemplo de ruta `get 'hello', to: 'Welcome#sgreet'`
    * `get`, indica el método HTML.
    * `hello`, es la ruta en el URL.
    * `Welcome`, es el controlador.
    * `greet`, es un método del controlador.
* `rails g`, ver todos los generadores.
* `rails g Welcome`, crear un nuevo controlador y sus ficheros asociados.
* `app/controllers/welcome_controller.rb`
```
  def greet
  end
```
* `app/views/welcome/greet.html.erb`
```
<p>Hello! How are you?</p>
```
---

# Resumen de órdenes

| Comando         | Descripción             |
| --------------- | ----------------------- |
| rails new demo  | Crear un nuevo proyecto |
| rails db:drop   | Elimina la BBDD |
| rails db:create | Crear la BBDD |
| rails db:migrate | Migración de la BBDD |
| rails s          | Iniciar la aplicación |
