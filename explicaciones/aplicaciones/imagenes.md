

# ImageMagik

## Recortar imágenes

Enlaces de Interés:
* [crop - Cutting and Bordering examples](http://www.imagemagick.org/Usage/crop/#crop)

Ejemplos:
* `convert paged.gif -crop 32x32+16+16 crop_page.gif`, crea una nueva imagen de tamaño 32x32 que se obtiene de recortar la imagen original con un desplazamiento de 16+16.
* `convert paged.gif -crop 32x32+0+0 crop_page_tl.gif`, crea una nueva imagen de tamaño 32x32 que se obtiene de recortar la imagen original con un desplazamiento de 0+0.
