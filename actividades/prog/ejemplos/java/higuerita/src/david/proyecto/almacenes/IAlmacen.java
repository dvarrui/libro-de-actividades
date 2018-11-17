//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes;

import java.util.ArrayList;
import david.proyecto.entidades.IEntidad;

public interface IAlmacen {

   public boolean open();

   public boolean close();

   public ArrayList getAll();

   public IEntidad getById(int id);

   public ArrayList getFind(IEntidad desde, IEntidad hasta);

   public boolean add(IEntidad registro);

   public boolean delete(IEntidad registro);

}

