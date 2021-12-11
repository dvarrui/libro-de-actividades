
```
Curso       : 202122, 202021, 201920, 201819, 201718
Area        : Sistemas operativos, servidor de impresión
Descripción : Practicar con servidor de impresión
Requisitos  : SO GNU/Linux
Tiempo      : 3 sesiones
```

---
# Servidor de Impresión GNU/Linux (CUPS)

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (2) Comprobar que el Servicio está en ejecución | | | |
| (3) Comprobar que se imprime de forma local  | | | |
| (4) Comprobar que se imprime de forma remota | | | |

# 1. Preparativos

Enlace de interés:
* [Vídeo LPIC-1 102 Printing using CUPS](https://youtu.be/6M4oGNn9cVc)

# 2. Servidor de Impresión

Instalación:
* Instalar el sistema de impresión CUPS para GNU/Linux.
* `systemctl status ...`, verificar que el servicio está en ejecución.

Configuración:
* Configurar CUPS `/etc/cups/cupsd.conf` (Ver vídeo). `Allow @LOCAL` significa que vamos a permitir el acceso al servicio vía Web del servidor a todas las máquinas de la red local (LAN).

```
Listen *:631
...
<Location />
  Order allow,deny
  Allow @LOCAL
</Location>

<Location /admin>
  ...
  Allow @LOCAL
</Location>

<Location /admin/conf>
  ...
  Allow @LOCAL
</Location>

<Location /admin/log>
  ...
  Allow @LOCAL
</Location>
```

* `systemctl restart cups`

Cortafuegos:
* Abrir en el cortafuegos el acceso al servicio de impresión `ipp`. En el cortafuegos hay varias zonas, para saber la que tenemos activa hacemos `firewall-cmd --get-default-zone`. Seguramente la zona por defecto será `public` pero hay que comprobarlo.

Panel web de CUPS:
* A continuación, conectar a la interfaz web de CUPS.
* Acceder a la sección de `Administración` con el **usuario/clave de root**. Desde ahí acceder a la sección `Ver archivo de registro de accesos`. Esto sólo es para comprobar que podemos acceder correctamente vía interfaz web.

# 3. Imprimir de forma local

Ahora vamos a usar una impresora de forma local en el servidor de impresión.

* Instalar el paquete `cups-pdf` que nos permite hacer uso de una impresora virtual PDF local. Usaremos esta impresora virtual para las pruebas en caso de no disponer de una impresora real.
* La impresora debe estar configurada como impresora por defecto.
* Crear una archivo "impresionXXlocal.txt" con algún contenido.
* Imprimir el documento en la impresora local.
* Comprobar el resultado. Los trabajos de impresión de la impresora virtual PDF se guardan en alguno de estos directorios:

```
/home/usuario/PDF
/var/spool/cups-pdf/anonymous
```

> NOTA: Además del interfaz de CUPS, también podemos hacer uso de la herramienta
que proporciona en entorno de escritorio GNOME en `Sistema -> Administración -> Impresión`.

* Ver contenido del PDF.

# 4. Imprimir de forma remota

> NOTA: Aunque lo indiquen los apuntes, para nuestras pruebas,
NO es necesario crear el fichero `/etc/cups/client.conf`
en la máquina cliente.

Ir al servidor.
* Habilitamos la impresora como recurso de red compartido.

> Es importante que el cliente tenga una IP definida en la cláusula Allow del servidor. Esto lo hicimos cuando especificamos "Allow @LOCAL".

Ir a un cliente.
* Abrir en el cortafuegos el acceso al servicio de impresión `ipp-client`. En el cortafuegos hay varias zonas, para saber la que tenemos activa hacemos `firewall-cmd --get-default-zone` (Seguramente la zona por defecto será `public`).
* Iniciar la herramienta "configuración de impresión". Desbloqueamos con la clave de root.
* Agregar impresora de red. Primero la buscamos en IP del servidor y nos debe aparecer automáticamente (Por ejemplo `ipp://ip-server:631/printers/CUPS-PDF`).
* Crear una archivo "impresionXXremota.txt" con algún contenido.
* Imprimir el documento en la impresora remota.

Ir al servidor.
* Comprobamos que se ha realizado la impresión remota.
* Ver contenido del PDF.
