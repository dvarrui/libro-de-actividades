package david.tenerife.lib.generador;

import java.io.PrintWriter;
import java.util.Date;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.Campo;
import david.tenerife.lib.Parametros;
import david.tenerife.lib.Tabla;
import david.tenerife.util.Ficheros;
import david.tenerife.util.Notacion;
import david.tenerife.util.RandomMultiple;
import david.tenerife.util.Fechas;
import david.tenerife.templates.ArgumentoJet;
import david.tenerife.templates.java.AlmacenTXTTemplate;
import david.tenerife.templates.java.IEntidadTemplate;

/**
 * Generador de código Java
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060107
 */
public class GeneradorJava {
	private BaseDatos bd;

	private Parametros param;

	public GeneradorJava(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	private void copiarArchivos() {
		// david/tenerife/ejb/conexiones/IConexion.java
		String ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/conexiones";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFicheroJava(
				"src/david/tenerife/ejb/conexiones/IConexion.java", ruta
						+ "/IConexion.java", "david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.conexiones");
		// david/tenerife/ejb/conexiones/Conexion.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/conexiones";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFicheroJava(
				"src/david/tenerife/ejb/conexiones/Conexion.java", ruta
						+ "/Conexion.java", "david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.conexiones");
		// david/tenerife/ejb/sesiones/ISesion.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/sesiones";
		Ficheros.crearRuta(ruta);
		Ficheros
				.copiarFicheroJava(
						"src/david/tenerife/ejb/sesiones/ISesion.java", ruta
								+ "/ISesion.java", "david."
								+ param.getValor(Parametros.PROYECTO)
								+ ".ejb.sesiones");
		// david/tenerife/ejb/sesiones/FabricaSesiones.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/sesiones";
		Ficheros.crearRuta(ruta);
		Ficheros
				.copiarFicheroJava(
						"src/david/tenerife/ejb/sesiones/FabricaSesiones.java",
						ruta + "/FabricaSesiones.java", "david."
								+ param.getValor(Parametros.PROYECTO)
								+ ".ejb.sesiones");
		// david/tenerife/ejb/sesiones/SesionPrueba.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/sesiones";
		Ficheros.crearRuta(ruta);
		Ficheros
				.copiarFicheroJava(
						"src/david/tenerife/ejb/sesiones/SesionPrueba.java",
						ruta + "/SesionPrueba.java", "david."
								+ param.getValor(Parametros.PROYECTO)
								+ ".ejb.sesiones");
		// david/tenerife/ejb/sesiones/SesionTXT.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/sesiones";
		Ficheros.crearRuta(ruta);
		Ficheros
				.copiarFicheroJava(
						"src/david/tenerife/ejb/sesiones/SesionTXT.java", ruta
								+ "/SesionTXT.java", "david."
								+ param.getValor(Parametros.PROYECTO)
								+ ".ejb.sesiones");
		// david/tenerife/ejb/sesiones/SesionSQL.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/ejb/sesiones";
		Ficheros.crearRuta(ruta);
		Ficheros
				.copiarFicheroJava(
						"src/david/tenerife/ejb/sesiones/SesionSQL.java", ruta
								+ "/SesionSQL.java", "david."
								+ param.getValor(Parametros.PROYECTO)
								+ ".ejb.sesiones");
		// david/tenerife/util/Fechas.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/util";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFicheroJava("src/david/tenerife/util/Fechas.java", ruta
				+ "/Fechas.java", "david."
				+ bd.getPaqueteJava() + ".util");
		// david/tenerife/util/Configuracion.java
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/util";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFicheroJava(
				"src/david/tenerife/util/Configuracion.java", ruta
						+ "/Configuracion.java", "david."
						+ bd.getPaqueteJava() + ".util");
		// david/tenerife/rec/inicio.properties
		ruta = param.getValor(Parametros.DIR_SALIDA) + "/java/david/"
				+ bd.getPaqueteJava() + "/rec";
		Ficheros.crearRuta(ruta);
		Ficheros.copiarFichero("src/david/tenerife/rec/inicio.properties", ruta
				+ "/inicio.properties");
	}

	/**
	 * Generar las clases JAVA para los ALMACENES de datos en Bases de Datos
	 */
	private void generarAlmacenesSQL() {
		try {
			PrintWriter fich;
			Date fecha = new Date();
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + param.getValor(Parametros.PROYECTO)
					+ "/almacenes/sql";
			Ficheros.crearRuta(ruta);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				// Crear fichero java para el Almacén
				fich = new PrintWriter(ruta + "/AlmacenSQL"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				fich.println("//Creado por Proyecto TENERIFE");
				fich.println("//Autor: David Vargas Ruiz");
				fich.println("//Fecha:" + fecha.toString());
				fich.println("package david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.sql;\n");
				// import
				fich.println("import david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".ejb.conexiones.Conexion;");
				fich.println("import david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".entidades.IEntidad;");
				fich.println("import david."
						+ param.getValor(Parametros.PROYECTO) + ".entidades."
						+ Notacion.getPascal(t.getNombre()) + ";");
				fich.println("import david."
						+ param.getValor(Parametros.PROYECTO)
						+ ".almacenes.IAlmacen;");
				fich.println("import java.util.ArrayList;");
				fich.println("import java.sql.*;\n");

				// Comienzo de la clase
				fich.println("public class AlmacenSQL"
						+ Notacion.getPascal(t.getNombre())
						+ " implements IAlmacen {");
				fich.println("   public static final String NOMBRETABLA=\""
						+ t.getNombreOrigen() + "\";");
				fich.println("   private Conexion conexion;\n");

				// -------------------
				// Método setConexion
				// -------------------
				fich
						.println("   public void setConexion(Conexion pConexion) {");
				fich.println("      conexion=pConexion;");
				fich.println("   }\n");

				// ----
				// open
				// ----
				fich.println("   public boolean open() {");
				fich.println("      return true;");
				fich.println("   }\n");

				// ----
				// close
				// ----
				fich.println("   public boolean close() {");
				fich.println("      return true;");
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
				fich.println("      try {");
				fich
						.println("         rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA);");
				fich.println("         while(rs.next()) {");
				fich.println("            reg = new "
						+ Notacion.getPascal(t.getNombre()) + "();");

				// Para cada campo debemos leer su valor
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("            reg.set"
							+ Notacion.getPascal(c.getNombre())
							+ "(rs.get"
							+ Notacion.getPascal(Notacion.getTipoJava(c
									.getTipo())) + "(" + j + "));");
				}
				fich.println("            lista.add(reg);");
				fich.println("         }");
				fich.println("      }  catch(Exception e) {");
				fich.println("         System.err.println(\"Error:\"+e);");
				fich.println("      }");
				fich.println("      return lista;");
				fich.println("   }\n");

				// ---------------
				// Método getById
				// ---------------
				fich.println("   public IEntidad getById(int id) {");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      ResultSet rs;");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " reg = new " + Notacion.getPascal(t.getNombre())
						+ "();");
				fich.println("      try {");
				fich
						.print("         rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA+\" WHERE ");

				c = t.getCampoClave();
				fich.print(c.getNombreOrigen() + " = \"+id+\";\");");

				fich.println("         while(rs.next()) {");
				// Para cada campo debemos leer su valor
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("         reg.set"
							+ Notacion.getPascal(c.getNombre())
							+ " (rs.get"
							+ Notacion.getPascal(Notacion.getTipoJava(c
									.getTipo())) + "(" + j + "));");
				}
				fich.println("         }");
				fich.println("      }  catch(Exception e) {");
				fich.println("         System.err.println(\"Error:\"+e);");
				fich.println("      }");
				fich.println("      return (IEntidad) reg;");
				fich.println("   }\n");

				// ---------------
				// Método getFind
				// ---------------
				fich
						.println("   public ArrayList getFind(IEntidad desde, IEntidad hasta) {");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      ResultSet rs;");
				fich.println("      ArrayList lista=new ArrayList();");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " reg;\n");
				fich.println("      try {");
				fich
						.println("         rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA);");
				fich.println("         while(rs.next()) {");
				fich.println("            reg = new "
						+ Notacion.getPascal(t.getNombre()) + "();");

				// Para cada campo debemos leer su valor
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("            reg.set"
							+ Notacion.getPascal(c.getNombre())
							+ "(rs.get"
							+ Notacion.getPascal(Notacion.getTipoJava(c
									.getTipo())) + "(" + j + "));");
				}
				fich.println("            lista.add(reg);");
				fich.println("         }");
				fich.println("      }  catch(Exception e) {");
				fich.println("         System.err.println(\"Error:\"+e);");
				fich.println("      }");
				fich.println("      return lista;");
				fich.println("   }\n");

				// ----------
				// Método add
				// ----------
				fich.println("   public boolean add(IEntidad pRegistro) {");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " registro = (" + Notacion.getPascal(t.getNombre())
						+ ") pRegistro;");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      try {");
				fich
						.print("         st.executeUpdate(\"INSERT INTO \"+NOMBRETABLA+\n         \" (");
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
						fich.print("         +\",'\"");
				}
				fich.println("            +\");\");");
				fich.println("         st.close();");
				fich.println("      } catch(Exception e) {");
				fich.println("         System.err.println(\"Error:\"+e);");
				fich.println("      }");
				fich.println("      return true;");
				fich.println("   }\n");

				// -------------
				// Método delete
				// -------------
				fich.println("   public boolean delete(IEntidad pRegistro) {");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " registro = (" + Notacion.getPascal(t.getNombre())
						+ ") pRegistro;");
				fich.println("      Statement st=conexion.getStatement();");
				fich.println("      try {");
				fich
						.print("         st.executeUpdate(\"DELETE \"+NOMBRETABLA+\" WHERE \"+\n         ");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					c = t.getCampo(j);
					fich.println("\"" + c.getNombreOrigen()
							+ "='\"+registro.get"
							+ Notacion.getPascal(c.getNombre() + "()+\"'\""));
					if (j + 1 < t.getNumeroCampos())
						fich.print("         +\" AND \"+");
				}
				fich.println("            +\";\");");
				fich.println("         st.close();");
				fich.println("      }  catch(Exception e) {");
				fich.println("         System.err.println(\"Error:\"+e);");
				fich.println("      }");
				fich.println("      return true;");
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

	/**
	 * Generar las clases JAVA para los ALMACENES de datos 
	 * en ficheros texto plano
	 */
	private void generarAlmacenesTXT() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + bd.getPaqueteJava()
					+ "/almacenes/txt";
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				arg.setTabla(t);

				// Crear fichero java para el Almacén
				fich = new PrintWriter(ruta + "/AlmacenTXT"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				//Escribir plantilla JET
				AlmacenTXTTemplate p = new AlmacenTXTTemplate();
				fich.println(p.generate(arg));
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	public void generarCodigo() {
		generarInterfazEntidades();
		generarEntidades();
		generarInterfazAlmacenes();
		generarFabricaAlmacenes();
		generarAlmacenesSQL();
		generarAlmacenesTXT();
		generarDatosTXT();
		copiarArchivos();
	}

	/**
	 * Generar ficheros datos/<tabla>.txt para los ALMACENES de texto plano
	 * plano
	 */
	private void generarDatosTXT() {
		try {
			RandomMultiple azar = new RandomMultiple();
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA) + "/datos";
			Ficheros.crearRuta(ruta);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				Campo c;

				// Crear fichero de datos
				fich = new PrintWriter(ruta + "/" + t.getNombre().toLowerCase()
						+ ".txt");
				// Crear 5 registros aleatorios
				for (int j = 0; j < 5; j++) {
					for (int k = 0; k < t.getNumeroCampos(); k++) {
						c = t.getCampo(k);

						if (Notacion.getTipoJava(c.getTipo()).equals("int")) {
							fich.print(azar.nextInt(10));
						} else if (Notacion.getTipoJava(c.getTipo()).equals(
								"String")) {
							fich.print(azar.nextString());
						} else if (Notacion.getTipoJava(c.getTipo()).equals(
								"Date")) {
							fich.print(Fechas.toCadena(azar.nextDate()));
						} else if (Notacion.getTipoJava(c.getTipo()).equals(
								"boolean")) {
							fich.print(azar.nextBoolean());
						}

						if (k < t.getNumeroCampos() - 1)
							fich.print("|");
					}
					fich.println();
				}
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	private void generarEntidades() {
		try {
			PrintWriter fich;
			Date fecha = new Date();
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + param.getValor(Parametros.PROYECTO)
					+ "/entidades";

			Ficheros.crearRuta(ruta);

			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				// Crear fichero java para la entidad
				fich = new PrintWriter(ruta + "/"
						+ Notacion.getPascal(t.getNombre()) + ".java");
				// Comentarios
				fich.println("//Creado por Proyecto TENERIFE");
				fich.println("//Autor: David Vargas Ruiz");
				fich.println("//Fecha:" + fecha.toString());
				fich
						.println("package david."
								+ param.getValor(Parametros.PROYECTO)
								+ ".entidades;\n");

				// import
				if (t.hayFecha()) {
					fich.println("import david."
							+ param.getValor(Parametros.PROYECTO)
							+ ".util.Fechas;");
					fich.println("import java.util.Date;");
				}

				fich.println("import java.util.StringTokenizer;");
				fich.println();
				// Inicio de la clase
				fich.println("public class "
						+ Notacion.getPascal(t.getNombre())
						+ " implements IEntidad, Comparable {");

				// Escribir las constantes de la clase
				fich.println("   private final static String SEPARADOR=\"|\";");

				// Atributos
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					fich.println("   private "
							+ Notacion.getTipoJava(c.getTipo()) + " "
							+ Notacion.getCamel(c.getNombre()) + ";");
				}
				fich.println();

				// Métodos GET y SET
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
				fich.println();

				// Escribir método toCadena
				fich.println("   public String toCadena() {");
				fich.println("      StringBuffer sb = new StringBuffer(200);");
				for (int j = 0; j < t.getNumeroCampos() - 1; j++) {
					Campo c = t.getCampo(j);
					if (Notacion.getTipoJava(c.getTipo()).equals("int")
							|| Notacion.getTipoJava(c.getTipo()).equals(
									"boolean")) {
						// El tipo int/boolean no usa toString()
						fich.println("      sb.append("
								+ Notacion.getCamel(c.getNombre())
								+ "+SEPARADOR);");
					} else if (Notacion.getTipoJava(c.getTipo()).equals("Date")) {
						// Las fechas usan Fechas.toCadena()
						fich.println("      sb.append(Fechas.toCadena("
								+ Notacion.getCamel(c.getNombre())
								+ ")+SEPARADOR);");
					} else {
						fich.println("      sb.append("
								+ Notacion.getCamel(c.getNombre())
								+ ".toString()+SEPARADOR);");
					}
				}
				if (Notacion.getTipoJava(
						t.getCampo(t.getNumeroCampos() - 1).getTipo()).equals(
						"int")
						|| Notacion.getTipoJava(
								t.getCampo(t.getNumeroCampos() - 1).getTipo())
								.equals("boolean")) {
					// El tipo int no usa toString()
					fich.println("      sb.append("
							+ Notacion.getCamel(t.getCampo(
									t.getNumeroCampos() - 1).getNombre())
							+ ");");
				} else {
					fich.println("      sb.append("
							+ Notacion.getCamel(t.getCampo(
									t.getNumeroCampos() - 1).getNombre())
							+ ".toString());");
				}

				fich.println("      return sb.toString();");
				fich.println("   }");

				// Escribir método fromCadena
				fich.println("   public void fromCadena(String pTexto) {");
				fich
						.println("      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					if (Notacion.getTipoJava(c.getTipo()).equals("int")) {
						fich.println("      set"
								+ Notacion.getPascal(c.getNombre())
								+ "(Integer.parseInt(st.nextToken()));");
					} else if (Notacion.getTipoJava(c.getTipo()).equals(
							"boolean")) {
						fich.println("      set"
								+ Notacion.getPascal(c.getNombre())
								+ "(Boolean.parseBoolean(st.nextToken()));");
					} else if (Notacion.getTipoJava(c.getTipo()).equals("Date")) {
						fich.println("      set"
								+ Notacion.getPascal(c.getNombre())
								+ "(Fechas.fromCadena(st.nextToken()));");
					} else {
						fich.println("      set"
								+ Notacion.getPascal(c.getNombre())
								+ "(st.nextToken());");
					}
				}
				fich.println("   }\n");

				// Escribir método equals
				fich.println("   public boolean equals(IEntidad registro) {");
				fich.println("      //No usar el método compareTo en lugar de equals");
				fich.println("      //equals detecta igualdad,compareTo es para ordenar");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " e = ("
						+ Notacion.getPascal(t.getNombre() + ") registro;"));
				fich.println("      if (this.toCadena().equals(e.toCadena())) return true;");
				fich.println("      return false;");
				fich.println("   }\n");

				// Escribir método compareTo
				fich.println("   public int compareTo(Object registro) {");
				fich.println("      // -1: Si x <= y");
				fich.println("      //  0: Si x == y");
				fich.println("      // +1: Si x >= y");
				fich.println("      " + Notacion.getPascal(t.getNombre())
						+ " e = ("
						+ Notacion.getPascal(t.getNombre() + ") registro;"));
				fich.println("      if (this.toCadena().equals(e.toCadena())) return 0;");
				fich.println("      return this.toCadena().compareTo(e.toCadena());");
				fich.println("   }\n");

				
				// Escribir método getDescripcion
				fich.println("   public String getDescripcion() {");
				fich.println("      StringBuffer sb = new StringBuffer(200);");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					if (c.isDescripcion()) {
						fich.println("      sb.append("
								+ Notacion.getCamel(c.getNombre())
								+ ".toString()+\" \");");
					}
				}
				fich.println("      return sb.toString().trim();");
				fich.println("   }\n");

				// Escribir método getClave
				fich.println("   public String getClave() {");
				fich.println("      StringBuffer sb = new StringBuffer(200);");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					if (c.isClave()) {
						if (Notacion.getTipoJava(c.getTipo()).equals("int")
								|| Notacion.getTipoJava(c.getTipo()).equals(
										"boolean")) {
							// El tipo int/boolean no usa toString()
							fich.println("      sb.append("
									+ Notacion.getCamel(c.getNombre())
									+ "+\"-\");");
						} else {
							fich.println("      sb.append("
									+ Notacion.getCamel(c.getNombre())
									+ ".toString()+\"-\");");
						}
					}
				}
				fich.println("      return sb.toString().trim();");
				fich.println("   }\n");

				// Escribir método getNumeroCampos
				fich.println("   public int getNumeroCampos() {");
				fich.println("      return " + t.getNumeroCampos() + ";");
				fich.println("   }\n");

				// Escribir método getCampo
				fich.println("   public Object getCampo(int index) {");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					fich.println("      if(index==" + j + ") ");
					if (Notacion.getTipoJava(c.getTipo()).equals("int")) {
						fich.println("         return new Integer(get"
								+ Notacion.getPascal(c.getNombre()) + "());");
					} else if (Notacion.getTipoJava(c.getTipo()).equals(
							"boolean")) {
						fich.println("         return new Boolean(get"
								+ Notacion.getPascal(c.getNombre()) + "());");
					} else {
						fich.println("         return get"
								+ Notacion.getPascal(c.getNombre()) + "();");
					}
				}
				fich.println("      return null;");
				fich.println("   }\n");

				// Escribir método getNombreCampo
				fich
						.println("   public static String getNombreCampo(int index) {");
				for (int j = 0; j < t.getNumeroCampos(); j++) {
					Campo c = t.getCampo(j);
					fich.println("      if(index==" + j + ") ");
					fich.println("         return \"" + c.getNombreOrigen()
							+ "\";");
				}
				fich.println("      return null;");
				fich.println("   }\n");

				// Cerrar el fichero de entidad

				fich.println("}");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar la clase JAVA FabricaAlmacenes Sustituye a AlmacenasMUL
	 */
	private void generarFabricaAlmacenes() {
		try {
			PrintWriter fich;
			Date fecha = new Date();
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + param.getValor(Parametros.PROYECTO)
					+ "/almacenes";
			Ficheros.crearRuta(ruta);

			// Crear fichero java para el Almacén
			fich = new PrintWriter(ruta + "/FabricaAlmacenes.java");
			fich.println("//Creado por Proyecto TENERIFE");
			fich.println("//Autor David Vargas Ruiz");
			fich.println("//Fecha:" + fecha.toString());
			fich.println("package david." + param.getValor(Parametros.PROYECTO)
					+ ".almacenes;\n");
			fich.println("import david." + param.getValor(Parametros.PROYECTO)
					+ ".almacenes.sql.*;");
			fich.println("import david." + param.getValor(Parametros.PROYECTO)
					+ ".almacenes.txt.*;");
			fich.println("import david." + param.getValor(Parametros.PROYECTO)
					+ ".almacenes.IAlmacen;");
			fich.println();

			fich.println("public class FabricaAlmacenes {");
			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				fich.println("   public static final String "
						+ t.getNombre().toUpperCase() + "=\""
						+ t.getNombreOrigen() + "\";");
			}
			fich.println("   public static final String TIPO_TXT=\"TXT\";");
			fich.println("   public static final String TIPO_SQL=\"SQL\";");
			fich.println();

			// ----------------------
			// Método createAlmacen()
			// ----------------------
			fich
					.println("   public static IAlmacen createAlmacen(String pTabla, String pTipo) {");
			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);
				fich.println("      if (pTabla.equals(FabricaAlmacenes."
						+ t.getNombre().toUpperCase() + ")) {");
				fich
						.println("         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))");
				fich.println("            return new AlmacenTXT"
						+ Notacion.getPascal(t.getNombre()) + "();");
				fich
						.println("         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))");
				fich.println("            return new AlmacenSQL"
						+ Notacion.getPascal(t.getNombre()) + "();");
				fich.println("         return null;");
				fich.println("      }");
			}
			fich.println("      return null;");
			fich.println("   }\n");

			// ------------------
			// Cerrar el fichero
			// ------------------
			fich.println("}");
			fich.println();
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar el interfaz para los ALMACENES
	 */
	private void generarInterfazAlmacenes() {
		try {
			PrintWriter fich;
			Date fecha = new Date();
			String ruta = param.getValor(Parametros.DIR_SALIDA)
					+ "/java/david/" + param.getValor(Parametros.PROYECTO)
					+ "/almacenes";
			Ficheros.crearRuta(ruta);

			fich = new PrintWriter(ruta + "/IAlmacen.java");
			fich.println("//Creado por Proyecto TENERIFE");
			fich.println("//Autor: David Vargas Ruiz");
			fich.println("//Fecha:" + fecha.toString());
			fich.println("package david." + param.getValor(Parametros.PROYECTO)
					+ ".almacenes;\n");
			fich.println("import java.util.ArrayList;");
			fich.println("import david." + param.getValor(Parametros.PROYECTO)
					+ ".entidades.IEntidad;");
			fich.println();
			fich.println("public interface IAlmacen {");
			fich.println();

			// -------
			// Métodos
			// -------
			fich.println("   public boolean open();\n");
			fich.println("   public boolean close();\n");
			fich.println("   public ArrayList getAll();\n");
			fich.println("   public IEntidad getById(int id);\n");
			fich
					.println("   public ArrayList getFind(IEntidad desde, IEntidad hasta);\n");
			fich.println("   public boolean add(IEntidad registro);\n");
			fich.println("   public boolean delete(IEntidad registro);\n");

			// ------------------
			// Cerrar el fichero
			// ------------------
			fich.println("}");
			fich.println();
			fich.close();
			// }
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

	/**
	 * Generar el interfaz para los Entidades
	 */
	private void generarInterfazEntidades() {
		try {
			PrintWriter fich;
			String ruta = param.getValor(Parametros.DIR_SALIDA) + "/java"
					+ "/david/" + bd.getPaqueteJava()
					+ "/entidades";
			// Verificar ruta
			Ficheros.crearRuta(ruta);
			ArgumentoJet arg = new ArgumentoJet();
			arg.setBaseDatos(bd);

			// Crear fichero java para el Almacén
			fich = new PrintWriter(ruta + "/IEntidad.java");
			
			//Escribir plantilla JET
			IEntidadTemplate p = new IEntidadTemplate();
			fich.println(p.generate(arg));
			fich.close();
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

}