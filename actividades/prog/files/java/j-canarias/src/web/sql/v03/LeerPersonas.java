package web.sql.v03;

import java.sql.*;

public class LeerPersonas {

	public static void main(String[] args) {
		try {
			BaseDatos bd = new BaseDatos();
			bd.abrir();

			Connection con = bd.getConexion();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM personas");
			
			Persona p = new Persona();
			
			while(rs.next()) {
				p.setCodigo(rs.getInt("CODIGO"));
				p.setApellido1(rs.getString("APELLIDO1"));
				p.setApellido2(rs.getString("APELLIDO2"));
				p.setNombre(rs.getString("NOMBRE"));
				p.mostrar();
			}
			
			rs.close();
			st.close();
			
		} catch (Exception e) {
			System.err.println(" [ERROR] " + e);
		}
	}

}
