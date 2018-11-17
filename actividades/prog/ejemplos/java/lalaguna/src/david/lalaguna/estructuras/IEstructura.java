// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006

package david.lalaguna.estructuras;

public interface IEstructura {

	public Object clone();

	public boolean equals(IEstructura estructura);

	public void fromCadena(String pTexto);

	public String toCadena();

}
