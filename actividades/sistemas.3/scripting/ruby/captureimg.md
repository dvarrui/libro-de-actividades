
```
Curso      : 201920
Requisitos : GNU/Linux, Ruby
Objetivo   : Ejercicio de scripting de sistemas con Ruby
```

---
# Control de Software (softwarectl)

| ID | Función                | Adecuado | Bien | Insuficiente |
| -- | ---------------------- | -------- | ---- | ------------ |
| 01 | captureimg             | 1        | 0    | 0            |
| 02 | captureimg --help      | 1        | 1    | 0            |
| 03 | captureimg --version   | 1        | 0    | 0            |
| 04 | captureimg NUMBER TIME | 7        | 4    | 0            |

---
## Entrega

* Vamos a crear un script ruby llamado **captureimg**.
* La entrega la realizaremos a través del repositorio Git.
* Una vez subida al repositorio, etiquetaremos la entrega con *captureimg*.

---
## Funcionamiento

**Opción 01**
* El script se llamará **captureimg**.
* Si el script se ejecuta **sin parámetros**, se mostrará un mensaje aconsejando usar la opción "--help" para ver la ayuda.

> Para leer los argumentos en Ruby usamos la variable ARGV.

**Opción 02**
* Si el script se ejecuta con el parámetro **--help** se mostrará la ayuda, que tendrá la siguiente forma:

```
Usage:
        captureimg NUMBER [TIME]
Options:
        --help        , mostrar esta ayuda.
        --version     , mostrar información sobre el autor del script
                        y fecha de creacion.
        NUMBER [TIME] , captura NUMBER imágenes cada TIME segundos.

Description:

        Este script se encarga de capturar NUMBER imágenes del escritorio
        cada TIME segundos y las guarda en un directorio "captureimg.d".
```

**Opción 03**
* Si el script se ejecuta con la opción **--version**, se muestra en pantalla información del autor del script y la fecha de creación.

**Opción 04**
* Se capturarán un número (NUMBER) de imágenes en intervalos de (TIME) segundos.
    * Un comando para capturar imágenes de pantalla puede ser este: `convert x:root image.png`. También se podría usar el programa `scrot`.
    * En Ruby una pausa de 1 segundo es `sleep(1)`.
* Las imágenes se guardarán en el directorio local `captureimg.d` con el nombre `image01.png`, `image02.png`, etc.
* Si la imagen N es igual a la imagen anterior (N-1) entonces no se guarda.
    * Se puede usar el comando `diff` del sistema para comparar dos ficheros y y comprobar si son iguales o no.
    * Si son iguales el comando `diff` devuelve código de salida 0, en caso contrario devuelve 1.
* En argumento NUMBER es obligatorio para saber el número de capturas que hay que realizar.
* El argumento TIME que indica el número de segundos entre las capturas es opcional. En el caso de que no se pase por parámetro se asume el valor por defecto de 5 segundos.

---
## Estilo de programación

Se valorará lo siguiente:
* Creación de funciones para agrupar bloques de código con una función definida.
* Incluir comentarios que ayuden a entender el código y hacerlo más legible.
    * Autor y versión.
    * Descripción
    * Comentar los bloques del script y/o funciones.
* Estilo:
    * El sangrado en Ruby es de 2 espacios.
    * No dejar líneas en blanco o vacías, a menos que sirvan para indicar la separación entre bloques del programa.
    * Las variables y nombres de funciones van en minúsculas.
    * Las variables son sujetos y las funciones verbos.
    * Escoger nombres adecuados para las variables y funciones.
