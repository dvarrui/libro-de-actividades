package david.tenerife.old;

import david.tenerife.util.*;
import david.tenerife.lib.*;

import java.io.*;
import java.util.Date;
/**
 * Versión anterior a 20051024
 * Funciona bien pero mezcla java y php -> separar
 * @author david
 *
 */
public class Generador {
	BaseDatos bd;

	Parametros param;

	public Generador(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	/**
	 * Generar las clases JAVA para los ALMACENES de datos
	 */
	private void generarAlmacenesJava() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				// Crear fichero java para el Almacén
				fich = new PrintWriter(param.getValor(Parametros.DIR_SALIDA)
						+ "/java" + "/Almacen"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				fich.println("//Proyecto TENERIFE");
				fich.println("//Fecha:" + fecha.toString());
				fich.println("//package " + param.getValor(Parametros.PROYECTO)
						+ ".almacenes\n");
				fich.println("import david.tenerife.sql.Conexion;");
				fich.println("import " + param.getValor(Parametros.PROYECTO)
						+ ".entidades." + Notacion.getPascal(t.getNombre())
						+ ";");
				fich.println("import java.sql.*;\n");

				fich.println("public class Almacen"
						+ Notacion.getPascal(t.getNombre()) + " {");
				fich.println("   public static final String NOMBRETABLA=\""
						+ t.getNombreOrigen() + "\";");
				fich.println("   private Conexion conexion;\n");
				fich.println("   public Almacen"
						+ Notacion.getPascal(t.getNombre())
						+ "(Conexion pConexion) {");
				fich.println("      conexion=pConexion;");
				fich.println("   }\n");

				// -------------
				// Método getAll
				// -------------
				fich.println("   public ArrayList getAll() {");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      ResultSet rs;");
				fich.println("      ArrayList lista=new ArrayList();");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " reg;\n");
				fich
						.println("      rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA);");
				fich.println("      while(rs.next()) {");
				fich.println("         reg = new "
						+ Notacion.getPascal(t.getNombre()) + "();");

				// Para cada campo debemos leer su valor
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("         reg.set"
							+ Notacion.getPascal(c.getNombre())
							+ "(rs.get"
							+ Notacion.getPascal(Notacion.getTipoJava(c
									.getTipo())) + "(" + j + "));");
				}
				fich.println("         lista.add(reg);");
				fich.println("      }");
				fich.println("      return lista;");
				fich.println("   }\n");

