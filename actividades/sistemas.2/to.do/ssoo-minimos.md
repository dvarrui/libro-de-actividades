
# SSOO. minimalistas

# 1. TinyCore

## 1.1 Instalar

* Página web: http://www.tinycorelinux.net/downloads.html
* Descargo TinyCorePlus para tener GUI y Wifi
* Crear MV e iniciar ISO con las opciones por defecto.
* Instalar el sistema.
* `filetool.sh -b`, grabar los cambios 
* Instalar ruby: `tce-load -wi ruby`

## 1.2 Configurar

* Configurar el terminal:

```bash
vi ~/.Xdefaults
aterm*font: -*-fixed-medium-r-*-*-18-*-*-*-*-*-*-*
filetool.sh -b
tce-load -wi rxvt-unicode, instalar otro terminal
````

* Configurar el teclado
```bash
tce-load -wi kmaps, descargar extensión para otros teclados
sudo loadkmap < /usr/share/kmap/qwerty/es.kmap
vi ~/.xsession

setxkbmap es &
filetool.sh -b
```

* (Opcional) Instalar herramientas de desarrollo

* `tce-load -wi compiletc ruby-dev`
    - Instala el paquete "compiletc" (que es un meta-paquete con GCC, Make, etc.):
* gem install bundler

> Persistencia: Por defecto, las gemas que instales con gem install se guardarán en /usr/local/lib/ruby/gems/.... Como Tiny Core es volátil, estas gemas se borrarán al reiniciar a menos que:
>
>     - Añadir la ruta de las gemas a tu /opt/.filetool.lst (no recomendado si son muchas, por la RAM).
>     - Recomendado: Configuriar una carpeta persistente en tu disco/USB para las gemas cambiando la variable de entorno GEM_HOME.

## 1.3 Configurar Gemas Persistentes

Para que las gemas no desaparezcan y no saturen el backup `mydata.tgz`:

* `mkdir -p /mnt/vda1/ruby_gems`, crear carpeta en partición persistente (asumiendo que es /mnt/vda1 o /mnt/sdb2):
* Editar archivo .ashrc (o .profile):
```
# Añadir al final:
    Plaintext
export GEM_HOME=/mnt/vda1/ruby_gems
export PATH=$PATH:$GEM_HOME/bin
```
* Guardar y ejecutar filetool.sh -b.
* Probar con un "Hola Mundo"
```bash
echo 'puts "¡Hola desde Ruby en Tiny Core!"' > hola.rb
ruby hola.rb
```

---

# ANEXO

## A.1 Idea

* Crear una MV mínima para iniciar una única aplicación. Por ejemplo
    - Simular una máquina de videojuegos de los 80.
    - Crear un entorno sandbox para tareas de informática.

## A.2 Enlaces de interés

https://suckless.org/

