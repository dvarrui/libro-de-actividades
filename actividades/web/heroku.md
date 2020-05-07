
```
Estado      : EN CONSTRUCCION!!!
Curso       : 201920
Area        : Aplicaciones Web, deploy, servicios
Descripción : Uso de la plataforma Heroku para desplegar aplicaciones web
Requisitos  :
```

# Introducción

Heroku es una plataforma para alojar aplicaciones en la nube. Heroku suporta oficialemente los siguientes lenguages: Node.js, Ruby, Java, PHP, Python, Go, Scala y Clojure.

> Enlaces de interés:
> * [getting started on Keroku](https://devcenter.heroku.com/start)

# 1. Instalar el cliente Heroku

* Crear una [cuenta en Heroku](https://signup.heroku.com/dc).
* Preparativos para un entorno Ruby:
    * `ruby -v` > 2.3.5
    * `gem install bundler`
* Tener instalado `git`.
* Como root ejecutar `curl https://cli-assets.heroku.com/install.sh | sh`. Esta acción ejecuta el instalador del "cliente Heroku".
* `heroku -v`, para consultar la versión instalada.
* `heroku login`, para abrir una sesió

# 2. Crear la aplicación

Para nuestro ejemplo, vamos a usar una aplicación de ejemplo que nos descargaremos de la siguiente forma:

```
git clone https://github.com/heroku/ruby-getting-started.git
cd ruby-getting-started
```

# 3. Desplegar la aplicación

Ya tenemos la aplicación y ahora queremos hacer el despliegue.

* `keroku create`, crea un repositorio en heroku.

```
> heroku create
Creating app... done, ⬢ arcane-river-01728
https://arcane-river-01728.herokuapp.com/ | https://git.heroku.com/arcane-river-01728.git
```

* `git push heroku master`, desplegar la aplicación en el repositorio remoto.
* `heroku open`, mostrar la aplicación.

# 4 [INFO] Más información

**Logs**
* Podemos usar el comando `heroku logs --tail`, para consultar los mensajes de log de nuestra aplicación.

**Procfile**
* `Procfile` es un fichero de texto en el directorio raíz de nuestra aplicación para definir el comando que se debe ejecutar para iniciar nuestra aplicación. En nuestro ejemplo será:

```
web: bundle exec puma -C config/puma.rb
```

**Dyno**
* Nuestra aplicación está corriendo en un contenedor ligero (dyno) podemos consultar la información con `heroku ps`.
Para tener más información sobre cómo dormir o potenciar el dyno
https://devcenter.heroku.com/articles/dyno-sleeping.
* Por defecto nuestra aplicación se despliega en un free dyno. Los free dynos se duermen después de media hora de inactividad.
* En los professional dynos se puede escalar la aplicación aumentando el número de dynos: `heroku ps:scale web=1`.

**Ejecutar en local**
* Instalar las dependencias en el equipo local `bundle install`.
* `bundle exec rake db:create db:migrate`
* `heroku local web`
* Abrir el navegador con el URL http://localhost:5000 para ver la aplicación corriendo en local.


---

# ANEXO

## Push local changes

https://devcenter.heroku.com/articles/getting-started-with-ruby#push-local-changes

In this step you’ll learn how to propagate a local change to the application through to Heroku. As an example, you’ll modify the application to add an additional dependency and the code to use it.

Modify Gemfile to include a dependency for the cowsay gem by including a line gem 'cowsay':

source 'https://rubygems.org'
ruby '>= 2.3.5', '< 2.7'

gem 'cowsay'

# Bundle edge Rails instead: gem 'rails', github: 'rails/rails'
gem 'rails', '5.2.3'
...

Modify app/views/welcome/index.erb so that it uses this gem, by changing the file so that its first few lines read as follows:

<pre>
<%= Cowsay.say("Hello", "Cow") %>
</pre>
...

Now test locally:

bundle install

heroku local

Visit your application at http://localhost:5000. You should see a cute ASCII picture displayed.

Now deploy this local change to Heroku.

Almost every deploy to Heroku follows this same pattern. First, add the modified files to the local git repository:

git add .

Now commit the changes to the repository:

git commit -m "Demo"

Now deploy, just as you did previously:

git push heroku master

Finally, check that everything is working:

heroku open
