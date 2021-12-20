
# Scripting

* [Un poco de teoría](teoria.md)
* [Paso a paso](paso-a-paso.md)
* [Más ideas](ideas.md)

## Modo de trabajo

* El alumno realizará el trabajo y sus pruebas en una máquina virtual.
* Cada MV debe estar configurada de la siguiente forma:
    * IP fija de la forma `172.19.XX.35`.
    * Servidor SSH activo y configurado para permitir el acceso de `root`.
    * La clave de `root` se debe pasar al profesor, o en su defecto añadir la clave pública del profesor al fichero `/root/.ssh/authorized_keys` de la MV.
* El profesor irá realizando una evaluación remota del trabajo que se va realizando cada día (Test de infraestructura - teuton).
* Por ese motivo, 15 minutos antes del término de la sesión del día el alumno debe dejar el script en una MV "limpia". Esto es, una MV donde no se ha ejecutado el script.
* El terminar la corrección remota, el alumno tendrá un feedback en el ficher `/root/case-XX.txt` o `/root/case-XX.html`.
