
```
Versión OBSOLETA!
Pendiente de actualizar
```

---

# Servicios en Windows

Entrega
* Entregar en formato HTML vía repositorio GIT.

---
# 1. Servicios en Windows

Desde las herramientas administrativas del entorno gráfico realizar las siguientes acciones:
* Consultar los servicios instalados en el sistema (Comando services.msc).
* Consultar los servicios activos. Desde entorno gráfico y con comandos ("net start", "tasklist /svc").
* Parar un servicio determinado.
* Comprobar que el proceso del servicio está en ejecución.
* Iniciar un servicio determinado.
* ¿Cómo podemos modificar las propiedades de inicio/parada, etc. de un servicio?

Responder
* ¿Qué es un servicio? Definición.
* Lista 3 servicios, e indica sus funciones.

---
# 2. Script Power Shell

Crear script "HolaMundo!" de PowerShell:
* Abrir una consola PowerShell con permisos administrador. Ejecutar el comando "Get-ExecutionPolicy" y comprobar que ésta en modo Restricted. Esto quiere decir que sólo podremos ejecutar scripts certificdos y firmados.
* Vamos a cambiar la política de ejecución con el comando: "Set-ExecutionPolicy RemoteSigned". Verificarlo con "Get-ExecutionPolicy". Ahora podremos ejecutar nuestros scripts sin problemas aunque hemos reducido la seguridad del sistema.
* Abrir PowerShell ISE para crear/editar/probar el script.

---
# 3. Comandos de PowerShell

Comandos de PowerShell para:
* Consultar los servicios instalados en el sistema (Get-Service)
* Consultar los servicios activos. Probar comandos:
```
get-service | where-object {$_.status -eq"running"}
tasklist /svc
```
* Parar un servicio determinado (comando Stop-Service).
* Iniciar un servicio determinado (comando Start-Service).
* Desde el entorno gráfico, comprobar que el proceso del servicio está en ejecución.

---

# Anexo

* El comando "netstat" nos da información de los programas que están haciendo uso de los puertos del equipo.
* El comando "service --status-all" en GNU/Linux nos muestra información de todos los servicios.

Control de procesos
Realizar las siguientes tareas:

    Analizar los procesos con el Monitor del sistema (Entorno gráfico).
    Abrir un terminal (Aplicaciones ­­> Accesorios ­­> Terminal). Buscar en el "Monitor del sistema" el proceso del terminal y pararlo. Comprobar el efecto.
    Volver a iniciar el proceso. Usando los comandos, matar el proceso y comprobar el efecto.
    Ejecutar el comando "gcalctool", "bash" o "gnome-terminal", desde un terminal.
    Pasar el proceso a modo no­interactivo (Background).
    Devolverlo a modo interactivo (foreground).
    Matar el proceso en el propio terminal.
    Ejecutar nuevamente los comandos/programas "gcalctool", "bash" o "gnome-terminal", con el & tras el comando.
    Buscar el ID del proceso y matarlo con el comado kill o killall.

Arranque y parada del sistema
Realizar las siguientes tareas:

    Ejecutar el comando shutdown para que el sistema reinicie automáticamente en 2 minutos enviando el mensaje “Reiniciamos en 2 minutos”.
    Cancele el comando shutdown anterior desde un terminal distinto (dese prisa).
    Desde una consola del sistema, reinicie el servidor web (apache2) y el servidor SSH (ssh) empleando los scripts adecuados de /etc/init.d
    Desde una consola del sistema, cambie al runlevel número 1. Luego, detenga el sistema con el comando halt.
