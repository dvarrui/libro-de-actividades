
#Taller Mejorar

* Realizar la siguiente práctica por grupos.
* Realizar un vídeo y editarlo adecuadamente según las indicaciones del profesor.
* Subir el vídeo a YouTube y entregar el URL del vídeo.

##Paso 1
* Recoger un ordenador asignado por el profesor. Debe tener una identificación pegada. Por ejemplo `TH16`.
* Recoger la ficha del equipo `TH16`. 
* Comprobar que el PC funciona correctamente. Capturar imagen.
* Comprobar si las características del equipo según la documentación coinciden con el PC real.
* Incluir detalles de las características en el informe:
    * Modelo de BIOS
    * Memoria RAM
    * CPU
    * Discos duros
    * etc.
* Apagarlo y desenchufar el cable de corriente.
* Rellenar la ficha papel que les entregará el profesor.
* Dicha ficha hay que completarla:
   * Miembros involucrados.
   * Incidencias detectadas al recibir el equipo.
   * Con las actuaciones realizadas, o referencia al vídeo realizado.
   * Incidencias no resueltas al entregar el equipo. 

##Paso2
* Desmontar el PC.
* Capturar imagen de todos los componentes desmontados.

##Paso3
* Volver a montarlo.
* Comprobar que funciona correctamente. Capturar imagen.

##Paso4
* Mejorar el equipo añadiendo:
    * HDD
    * RAM
    * 2º tarjeta gráfica
    * 2ª tarjeta de red
    * 2ª unidad DVD
* Comprobar que funciona correctamente. Capturar imagen.

##Paso5
* Instalar el SO OpenSUSE 13.2 con la siguiente configuración:
    * Esquema de particiones:
        * 1 partición extendida 
        * 1 partición lógica de 2GB para swap
        * 1 partición lógica de 20GB para /
        * 1 partición lógica de 10GB para /home
        * Dejar el resto sin usar (por ahora)
    * Usuarios:
        * Usuario profesor con clave profesor
        * Usuario root con clave profesor
    * Entorno gráfico a elegir por el alumno
    * Nombre de equipo: `THXX` seǵun la etiqueta del mismo.
    * NOmbre de dominio: `taller`
    * Configuración de red:
        * IP estática: 172.19.107.XX, donde XX es el número del equipo.
        * Máscara: 255.255.0.0
        * Enlace: 172.19.0.1
        * DNS: 8.8.4.4
    * Instalar y activar OpenSSH
    * Configuración de red dinámica

> Al terminar la actividad hay que dejar la ficha en su sitio, y el PC recogido y en donde le corresponde.

