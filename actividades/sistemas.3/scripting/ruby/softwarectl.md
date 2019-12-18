
```
Curso      : 201920
Requisitos : GNU/Linux, Ruby
```

---
# Control de Software (softwarectl)

| Función             | Peso |
| ------------------- | ---- |
| softwarectl         | 1    |
| softwarectl --help    | 1  |
| softwarectl --version | 1  |
| softwarectl --status FILENAME | 7 |
| softwarectl --run FILENAME | 10 |

## Entrega

* Vamos a crear un script ruby llamado **softwarectl**.
* La entrega la realizaremos a través del repositorio Git.
* Una vez subida al repositorio, etiquetaremos la entrega con *softwarectl*.

## Funcionamiento

* El script se llamará **softwarectl**.
* Si el script se ejecuta **sin parámetros**, se mostrará un mensaje aconsejando usar la opción "--help" para ver la ayuda.
* Si el script se ejecuta con el parámetro **--help** se mostrará la ayuda, que tendrá la siguiente forma:
```
Usage:
        systemctml [OPTIONS] [FILENAME]
Options:
        --help, mostrar esta ayuda.
        --version, mostrar información sobre el autor del script
                   y fecha de creacion.
        --status FILENAME, comprueba si puede instalar/desintalar.
        --run FILENAME, instala/desinstala el software indicado.
Description:

        Este script se encarga de instalar/desinstalar
        el software indicado en el fichero FILENAME.
        Ejemplo de FILENAME:
        tree:install
        nmap:install
        atomix:remove
```
* Si el script se ejecuta con la opción **--version**, se muestra en pantalla información del autor del script y la fecha de creación.
* Si el script se ejecuta con la opción **--status FILENAME**, entonces se lee el contenido del fichero FILENAME, y para cada PACKAGENAME se muestra en pantalla su estado actual. Esto es, `(I) installed` o `(U) uninstalled`.
* Si el script se ejecuta con la opción **--run FILENAME**:
    * Primero se comprueba que se seamos el usuario `root`. En caso contrario se mostrará un mensaje de advertencia en pantalla y se finaliza el script con error (`exit 1`).
    * Segundo se lee el contenido del fichero `FILENAME`, y para cada PACKAGENAME se procede a ejecutar la acción asociada, que puede ser "install" o "remove".
* El fichero **FILENAME** es un fichero de texto con tantas líneas como paquetes de software. Cada línea tendrá el siguiente contenido `PACKAGENAME:ACTION`. Donde:
    * **PACKAGENAME** es el nombre del paquete o software.
    * **ACTION** podrá ser `install` o `remove`.

## Consejos técnicos

* El script leerá el contenido del fichero de entrada, y lo cargará en un array para luego procesarlos.
* Para saber si tenemos un programa instalado o no...
    * `whereis PACKAGENAME |grep bin |wc -l`
    * `zypper se nmap|grep 'i '|wc -l`
    * Si el resultado es 0 -> NO está instalado
    * Si el resultado es mayor que 0 -> SI está instalado
* Para instalar software en OpenSUSE podemos usar `zypper install -y tree` o incluso `apt install -y tree` que además también vale en Debian/Ubuntu.
* El script debe actuar de forma NO interactiva. No debe hacer preguntas al usuario durante la ejecución.
* Antes de realizar cada acción comprobar:
    * Si la acción es "eliminar software" hay que comprobar si el paquete está instalado y por tanto se puede desinstalar. Si no se puede hay que mostrar un mensaje en pantalla "software desinstalado".
    * Si la acción es "instalar software" hay que comprobar si el paquete no está instalado. En caso contrario hay que mostrar un mensaje en pantalla de que el "software ya está instalado".

---
# ANEXO

Para saber en que SO estamos podemos usar la gema [os](https://github.com/rdp/os):
* `gem install os` para instalar la gema.
* [Información de la gema os](https://github.com/rdp/os).

Ejemplo con irb:
```
> irb
irb> require 'os'

irb> puts OS.linux?
true

irb> puts OS.report

---
arch: x86_64-linux-gnu
target_os: linux-gnu
target_vendor: suse
target_cpu: x86_64
target: x86_64-suse-linux-gnu
host_os: linux-gnu
host_vendor: suse
host_cpu: x86_64
host: x86_64-suse-linux-gnu
RUBY_PLATFORM: x86_64-linux-gnu
=> nil
```
