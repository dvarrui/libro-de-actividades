
# Nomenclatura

Desde el comienzo del curso se ha pedido seguir la siguiente nomenclatura:
* Los nombres de ficheros/carpetas deben ser en minúsculas y sin espacios.
* Los nombres no deben contener caracteres especiales como tiles, eñes, etc.
* Las imágenes deben numerarse desde 01, 02, etc. Por ejemplo: image01.png, image02.png, etc.
* El contenido de un documento Markdown, NO debe contener etiquetado HTML. Solamente debe contener etiquetado Markdown.

_¿Son unas normas elegidas al azar? ¿O tienen algún motivo?_

## Problema 1: Nombres de ficheros y carpetas

Por ejemplo, supongamos que se crean los siguientes ficheros para un repositorio Git:

```
.
├── GNU  Linux
│   ├── 10.png
│   ├── 11.png
│   ├── 12.PNG
│   ├── 20.png
│   ├── 2.png
│   ├── 3.png
│   └── 4.png
└── README.md
```

Estado:
1. El nombre de la carpeta no sigue la nomenclatura especificada.
2. Los ficheros 2,png, 3.png y 4.png no aparecen en orden.
3. El fichero 12.PNG tiene una extensión en mayúscula.

Problema:
1. Los nombres con espacios generan confusión. ¿Cuántos espacios hay entre GNU y Linux? ¿1, 2, 3? Al escribir el nombre nos confundiremos. Además si ponemos espacios en los nombres vamos a complicar a los usuarios que trabajen desde el terminal.
2. Si tenemos orden, es más fácil localizar lo que están mal y reducimos los problemas. Moraleja, mantener un orden. Si nombramos los ficheros como 02.png, 03.png y 04.png... Primero que no cuesta nada y segundo que mantenemos la información ordenada.
3. Los sistemas de ficheros no son iguales. Windows suele usar NFTS, GNU/Linux etx4, MacOS otro diferente. En ext4 podemos tener los siguientes nombres para diferentes ficheros al mismo tiempo: 12.png, 12.PNG, 12.Png, 12.pNg, 12.pnG, 12.PNg, 12.pNG, 12.PnG... pero para Windows "es el mismo archivo". Para evitar estos problemas... "siempre todo en minúsculas".

## Problema 2: Etiquetado incorrecto

Luego tenemos que el contenido Markdown del fichero README.md tiene lo siguiente:
```
Configurar el cliente GNU/Linux para tener dos servidores DNS.

<img src="CLIENTE GNU/3.png">
```

Estado:
1. El contenido del fichero md tiene marcas HTML en lugar de Markdown.

Problema:
1. El fichero Markdown lo podemos visualizar en texto plano cuando trabajamos con editores como Atom, vi, etc. Cuando se sube a GitHub, se transforma el etiquetado a HTML y lo vemos como página web, pero también es posible convertir el etiquetado Markdow a PDF o a otro formato que queramos. Pero para que esto funcione sin problemas, el fichero original md debe contener sólo etiquetado Markdown. Si contiene HTML es una página web, si contiente Markdown es Markdown y si tiene una mezcla ¿qué es?

IMPORTANTE:
* Un documento Markdown NO debe contener tags HTML porque NO debemos cambiar el aspecto.
* El documento markdown sólo debe reflejar la estructura del documento y su contenido.
