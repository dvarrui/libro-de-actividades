
# Rails

* Empresas que usan Rails: GitHub, GitLab, etc.
* Característiscas:
    * **DRY**: Don't Repeat Yourself
    * **CoC**: Convention over Configuration
    * Lenguaje Ruby.
* Arquitectura MVC.

---

# Instalar Ruby and Rails

* `ruby -v`, consultar la versión de ruby.
    * Instalar ruby (`apt install ruby`).
* Se recomienda usar entornos virtuales (rbenv, rvm, etc.)
* `gem install rails`, para instalar rails (o consultar installrails.com).

```
david@camaleon:~/charlas/rails> gem install rails
Successfully installed concurrent-ruby-1.1.3
Successfully installed i18n-1.1.1
Successfully installed thread_safe-0.3.6
Successfully installed tzinfo-1.2.5
Successfully installed activesupport-5.2.2
Successfully installed rack-2.0.6
Successfully installed rack-test-1.1.0
Successfully installed mini_portile2-2.3.0
Building native extensions. This could take a while...
Successfully installed nokogiri-1.8.5
Successfully installed crass-1.0.4
Successfully installed loofah-2.2.3
Successfully installed rails-html-sanitizer-1.0.4
Successfully installed rails-dom-testing-2.0.3
Successfully installed builder-3.2.3
Successfully installed erubi-1.7.1
Successfully installed actionview-5.2.2
Successfully installed actionpack-5.2.2
Successfully installed activemodel-5.2.2
Successfully installed arel-9.0.0
Successfully installed activerecord-5.2.2
Successfully installed globalid-0.4.1
Successfully installed activejob-5.2.2
Successfully installed mini_mime-1.0.1
Successfully installed mail-2.7.1
Successfully installed actionmailer-5.2.2
Building native extensions. This could take a while...
Successfully installed nio4r-2.3.1
Successfully installed websocket-extensions-0.1.3
Building native extensions. This could take a while...
Successfully installed websocket-driver-0.7.0
Successfully installed actioncable-5.2.2
Successfully installed mimemagic-0.3.2
Successfully installed marcel-0.3.3
Successfully installed activestorage-5.2.2
Successfully installed railties-5.2.2
Successfully installed bundler-1.17.1
Successfully installed sprockets-3.7.2
Successfully installed sprockets-rails-3.2.1
Successfully installed rails-5.2.2
37 gems installed
david@camaleon:~/charlas/rails> rails -v
Rails 5.2.2
```

* `rails -v`, consultar la version de rails.

---

# Bases de datos

> **Instalar la BBDD**
> * `zypper in sqlite3 sqlite3-devel`
> * `zypper install gcc gcc-devel gcc8-c++ `
> * `cd /usr/bin; ln -s gcc g++`
> * `gem install mini_racer`

---

# Resumen

* `gem install rails`, para instalar rails.
