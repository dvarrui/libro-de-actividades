#!/usr/bin/env ruby

puts "[+] Creando el directorio <backupXXr>"
system "mkdir backupXXr"

echo "[+] Creando el fichero README"
system "touch backupXXr/README.txt"

puts "[+] Poniendo contenido al fichero README"
system("echo 'Nombre del responsable: OBIWAN' >> backupXXr/README.txt")
system("echo 'Este es el contenido del fichero README.txt' >> backupXXr/README.txt")

echo "[+] Fin del script #{$0}"
exit 0
