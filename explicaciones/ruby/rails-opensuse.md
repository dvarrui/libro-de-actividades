
# Instalar rails en OpenSUSE

> Enlaces de interés:
>
> * [How To Install Ruby 2.4.2 with rbenv on OpenSUSE Leap 42.2](https://www.youtube.com/watch?v=OFF5CwP9uDk)
> * [How To Install Ruby on Rails with rbenv And Create a Test Application on OpenSUSE Leap 42.2](https://www.youtube.com/watch?v=2ZbL8Vl6i38)


## Instalar rbenv y Ruby

Proceso para instalar Ruby/rbenv en OpenSUSE.

Con el usuario root:
* `lsb_release -a`
* `zypper -n install automake gdbm-devel libffi-devel libyaml-devel libopenssl-devel ncurses-devel readline-devel zlib-devel sqlite3-devel nodejs10 git`
* `zypper install -t pattern devel_C_C++`

Con nuestro usuario:
* `git clone https://github.com/rbenv/rbenv.git ~/.rbenv`
* `echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bashrc`
* `echo 'eval "$(rbenv init -)"' >> ~/.bashrc`
* `exec $SHELL`
* `git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build`
* `rbenv install -l`, list all available versions.
* `rbenv install 2.5.2`
* `rbenv global 2.5.2`
* `rbenv versions`
* `sudo gem install bundler`
* `rbenv rehash`
* `ruby -v`
* `sudo gem install rails`
* `rails -v`

## Comprobamos

* `rails new demo`
* `cd demo`
* `demo> rake db:create`, Create databases 'db/development.sqlite3' and 'db/test.sqlite3'
* `rails server --binding=127.0.0.1`
* `http://127.0.0.1:3000`

---

# ANEXO

## rbenv

`rbenv` es una herramienta que proporciona entornos virtuales para ruby.
De esta forma pueden convivir distintas versiones de ruby en nuestro sistema.

`rbenv` intercepta los comandos de ruby usando ejecutables `shim` inyectados en las rutas del PATH. `rbenv` determinaa la versión de Ruby especificada por la aplicación y pasa los comandos a la instalación de Ruby adecuada.

Enlaces de interés:
* [https://github.com/rbenv/rbenv](https://github.com/rbenv/rbenv)
