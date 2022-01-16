#!/bin/bash

# elimina media y todo su contenido si existe
rm -fr media

# crear estructura de directorios
mkdir media
mkdir media/documentales
mkdir media/peliculas
mkdir media/series
mkdir media/series/breaking-bad
mkdir media/series/breaking-bad/t1
mkdir media/series/breaking-bad/t2
mkdir media/series/breaking-bad/t3
mkdir media/series/juego-de-tronos

# crear los ficheros
touch media/1x01-se_acerca_el_invierno.avi
touch media/1x02-el_camino_real.avi
touch media/documentales/biografia-de-bill-gates.mp4
touch media/documentales/codigo-linux.mp4
touch media/peliculas/avatar.mkv
touch media/peliculas/matrix-revolutions.avi
touch media/series/breaking-bad/t1/1x01-piloto.avi
touch media/series/breaking-bad/t1/1x02-el_gato_esta_en_la_bolsa.avi
touch media/series/breaking-bad/t1/1x03-y_la_bolsa_esta_en_el_rio.avi
touch media/series/breaking-bad/t2/2x01-7_37.avi
touch media/series/breaking-bad/t2/2x02-a_la_parrilla.avi
touch media/series/breaking-bad/t2/2x03-picado_por_una_abeja_muerta.avi
