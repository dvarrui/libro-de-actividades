

4. SetUID

Para ejecutar un programa con los permisos del creador del script, el autor debe activar el permiso UID sobre dicho programa/script:

# chmod u+s script.sh

Ahora podemos ejecutar el script con otro usuario, pero debemos tener en cuenta que no podemos usar la shell bash. En su defecto, pondremos la shell zsh. En la primera línea del script pondremos

`#!/bin/zsh`
