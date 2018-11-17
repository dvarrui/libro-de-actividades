/**
 *	@AlmacenDePredicados.java
 */

import  java.io.*;
import  java.sql.*;
import  java.util.Properties;

//-------------------------
//Clase AlmacenDePredicados
//-------------------------
class AlmacenDePredicados extends Object
{
	Connection	con;
	String			usuario;
 	
	
 	//----------------
 	// abrirConexion()
 	//----------------
 	public boolean abrirConexion( String url, String login, String password)
  {

		try
		{
			//Cargar el driver JDBC
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con	      = DriverManager.getConnection(url, login, password);
			usuario 	= new String(login.toString());
		}
		catch(Exception e) 
		{	
			System.err.println("ERROR abrirConexion(): " + e.getMessage());
			return false;
		}
		return true;
	}

 	//----------------------------------------------------------------------
 	// cerrarConexion()
 	//----------------------------------------------------------------------
 	public boolean cerrarConexion()
  {

		try
		{
			con.close();
		}
		catch(Exception e) 
		{	
			System.err.println("ERROR cerrarConexion(): " + e.getMessage());
			return false;
		}
		return true;
	}

	
	//-------------------------------------------------------------
	//construirObjeto()
 	//-------------------------------------------------------------
 	public boolean construirObjeto(String objeto, String descripcion, String operacion)
 	{
		Statement 			stm;
		ResultSet				rs;
		String					str;

		try
 		{	//Insertar Objeto en la BBD
			stm = con.createStatement();
			str = new String("SELECT count(*) FROM sys_objetos WHERE cod_objeto='"+objeto+"'");
			rs  = stm.executeQuery(str);
			
			rs.next();
			if (rs.getFloat(1) == 0 && operacion.equals("i"))
			{	//Crear un nuevo objeto
				str = new String("insert into SYS_OBJETOS (cod_objeto, des_objeto, cod_usuario) "+
   			  								"values ('"+objeto+"','"+descripcion+"','"+usuario+"')");
   	  	stm.executeUpdate(str);
			}
			else if (operacion.equals("i"))
			{	//Error el crear el objeto como nuevo
				System.err.println("WARN construirObjeto(insertar): El objeto ["+objeto+"] ya existe.");
				rs.close();
			} 
			else if (operacion.equals("m"))
			{	//Modificar objeto
				str = new String("UPDATE sys_objetos SET des_objeto='"+descripcion+"' AND "+
													"cod_usuario='"+usuario+"' WHERE cod_objeto='"+objeto+"'");
   	  	stm.executeUpdate(str);
			}
			else if (operacion.equals("e"))
			{	//Eliminar objeto
			}
 		}
 		catch(Exception e) 
		{	
			System.err.println("ERROR construirObjeto(): " + e.getMessage());
			return false;
		}
		return true;
 	}
 	
 	
	//-------------------------------------------------------------
	//construirPalabra()
 	//-------------------------------------------------------------
 	public boolean construirPalabra(String palabra, String objeto, 
 																	String idioma, String operacion)
 	{
		Statement 			stm;
		ResultSet				rs;
		String					str;

		try
 		{	
			stm = con.createStatement();
			str = new String("SELECT count(*) FROM sys_palabra_asoc_objeto WHERE "+
												"cod_palabra='"+palabra+"' AND cod_objeto='"+objeto+"' "+
												" AND cod_idioma='"+idioma+"'");
			rs  = stm.executeQuery(str);
			
			rs.next();
			if (rs.getFloat(1) == 0 && operacion.equals("i"))
			{	
				//Comprobar que exista el objeto antes de añadir la palabra
				rs.close();
				str = new String("SELECT count(*) FROM sys_objetos WHERE cod_objeto='"+objeto+"'");
				rs  = stm.executeQuery(str);
				rs.next();
				if (rs.getFloat(1) ==0)
				{
					System.err.println("ERROR construirPalabra(insertar): El objeto ["+objeto+"] no existe!!!");
				  return false;
				}
				else
				{
					//Comprobar que exista el idioma antes de añadir la palabra
					rs.close();
					str = new String("SELECT count(*) FROM sys_palabra_idiomas WHERE cod_idioma='"+idioma+"'");
					rs  = stm.executeQuery(str);
					rs.next();
					if (rs.getFloat(1) == 0 && operacion.equals("i"))
					{
						System.err.println("ERROR construirPalabra(insertar): El idioma ["+idioma+"] no existe!!!");
					  return false;
					}
					else
					{
						//Crear nueva palabra_asoc_objeto
						str = new String("insert into SYS_PALABRA_ASOC_OBJETO (cod_palabra, cod_objeto, "+
														"cod_idioma, cod_usuario) "+
   			  									"values ('"+palabra+"','"+objeto+"','"+idioma+"','"+usuario+"')");
   	  			stm.executeUpdate(str);
					}
				}
			}
			else if (operacion.equals("i"))
			{	//Error al crear palabra_asoc_objeto
				System.err.println("WARN construirPalabra(insertar): La palabra ["+palabra+","+
														objeto+","+idioma+"] ya existe!!!");
				rs.close();
			} 
			else if (operacion.equals("m"))
			{	//Modificar palabra
			}
			else if (operacion.equals("e"))
			{	//Eliminar palabra
			}
 		}
 		catch(Exception e) 
		{	
			System.err.println("ERROR construirPalabra(): " + e.getMessage());
			return false;
		}
		return true;
 	}
 	

