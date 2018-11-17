//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.lalaguna.almacenes;

import java.util.ArrayList;
import david.lalaguna.entidades.IEntidad;
import david.lalaguna.estructuras.IEstructura;

public interface IAlmacen {

	public boolean add(IEstructura estructura);

	public boolean close();

	public boolean delete(IEstructura estructura);

	public ArrayList getAll();

	public IEntidad getByLongId(long id);

	public IEntidad getByStringId(String id);

	public ArrayList getFind(IEntidad desde, IEntidad hasta);

	public boolean open();

}