				//---------------
				// Método getById
				//---------------
				if (!t.hayClave()) {
					fich.println("   /*");
					fich.println("   //No existe un campo Primary Key");
					fich.println("   //Se puede borrar este método");
				}
				fich.println("   public " + Notacion.getPascal(t.getNombre())
						+ " getById(int id) {");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      ResultSet rs;");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " reg = new " + Notacion.getPascal(t.getNombre())
						+ "();");
				fich
						.print("      rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA+\" WHERE ");

				c = t.getCampoClave();
				fich.print(c.getNombreOrigen() + " = \"+id+\";\");");

				fich.println("      while(rs.next()) {");
				// Para cada campo debemos leer su valor
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("      reg.set"
							+ Notacion.getPascal(c.getNombre())
							+ " (rs.get"
							+ Notacion.getPascal(Notacion.getTipoJava(c
									.getTipo())) + "(" + j + "));");
				}
				fich.println("      }");
				fich.println("      return reg;");
				fich.println("   }\n");

				if (!t.hayClave()) {
					fich.println("   */");
				}
				// ---------------
				// Método getFind
				// ---------------
				fich.println("   public ArrayList getFind() {");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      ResultSet rs;");
				fich.println("      ArrayList lista=new ArrayList();");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " reg;\n");
				fich
						.println("      rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA);");
				fich.println("      while(rs.next()) {");
				fich.println("         reg = new "
						+ Notacion.getPascal(t.getNombre()) + "();");

				// Para cada campo debemos leer su valor
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("         reg.set"
							+ Notacion.getPascal(c.getNombre())
							+ "(rs.get"
							+ Notacion.getPascal(Notacion.getTipoJava(c
									.getTipo())) + "(" + j + "));");
				}
				fich.println("         lista.add(reg);");
				fich.println("      }");
				fich.println("      return lista;");
				fich.println("   }\n");

				// ----------
				// Método add
				// ----------
				fich.println("   public void add("
						+ Notacion.getPascal(t.getNombre()) + " registro) {");
				fich.println("      Statement st=conexion.getStatement();");
				fich
						.print("      st.executeUpdate(\"INSERT INTO \"+NOMBRETABLA+\n         \" (");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.print(c.getNombreOrigen());
					if (j + 1 < t.getNumeroCampos())
						fich.print(",");
				}
				fich.print(") \"+\n         \"");
				fich.print("VALUES ('\"");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("+registro.get"
							+ Notacion.getPascal(c.getNombre()) + "()+\"'\"");
					if (j + 1 < t.getNumeroCampos())
						fich.print("         ,\"'\"");
				}
				fich.println("         +\");\");");
				fich.println("      st.close();");
				fich.println("   }\n");

				// -------------
				// Método delete
				// -------------
				fich.println("   public void delete("
						+ Notacion.getPascal(t.getNombre()) + " registro) {");
				fich.println("      Statement st=conexion.getStatement();");
				fich
						.print("      st.executeUpdate(\"DELETE \"+NOMBRETABLA+\" WHERE \"+\n         ");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("\""+c.getNombreOrigen()+"='\"+registro.get"+Notacion.getPascal(c.getNombre()+"()+\"'\""));
					if (j + 1 < t.getNumeroCampos())
						fich.print("         +\" AND \"+");
				}
				fich.println("         +\";\");");
				fich.println("      st.close();");
				fich.println("   }\n");

				// ------------------
				// Cerrar el fichero
				// ------------------
				fich.println("}");
				fich.println();
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	public void generarCodigo() {
		if (param.getValor(Parametros.CODE_SALIDA).equals(Parametros.JAVA)) {
			// Se genera código de salida JAVA
			generarEntidadesJava();
			generarAlmacenesJava();
			generarEntidadesPHP();
		} else if (param.getValor(Parametros.CODE_SALIDA)
				.equals(Parametros.PHP)) {
			// Se genera código de salida PHP
			generarEntidadesPHP();
		} else
			System.err.println("Error");
	}

	private void generarEntidadesJava() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				// Crear fichero java para la entidad
				// new FileWriter(conf.leeString("dir.salida.java")
				fich = new PrintWriter(param.getValor(Parametros.DIR_SALIDA)
						+ "/java" + "/" + Notacion.getPascal(t.getNombre())
						+ ".java");
				fich.println("//Proyecto TENERIFE");
				fich.println("//Fecha:" + fecha.toString());
				fich.println("//package " + param.getValor(Parametros.PROYECTO)
						+ ".entidades\n");

				if (t.hayFecha())
					fich.println("import java.util.*;\n");
				fich.println("public class "
						+ Notacion.getPascal(t.getNombre()) + " {");

				// JAVA: Escribir lo atributos en fichero Entidad
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					fich.println("   private "
							+ Notacion.getTipoJava(c.getTipo()) + " "
							+ Notacion.getCamel(c.getNombre()) + ";");
				}
				fich.println();

				// JAVA: Escribir los métodos GET y SET
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					fich.print("   public " + Notacion.getTipoJava(c.getTipo())
							+ " get" + Notacion.getPascal(c.getNombre())
							+ "()\t{ return "
							+ Notacion.getCamel(c.getNombre()) + ";}\n");
					fich.print("   public void set"
							+ Notacion.getPascal(c.getNombre()) + "("
							+ Notacion.getTipoJava(c.getTipo()) + " p"
							+ Notacion.getPascal(c.getNombre()) + ")\t{ this."
							+ Notacion.getCamel(c.getNombre()) + " = p"
							+ Notacion.getPascal(c.getNombre()) + ";}\n");
				}
				fich.println("}");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero borrar,modificar y crear 
	 * para cada entidad PHP
	 */
	private void generarEntidadesPHP() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				// Crear fichero java para la entidad
				// new FileWriter(conf.leeString("dir.salida.java")
				fich = new PrintWriter(param.getValor(Parametros.DIR_SALIDA)
						+ "/"+Parametros.PHP.toLowerCase()+"/" + t.getNombre().toLowerCase()
						+ "_borrar.php");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString()+"-->");
				fich.println("<?\n   include(\"../conexion/conectardb.php\");");
				fich.println("   $conexion = conectar_bd();\n?>");
				fich.println("<html><head>");
				fich.println("<title>Borrar "+t.getNombre()+"</title></head>");
				fich.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../css/principal.css\">");
				fich.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				fich.println("   function redireccionar() {");
				fich.println("      setTimeout(\"location.href='../index.htm'\", 1000);");
			    fich.println("   }");
			    fich.println("</SCRIPT>\n</head>");
			    fich.println("<body onload=\"redireccionar()\">");
			    fich.println("<?");
				fich.print("   $consulta= \"DELETE FROM "+t.getNombre());
				fich.print(" WHERE "+t.getCampoClave().getNombreOrigen());
				fich.println("='{$HTTP_POST_VARS['"+t.getCampoClave().getNombreOrigen().toLowerCase()+"']}'\";");
				fich.println("   $query = mysql_query($consulta, $conexion);");
			    fich.println("   if (mysql_errno($conexion))");
			    fich.println("	    echo \"No se pudo borrar\";");
			    fich.println("   else");
			    fich.println("	    echo \"Registro eliminado correctamente\";");
			    fich.println("?>");
			    fich.println("<p><a href=\"../index.htm\">Indice</a></p>");
			    fich.println("</body>\n</html>");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}
}