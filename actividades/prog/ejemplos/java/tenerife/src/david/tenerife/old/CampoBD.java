
package david.tenerife.old;

/**
 * @author david
 */
public class CampoBD {
	private String tabla;
	private String nombre;
	private String tipo;

	/**
	 * @return Devuelve nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            El nombre a establecer.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTabla() {return tabla;}

	public void setTabla(String tabla) {this.tabla = tabla;	}

	public String getTipo() {return tipo;}

	public void setTipo(String tipo) {this.tipo=tipo;}

	/**
	 * @return Devuelve tipoJava.
	 */
	public String getTipoJava() {
		if (tipo.startsWith("int")) return "int";
		if (tipo.startsWith("float")) return "float";
		if (tipo.startsWith("real")) return "float";
		if (tipo.startsWith("date")) return "Date";
		if (tipo.startsWith("time")) return "Date";
		if (tipo.startsWith("text")) return "String";
		if (tipo.startsWith("var")) return "String";

		return "*Error en el tipo("+tipo+")*";
	}

	public String getJavaAtributo() {
		String v = this.normalizar(nombre);
		String resultado = v.substring(0, 1).toLowerCase()
				+ v.substring(1);

		return resultado;
	}

	public String getJavaClase() {
		String v = this.normalizar(tabla);
		String resultado = v.substring(0, 1).toUpperCase()
				+ v.substring(1);

		return resultado;
	}
	
	public String getJavaMetodo() {
		String v = this.normalizar(nombre);
		String resultado = v.substring(0, 1).toUpperCase()
				+ v.substring(1);

		return resultado;
	}

	public String getJavaMetodoResultSet() {
		String v = this.normalizar(this.getTipoJava());
		String resultado = v.substring(0, 1).toUpperCase()
				+ v.substring(1);

		return resultado;
	}

	public String toString() {
		return "[CampoBD] "+tabla+"."+nombre+" "+tipo;
	}
	
	
	/**
	 * @param variable
	 * @return Si variable=log_avisos devuelve logAvisos
	 */
	private String normalizar(String variable)
	{
		StringBuffer resultado = new StringBuffer();
		for(int i=0; i<variable.length();i++)
		{
			if (variable.charAt(i)!='_')
			{
				if (i>0)
				{
					if (variable.charAt(i-1)=='_') 
						resultado.append(variable.substring(i,i+1).toUpperCase());
					else
						resultado.append(variable.charAt(i));
				}
				else
					resultado.append(variable.charAt(i));
			}
		}
		return resultado.toString();
	}
}