	//-------------------------------------------------------------------------------
	//construirAtributo()
 	//-------------------------------------------------------------------------------
 	public boolean construirAtributo(String objeto, String atributo, String operador,
 																	String valor, String operacion)
 	{
		Statement 			stm;
		ResultSet				rs;
		String					str;

		try
 		{	
			stm = con.createStatement();
			
			//Comprobar el par (atributo,operador)
			if (operador.equals(":="))
			{
				str = new String("SELECT count(*) FROM sys_objeto_atributos WHERE "+
													"cod_objeto='"+objeto+"' AND cod_atributo='"+atributo+"' "+
													" AND cod_operador='"+operador+"'");
				rs  = stm.executeQuery(str);
				rs.next();
  			if (rs.getFloat(1) != 0)
  			{
  				System.err.println("WARN construirAtributo(): Atributo ["+objeto+"."+atributo+operador+valor+"] existente.");
  				return false;
  			}
  			rs.close();
			}
			else if (operador.equals("VP"))
			{
				str = new String("SELECT count(*) FROM sys_objeto_atributos WHERE "+
													"cod_objeto='"+objeto+"' AND cod_atributo='"+atributo+"' "+
													" AND cod_operador='"+operador+"' AND cod_valor='"+valor+"'");
				rs  = stm.executeQuery(str);
				rs.next();
  			if (rs.getFloat(1) != 0)
  			{
  				System.err.println("WARN construirAtributo(): Atributo ["+objeto+"."+atributo+" "+
  														operador+" "+valor+"] existente.");
  				return false;
  			}
  			rs.close();
			}
					
			//Comprobar que existe el Atributo
			str = new String("SELECT count(*) FROM sys_objetos WHERE cod_objeto='"+atributo+"'");
			rs  = stm.executeQuery(str);
			rs.next();
  		if (rs.getFloat(1) == 0)
  		{
  			System.err.println("ERROR construirAtributo(): Atributo ["+atributo+"] no existe.");
  			return false;
  		}

			//Comprobar que existe el Objeto
			str = new String("SELECT count(*) FROM sys_objetos WHERE cod_objeto='"+objeto+"'");
			rs  = stm.executeQuery(str);
			rs.next();
  		if (rs.getFloat(1) == 0)
  		{
  			System.err.println("ERROR construirAtributo: Objeto ["+objeto+"] no existe.");
  			rs.close();
  			return false;
  		}
  		
			//Comprobar que existe el Operador
			str = new String("SELECT count(*) FROM sys_objeto_atributo_operadores WHERE cod_operador='"+operador+"'");
			rs  = stm.executeQuery(str);
			rs.next();
  		if (rs.getFloat(1) == 0)
  		{
  			System.err.println("ERROR construirAtributo: Operador ["+operador+"] no existe.");
  			rs.close();
  			return false;
  		}
    		
			//Comprobar que existe el valor definido para dicho atributo
  		if (operador.equals(":=") || operador.equals("VP"))
  		{
				str = new String("SELECT count(*) FROM sys_objeto_atributos WHERE cod_objeto='"+atributo+"' "+
													" AND cod_atributo='valorObjeto' AND cod_valor='"+valor+"'");
				rs  = stm.executeQuery(str);
				rs.next();
  			if (rs.getFloat(1) == 0)
  			{
  				System.err.println("ERROR construirAtributo: Valor ["+atributo+" "+operador+" "+valor+"] no definido.");
  				rs.close();
  				return false;
  			}
  		}
  		else if (operador.equals("SE") && valor.length()!=0)
			{
 				System.err.println("ERROR construirAtributo: Valor ["+atributo+" "+operador+" "+valor+"] no nulo.");
 				return false;
 			}
 			

  		
			if (operacion.equals("i"))
			{	
				//Insertar el nuevo registro en la BBDD
				str = new String("INSERT INTO sys_objeto_atributos (cod_objeto,cod_atributo,cod_operador,cod_valor,cod_usuario) "+
												"VALUES('"+objeto+"','"+atributo+"','"+operador+"','"+valor+"','"+usuario+"')");
				stm.executeUpdate(str);
			}
			else if (operacion.equals("m"))
			{	//Modificar objeto.atributo.operador.valor
			}
			else if (operacion.equals("e"))
			{	//Eliminar objeto.atributo.operador.valor
			}
 		}
 		catch(Exception e) 
		{	
			System.err.println("ERROR construirAtributo(): " + e.getMessage());
			return false;
		}
		return true;
 	}
 	
