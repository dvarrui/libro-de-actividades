
# SSOO. minimalistas

# 1. TinyCore

## 1.1 Instalar

* Página web: http://www.tinycorelinux.net/downloads.html
* Descargo TinyCorePlus para tener GUI y Wifi
* Crear MV e iniciar ISO con las opciones por defecto.
* Instalar el sistema.
* `filetool.sh -b`, grabar los cambios 
* Instalar ruby: `tce-load -wi ruby`

## Configurar

* Configurar el terminal:

```bash`
vi ~/.Xdefaults
aterm*font: -*-fixed-medium-r-*-*-18-*-*-*-*-*-*-*
filetool.sh -b
tce-load -wi rxvt-unicode, instalar otro terminal
````

* Configurar el teclado
```bash`
tce-load -wi kmaps, descargar extensión para otros teclados
sudo loadkmap < /usr/share/kmap/qwerty/es.kmap
vi ~/.xsession

setxkbmap es &
filetool.sh -b
```

