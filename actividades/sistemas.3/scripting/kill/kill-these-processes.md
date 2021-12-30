
```
Curso       : OLD
Area        : Sistemas operativos, scripting, servicios, procesos
Descripción : Script para matar aplicaciones de los usuarios y
              leyendo la configuración desde un fichero externo.
Requisitos  : GNU/Linux, comandos de procesos, Ruby
Tiempo      : 5 sesiones
```

# Eliminar determinados procesos

# 1. Entrega

* Vamos a crear un script ruby llamado **kill-these-processes.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *kill-these-processes*.

# 2. Fichero de configuración

* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrará un mensaje y se finaliza el script.
* Los datos de entrada al script estarán en un fichero llamado `processes-black-list.txt`, con el formato `nombre-del-ejecutable:action` en cada fila.
* El script leerá el contenido del fichero de entrada, donde
vendrán definidos los nombres de los ejecutables y su acción.
Deberá cargarlos en un array para luego procesarlos.

> Elegir una lista de procesos que NO sean importantes para el correcto funcionamiento del sistema como: firefox, libreoffice, geany, etc.

# 3. Aplicar acción de control a cada proceso de la lista

* Para cada elemento del array deberemos ejecutar los comandos necesarios
para aplicar sobre cada proceso su acción asociada. Por ejemplo:

* Si `action=kill, k, remove o r` entonces:
    1. Si el proceso está en ejecución, se da la orden de eliminar el proceso.
    2. Se muestra mensaje en pantalla como `_KILL_: Proceso <nombre> eliminado`.
* Si `action=notify o n`, entonces:
    1. Se muestra un mensaje en pantalla avisando si dicho programa está en ejecución ahora mismo. Ejemplo: `NOTICE: Proceso <nombre> en ejecución`.

Podemos hacer la comprobación de dos formas:
* (A) Antes de ejecutar cada acción, comprobamos si el proceso está en ejecución (ps -ef). Para no matar procesos que no están vivos.
* (B) Ejecutamos la acción sobre el proceso y comprobamos el resultado.
    * NOTA: Los comandos devuelven un 0 cuando han ido bien, y distinto de 0 cuando han ido mal.

Ejemplo:
```
    # system devuelve true o false, según haya ido bien o mal.
    resultado = system("killall firefox")
    if resultado
      puts "Se eliminó el proceso firefox"
    else
      # No se realizó la acción
    end
```

> **COMANDOS que pueden servir de ayuda**
> * `killall nombre-del-proceso` para eliminar todos los procesos con tal nombre.
> * `ps -e| grep nombre-proceso| grep -v color` para averiguar el PID de un proceso.
> * `ps -e| grep nombre-proceso| wc -l` muestra el número de líneas donde
aparece el texto buscado
> * `kill -9 PID` para emitir una señal de cierre del proceso PID.

* El script debe actuar de forma NO interactiva. No debe hacer preguntas
al usuario durante su ejecución. La información de entrada se leerá del fichero de configuración propuesto.

# 4. Programar la tarea

## 4.1 Funciones

* Mover el código de control dentro de una función (`controlar_procesos`)
que reciba como parámetros: el nombre-del-proceso y la acción. Veamos ejemplo:

```
def controlar_procesos(name, action)
   ...
   Escribir aquí el código necesario para ejecutar la acción sobre el proceso
   ...
end
```

## 4.2 Ejecución periódica

* Invocar el script con el comando watch para ejecutar el script cada 5 segundos.

> **Curiosidad:**
>
> Veamos ejemplo de bucle infinito en el script:
> ```
> while(true) do
>    ...
>    ejecutar acciones dentro del bucle que se repetirán eternamente
>    ...
>    sleep(5) #esperar 5 segundos antes de volver a repetir el bucle
> end
> ```

## 4.3 Programar la tarea

* Configurar crontab para ejecutar el script de forma periódica cada minuto.
* Comprobar los resultados.

# ANEXO

# Control de parada

* Justo antes de empezar el bucle infinito, el script debe crear
un fichero vacío con el nombre `state.running`. Este fichero
va a ayudarnos a parar nuestro bucle infinito.
* El bucle infinito de control sólo se detendrá cuando eliminemos dicho fichero.

> Esto es, modificar la condición del bucle `while true`...
> para que sea algo como `while(File.exist? "state.running")`.
