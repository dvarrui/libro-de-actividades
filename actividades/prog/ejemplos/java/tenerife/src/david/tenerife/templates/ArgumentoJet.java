package david.tenerife.templates;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.Tabla;

/**
 * Se utiliza esta clase para pasar varios argumentos a las plantillas JET
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 200512130
 *
 */
public class ArgumentoJet {
	private BaseDatos basedatos;
	private Tabla tabla;
	
	public BaseDatos getBaseDatos() {
		return basedatos;
	}
	public void setBaseDatos(BaseDatos basedatos) {
		this.basedatos = basedatos;
	}
	public Tabla getTabla() {
		return tabla;
	}
	public void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}
}
