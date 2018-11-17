package david.dir200607.ejemplo0301;

import java.util.Date;

public class Persona {
	private String nombre;

	private String apellido1;

	private String apellido2;

	private Date fechaNacimiento;

	private String dni;

	private String telefono1;

	private String correoElectronico;

	Persona() {
		
	}
	
	Persona(String nombre, String apellido1, String telefono1) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.telefono1 = telefono1;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getNombreCompleto() {
		return nombre + " " + apellido1 + " " + apellido2;
	}

	public boolean esMayorDeEdad() {
		return true;
	}

	public boolean equals(Persona p) {
		if (!this.nombre.equals(p.nombre)) return false;
		if (!this.apellido1.equals(p.apellido1)) return false;
		if (!this.apellido2.equals(p.apellido2)) return false;
		return true;
	}
}
