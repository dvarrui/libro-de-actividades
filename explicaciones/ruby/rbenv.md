

# rbenv

`rbenv` es una herramienta que proporciona entornos virtuales para ruby.
De esta forma pueden convivir distintas versiones de ruby en nuestro sistema.

`rbenv` intercepta los comandos de ruby usando ejecutables `shim` inyectados en las rutas del PATH. `rbenv` determinaa la versión de Ruby especificada por la aplicación y pasa los comandos a la instalación de Ruby adecuada.

Enlaces de interés:
* [https://github.com/rbenv/rbenv](https://github.com/rbenv/rbenv)

# 1. Instalación de rbenv

* Agregar los shims al PATH. Los shims son los scripts que ejecutan las herramientas de CLI.

```
export PATH=$HOME/.rbenv/shims:$PATH
```

* `git clone https://github.com/rbenv/rbenv.git ~/.rbenv`, hacemos la instalación de rbenv

* Comprobamos:

```
david@camaleon:~> rbenv versions
* system (set by /home/david/.rbenv/version)
  2.2.2
```



    Optionally, try to compile dynamic bash extension to speed up rbenv. Don't worry if it fails; rbenv will still work normally:

    $ cd ~/.rbenv && src/configure && make -C src

    Add ~/.rbenv/bin to your $PATH for access to the rbenv command-line utility.

    $ echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bash_profile

    Ubuntu Desktop note: Modify your ~/.bashrc instead of ~/.bash_profile.

    Zsh note: Modify your ~/.zshrc file instead of ~/.bash_profile.

    Run ~/.rbenv/bin/rbenv init for shell-specific instructions on how to initialize rbenv to enable shims and autocompletion.

    Restart your shell so that PATH changes take effect. (Opening a new terminal tab will usually do it.) Now check if rbenv was set up:

    $ type rbenv
    #=> "rbenv is a function"

    (Optional) Install ruby-build, which provides the rbenv install command that simplifies the process of installing new Ruby versions.

Upgrading

If you've installed rbenv manually using git, you can upgrade your installation to the cutting-edge version at any time.

# 2. Probando rbenv

* Hay que indicar a rbenv que rubie vas a utilizar.

```
david@camaleon:~> rbenv global 2.2.2
david@camaleon:~> rbenv versions
  system (set by /home/david/.rbenv/version)
* 2.2.2
```

# 3. Instalar rails usando rbenv

Vamos a instalar rails con el ruby del sistema.
* IMPORTANTE: No instalar rails con sudo.

```
david@camaleon:~> gem install rails
Fetching: rails-4.2.4.gem (100%)
Successfully installed rails-4.2.4
Parsing documentation for rails-4.2.4
Installing ri documentation for rails-4.2.4
Done installing documentation for rails after 367 seconds
1 gem installed
```

* Comprobación de las gemas instaladas:

```
david@camaleon:~>
actionmailer (4.2.4)
actionpack (4.2.4)
actionview (4.2.4)
activejob (4.2.4)
activemodel (4.2.4)
activerecord (4.2.4)
activesupport (4.2.4)
arel (6.0.3)
bigdecimal (1.2.4)
builder (3.2.2, 3.0.4)
bundler (1.10.6)
crass (1.0.2)
erubis (2.7.0)
globalid (0.3.6)
i18n (0.7.0)
io-console (0.4.2)
json (1.8.1)
loofah (2.1.0.rc1)
mail (2.6.3)
mime-types (2.6.2)
mini_portile (0.7.0.rc4)
minitest (5.8.1, 4.7.5)
net-sftp (2.1.2)
net-ssh (3.0.1)
nokogiri (1.6.7.rc3)
psych (2.0.5)
rack (1.6.4)
rack-test (0.6.3)
rails (4.2.4)
rails-deprecated_sanitizer (1.0.3)
rails-dom-testing (1.0.7)
rails-html-sanitizer (1.0.2)
railties (4.2.4)
rake (10.1.0)
rdoc (4.1.0)
sprockets (3.4.0)
sprockets-rails (3.0.0.beta2)
test-unit (2.1.3.0)
thor (0.19.1)
thread_safe (0.3.5)
tzinfo (1.2.2)
```

##3. ¿Dónde está el comando rails?
Ejecuto `rbenv rehash`, pero sigue sin aparecer el comando de rails:

```
# Aqui te esta diciendo que no estas utilizando la version de Ruby que instalaste con Rbenv.
david@camaleon:~> rbenv versions
* system (set by /home/david/.rbenv/version)
  2.2.2

david@camaleon:~> whereis rails
rails:
david@camaleon:~>

camaleon:/home/david # whereis rails
rails:
camaleon:/home/david # wich rails
If 'wich' is not a typo you can use command-not-found to lookup the package that contains it, like this:
    cnf wich
```

##4. ¿Cuál es el siguiente paso?

Para saber que cual shim estas utilizando.
```
$ rbenv which rails
```
