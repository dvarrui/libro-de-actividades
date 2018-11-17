// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006

package david.proyecto.entidades;


public interface IEntidad {

   public String toCadena();
   public void fromCadena(String pTexto);

   public String getDescripcion();
   public String getClave();

   public int getNumeroCampos();
   public Object getCampo(int index);

   public boolean equals(IEntidad registro);

}


