
# Rails

* Empresas que usan Rails: GitHub, GitLab, etc.
* Característiscas:
    * **DRY**: Don't Repeat Yourself
    * **CoC**: Convention over Configuration
* Filosofía Ruby => Magia vs Filosofía Python => Explicitar lo que se hace.
* Arquitectura MVC.

## Instalar Ruby and Rails

* Para instalarlo consultar installrails.com.
* `ruby -v`, consultar la versión de ruby.
* `rails -v`, consultar la version de rails.
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
    * `rails db:drop db:create db:migrate`, crear la base de datos...
    * Tendremos dos entornos de trabajo: develoment, testing.
    * `rails s`, iniciar la aplicación.
    * Abrir navegador con URL `http://localhost:3000`

---

# Las rutas
