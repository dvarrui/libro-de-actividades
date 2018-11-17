package david.tenerife.lib.generador;

import java.io.PrintWriter;
import java.util.Date;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.Campo;
import david.tenerife.lib.Parametros;
import david.tenerife.lib.Tabla;
import david.tenerife.util.Ficheros;
import david.tenerife.util.Notacion;
import david.tenerife.templates.ArgumentoJet;
import david.tenerife.templates.jsp.IndexTemplate;
import david.tenerife.templates.jsp.ComprobarTemplate;
import david.tenerife.templates.jsp.DesconectarTemplate;
import david.tenerife.templates.jsp.MenuTemplate;
import david.tenerife.templates.jsp.ListadoTemplate;

/**
 * Generador de código JSP
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060207
 * 
 */
public class GeneradorJSP {
	private BaseDatos bd;

	private Parametros param;

	public GeneradorJSP(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	private void copiarArchivos() {
		String ruta;
		// david/tenerife/web/html/errorLogin.html
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/jsp/web/html";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFichero(Parametros.DIR_BASE
				+ "david/tenerife/web/html/errorLogin.html", ruta
				+ "/errorLogin.html");
		// david/tenerife/web/html/errorAcceso.html
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/jsp/web/html";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFichero(Parametros.DIR_BASE
				+ "david/tenerife/web/html/errorAcceso.html", ruta
				+ "/errorAcceso.html");
		// david/tenerife/web/html/desconectado.html
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/jsp/web/html";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFichero(Parametros.DIR_BASE
				+ "/david/tenerife/web/html/desconectado.html", ruta
				+ "/desconectado.html");
		// david/tenerife/web/WEB-INF/web.xml
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/jsp/web/WEB-INF";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFichero(Parametros.DIR_BASE
				+ "david/tenerife/web/webinf/web.xml", ruta + "/web.xml");
		// david/tenerife/web/WEB-INF/classes/
		ruta = param.getValor(Parametros.DIR_SALIDA)
				+ "/jsp/web/WEB-INF/classes";
		Ficheros.crearRuta(ruta);
		// david/tenerife/web/WEB-INF/lib/
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/jsp/web/WEB-INF/lib";
		Ficheros.crearRuta(ruta);
		// david/tenerife/web/WEB-INF/tld/
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/jsp/web/WEB-INF/tld";
		Ficheros.crearRuta(ruta);
	}

	public void generarCodigo() {
		copiarArchivos();
		generarIndex();
		generarLoginComprobar();
		generarLoginDesconectar();
		generarLoginMenu();
		generarTablaIndex();
		generarTablaListado();
		generarTablaNuevo();
		generarTablaNuevoRegistro();
		generarTablaConsultar();
		generarTablaEditar();
		generarTablaBorrar();
		// generarModificar();
	}

	/**
	 * Generar fichero /index.jsp
	 */
	private void generarIndex() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
					+ Parametros.JSP.toLowerCase() + "/web";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);
			
			// Crear fichero index.jsp
			fich = new PrintWriter(ruta + "/index.jsp");
			IndexTemplate p = new IndexTemplate();
			fich.println(p.generate(arg));
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero /login/comprobar.jsp
	 */
	private void generarLoginComprobar() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
					+ Parametros.JSP.toLowerCase() + "/web/jsp/login";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			// Crear fichero comprobar.jsp
			fich = new PrintWriter(ruta + "/comprobar.jsp");
			ComprobarTemplate p = new ComprobarTemplate();
			fich.println(p.generate(arg));
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero /login/desconectar.jsp
	 */
	private void generarLoginDesconectar() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
					+ Parametros.JSP.toLowerCase() + "/web/jsp/login";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			// Crear fichero index.jsp
			fich = new PrintWriter(ruta + "/desconectar.jsp");
			DesconectarTemplate p = new DesconectarTemplate();
			fich.println(p.generate(arg));
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero /login/menu.jsp
	 */
	private void generarLoginMenu() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
					+ Parametros.JSP.toLowerCase() + "/web/jsp/login";
			Ficheros.crearRuta(ruta);

			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			// Crear fichero menu.jsp
			fich = new PrintWriter(ruta + "/menu.jsp");
			MenuTemplate p = new MenuTemplate();
			fich.println(p.generate(arg));
			fich.close();

			// Crear fichero loginMenu.jsp
			/*
			fich = new PrintWriter(ruta + "/menu.jsp");
			fich.println("<!--Proyecto TENERIFE-->");
			fich.println("<!--Fecha:" + fecha.toString() + "-->");
			fich.println("<!--Nombre      : jsp/login/menu.jsp-->");
			fich.println("<!--Descripción : Menú principal-->");

			fich.println("<%@ page import = \"david."
					+ param.getValor(Parametros.PROYECTO)
					+ ".util.Configuracion\" %>");
			fich.println("<jsp:useBean id=\"vs_conf\" class=\"david."
					+ param.getValor(Parametros.PROYECTO)
					+ ".util.Configuracion\" scope=\"session\"/>");

			fich.println("<%@ page import = \"david."
					+ param.getValor(Parametros.PROYECTO)
					+ ".ejb.sesiones.SesionPrueba\" %>");
			fich.println("<jsp:useBean id=\"vs_sesion\" class=\"david."
					+ param.getValor(Parametros.PROYECTO)
					+ ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>");

			fich.println("<% if (!vs_sesion.isLogin()||vs_sesion==null) {");
			fich
					.println("%> <jsp:forward page=\"../../html/errorLogin.html\"/> <%");
			fich.println("} %>");

			fich.println("<html>");
			fich.println("<body");
			fich.println("<h1>Men&uacute; principal</h1>");
			fich.println("<h2>"
					+ param.getValor(Parametros.PROYECTO).toUpperCase()
					+ "</h2>");
			fich.println("<a href=\"desconectar.jsp\">Desconectar</a><br>");
			fich.println("<h3>Aplicaciones disponibles</h3>");
			fich.println("<table border=1>");
			fich.println("<%");

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				fich.println("   if (vs_sesion.tienePermiso(\"" + t.getNombre()
						+ "\"))");
				fich.println("   { ");
				fich.println("      out.println(\"<tr>\");");
				fich.println("      out.print(\"<td>" + t.getNombre()
						+ "</td>\");");
				fich.println("      out.print(\"<td>\");");
				fich.println("      out.print(\"<td><a href=\\\"../"
						+ t.getNombre() + "/index.jsp\\\">Indice</a></td>\");");
				fich.println("      out.print(\"<td>\");");
				fich.println("      if (vs_sesion.tienePermiso(\""
						+ t.getNombre() + ".listado\")) {");
				fich.println("         out.print(\"<a href=\\\"../"
						+ t.getNombre() + "/listado.jsp\\\">Listado</a>\");");
				fich.println("      }");
				fich.println("      out.println(\"</td>\");");
				fich.println("      out.print(\"<td>\");");
				fich.println("      if (vs_sesion.tienePermiso(\""
						+ t.getNombre() + ".nuevo\")) {");
				fich.println("         out.print(\"<a href=\\\"../"
						+ t.getNombre() + "/nuevo.jsp\\\">Nuevo</a>\");");
				fich.println("      }");
				fich.println("      out.println(\"</td>\");");
				fich.println("      out.println(\"</tr>\");");
				fich.println("   }");
			}
			fich.println("%>");
			fich.println("</table>");
			fich.println("<h3>Datos de la sesi&oacute;n</h3>");
			fich.println("<%");
			fich
					.println("   out.println(\"<b>Usuario</b>: \"+vs_sesion.getUsuario()+\"<br>\");");
			fich
					.println("   out.println(\"<b>Fecha login</b>: \"+vs_sesion.getFechaLogin()+\"<br>\");");
			fich
					.println("   out.println(\"<b>Entorno</b>: \"+vs_sesion.getEntorno()+\"<br>\");");
			fich.println("%>");
			fich.println("</body>");
			fich.println("</html>");

			fich.close();*/
		} catch (Exception e) {
			System.err.println("[ERROR]: <menu.jsp> " + e);
		}
	}

	/**
	 * Generar fichero borrar para cada entidad PHP
	 */
	private void generarTablaBorrar() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
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
				fich
						.print("   $consulta= \"DELETE FROM "
								+ t.getNombreOrigen());
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
	 * Generar fichero tabla/consultar.jsp
	 */
	private void generarTablaConsultar() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
						+ t.getNombre().toLowerCase();
				Ficheros.crearRuta(ruta);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/consultar.jsp");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<!--Nombre      : jsp/" + t.getNombre()
						+ "/consultar.jsp-->");
				fich.println("<!--Descripción : Consultar registro-->");

				// Configuracion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" %>");
				fich.println("<jsp:useBean id=\"vs_conf\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" scope=\"session\"/>");
				// Sesion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesion.Sesion\" %>");
				fich.println("<jsp:useBean id=\"vs_sesion\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.Sesion\" scope=\"session\"/>");
				// Almacen
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.Almacen"
						+ Notacion.getPascal(t.getNombre()) + "\" %>");
				fich.println("<jsp:useBean id=\"vs_almacen"
						+ Notacion.getPascal(t.getNombre())
						+ "\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.Almacen"
						+ Notacion.getPascal(t.getNombre())
						+ "\" scope=\"session\"/>");

				// Comprobar permisos
				fich
						.println("<% if (!vs_sesion.isLogin()||vs_sesion==null)) {");
				fich
						.println("%> <jsp:forward page=\"../../html/errorLogin.html\"/> <%");
				fich.println("} %>");

				fich.println("<html>");
				fich.println("<body");
				fich.println("<a href=\"../login/menu.jsp\">Principal</a> &gt");
				fich.println("<a href=\"../" + t.getNombre() + "/index.jsp\">"
						+ t.getNombre() + "</a> |");
				fich
						.println("<a href=\"../login/desconectar.jsp\">Desconectar</a><br>");

				fich.println("<h3>Consultar registro de " + t.getNombre()
						+ "</h3>");

				// Verificar permisos
				fich.println("<% if (!vs_sesion.tienePermiso(\""
						+ t.getNombre() + ".consultar\") {");
				fich.println("   vs_sesion.logout();");
				fich
						.println("%> <jsp:forward page=\"../../html/errorAcceso.html\"/> <%");
				fich.println("} ");

				fich.println("   vs_almacen"
						+ Notacion.getPascal(t.getNombre()) + ".abrir();");

				fich.println("   " + Notacion.getPascal(t.getNombre())
						+ " reg;");
				fich.println("   reg = vs_almacen"
						+ Notacion.getPascal(t.getNombre()) + ".getById();");

				fich.println("%>");

				fich.println("<table>");

				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("<tr>");
					fich.println("<td>" + c.getNombreOrigen() + "</td>");
					fich.println("<td><input type=text name="
							+ Notacion.getCamel(c.getNombre()) + "></td>");
					fich.println("</tr>");
				}
				fich.println("</table>");
				fich.println("</body>");
				fich.println("</html>");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero tabla/editar.jsp
	 */
	private void generarTablaEditar() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
						+ t.getNombre().toLowerCase();
				Ficheros.crearRuta(ruta);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/editar.jsp");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<!--Nombre      : jsp/" + t.getNombre()
						+ "/nuevo.jsp-->");
				fich.println("<!--Descripción : Nuevo registro-->");

				// Configuracion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" %>");
				fich.println("<jsp:useBean id=\"vs_conf\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" scope=\"session\"/>");
				// Sesion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesion.Sesion\" %>");
				fich.println("<jsp:useBean id=\"vs_sesion\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.Sesion\" scope=\"session\"/>");
				// Almacen
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.Almacen"
						+ Notacion.getPascal(t.getNombre()) + "\" %>");
				fich.println("<jsp:useBean id=\"vs_almacen"
						+ Notacion.getPascal(t.getNombre())
						+ "\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.Almacen"
						+ Notacion.getPascal(t.getNombre())
						+ "\" scope=\"session\"/>");

				// Comprobar permisos
				fich.println("<% if (!vs_sesion.isLogin()||vs_sesion==null) {");
				fich
						.println("%> <jsp:forward page=\"../../html/errorLogin.html\"/> <%");
				fich.println("} %>");

				fich.println("<html>");
				fich.println("<body");
				fich.println("<a href=\"../login/menu.jsp\">Principal</a> &gt");
				fich.println("<a href=\"../" + t.getNombre() + "/index.jsp\">"
						+ t.getNombre() + "</a> |");
				fich
						.println("<a href=\"../login/desconectar.jsp\">Desconectar</a><br>");

				fich
						.println("<h3>Nuevo registro de " + t.getNombre()
								+ "</h3>");

				fich.println("<% if (!vs_sesion.tienePermiso(\""
						+ t.getNombre() + ".nuevo\") {");
				fich.println("   vs_sesion.logout();");
				fich
						.println("%> <jsp:forward page=\"../../html/errorAcceso.html\"/> <%");
				fich.println("} ");

				fich.println("   vs_almacen"
						+ Notacion.getPascal(t.getNombre()) + ".abrir();");
				fich.println("   " + Notacion.getPascal(t.getNombre())
						+ " reg;");
				fich.println("%>");
				fich.println("<form method=post action=\"jsp/" + t.getNombre()
						+ "/nuevoProcesar.jsp\">");

				fich.println("<table>");

				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("<tr>");
					fich.println("<td>" + c.getNombreOrigen() + "</td>");
					fich.println("<td><input type=text name="
							+ Notacion.getCamel(c.getNombre()) + "></td>");
					fich.println("</tr>");
				}
				fich
						.println("<tr><td></td><td><input type=submit value=\"Aceptar\"></td>");
				fich.println("</form>");
				fich.println("</table>");
				fich.println("</body>");
				fich.println("</html>");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero /tabla/index.jsp
	 */
	private void generarTablaIndex() {
		try {
			PrintWriter fich;
			Date fecha = new Date();
			String ruta;

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
						+ t.getNombre();
				Ficheros.crearRuta(ruta);

				// Crear fichero index.jsp
				fich = new PrintWriter(ruta + "/index.jsp");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<!--Nombre      : jsp/" + t.getNombre()
						+ "/index.jsp-->");
				fich.println("<!--Descripción : Menú Tabla-->");

				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" %>");
				fich.println("<jsp:useBean id=\"vs_conf\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" scope=\"session\"/>");

				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.SesionPrueba\" %>");
				fich.println("<jsp:useBean id=\"vs_sesion\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>");

				fich.println("<% if (!vs_sesion.isLogin()||vs_sesion==null) {");
				fich
						.println("%> <jsp:forward page=\"../../html/errorLogin.html\"/> <%");
				fich.println("} %>");

				fich.println("<html>");
				fich.println("<body");
				fich.println("<a href=\"../login/menu.jsp\">Principal</a> |");
				fich
						.println("<a href=\"../login/desconectar.jsp\">Desconectar</a><br>");
				fich.println("<h2>Men&uacute; " + t.getNombre() + "</h2>");
				fich.println("<h3>Aplicaciones disponibles</h3>");
				fich.println("<ul>");
				fich.println("<%");
				fich.println("   if (vs_sesion.tienePermiso(\"" + t.getNombre()
						+ ".listado\"))");
				fich.println("{ %><li><a href=\"../" + t.getNombre()
						+ "/listado.jsp\">Acceso a Listado</a></li><% }");
				fich.println("   if (vs_sesion.tienePermiso(\"" + t.getNombre()
						+ ".nuevo\"))");
				fich.println("{ %><li><a href=\"../" + t.getNombre()
						+ "/nuevo.jsp\">Acceso a Nuevo</a></li><% }");
				fich.println("   if (vs_sesion.tienePermiso(\"" + t.getNombre()
						+ ".consultar\"))");
				fich.println("{ %><li><a href=\"../" + t.getNombre()
						+ "/consultar.jsp\">Acceso a Consultar</a></li><% }");
				fich.println("   if (vs_sesion.tienePermiso(\"" + t.getNombre()
						+ ".editar\"))");
				fich.println("{ %><li><a href=\"../" + t.getNombre()
						+ "/editar.jsp\">Acceso a Editar</a></li><% }");
				fich.println("   if (vs_sesion.tienePermiso(\"" + t.getNombre()
						+ ".borrar\"))");
				fich.println("{ %><li><a href=\"../" + t.getNombre()
						+ "/borrar.jsp\">Acceso a Borrar</a></li><% }");
				fich.println("%>");
				fich.println("</ul>");
				fich.println("</body>");
				fich.println("</html>");

				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero /tabla/listado.jsp
	 */
	private void generarTablaListado() {
		try {
			PrintWriter fich;
			String ruta;
			ArgumentoJet arg;

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
						+ t.getNombre();
				Ficheros.crearRuta(ruta);
				
				arg = new ArgumentoJet();
				arg.setBaseDatos(bd);
				arg.setTabla(t);

				// Crear fichero listado.jsp
				fich = new PrintWriter(ruta + "/listado.jsp");
				ListadoTemplate p = new ListadoTemplate();
				fich.println(p.generate(arg));
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]: <generar.jsp.listado> " + e);
		}
	}

	/**
	 * Generar fichero tabla/nuevo.jsp
	 */
	private void generarTablaNuevo() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
						+ t.getNombre().toLowerCase();
				Ficheros.crearRuta(ruta);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/nuevo.jsp");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<!--Nombre      : jsp/" + t.getNombre()
						+ "/nuevo.jsp-->");
				fich.println("<!--Descripción : Nuevo registro-->");

				// Configuracion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" %>");
				fich.println("<jsp:useBean id=\"vs_conf\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" scope=\"session\"/>");
				// Sesion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.SesionPrueba\" %>");
				fich.println("<jsp:useBean id=\"vs_sesion\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>");
				// Entidad
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO) + ".entidades."
						+ Notacion.getPascal(t.getNombre()) + "\" %>");
				fich.println("<jsp:useBean id=\"vs_entidad"
						+ Notacion.getPascal(t.getNombre())
						+ "\" class=\"david."
						+ param.getValor(Parametros.PROYECTO) + ".entidades."
						+ Notacion.getPascal(t.getNombre())
						+ "\" scope=\"session\"/>");

				// Comprobar permisos
				fich.println("<% if (!vs_sesion.isLogin()||vs_sesion==null) {");
				fich
						.println("%> <jsp:forward page=\"../../html/errorLogin.html\"/> <%");
				fich.println("} %>");

				fich.println("<html>");
				fich.println("<body");
				fich.println("<a href=\"../login/menu.jsp\">Principal</a> &gt");
				fich.println("<a href=\"../" + t.getNombre() + "/index.jsp\">"
						+ t.getNombre() + "</a> |");
				fich
						.println("<a href=\"../login/desconectar.jsp\">Desconectar</a><br>");

				fich
						.println("<h3>Nuevo registro de " + t.getNombre()
								+ "</h3>");

				fich.println("<% if (!vs_sesion.tienePermiso(\""
						+ t.getNombre() + ".nuevo\")) {");
				fich.println("   vs_sesion.logout();");
				fich
						.println("%> <jsp:forward page=\"../../html/errorAcceso.html\"/> <%");
				fich.println("} ");

				fich.println("   " + Notacion.getPascal(t.getNombre())
						+ " reg;");
				fich.println("%>");
				fich.println("<form method=post action=\"nuevoRegistro.jsp\">");

				fich.println("<table>");

				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("<tr>");
					fich.println("<td>" + c.getEtiqueta() + "</td>");
					fich.println("<td><input type=text name="
							+ Notacion.getCamel(c.getNombre()) + "></td>");
					fich.println("</tr>");
				}
				fich
						.println("<tr><td></td><td><input type=submit value=\"Aceptar\"></td>");
				fich.println("</form>");
				fich.println("</table>");
				fich.println("</body>");
				fich.println("</html>");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar fichero tabla/nuevoRegistro.jsp
	 */
	private void generarTablaNuevoRegistro() {
		try {
			PrintWriter fich;
			Date fecha = new Date();

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				String ruta = param.getValor(Parametros.DIR_SALIDA) + "/"
						+ Parametros.JSP.toLowerCase() + "/web/jsp/"
						+ t.getNombre().toLowerCase();
				Ficheros.crearRuta(ruta);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/nuevoRegistro.jsp");
				fich.println("<!--Proyecto TENERIFE-->");
				fich.println("<!--Fecha:" + fecha.toString() + "-->");
				fich.println("<!--Nombre      : jsp/" + t.getNombre()
						+ "/nuevo.jsp-->");
				fich.println("<!--Descripción : Nuevo registro-->");

				// import Configuracion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" %>");
				fich.println("<jsp:useBean id=\"vs_conf\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Configuracion\" scope=\"session\"/>");
				// import Fecha
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".util.Fechas\" %>");
				// import Sesion
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.SesionPrueba\" %>");
				fich.println("<jsp:useBean id=\"vs_sesion\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>");
				// import Entidad
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO) + ".entidades."
						+ Notacion.getPascal(t.getNombre()) + "\" %>");
				fich.println("<jsp:useBean id=\"vs_entidad"
						+ Notacion.getPascal(t.getNombre())
						+ "\" class=\"david."
						+ param.getValor(Parametros.PROYECTO) + ".entidades."
						+ Notacion.getPascal(t.getNombre())
						+ "\" scope=\"session\"/>");
				// import IEntidad
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".entidades.IEntidad\" %>");
				// import Almacen
				fich.println("<%@ page import = \"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.txt.AlmacenTXT"
						+ Notacion.getPascal(t.getNombre()) + "\" %>");
				fich.println("<jsp:useBean id=\"vs_almacen"
						+ Notacion.getPascal(t.getNombre())
						+ "\" class=\"david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.txt.AlmacenTXT"
						+ Notacion.getPascal(t.getNombre())
						+ "\" scope=\"session\"/>");

				// Comprobar permisos
				fich.println("<%");
				fich.println("   if (!vs_sesion.isLogin()||vs_sesion==null) {");
				fich
						.println("%> <jsp:forward page=\"../../html/errorLogin.html\"/> <%");
				fich.println("   } ");
				fich.println("   if (!vs_sesion.tienePermiso(\""
						+ t.getNombre() + ".nuevo\")) {");
				fich.println("      vs_sesion.logout();");
				fich
						.println("      %> <jsp:forward page=\"../../html/errorAcceso.html\"/> <%");
				fich.println("   } ");

				// Cargar entidad con valores
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					if (Notacion.getTipoJava(c.getTipo()).equals("String")) {
						fich.println("   vs_entidad"
								+ Notacion.getPascal(t.getNombre()) + ".set"
								+ Notacion.getPascal(c.getNombre())
								+ "(request.getParameter(\""
								+ Notacion.getCamel(c.getNombre()) + "\"));");
					}
					if (Notacion.getTipoJava(c.getTipo()).equals("int")) {
						fich.println("   vs_entidad"
								+ Notacion.getPascal(t.getNombre()) + ".set"
								+ Notacion.getPascal(c.getNombre())
								+ "(Integer.parseInt(request.getParameter(\""
								+ Notacion.getCamel(c.getNombre()) + "\")));");
					}
					if (Notacion.getTipoJava(c.getTipo()).equals("Date")) {
						fich.println("   vs_entidad"
								+ Notacion.getPascal(t.getNombre()) + ".set"
								+ Notacion.getPascal(c.getNombre())
								+ "(Fechas.fromCadena(request.getParameter(\""
								+ Notacion.getCamel(c.getNombre()) + "\")));");
					}
				}
				fich.println("   boolean resultado;");
				fich.println("   IEntidad e = (IEntidad) vs_entidad"
						+ Notacion.getPascal(t.getNombre()) + ";");
				fich.println("   resultado = vs_almacen"
						+ Notacion.getPascal(t.getNombre()) + ".add(e);");

				fich.println("%>");
				fich.println("<html>");
				fich.println("<body>");
				fich.println("<% if (resultado) {");
				fich
						.println("      out.println(\"<h3>Registro grabado correctamente</h3>\");");
				fich.println("   } else {");
				fich
						.println("      out.println(\"<h3>No se ha realizado</h3>\");");
				fich.println("   }");
				fich.println("%>");
				fich.println("<a href=\"nuevo.jsp\">Volver</a>");
				fich.println("</body>");
				fich.println("</html>");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

}
