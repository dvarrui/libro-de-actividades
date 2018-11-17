/* Proyecto TENERIFE */
/* Fecha:Tue Feb 07 22:55:26 WET 2006 */

CREATE TABLE usuarios
{
   cod_usuario   INTEGER   PRIMARY KEY,
   des_usuario   TEXT,
   password   TEXT,
   cod_perfil   INTEGER,
   fec_desde   DATE,
   fec_hasta   DATE,
   activo   BOOLEAN
}

