package david.tenerife.old;

import david.tenerife.ejb.conexiones.Conexion;
import david.tenerife.util.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Principal extends Object {

	public static void main(String args[]) {
		//Declaraci�n de variables
		Configuracion conf; //Configuraci�n del proyecto
		Conexion conexion; //Conexi�n con la base de datos
		Statement st1;
		ResultSet rs1;
		ResultSetMetaData rsmd;
		boolean tiene_pk;
		ArrayList tablas; //Listado de tablas de la BBDD
		PrintWriter fich; //Fichero de LOG
		PrintWriter fich2; //Fichero de JAVA. Variable usada para generar c�digo java
		//PrintWriter fich3; //Fichero de PHP. Variable usada para generar c�digo php

		try {
			/*
			 * PASO 01: Leer fichero de configuraci�n Abrir fichero de log
			 * <fich>
			 */
			conf = new Configuracion();
			conf.setFichero("david.tenerife.rec.inicio");

			fich = new PrintWriter(new FileWriter(conf.getString("file.log")));
			fich.println("Inicio " + new java.util.Date() + "\n");

			/*
			 * PASO 02: Establecer Conexi�n con BBDD Crear MetaDatos
			 */
			conexion = new Conexion();
			conexion.inicializar(conf.getString("driver"), conf
					.getString("basededatos"), conf.getString("usuario"), conf
					.getString("clave"), conf.getString("entorno"));

			DatabaseMetaData md = conexion.leeConnection().getMetaData();

			fich.println("DriverName             = " + md.getDriverName());
			fich.println("DriverVersion          = " + md.getDriverVersion());
			fich.println("URL                    = " + md.getURL());
			fich.println("DatabaseProductName    = "
					+ md.getDatabaseProductName());
			fich.println("DatabaseProductVersion = "
					+ md.getDatabaseProductVersion());
			fich.println("UserName               = " + md.getUserName());

			/* PASO 03: Lectura de las tablas de la BBDD */
			fich.println("\nTablas de la BBDD:\n");

			rs1 = md.getTables(null, null, null, new String[] { "TABLE" });
			tablas = new ArrayList();
			while (rs1.next()) {
				tablas.add(rs1.getString("TABLE_NAME"));
			}
			rs1.close();
			for (int i = 0; i < tablas.size(); i++)
				fich.println("\ttabla[" + i + "]=" + (String) tablas.get(i));

			//PASO 04: Para cada tabla hacer...
			fich.println("\nDefinici�n de cada tabla:\n");

			for (int i = 0; i < tablas.size(); i++) {
				CampoBD reg = new CampoBD();
				ArrayList columnas = new ArrayList(); //Contiene todos los campos de la tabla

				//PASO 05: Leer las columnas de la tabla
				st1 = conexion.leeConnection().createStatement();
				rs1 = st1.executeQuery("SELECT * FROM "
						+ tablas.get(i).toString());
				rsmd = rs1.getMetaData();

				for (int j = 1; j <= rsmd.getColumnCount(); j++) {
					reg = new CampoBD();
					reg.setTabla(tablas.get(i).toString());
					reg.setNombre(rsmd.getColumnLabel(j));
					reg.setTipo(rsmd.getColumnTypeName(j) + "("
							+ rsmd.getColumnDisplaySize(j) + ")");

					columnas.add(reg);
				}
				reg.setNombre(reg.getTabla()); //DVR???

				//PASO 06: Generar salida para LOG
				//LOG: Lectura de los campos de las tablas
				fich.println("\nTabla " + tablas.get(i).toString() + " con = "
						+ columnas.size() + " campos.\n");
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich.println("  \t" + c.getTabla() + "." + c.getNombre()
							+ "\t{" + c.getTipo() + "}");
				}
				//LOG: Claves primarias
				tiene_pk=false;
				rs1 = md.getPrimaryKeys(null, null, tablas.get(i).toString());
				while (rs1.next()) {
					tiene_pk=true;
					fich.println("PK\t" + tablas.get(i).toString() + "."
							+ rs1.getString("COLUMN_NAME"));
				}

				//LOG:
				rs1 = md.getExportedKeys(null, null, tablas.get(i).toString());
				if (rs1 != null) {
					while (rs1.next()) {
						fich.println("EK\t" + tablas.get(i).toString() + "."
								+ rs1.getString(8));
					}
				}

				rs1 = md.getImportedKeys(null, null, tablas.get(i).toString());
				if (rs1 != null) {
					while (rs1.next()) {
						fich.println("IK\t" + tablas.get(i).toString() + "."
								+ rs1.getString(8));
					}
				}

				
				//PASO 07: Crear fichero java para la entidad
				fich2 = new PrintWriter(new FileWriter(conf
						.getString("dir.salida.java")
						+ "/" + reg.getJavaClase() + ".java"));
				fich2.println("import java.util.*; //S�lo necesario si usamos fechas\n");
				fich2.println("public class " + reg.getJavaClase() + " {");
				
				//JAVA: Escribir lo atributos en fichero Entidad
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich2.println("   private " + c.getTipoJava() + " "
							+ c.getJavaAtributo() + ";");
				}
				fich2.println();

				//JAVA: Escribir los m�todos GET
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich2.print("   public " + c.getTipoJava() + " get"
							+ c.getJavaMetodo() + "()\t{ return "
							+ c.getJavaAtributo() + ";}\n");
				}
				fich2.println();

				//JAVA: Escribir los m�todos SET
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich2.print("   public void set" + c.getJavaMetodo() + "("
							+ c.getTipoJava() + " " + c.getJavaAtributo()
							+ ")\t{ this." + c.getJavaAtributo() + "="
							+ c.getJavaAtributo() + ";}\n");
				}
				fich2.println("}");
				fich2.close();

				//PASO 08: Crear la clase BaseDatos
				fich2 = new PrintWriter(new FileWriter(conf
						.getString("dir.salida.java")
						+ "/BaseDatos" + reg.getJavaClase() + ".java"));
				fich2.println("import "+conf.getString("paquete.util")+".*; //Necesario para la clase Conexion");
				fich2.println("import java.sql.*;\n");
				fich2.println("public class BaseDatos" + reg.getJavaClase()
						+ " {");
				fich2.println("   public static final String NOMBRETABLA=\""
						+ reg.getTabla() + "\";");
				fich2.println("   private Conexion conexion;\n");
				fich2.println("   public BaseDatos" + reg.getJavaClase()
						+ "(Conexion pConexion) {");
				fich2.println("      conexion=pConexion;");
				fich2.println("   }\n");
				//Clase BD: M�todo getAll
				fich2.println("   public ArrayList getAll() {");
				fich2.println("      Statement st=conexion.getStatement();");
				fich2.println("      ResultSet rs;");
				fich2.println("      ArrayList lista=new ArrayList();\n");
				fich2
						.println("      rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA);");
				fich2.println("      while(rs.next())");
				fich2.println("      {");
				fich2.println("         " + reg.getJavaClase() + " reg = new "
						+ reg.getJavaClase() + "();");

				//Para cada campo debemos leer su valor
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich2.println("         reg.set" + c.getJavaMetodo()
							+ "(rs.get" + c.getJavaMetodoResultSet() + "(" + j + "));");
				}

				fich2.println("         lista.add(reg);");
				fich2.println("      }");
				fich2.println("      return lista;");
				fich2.println("   }\n");
				//Clase BD: M�todo getById
				if (tiene_pk==false) 
				{
					fich2.println("/*");
					fich2.println("   //No existe un campo Primary Key");
					fich2.println("   //Se puede borrar este m�todo");
				}
				fich2.println("   public " + reg.getJavaClase()
						+ " getById(int id) {");
				fich2.println("      Statement st=conexion.getStatement();");
				fich2.println("      ResultSet rs;");
				fich2.println("      " + reg.getJavaClase() + " reg = new "
						+ reg.getJavaClase() + "();");

				fich2.print("      rs = st.executeQuery(\"SELECT * FROM \"+NOMBRETABLA+\" WHERE \"");

				rs1 = md.getPrimaryKeys(null, null, tablas.get(i).toString());
				while (rs1.next()) {
					fich2.print(rs1.getString("COLUMN_NAME") + " = \"+id");
				}

				fich2.println(");");

				fich2.println("      while(rs.next())");
				fich2.println("      {");

				//Para cada campo debemos leer su valor
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich2.println("         reg.set" + c.getJavaMetodo()
							+ "(rs.get" + c.getJavaMetodoResultSet() + "(" + j + "));");
				}
				fich2.println("      }");
				fich2.println("      return reg;");
				fich2.println("   }");
				if (tiene_pk==false) fich2.println("*/");
				fich2.println();
				
				//Clase BD: Crear m�todo ADD
				fich2.println("   public void add("+reg.getJavaClase()
						+" "+reg.getJavaAtributo() +") {");
				fich2.println("      Statement st=conexion.getStatement();");
				fich2.print("      st.executeUpdate(\"INSERT INTO \"+NOMBRETABLA+\" (");

				for (int j = 0; j < columnas.size(); j++) {
							CampoBD c = (CampoBD) columnas.get(j);
							fich2.print(c.getNombre());
							if (j+1<columnas.size()) fich2.print(",");
				}
				fich2.println(")\"");
				fich2.print("         +\"VALUES ('\"");
				for (int j = 0; j < columnas.size(); j++) {
					CampoBD c = (CampoBD) columnas.get(j);
					fich2.print("+"+reg.getJavaAtributo()+".get"+c.getJavaMetodo()+"()");
					if (j+1<columnas.size()) fich2.print("+\"','\"");
				}
				fich2.println("+\"');\");");
				fich2.println("      st.close();");
				fich2.println("   }\n");
				
				fich2.println("}"); //Terminar la creaci�n de Almac�n
				fich2.close();
				
				//PASO 09: Generar salida para PHP
				
				//PASO 10: Desconectar
				st1.close();
				if (rs1 != null)
					rs1.close();
			}
			conexion.desconectar();
			fich.println("\nFin    " + new java.util.Date());
			fich.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}