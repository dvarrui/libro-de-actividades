/* Proyecto TENERIFE */
/* Fecha:Tue Feb 07 22:55:26 WET 2006 */

CREATE TABLE citas
{
   cod_cita   INTEGER   PRIMARY KEY,
   cod_agenda   INTEGER,
   cod_persona   INTEGER,
   fecha   DATE,
   cod_catalogo   INTEGER,
   realizado   BOOLEAN
}

