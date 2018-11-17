package web.sql.v05.sql;

import java.sql.*;
import java.util.ArrayList;

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
	
	public boolean insert(Connection con) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO Personas(codigo,apellido2,apellido1,nombre) "
					+"VALUES('"+getCodigo()+"','"+getApellido2()+"','"
					+getApellido1()+"','"+getNombre()+"');");
			st.close();
			return true;
		} catch(Exception e) {
			System.err.println(" [ERROR] "+e);
			return false;
		}
	}
	
	public boolean delete(Connection con) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM Personas WHERE codigo='"+getCodigo()+"';");
			st.close();
			return true;
		} catch(Exception e) {
			System.err.println(" [ERROR] "+e);
			return false;
		}
	}
	
	public boolean update(Connection con) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE Personas SET "
					+" nombre='"+getNombre()+"',"
					+" apellido1='"+getApellido1()+"',"
					+" apellido2='"+getApellido2()+"' "
					+" WHERE codigo='"+getCodigo()+"';"
					);
			st.close();
			return true;
		} catch(Exception e) {
			System.err.println(" [ERROR] "+e);
			return false;
		}
	}

	public static ArrayList<Persona> select(Connection con) {
		return Persona.select(con,"SELECT * FROM Personas;");
	}
	
	public static ArrayList<Persona> select(Connection con, String comandoSQL) {
		ArrayList<Persona> lista = new ArrayList<Persona>();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(comandoSQL);
			
			while(rs.next()) {
				Persona p = new Persona();
				
				p.setCodigo(rs.getInt("CODIGO"));
				p.setApellido1(rs.getString("APELLIDO1"));
				p.setApellido2(rs.getString("APELLIDO2"));
				p.setNombre(rs.getString("NOMBRE"));
				
				lista.add(p);
			}
			
		} catch (Exception e) {
			System.err.println(" [ERROR] "+e);
		}
		return lista;
	}

}
