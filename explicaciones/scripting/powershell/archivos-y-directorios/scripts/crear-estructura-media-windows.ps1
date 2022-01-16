# elimina media y todo su contenido si existe
Remove-Item -Recurse media -ErrorAction SilentlyContinue

# crear estructura de directorios

function New-Directory($dir) {
    New-Item -ItemType Directory $dir | Out-Null
}

New-Directory media
New-Directory media\documentales
New-Directory media\peliculas
New-Directory media\series
New-Directory media\series\breaking-bad
New-Directory media\series\breaking-bad\t1
New-Directory media\series\breaking-bad\t2
New-Directory media\series\breaking-bad\t3
New-Directory media\series\juego-de-tronos

# crear los ficheros

function New-EmptyFile($file) {
    New-Item -ItemType File $file | Out-Null
}

New-EmptyFile media\1x01-se_acerca_el_invierno.avi
New-EmptyFile media\1x02-el_camino_real.avi
New-EmptyFile media\documentales\biografia-de-bill-gates.mp4
New-EmptyFile media\documentales\codigo-linux.mp4
New-EmptyFile media\peliculas\avatar.mkv
New-EmptyFile media\peliculas\matrix-revolutions.avi
New-EmptyFile media\series\breaking-bad\t1\1x01-piloto.avi
New-EmptyFile media\series\breaking-bad\t1\1x02-el_gato_esta_en_la_bolsa.avi
New-EmptyFile media\series\breaking-bad\t1\1x03-y_la_bolsa_esta_en_el_rio.avi
New-EmptyFile media\series\breaking-bad\t2\2x01-7_37.avi
New-EmptyFile media\series\breaking-bad\t2\2x02-a_la_parrilla.avi
New-EmptyFile media\series\breaking-bad\t2\2x03-picado_por_una_abeja_muerta.avi
