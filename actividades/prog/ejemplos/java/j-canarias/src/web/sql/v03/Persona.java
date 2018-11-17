package web.sql.v03;

public class Persona {
	private String apellido1;
	private String apellido2;
	private int codigo;
	private String nombre;

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void mostrar() {
		System.out.println("[Persona]="+codigo+"|"+apellido1+"|"+apellido2+"|"+nombre);
	}
	
}
