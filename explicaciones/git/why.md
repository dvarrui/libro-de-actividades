
# Nomenclatura

Desde el comienzo del curso se ha pedido lo siguiente:
* Los nombres de ficheros/carpetas deben ser en minúsculas y sin espacios.
* Además los nombres no deben contener caracteres especiales como tiles, eñes, etc.

¿Cuál es el motivo?

Por ejemplo, supongamos que se crean los siguientes ficheros para un repositorio Git:

```
.
├── CLIENTE GNU
│   ├── 10.png
│   ├── 11.png
│   ├── 12.png
│   ├── 20.png
│   ├── 2.png
│   ├── 3.png
│   └── 4.png
└── README.md
```

Luego tenemos que el contenido Markdown del fichero README.md tiene lo siguiente:
```
Configurar el cliente GNU/Linux para tener dos servidores DNS.

<img src="CLIENTE GNU/3.png">
```

Vemos que tenemos lo siguiemnte:
1. El nombre de la carpeta no sigue la nomenclatura especificada.
2. El contenido del fichero md tiene marcas HTML en lugar de Markdown.

## Problemas

Aparentemente no pasa nada malo. Pero trabajar de esta forma nos va a dar problemas. Veamos.

1. El documento Markdown NO debe contener tags HTML porque NO debemos cambiar el aspecto. El documento markdown sólo debe reflejar la estructura del documento y su contenido.

