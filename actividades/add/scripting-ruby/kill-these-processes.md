
#Eliminar determinados procesos

##1. Entrega
* Vamos a crear un script ruby llamado **kill-these-processes.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *kill-these-processes*.

##2. Fichero de configuración

* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrará un mensaje y se finaliza el script.
* Los datos de entrada al script estarán en un fichero llamado `processes-black-list.txt`,
con el formato `nombre-del-ejecutable:action` en cada fila.
* El script leerá el contenido del fichero de entrada, donde 
vendrán definidos los nombres de los ejecutables y su acción. 
Deberá cargarlos en un array para luego procesarlos.

> Elegir una lista de procesos que NO sean importantes para el correcto funcionamiento 
del sistema como: firefox, libreoffice, geany, etc.

##3. Aplicar acción de control a cada proceso de la lista

* Para cada elemento del array deberemos ejecutar los comandos necesarios
para aplicar sobre cada proceso su acción asociada. Por ejemplo:
    * Si `action=kill, k, remove o r` entonces si el proceso está en ejecución,
    se da la orden de eliminar el proceso y se muestra mensaje en pantalla
    `_KILL_: Proceso <nombre> eliminado`.
    * Si `action=notify o n`, entonces se muestra un mensaje en pantalla
    avisando si dicho programa está en ejecución ahora mismo
    `NOTICE: Proceso <nombre> en ejecución`.
* Podemos hacer la comprobación de dos formas:
    * (A) Antes de ejecutar cada acción, comprobamos si el proceso está en ejecución.
    Para no matar procesos que no están vivos.
    * (B) Ejecutamos la acción sobre el proceso y comprobamos el resultado.
    Ejemplo:
```
    resultado=system("killall firefox")
    if resultado 
      puts "Se eliminó el proceso firefox"
    else
      #No se realizó la acción
    end
```

> **COMANDOS que pueden servir de ayuda**
> * `killall nombre-del-proceso` para eliminar todos los procesos con tal nombre.
> * `ps -e| grep nombre-proceso| grep -v color` para averiguar el PID de un proceso.
> * `ps -e| grep nombre-proceso| wc -l` muestra el número de líneas donde
aparece el texto buscado
> * `kill -9 PID` para emitir una señal de cierre del proceso PID.

* El script debe actuar de forma NO interactiva. No debe hacer preguntas
al usuario durante su ejecución.

##4. Una función y un bucle infinito

* Mover el código de control dentro de una función (`controlar_procesos`)
que reciba como parámetros: el nombre-del-proceso y la acción. Veamos ejemplo:

```
def controlar_procesos(name, action)
   ...
   Escribir aquí el código necesario para ejecutar la acción sobre el proceso
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
