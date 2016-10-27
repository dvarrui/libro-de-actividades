
#Cortafuegos (Firewall)

El cortafuegos a firewall es un software que controla las comunicaciones,
permitiendo/denegando las entradas/salida de red según especifiquemos en las
reglas de seguridad.

##1. Windows 7

Abrir el cortafuegos de Windows para permitir que funcionen los `ping`
(`echo request` y `echo replay`).

* Ir a `Panel de control -> Firewall`
* Comprobar que el cortafuegos está activo.
* Ir a `Configuración avanzada` y modificar las `Reglas de entrada`  y `Reglas de salida`
como se muestra en las siguientes imágenes.

![w7-firewall-ping-entrada](./images/w7-firewall-ping-entrada.png)

![w7-firewall-ping-salida](./images/w7-firewall-ping-salida.png)

##2. Windows Server

`pendiente`

##3. OpenSUSE

* Ir a `Yast -> Firewall`
* Mantendremos el cortafuegos activo.
* Permitiremos el acceso externo únicamente a los servicios que necesitemos.
* Ir a `Servicios autorizados -> Zona externa`
