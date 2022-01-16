# Instalación de VBox Guest Additions en GNU/Linux

Instalar las Guest Additions en sistemas GNU/Linux corriendo en una máquina virtual de VirtualBox:

1. Ir al menú **Dispositivos** de la máquina virtual y seleccionar **Insertar imagen de CD ded las Guest Additions**:

   ![](screenshot01.png)

2. Abrir un terminal y ejecutar en orden los siguientes comandos:

    ```bash
    sudo apt-get update
    sudo apt-get install dkms
    cd /media/<usuario>/VBox_GAs_x.y.z/
    sudo ./VBoxLinuxAdditions.run 
    sudo reboot
    ```

> Reemplazar `<usuario>` con tu nombre de usuario. Por ejemplo, en mi caso mi usuario es **idp** y me quedaría así:
>    ```bash
>    cd /media/idp/VBox_GAs_x.y.z/
>    ```

Siendo **x.y.z** la versión de VirtualBox que estás utilizando.

