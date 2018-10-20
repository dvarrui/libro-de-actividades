

# rbenv

`rbenv` es una herramienta que proporciona entornos virtuales para ruby.
De esta forma pueden convivir distintas versiones de ruby en nuestro sistema.

`rbenv` intercepta los comandos de ruby usando ejecutables `shim` inyectados en las rutas del PATH. `rbenv` determinaa la versión de Ruby especificada por la aplicación y pasa los comandos a la instalación de Ruby adecuada.

Enlaces de interés:
* [https://github.com/rbenv/rbenv](https://github.com/rbenv/rbenv)

---

# 1. Instalación de rbenv

* `git clone https://github.com/rbenv/rbenv.git ~/.rbenv`, hacemos la instalación de rbenv
* Optionally, try to compile dynamic bash extension to speed up rbenv.
Don't worry if it fails; rbenv will still work normally:
` $ cd ~/.rbenv && src/configure && make -C src`
* Add `~/.rbenv/bin` to your $PATH for access to the rbenv command-line utility.
`$ echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bash_profile`

> * Ubuntu Desktop note: Modify your ~/.bashrc instead of ~/.bash_profile.
> * Zsh note: Modify your ~/.zshrc file instead of ~/.bash_profile.

* Run `~/.rbenv/bin/rbenv init` for shell-specific instructions on how to initialize rbenv to enable shims and autocompletion.
* Restart your shell so that PATH changes take effect. (Opening a new terminal tab will usually do it.) Now check if rbenv was set up:
```
$ type rbenv
#=> "rbenv is a function"
```
* (Optional) Install ruby-build, which provides the rbenv install command that simplifies the process of installing new Ruby versions. `zypper in rubygem-builder-3_0`
* Upgrading: If you've installed rbenv manually using git, you can upgrade your installation to the cutting-edge version at any time.

---

# 2. Probando rbenv

* Hay que indicar a rbenv que ruby vas a utilizar.

```
david@camaleon:~> rbenv versions
* system (set by /home/david/.rbenv/version)
```
