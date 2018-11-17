package web.sql.v04;

import java.util.*;

public class Ejemplo {

	public static void main(String[] args) {
		try {
			BaseDatos bd = new BaseDatos();
			bd.abrir();

			//-----------------------
			//Crear un nuevo registro
			Persona p = new Persona();
			p.setCodigo(16);
			p.setApellido1("probando1");
			p.setApellido2("probando2");
			p.setNombre("probando");
			
			p.insert(bd.getConexion());
			
			//-------------------------
			//Borrar un registro creado
			p.setCodigo(1);
			p.setApellido1("probando1");
			p.setApellido2("probando2");
			p.setNombre("probando");
			p.delete(bd.getConexion());
			
			//----------------------------------
			//Leer todo el contenido de la tabla
			ArrayList<Persona> a = Persona.select(bd.getConexion());
			
			for(int i=0; i<a.size();i++) {
				Persona p2 = (Persona) a.get(i);
				p2.mostrar();
			}
			
			bd.cerrar();
		} catch (Exception e) {
			System.err.println(" [ERROR] " + e);
		}
	}

}