	//---------------------------------------------------------------------------------
	//construirIdioma()
 	//---------------------------------------------------------------------------------
 	public boolean construirIdioma(String idioma, String descripcion, String operacion)
 	{
		Statement 			stm;
		ResultSet				rs;
		String					str;

		try
 		{	//Insertar Objeto en la BBD
			stm = con.createStatement();
			str = new String("SELECT count(*) FROM sys_palabra_idiomas WHERE cod_idioma='"+idioma+"'");
			rs  = stm.executeQuery(str);
			
			rs.next();
			if (rs.getFloat(1) == 0 && operacion.equals("i"))
			{	//Crear un nuevo objeto
				str = new String("insert into SYS_palabra_idiomas (cod_idioma, des_idioma, cod_usuario) "+
   			  								"values ('"+idioma+"','"+descripcion+"','"+usuario+"')");
   	  	stm.executeUpdate(str);
			}
			else if (operacion.equals("i"))
			{	//Error el crear el objeto como nuevo
				System.err.println("WARN construirIdioma(insertar): El idioma ["+idioma+"] ya existe!!!");
				rs.close();
			} 
			else if (operacion.equals("m"))
			{	//Modificar idioma
			}
			else if (operacion.equals("e"))
			{	//Eliminar idioma
			}
 		}
 		catch(Exception e) 
		{	
			System.err.println("ERROR construirIdioma(): " + e.getMessage());
			return false;
		}
		return true;
 	}

 	
	//-------------------------------------------------------------------------------
	//verDefinicionObjeto()
 	//-------------------------------------------------------------------------------
 	public void verDefinicionObjeto(String objeto)
 	{
		Statement 			stm;
		ResultSet				rs;
		String					str;

		try
 		{	
			stm = con.createStatement();
			
  		str = new String("SELECT * FROM sys_objetos WHERE cod_objeto='"+objeto+"'");
			rs  = stm.executeQuery(str);
			rs.next();
			System.out.println("cod_objeto  = "+rs.getString("cod_objeto"));
			System.out.println("des_objeto  = "+rs.getString("des_objeto"));
			System.out.println("cod_usuario = "+rs.getString("cod_usuario"));
			System.out.println("fec_ult_mod = "+rs.getString("fec_ult_mod"));
			rs.close();
			
  		str = new String("SELECT * FROM sys_objeto_atributos WHERE cod_objeto='"+objeto+"' "+
  										"order by cod_atributo, cod_valor");
			rs  = stm.executeQuery(str);
			while(rs.next())
  		{
				System.out.println(rs.getString("cod_objeto")+"."+rs.getString("cod_atributo")+
													" "+rs.getString("cod_operador")+" "+rs.getString("cod_valor"));
			}
 			rs.close();
 		}
 		catch(Exception e) 
		{	
			System.err.println("ERROR verDefinicionObjeto(): " + e.getMessage());
		}
 	}

 	
	//----------------------------------------------
	//verDefinicionPalabra()
 	//----------------------------------------------
 	public void verDefinicionPalabra(String palabra)
 	{
		Statement 			stm;
		ResultSet				rs;
		String					str;

		try
 		{	
			stm = con.createStatement();
			
  		str = new String("SELECT * FROM sys_palabra_asoc_objeto WHERE cod_palabra='"+palabra+"'");
			rs  = stm.executeQuery(str);
			
			while(rs.next())
			{
				str = new String(rs.getString("cod_objeto"));
				System.out.println("Palabra     = "+rs.getString("cod_palabra"));
				System.out.println("ID concepto = "+str);
				System.out.println("ID idioma   = "+rs.getString("cod_idioma"));
				System.out.println("Usuario     = "+rs.getString("cod_usuario"));
				System.out.println("Fec_ult_mod = "+rs.getString("fec_ult_mod"));
				System.out.println();
				verDefinicionObjeto(str);
				System.out.println();
			}
			rs.close();
 		}
 		catch(Exception e) 
		{	
			System.err.println("ERROR verDefinicionPalabra(): " + e.getMessage());
		}
 	}

	
 	
