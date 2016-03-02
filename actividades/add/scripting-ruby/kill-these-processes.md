
#Eliminar determinados procesos

##1. Entrega
* Vamos a crear un script ruby llamado **kill-these-processes.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *kill-these-processes*.

##2. Fichero de configuración

* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrara un mensaje y se finaliza el script.
* Los datos de entrada al script estarán en un fichero *processes-black-list.txt*,
con el formato `nombre-del-ejecutable:action` en cada fila.
* El script deberá leer el contenido del fichero de entrada, 
donde vendrán definidos los nombres de los ejecutables. Deberá cargarlos en un array
para luego procesarlos.

> Elegir procesos que no sean básicos para el correcto funcionamiento 
del sistema como: firefox, libreoffice, geany, etc.

##3. Aplicar acción de control a cada proceso de la lista

* Para cada elemento del array deberemos ejecutar los comandos necesarios
para aplicar sobre el proceso.
    * Si `action=kill, k, remove o r` entonces si el proceso está en ejecución,
    se da la orden de eliminar el proceso y se muestra mensaje en pantalla
    (`_KILL_: Proceso <nombre> eliminado`).
    * Si `action=notify o n`, entonces se muestra un mensaje en pantalla
    avisando si dicho programa está en ejecución ahora mismo
    (`NOTICE: Proceso <nombre> en ejecución`).
* Antes de realizar cada acción comprobar si el proceso está en ejecución.

> **COMANDOS de ayuda**
> * `killall nombre-del-proceso` para eliminar todos los procesos con tal nombre.
> * `ps -ef| grep nombre-proceso| grep -v color` para averiguar el PID de un proceso.
> * `kill -9 PID` para emitor la señal de cierre del proceso PID.

* El script debe actuar de forma NO interactiva. No debe preguntar decisiones
durante su ejecución.

##4. Función y bucle

* Desarrollar el código anterior dentro de una función (`controlar_procesos`)
que reciba como parámetros: el nombre-del-proceso y la acción. Veamos ejemplo:

```
def controlar_procesos(name, action)
   ...
   Escribir aquí el código necesario para actuar sobre el proceso
   ...
end
```

* Hacer un bucle infinito que cada 5 segundos ejecuta el código de la función
`controlar_procesos` contínuamente.

> Veamos ejemplo de bucle infinito:
> ```
> while(true) do
>    ...
>    ejecutar acciones dentro del bucle que se repetirán eternamente
>    ...
>    sleep(5) #esperar 5 segundos antes de volver a repetir el bucle
> end
> ```

##5. Control de parada

* Justo antes de empezar el bucle infinito, el script debe crear 
un fichero vacío con el nombre `state.running`. Este fichero
va a ayudarnos a parar nuestro bucle infinito.
* El bucle infinito de control sólo se detendrá cuando eliminemos dicho fichero.

> Esto es, modificar la condición del bucle `while true`...
> para que sea algo como `while(File.exist? "state.running")`.
