
# Entregas en formato VIDEO

Enlaces de interés:
* [Convertir una vídeo en GIF animando](https://www.linuxadictos.com/como-convertir-un-video-en-un-gif-animado-en-linux.html)

En este tipo de entregas el alumno/grupo deberá realizar un vídeo donde se muestre el funcionamiento de la actividad a evaluar.

Cuando la entrega sea un vídeo:
* Hay que entregar URL con la ruta al vídeo subido a Youtube.
* En el caso de presentar varios vídeos, numerarlos secuencialmente para mantener el orden.
* Debe aparecer en el vídeo: los nombres de los componentes, fecha y título de la actividad y el nombre del centro "IES Puerto de la Cruz".

---

# Grabador

* En el PC de clase disponemos de un programa para grabar el escritorio.
* Para editar el vídeo pueden usar la herramienta que quieran. Por ejemplo kdenlive.

---

# Conversor de formato multimedia

Crea un fichero de texto con el nombre "conversor-de-video". Escribe el siguiente contenido dentro del fichero:

```
#!/bin/bash
echo "[INFO] Realizando conversión de formato..."
mencoder -idx out.ogv -ovc lavc -oac mp3lame -o out.avi
echo "[INFO] Done"
```

Pon permisos de ejecución al fichero: `chmod +x conversor-de-video`
Para ejecutarlo, simplemente escribe en la consola: `./conversor-de-video`
¡Ya está!
