package david.tenerife.lib.generador;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.Campo;
import david.tenerife.lib.Parametros;
import david.tenerife.lib.Tabla;
import david.tenerife.util.*;

import java.io.*;
import java.util.Date;

/**
 * Gnerador de código PHP
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051024
 */
public class GeneradorPHP {
	BaseDatos bd;

	Parametros param;

	public GeneradorPHP(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	public void generarCodigo() {
		generarAgregar();
		generarBorrar();
		//generarModificar();
	}

	/**
	 * Generar fichero borrar para cada entidad PHP
	 */
	private void generarBorrar() {
		try {
			PrintWriter fich;
			Date fecha = new Date();


			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				String ruta = param.getValor(Parametros.DIR_SALIDA)
				+ "/" + Parametros.PHP.toLowerCase() + "/"
				+ t.getNombre().toLowerCase();
				Ficheros.crearRuta(ruta);

				// Crear fichero java para la entidad
				// new FileWriter(conf.leeString("dir.salida.java")
				fich = new PrintWriter(ruta + "/borrar.php");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<?\n   include(\"../conexion/conectardb.php\");");
				fich.println("   $conexion = conectar_bd();\n?>");
				fich.println("<html><head>");
				fich.println("<title>Borrar " + t.getNombre()
						+ "</title></head>");
				fich
						.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../css/principal.css\">");
				fich.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				fich.println("   function redireccionar() {");
				fich
						.println("      setTimeout(\"location.href='../index.htm'\", 1000);");
				fich.println("   }");
				fich.println("</SCRIPT>\n</head>");
				fich.println("<body onload=\"redireccionar()\">");
				fich.println("<?");
				fich.print("   $consulta= \"DELETE FROM " + t.getNombreOrigen());
				fich.print(" WHERE " + t.getCampoClave().getNombreOrigen());
				fich.println("='{$HTTP_POST_VARS['"
						+ Notacion.getCamel(t.getCampoClave().getNombre())
						+ "']}'\";");
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


	/**
	 * Generar fichero agregar para cada entidad PHP
	 */
	private void generarAgregar() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				String ruta = param.getValor(Parametros.DIR_SALIDA)
				+ "/" + Parametros.PHP.toLowerCase() + "/"
				+ t.getNombre().toLowerCase();
				Ficheros.crearRuta(ruta);

				// Crear fichero java para la entidad
				// new FileWriter(conf.leeString("dir.salida.java")
				fich = new PrintWriter( ruta + "/agregar.php");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<?\n   include(\"../conexion/conectardb.php\");");
				fich.println("   $conexion = conectar_bd();\n?>");
				fich.println("<html><head>");
				fich.println("<title>Agregar " + t.getNombre()
						+ "</title></head>");
				fich
						.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../css/principal.css\">");
				fich.println("<SCRIPT LANGUAGE=\"JavaScript\">");
				fich.println("   function redireccionar() {");
				fich
						.println("      setTimeout(\"location.href='../index.htm'\", 1000);");
				fich.println("   }");
				fich.println("</SCRIPT>\n</head>");
				fich.println("<body onload=\"redireccionar()\">");
				fich.println("<?");
				fich.print("   $consulta= \"INSERT INTO " + t.getNombreOrigen()+"(");
				for(int j=0;j<t.getNumeroCampos();j++) {
					c = t.getCampo(j);
					fich.print(c.getNombreOrigen());
					if (j+1<t.getNumeroCampos())
						fich.print(",");
				}
				fich.print(") VALUES (");
				for(int j=0;j<t.getNumeroCampos();j++) {
					c = t.getCampo(j);
					fich.print("'{$HTTP_POST_VARS['"
							+ Notacion.getCamel(Notacion.getPascal(c.getNombre()))
							+ "']}'");
					if (j+1<t.getNumeroCampos())
						fich.print(",");
				}
				fich.println("\";");
				fich.println("   $query = mysql_query($consulta, $conexion);");
				fich.println("   if (mysql_errno($conexion))");
				fich.println("	    echo \"No se pudo agregar\";");
				fich.println("   else");
				fich.println("	    echo \"Registro creado correctamente\";");
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
