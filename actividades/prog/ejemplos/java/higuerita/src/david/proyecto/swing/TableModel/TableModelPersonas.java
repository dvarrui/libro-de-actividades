// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006

package david.proyecto.swing.TableModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import david.proyecto.entidades.Personas;
import david.proyecto.almacenes.IAlmacen;

// Comienza la clase
public class TableModelPersonas extends AbstractTableModel {
   static final long serialVersionUID = 1;//para evitar warnings
   
   private ArrayList datos;
   private IAlmacen almacen;

   // Constructor
   public TableModelPersonas(IAlmacen pAlmacen) {
      setAlmacen(pAlmacen);
   }
   
   // ---------------------
   // Método getColumnCount
   // ---------------------
   public int getColumnCount() {
      return (new Personas()).getNumeroCampos();
   }
   
   // --------------------
   // Método getColumnName
   // --------------------
   public String getColumnName(int index) {
      return Personas.getNombreCampo(index);
   }

   // ------------------
   // Método getRowCount
   // ------------------
   public int getRowCount() {
      return datos.size();
   }
   
   // -----------------
   // Método getValueAt
   // -----------------
   public Object getValueAt(int fila, int col) {
      Personas reg = (Personas) datos.get(fila);
      return reg.getCampo(col);
   }
   
   /*
    * Este metodo sirve para determinar el editor predeterminado
    * para cada columna de celdas
    */
   public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
   }

   /*
    * No tienes que implementar este método a menos que
    * las celdas de tu tabla sean Editables
    */
   public boolean isCellEditable(int row, int col) {
      return false;
   }
   
   /*
    * Otros Métodos
    */
   public void setAlmacen(IAlmacen almacen) {
      this.almacen = almacen;
      reset();
   }
   
   public void reset() {
      datos = this.almacen.getAll();
      this.fireTableDataChanged();
   }
}

