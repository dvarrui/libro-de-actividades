

#1. Modo de trabajo
En cada UD la forma de proceder será la siguiente:
* El profesor hace una breve explicación/introducción sobre el tema.
* El profesor sube algunos contenidos al servidor del departamento, para que estén a disposición de la clase.
* El profesor irá activando actividades y programando sus fechas de inicio-fin en la plataforma moodle.
* Los alumnos deben leer la documentación.
* Los alumnos deben realizar las actividades y entregar lo que se les pide, en tiempo y forma.
Las dudas se resolverán:
* Leyendo la documentación proporcionada por el profesor y los foros de dudas.
* Consultando otras fuentes (Internet)
* Preguntando a los compañeros y/o al profesor.
* Siempre que se pueda las dudas/respuestas se plasmarán en el foro de dudas de la asignatura para que estén a disposición de todo el grupo.
* Si el profesor lo considera conveniente, cuando alguna preguntas/dudas se repita con frecuencia, entonces el profesor parará la clase y hará una explicación al grupo a la cual deberán estar todos atentos.

* El desarrollo/aprendizaje de la UD se basa en la resolución de las actividades. Antes de cerrar la UD se pone un examen tipo test basado en los apuntes proporcionados, las actividades realizadas, los comentarios de foros y explicaciones dadas.
* Las recuperaciones se harán siempre en el siguiente trimestre, excepto en el 3er trim. que se harán en junio.
* Por falta de tiempo, las conversaciones/charlas/debates en relación a la forma de trabajar en la asignatura, en aquellos aspectos que afecten a todo el grupo NO se podrán tratar en las horas lectivas normales. Debemos buscar algún hueco para ello.


#2. Entrega de los trabajos
Las fechas/horas y forma para las entregas de los trabajos estará especificado en la actividad correspondiente de moodle. El profesor NO tiene que recordar fechas, ni comentar nada en clase al respecto.

##2.1 Informes
Cuando el trabajo se pida entregar con un informe, éste deberá realizarse obligatoriamente en un formato que podemos ver/consultar en cualquier plataforma sin restricciones técnicas y/o legales.
Por ejemplo podremos usar los formatos PDF, ODT, HTML. Si queremos usar otro formato, es posible siempre que se cumpla el criterio anterior.

Además se proporciona una plantilla en la propia plataforma moodle que servirá de modelo base para la confección de dicho informe. OJO es muy importante usar un lenguaje apropiado, no cometer faltas de ortografía, poner encabezados y pies de página, poner portada con título del trabajo y componentes del grupo, una página de índice. El documento debe tener una estructura clara y contener las imágenes justas y necesarias.

Si debemos comprimir la entrega, también debemos tener cuidado y elegir un formato de compresión que se ajuste al criterio anterior. Por ejemplo: ZIP, 7Z.


##2.2 Vídeos

En este tipo de entregas el alumno/grupo deberá realizar un vídeo donde se muestre el funcionamiento de la actividad a evaluar.
```
    En el moodle deberán escribir la URL con la ruta al vídeo subido a Youtube.
    En el caso de presentar varios vídeos, numerarlos secuencialmente para mantener el orden y entregar todas sus URL de forma ordenada.
    Debe aparecer en el vídeo: los nombres de los componentes, fecha y título de la actividad y el nombre del centro "IES Puerto de la Cruz".
    Para editar el vídeo pueden usar la herramienta que quieran. Por ejemplo kdenlive.
```

##2.3 Conversor de formato multimedia
Para convertir ficheros de audio/vídeo de un formato a otro, podemos hacer uso del programa kdenlive o cualquier otro para tal efecto.

Ejemplo de otra forma de hacerlo, creando un fichero de texto con el nombre "conversor-de-video", para convertir ficheros del formato OGV al formato AVI. Escribe el siguiente contenido dentro del fichero:
```
#!/bin/bash
echo "[INFO] Realizando conversión de formato..."
mencoder -idx out.ogv -ovc lavc -oac mp3lame -o out.avi
echo "[INFO] Done"
```

Pon permisos de ejecución al fichero: chmod +x conversor-de-video
Para ejecutarlo, simplemente escribe en la consola: conversor-de-video
¡Ya está!

#3. Imágenes de SO listas para usar
Las imágenes de los SO tanto en formato ISO como VBox las tienen disponibles en:
* El servidor leela del departamento. Además también hay colgadas varias imágenes de debian con diferentes entornos gráficos.
* En internet.
Para el que le guste [aquí les dejo una página](http://virtualboxes.org/images/) donde pueden descargar imagenes de distros variadas (GNU/Linux, GNU/kfree, Solaris, BSD, etc.) listas para usar.


