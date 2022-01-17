
# Problemas con la práctica de RAID

# 1. Al quitar 5 disco la nomenclatura cambia sde->sdd sdd>sde

**Descripción**: El disco que quité fue el SDE, que visto desde la MV, es el disco de la quinta posición, es decir, el último.

Probé los comandos que adjunto en las capturas y sigue dando el mensaje de que el disco está en uso.

Algo que si he notado después de reiniciar varias veces la MV es que, según le dé al sistema, el RAID 1 se configura en el disco SDD una veces y otras en el SDE, dejando el otro disco con el mismo problema de estar en uso y no poder montarlo en el RAID. Es decir, unas veces es el disco SDD el que me da problemas porque no lo puedo añadir al RAID 1 y otras veces es el disco SDE. ¿Qué puedo hacer?

![](01/vbox-discos.png)

![](01/vbox-ficheros.png)

![](01/mv-particiones.png)

![](01/mv-comandos.png)

**Profesor:**
Hay varias cosas que hay que ir separando para aclararnos:
1) El hostname de MV está mal. OJO
2) Hay que quitar el disco 5 ...(yo no lo veo quitado)
3) Parece que se te están cambiando los nombres de los discos sde por sdd y al revés. Si sde está busy prueba con sdd. Si te fijas tu última captura de pantalla lo muestra claramente (salida del comando "cat /proc/mdadm").. Recuerden... si no entienden bien lo que hace un comando o la salida que muestra en pantalla no la saben interpretar... NO sigan adelante. Hay que entender antes de seguir. Leelo y si no lo ves pregunta sobre esa imagen. Ahí se ve.
4) Si no queremos evitar que al quitar el 5 disco... vaya saltando de sdd a sde y al revés... yo lo explico. Para eso necesito que me envíes capturas de los siguientes comandos:

```
sudo blkid
cat /etc/fstab
lsblk
```

Con esta información les explico. Gracias por la descripción del problema. Vamos bien!

# 2. Problemas para instalar OpenSUSE en RAID0

*+Descripción:** Sigo teniendo problemas con la instalación y sigo sin poder instalar, hay veces que se me para en el 97%, otras en el 94%, otras ni si quiera me inicia la instalación de opensuse y hoy me salió un error nuevo.

¿Alguna solución que pueda hacer?, porque llevo ya una semana intentándolo y si vas a dejar la entrega para el martes va a ser imposible que lo tenga. Adjunto imagen del nuevo error que me dio hoy.

![](02/mv-instalacion-raid0.png)

**Profesor:** Están pasando los días para entregar la actividad.
Entiendo que pueden haber problemas/dudas y los iremos aclarando. Pero a estas alturas lo problemas/dudas que deben tener es a partir del punto 3...
En caso contrario es que han esperado demasiado tiempo:
1. para empezar la actividad y/o
2. para consultar dudas/problemas.

_Sugerencia_: graba un vídeo con todo el proceso desde el principio hasta llegar al problema. Que se vea grande para no tener que hacer zoom. Usa un editor de vídeos para cortar las partes "aburridas" donde hay que esperar o pones pausa en la grabación. De esta forma tendremos un vídeo donde se ven las decisiones que vas tomando durante el proceso.

El vídeo lo puedes subir por el foro de Moodle porque no debe ser mayor a 49 MiB.