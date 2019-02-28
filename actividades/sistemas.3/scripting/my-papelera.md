
```
Última modificación: lunes, 1 de diciembre de 2014, 08:21
```

# Scripting: Mi Papelera

Papelera

# Descripción del ejercicio

Cuando utilizamos el comando “rm” en Linux, ya sea para eliminar directorios o ficheros, éstos son eliminados de forma permanente. En la interfaz gráfica (GNOME, KDE, etc.) disponemos de una papelera de reciclaje, de forma que cuando borramos algo, en lugar de eliminarlo, se envía a esta papelera. Así, si por error hemos eliminado algo, siempre podremos recuperarlo; a no ser que hayamos vaciado la papelera.

Este proyecto consiste en elaborar un scripts Ruby/BASH en GNU/Linux que nos permitan disponer de una papelera de reciclaje en toda regla:

* El nombre del script será papelera.
* La papelera de reciclaje será un directorio oculto en nuestro directorio de usuario (HOME). Es decir, si nosotros somos el usuario “user”, nuestra papelera será /home/user/.papelera (recuerda que los ficheros y directorios ocultos empiezan por “.”). El directorio HOME del usuario que ejecuta el script lo podemos sacar de la variable $HOME.
* La sintaxis del script es la siguiente:
```
papelera [ --help | -r file [ destino ] | --info | --clean | file ]
```

El funcionamiento del script será el siguiente:
* --help = Muestra la ayuda, explicando para qué sirve el script, sus distintas opciones y cómo se utiliza.
* -r fichero destino = Recupera un fichero o directorio, es decir, mueve el “fichero” (o directorio) de la papelera al directorio de “destino”. Si no se especifica el destino, se deberá utilizar el directorio actual.
* --info = Se mostrará un pequeño informe indicando el número de ficheros que hay en la papelera y el número de directorios (de forma separada).
* --clean = Elimina todo el contenido de la papelera de forma definitiva. Se mostrará un mensaje pidiendo confirmación para vaciar la papelera, a no ser que la papelera no exista. Si el usuario responde “si”, se vaciará, de lo contrario no se hará nada.
* -i = Se mostrará un menú que permitirá seleccionar entre vaciar la papelera (--clean) o mostrar las estadísticas (--info).
* file = Nombre del fichero o directorio que queremos enviar a la papelera. Si el directorio “.papelera” no existe, se deberá crear.
* Sin opciones = Se mostrará la forma de uso del script.

---

# NOTA

Se tendrá en cuenta que:
* Se verifiquen los parámetros especificados por el usuario y se controlen, en la medida de lo posible, los errores.
* Además, el script deberá poder ser utilizado por cualquier usuario del sistema sin necesidad de cambiar el código del mismo.
* Usaremos la estructura de control case, en lugar de múltiples if-then.
* Se recomienda instalar el editor geany, aunque valdría con cualquier editor de texto.
