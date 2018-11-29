

# Servidor de Impresión GNU/Linux (CUPS)

---

# 1. Preparativos

Enlace de interés:
* [Vídeo LPIC-1 102 Printing using CUPS](https://youtu.be/6M4oGNn9cVc)

---

# 2. Servidor de Impresión

Instalar el sistema de impresión CUPS para GNU/Linux.

* Verificar que el servicio está en ejecución.
* A continuación, conectar a la interfaz web de CUPS.
* Acceder a la sección de Administración, y dentro de ahí a la
parte de "Ver archivo de registro de accesos".

---

# 3. Impresora Virtual PDF

Ahora vamos a configurar una impresora de forma local en el servidor de impresión. El paquete "cups-pdf" nos instala una impresora virtual PDF, uqe podemos usar para las pruebas en caso de no disponer de una impresora real.

Los trabajos de impresión de la impresora virtual PDF se guardan en alguno de estos directorios:
```
    /home/usuario/PDF
    /var/spool/cups-pdf/anonymous
```

> NOTA: Además del interfaz de CUPS, también podemos hacer uso
de la herramienta que proporciona en entorno de escritorio GNOME
en "Sistema -> Administración -> Impresión".

---

# 4. Cliente de impresión

> NOTA: Aunque lo indiquen los apuntes, para nuestras pruebas,
NO es necesario crear el fichero `/etc/cups/client.conf `
en la máquina cliente.

* Ir al servidor.
* Habilitamos la impresora como recurso de red compartido.
* Desde otra máquina hacemos uso de la impresora de red.
