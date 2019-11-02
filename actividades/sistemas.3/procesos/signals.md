
```
EN CONSTRUCCIóN!!!
```

----
# Procesos

Recursos
* Se puede utilizar el script [bucle.sh](files/bucle.sh), que está disponible como recursos, para la práctica.

## Procesos y prioridades

Vamos a buscar determinados procesos, y a modificar sus prioridades.
Realizar las siguientes tareas:
* Crear/descargar el script "bucle.sh".
* Abrir un terminal en XWindows con 3 pestañas.
* Lanzar el script en el terminal1: bucle.sh 5 "Hola Luke!"
* Lanzar el script en el terminal2: bucle.sh 6 "Hola Skywalker!"
* Ir al terminal3.
* Localizar el PID, PPID, y prioridad de los procesos anteriores.
* Disminuir la prioridad del proceso 1.
* Aumentar la prioridad del proceso 2.
* ¿Qué ocurre?

---
## Procesos y señales

Vamos a buscar los procesos y a enviarles varias señales.

Realizar las siguientes tareas:
* Crear/descargar el script "bucle.sh".
* Abrir un terminal en XWindows con 3 pestañas.
* Lanzar el script en el terminal1: bucle.sh 5 "Hola Luke!"
* Lanzar el script en el terminal2: bucle.sh 6 "Hola Skywalker!"
* Ir al terminal3.
* Localizar el PID, PPID, y prioridad del proceso 1.
* Enviar la señal de apagado suave al proceso1. ¿Qué ocurre?
* Localizar el PID, PPID, y prioridad del proceso 1.
* Enviar la señal kill al proceso2. ¿Qué ocurre?
* Volver a lanzar los dos procesos.
* Usar el comando pstree para localizar el proceso padre de ambos.
* ¿Qué sucede si enviamos una señal de apagado al proceso padre?
* ¿Qué sucede si enviamos una señal de apagado al proceso PID=1?

---
# Consumo de CPU/memoria

Vamos a buscar los procesos que más CPU están consumiendo, y los de más consumo de memoria.

Realizar las siguientes tareas:
* Usar los comandos ps, sort, grep, etc. para localizar los 5 procesos que más CPU están consumiendo.
* Incluir los comandos anteriores en un script llamado "ver_consumo_cpu".
* Usar los comandos ps, sort, grep, etc. para localizar los 5 procesos que más memoria están consumiendo.
* Incluir los comandos anteriores en un script llamado "ver_consumo_mem".
* ¿Cómo podemos saber qué procesos están usando la CPU en este instante?
* Lanzar el comando "find / -name a 2>/dev/null". Ejecutar top, y ver lo que muestra.
* Lanzar el script "find-pesado.sh" y comprobar que el resultado es similar al punto anterior.
* Lanzar el script "iterador-ligero.sh". Ejecutar el comando top y comprobar la salida que muestra. ¿Qué conclusión podemos sacar?
* ¿Qué diferencia hay entre los scripts anteriores y su carga de CPU/memoria?
