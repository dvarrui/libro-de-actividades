
# Entregas en formato VIDEO

Enlaces de interés:
* [Convertir una vídeo en GIF animando](https://www.linuxadictos.com/como-convertir-un-video-en-un-gif-animado-en-linux.html)

En este tipo de entregas el alumno/grupo deberá realizar un vídeo donde se muestre el funcionamiento de la actividad a evaluar.

Cuando la entrega sea un vídeo:
* Hay que entregar URL con la ruta al vídeo subido a Youtube.
    * NO poner HTML con el vídeo incrustado dentro de la entrega.
    * SI entregar URL para poder acceder al vídeo.
* En el caso de presentar varios vídeos, numerarlos secuencialmente para mantener el orden.
* Debe aparecer en el vídeo: `fuwXX-curso1819` de cada componente, fecha y título de la actividad y el nombre del centro "IES Puerto de la Cruz".
* TODOS los componentes del grupo deben entregar el trabajo, aunque sea el mismo.

---

# ANEXO

## Programa: Capturador de vídeo

Disponemos de varios programas para grabar el escritorio. Estos se llaman
programas capturadores de vídeo.

![record-my-desktop](./images/record-my-desktop.png)

*RecordMyDesktop* es un programa capturador de vídeo. Esto es, cuando se activa
graba un vídeo con todo lo que hagamos en el escritorio de nuestro PC.
Además, si conectamos un micrófono, puede grabar nuestra voz como parte
del sonido del vídeo.

Al terminar la grabación, obtenemos como resultado un vídeo (Formato ogv),
que podemos ver en cualquier PC (VLC player) o incluso subir a YouTube.

Es una herramienta muy útil para enseñar mediante vídeos.

[Ver vídeo en YouTube](https://youtu.be/NyF9-5sGtak)

> Otro capturador de vídeo muy fácil de usar es SimpleScreenRecorder

---

## Programa: Editor de vídeo

Para editar el vídeo pueden usar la herramienta que quieran. Por ejemplo kdenlive.

---

## Conversor de formato multimedia

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

# OpenSUSE

* [SDB:Firefox MP4/H.264 Video Support](https://en.opensuse.org/SDB:Firefox_MP4/H.264_Video_Support)
* [Additional package repositories](https://en.opensuse.org/Additional_package_repositories#Packman)


# Cómo entregar un VIDEO

* Es obligatorio hacer la entrega de cada actividad en el plazo previsto.
* En el caso de los trabajos en grupo:
    * En el trabajo deben aparecer los nombres de los componentes del grupo de trabajo.
    * Todos los componentes del grupo deben hacer la entrega (aunque sea repetida) al profesor.
* Para los nombres de los archivos:
    * Siempre en minúsculas.
    * NO uses espacios, ni tildes, ni eñes, etc.
    * Si necesitas separar palabras usa guiones `-` o `_`.

---

# 1. Entrega

* Entregar URL con la ruta al vídeo subido a Youtube.
* En el caso de presentar varios vídeos, numerarlos secuencialmente para mantener el orden.
Por ejemplo: video01, video02, etc.

---

# 2. Esquema del vídeo

* Cada vídeo debe diseñarse teniendo en mente un esquema y/o secuencia de apartados a tratar él de forma ordenada.
    * Presentación de la actividad y de los componentes del grupo
    * ¿Qué vamos a ver en este vídeo? Explicación/resumen
    * Comprobar el estado inicial del equipo.
    * Identificar los componentes duplicados/repetidos.
    * Mostrar proceso que se socilita.
    * Comprobar el estado final del equipo.
    * Despedirse.

## 2. 1 Presentación inicial

* Al comienzo del vídeo, debe mostrarse lo siguiente:
    * El nombre del centro "IES Puerto de la Cruz".
    * Curso y grupo (Por ejemplo: "Curso210516 - 1ºASIR")
    * Asignatura: "Sistemas Operativos" o "Fundamentos de Hardware"
    * Fecha y título de la actividad
    * El/Los nombre/s de el/los componente/s

---

# 3. Contenido

## 3.1 Imágenes

* Mostrar el **logo del IES Puerto de la Cruz** durante todo el vídeo.
    * Para que se vea bien el logo, debemos crear un `Clip de presentación`, y
    luego dentro añadimos la imagen del logo.
    * Usar el color del logo adecuado para que haga contraste con el fondo.
* Las imágenes deben ser claras y adecuadas al contenido que se quiere mostrar.
* En el caso de imágenes estáticas, es conveniente incluir flechas y comentarios
para marcar/resaltar las zonas de la imagen que requieran nuestra atención.
* Si hacen fotos con el móvil, es recomendable ponerlo en horizontal.

## 3.2 Subtítulos/textos

* Subtitular si la imagen no es lo suficientemente explícita.
* No subtitular con textos que digan algo que se muestre claramente en la imagen.
* El color/tamaño de las letras debe tener el contraste suficiente con la imagen
del fondo para poder verse con claridad.
* No repetir de forma contínua en la entrega lo mismo que pone el enunciado.
* Usar el color rojo de forma esporádica, y sólo para resaltar o destacar
algún punto/apartado/texto del contenido.
* Usar el lenguaje escrito forma correcta y apropiada.

## 3.3 Sonido/Música

* El sonido no debe resultar molesto, ni el volumen ser excesivo.
* Si queremos usar música con licencia libre, podemos descargar de la web de Jamendo.
* Usar el lenguaje hablado de forma correcta y apropiada.
