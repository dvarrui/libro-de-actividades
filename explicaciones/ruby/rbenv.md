
#Instalación de rbenv y rails en OpenSUSE 13.2

##1. Hago la instalación de rbenv
Comprobación:
```
david@camaleon:~> rbenv versions
* system (set by /home/david/.rbenv/version)
  2.2.2
```

##2. Hago la instalación de rails en el ruby del sistema

```
david@camaleon:~> sudo gem install rails
Fetching: rails-4.2.4.gem (100%)
Successfully installed rails-4.2.4
Parsing documentation for rails-4.2.4
Installing ri documentation for rails-4.2.4
Done installing documentation for rails after 367 seconds
1 gem installed
```

Comprobación de las gemas instaladas:
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
