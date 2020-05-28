
> Es obligatorio hacer la entrega de cada actividad en el plazo previsto.
>
> En el caso de los trabajos en grupo:
> * En el trabajo deben aparecer los nombres de los componentes del grupo de trabajo.
> * Todos los componentes del grupo deben hacer la entrega (aunque sea repetida) al profesor.

# Guía para la entregas de documentos Markdown vía Git

## Texto escrito

* No repetir en la entrega del informe lo mismo que pone el enunciado.
* Usar el color rojo de forma esporádica, y sólo para resaltar o destacar
algún punto/apartado/texto del contenido.
* Usar el lenguaje escrito de forma correcta y apropiada.

## Imágenes

* Las imágenes deben ser claras y adecuadas al contenido que se quiere mostrar.
Si el fondo del informe y el de las imágenes son blancos, se van a mezclar
ambos, produciendo confusión.
* Tener cuidado de no mostrar las imágenes deformadas.
* Tener cuidado de no mostrar el contenido tan pequeño que cueste leerlo.
* Si la imagen muestra mucha información, se deberá resaltar la parte relevante
para facilitar su lectura, Por ejemplo con una marca roja, como por ejemplo:
una flecha, un subrayado, un cuadro, etc.
* Usar el recorte de la imagen para no mostrar un exceso de detalle.

## Estructura del repositorio Git

* Estructurar el directorio de trabajo de la siguiente forma:
```
.
├── trim1
│   ├── u1-nombre
│   │   ├── a1-nombre.md
│   │   ├── a2-nombre.md
│   │   └── a3-nombre.md
│   └── u2-nombre
│       ├── a1-nombre.md
│       ├── a2-nombre.md
│       └── a3-nombre.md
└── trim2
    ├── u3-nombre
    │   ├── a1-nombre.md
    │   ├── a2-nombre.md
    │   └── a3-nombre.md
    └── u4-nombre
        ├── a1-nombre.md
        ├── a2-nombre.md
        └── a3-nombre.md
```

* Dentro del directorio de cada actividad, crear el siguiente esquema:

```
.
├── files
│   └── imagen01.png
└── README.md
```

Donde tendremos el/los ficheros Markdown (.md) de la actividad, y un
subdirectorio `files` para contener las imagenes del documento Markdown.
