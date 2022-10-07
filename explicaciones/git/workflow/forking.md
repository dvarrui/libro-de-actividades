[<< back](README.md)

# Forking workflow (flujo de bifurcación)

> Enlace de interés:
> * https://www.atlassian.com/es/git/tutorials/comparing-workflows/forking-workflow

* Tenemos: repositorio base en el servidor

| ID       | Lugar  | Repositorio      | Responsable   |
| -------- | ------ | ---------------- | ------------- |
| base     | server | Público oficial  | Mantenedor    |
| dev-pub  | server | Público personal | Desarrollador |
| dev-priv | local  | Privado local    | Desarrollador |

* Los desarrolladores envían los cambios a sus propios repositorios en servidor y solo el mantenedor del proyecto puede enviar al repositorio oficial. Esto permite al mantenedor aceptar confirmaciones de cualquier desarrollador sin darle acceso de escritura al código base oficial.

1. Un desarrollador **"bifurca" un repositorio "oficial" en servidor**. Esto crea su propia copia en servidor.
1. La nueva copia en servidor se clona en su sistema local.
1. Se añade una ruta remota de Git para el repositorio "oficial" al clon local.
1. Se crea una nueva rama de función local.
1. El desarrollador hace cambios en la nueva rama.
1. Se crean nuevas confirmaciones para los cambios.
1. La rama se envía a la copia en servidor del desarrollador.
1. El desarrollador abre una **solicitud de incorporación de cambios desde la nueva rama al repositorio "oficial"**.
1. La solicitud de incorporación de cambios se aprueba para la fusión y se hace la fusión en el repositorio en servidor original.
