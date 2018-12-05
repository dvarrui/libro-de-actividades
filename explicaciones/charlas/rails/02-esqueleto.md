
# Rails

# Solucionar un problema

* Hace falta un programa/utilidad para donde tengamos `usuarios`.
* Cada usuario tendrá asociado a su perfil un conjunto de `tags`.
* Cada usuario podrá enviar mensajes con asunto y cuerpo pero sin remite. En su lugar se indican una serie de `tags`.
* Cada usuario podrá leer/consultar sólo los mensajes que tengan alguna coincidencia de tags con su perfil.

Llamaremos a la aplicación Messenger.

---

# Crear el esqueleto

> * `rails new demo` (usará base de datos sqlite)
> * `rails new demo -d=mysql` (usará base de datos mysql)
> * `rails new demo --database=postgresql` (usará base de datos PostGreSQL.)

* `rails new messenger`, crear un nuevo proyecto llamado messenger.
* `tree messenger`, ver las carpetas/ficheros que se han creado:
    * `messenger/app/models`
    * `messenger/app/views`
    * `messenger/app/controllers`
    * `messenger/db`, esquema de la base de datos.

---

# Bases de datos

> * `rails db:drop`, elimina las base de datos.
> * `rails db:migrate`, hace una modificación/migración del esquema de la base de datos.
> * `rails db:drop db:create db:migrate`, ¡todo en uno!

* `cd messenger`
* `rails db:create`, crear la base de datos.    
```
messenger> rails db:create
Created database 'db/development.sqlite3'
Created database 'db/test.sqlite3'
```
> NOTA: Tendremos dos entornos de trabajo: develoment y test.

---

# Servidor

* `rails s`, iniciar el servidor de la aplicación.
    * Podremos ver las peticiones que se produzcan a nuestra aplicación directamente en el terminal.
* Abrir navegador con URL `http://localhost:3000`

---

# Resumen

* `rails new messenger`, crear un nuevo proyecto llamado messenger.
* `cd messenger`
* `rails db:create`, crear la base de datos.    
* `rails s`, iniciar el servidor de la aplicación.
* Abrir navegador con URL `http://localhost:3000`
