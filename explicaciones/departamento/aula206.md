
```
* Documento sobre la configuración del aula 206
``` 

#Aula 206

#Historia

|Fecha       | Acción |
|----------- | ------ |
| 2016-06-02 | Se instala el equipo `PC1` del aula 206 , con GNU/Linux |

#Inventario

* prueba

|PC | RAM |
|-- | --- |
| 1 | 4 GB |

*otros

   
|PC | RAM |HDD    |MAC |CPU |Estado |
|-- |---- |------ |----|--- |------ |
| 1 |4 GB |250 GB |00-18-71-71-10-f5 | Pentium 3.2 GHz | Pdte. activar VTx en BIOS |



| 2 | 4 GB | 250 GB | 00-40-f4-64-f8-01, 00-18-71-71-11-43 |  | Tiene 2 tarjetas de red |
| 3 | GB | GB |  |  | Pendiente |
| 4 | GB | GB |  |  | Pendiente |
| 5 | GB | GB |  |  | Pendiente |
| 6 | GB | GB |  |  | Pendiente |
| 7 | GB | GB |  |  | Pendiente |
| 8 | GB | GB |  |  | Pendiente |
| 9 | GB | GB |  |  | Pendiente |
|10 | GB | GB |  |  | Pendiente |
|11 | GB | GB |  |  | Pendiente |

#Configuración

* Se instala el sistema operativo Xubuntu 14.4 LTS
* Se instala el software según este [script](./files/script-instalar-aula206.md).

#Clonación

* En `/etc/fstab`, modificar montaje de la swap con nombre dispositivo en lugar de UUID.
    * Para facilitar las clonaciones.
* Realizamos la clonación con Clonezilla.