 	//-------------------------------------
 	// procedimiento MAIN de appConstructor
	//-------------------------------------
 	public static void main(String args[]) 
	{
  	Properties props = System.getProperties();
  	props.setProperty("file.encoding","ISO8859_1");

  	if (args.length > 0) 
  	{
    	if (args[0].equals("-f"))
    	{	
    		//Ejecutar la construcción desde fichero
    		//parse("txt", args[1], args[2]);		//Realizar parsing de tipo txt
    	}
  		else if (args[0].equals("-h") || args[0].equals("-ayuda") || args[0].equals("-?"))
  		{ 
  			//Mostrar menú de ayuda
 				System.err.println("Programa:                appConstructor");
 				System.err.println("Función:                 Construye los objetos.");
 				System.err.println("-f  p1 p2 p3 p4          Construye desde el fichero p1 hacia la BBDD p2.");
				System.err.println("                         p3 y p4 identifican al usuario/clave de acceso a BBDD.");
				System.err.println("-h                       Muestra la ayuda en pantalla");
				System.err.println("-?");
				System.err.println("-ayuda");
				System.err.println("-io p1 p2 p3 p4 p5       Construir nuevo Objeto.");
				System.err.println("                         p1 = objeto,  p2 = descripción.");
				System.err.println("                         p2,p3,p4 = localizador de la BBDD, usuario y clave.");
				System.err.println("-ip p1 p2 p3 p4 p5 p6    Construir Palabra.");
				System.err.println("                         p1 = palabra, p2 = objeto,   p3 = idioma.");
				System.err.println("                         p4 = BBDD   , p5 = usuario y p6 = clave.");
				System.err.println("-ia p1 p2 p3 p4 p5 p6 p7 Construir Atributo.");
				System.err.println("                         p1 = objeto , p2 = atributo, p3 = operador, p4 = valor.");
				System.err.println("                         p5 = BBDD   , p6 = usuario y p7 = clave.");
				System.err.println("-ii p1 p2 p3 p4 p5       Construir Idioma.");
				System.err.println("                         p1 = código , p2 = descripcion.");
				System.err.println("                         p3 = BBDD   , p4 = usuario y p5 = clave.");
				System.err.println("-vo p1 p2 p3 p4          Ver definición del objeto.");
				System.err.println("                         p1 = objeto.");
				System.err.println("                         p2 = BBDD   , p3 = usuario y p4 = clave.");
				System.err.println("-vp p1 p2 p3 p4          Ver definición de la palabra.");
				System.err.println("                         p1 = objeto.");
				System.err.println("                         p2 = BBDD   , p3 = usuario y p4 = clave.");
				System.err.println("-v                       Versión del programa");
				System.err.println("-version");
  		}
    	else if (args[0].equals("-io"))
    	{	
    		//Realizar la inserción de un nuevo objeto
    		appConstructor app = new appConstructor();
    		//Usar los parámetros args[3], args[4] y args[5]
    		app.abrirConexion("jdbc:odbc:google",args[4],"");
    		app.construirObjeto(args[1],args[2],"i");
    		app.cerrarConexion();
    	}
    	else if (args[0].equals("-ip"))
    	{	
    		//Realizar la inserción de palabra_asoc_objeto
    		appConstructor app = new appConstructor();
    		//Usar los parámetros args[4], args[5] y args[6]
    		app.abrirConexion("jdbc:odbc:google",args[5],"");
    		app.construirPalabra(args[1],args[2],args[3],"i");
    		app.cerrarConexion();
    	}
    	else if (args[0].equals("-ia"))
    	{	
    		//Construir Atributo
    		appConstructor app = new appConstructor();
    		//Usar los parámetros args[5], args[6] y args[7]
    		app.abrirConexion("jdbc:odbc:google",args[6],"");
    		app.construirAtributo(args[1],args[2],args[3],args[4],"i");
    		app.cerrarConexion();
    	}
    	else if (args[0].equals("-ii"))
    	{	
    		//Construir Idioma
    		appConstructor app = new appConstructor();
    		//Usar los parámetros args[3], args[4] y args[5]
    		app.abrirConexion("jdbc:odbc:google",args[4],"");
    		app.construirIdioma(args[1],args[2],"i");
    		app.cerrarConexion();
    	}
    	else if (args[0].equals("-vo"))
    	{	
    		//Ver definición del objeto
    		appConstructor app = new appConstructor();
    		//Usar los parámetros args[2], args[3] y args[4]
    		app.abrirConexion("jdbc:odbc:google",args[3],"");
    		app.verDefinicionObjeto(args[1]);
    		app.cerrarConexion();
    	}
    	else if (args[0].equals("-vp"))
    	{	
    		//Ver definición de la palabra
    		appConstructor app = new appConstructor();
    		//Usar los parámetros args[2], args[3] y args[4]
    		app.abrirConexion("jdbc:odbc:google",args[3],"");
    		app.verDefinicionPalabra(args[1]);
    		app.cerrarConexion();
    	}
    	else if (args[0].equals("-v")||args[0].equals("-version"))
    	{	
    		System.out.println("Programa appConstructor\nVersión 0.0.2 2002.06.17\nDVR");
    	}
	  	else
  		{	
  			System.err.println("Escribir: java appConstructor -h");
  		}
  	}
  	else
  	{	System.err.println("Escribir: java appCargador -h");}
  }
}
 