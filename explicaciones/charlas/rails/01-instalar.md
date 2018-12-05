
# Rails

* Empresas que usan Rails: GitHub, GitLab, etc.
* Característiscas:
    * **DRY**: Don't Repeat Yourself
    * **CoC**: Convention over Configuration
* Filosofía Ruby => Magia vs Filosofía Python => Explicitar lo que se hace.
* Arquitectura MVC.

## Instalar Ruby and Rails

* `ruby -v`, consultar la versión de ruby.
* Se recomienda usar entornos virtuales (rbenv, rvm, etc.)
* Para instalar rails:
    * `gem install rails`
    * o consultar installrails.com
* `rails -v`, consultar la version de rails.
* Instalar la BBDD:
    * `zypper in sqlite3 sqlite3-devel`
    * `zypper install gcc gcc-devel gcc8-c++ `
    * `cd /usr/bin; ln -s gcc g++`
    * `gem install mini_racer`

## Mi primer proyecto

* Crear un nuevo proyecto llamado demo:
    * `rails new demo` (usará base de datos sqlite)
    * `rails new demo -d=mysql` (usará base de datos mysql)
    * `rails new demo --database=postgresql` (usará base de datos PostGreSQL.)
* `tree demo`, ver las carpetas/ficheros que se han creado:
    * `demo/app/models`
    * `demo/app/views`
    * `demo/app/controllers`
    * `demo/db`, esquema de la base de datos.
    * `demo/Gemfile`, fichero de gemas (librerías de ruby)
* `cd demo`
    * `rails db:drop`, elimina las base de datos.
    * `rails db:create`, crear la base de datos.    
    * `rails db:migrate`, hace una modificación/migración del esquema de la base de datos.
    * `rails db:drop db:create db:migrate`, ¡todo en uno!

> NOTA: Tendremos dos entornos de trabajo: develoment, testing.

* `rails s`, iniciar la aplicación. Podremos ver las peticiones que se produzcan a nuestra aplicación directamente en el terminal.
* Abrir navegador con URL `http://localhost:3000`

---

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
