/**
 *	@AlmacenFisico.java
 *  Versión : 0.0.1
 *  Fecha UM: 20030216
 */

/* Descripción:
   Esta clase ofrece al usuario servicios de almacenamiento de los predicados
   de forma que el sistema de almacenamiento físico es independiente al usuario.
   Podría almacenarse la información en SGBDR, en ficheros planos TXT, etc.
   Esta clase crea una capa abstracta para facilitar las tareas de almacenaje.
*/

import  java.io.*;
import  java.sql.*;
import	java.util.Properties;


public class AlmacenFisico extends Object
{
	private	Connection	con;
	private	String			usuario;
 	
	
 	//--------
 	// abrir()
 	//--------
 	public boolean abrir( String url, String login, String password)
  {
		/* Método que abre una conexión con la base de datos o sistema de 
		almacenamiento persistente de los datos	*/
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

 	//---------
 	// cerrar()
 	//---------
 	public boolean cerrar()
  {
		/* Se cierra la conexión de la base de datos o sistema de almacenamiento
		persistente	*/
		try
		{	con.close();}
		catch(Exception e) 
		{	
			System.err.println("ERROR cerrar(): " + e.getMessage());
			return false;
		}
		return true;
	}
	
	
	//-----------------------------------
	private int selectTermino(String term)
	{
		/* Comprueba que el término exista */
		return 1;
	}
	
	//-----------------------------------
	public int insertTermino(String term)
	{
		/* Crea un nuevo término en el almacén */
		return 1;
	}

	//-----------------------------------
	public int updateTermino(String term)
	{
		/* Modifica un término en el almacén */
		return 1;
	}
	
	//-----------------------------------
	public int deleteTermino(String term)
	{
		/* Borra el término del almacén */
		return 1;
	}

	//---------------------------------------------------------
	public boolean selectPredicado(String predTexto, String Mt)
	{
		/* Esta función localiza el predicado <pred> en el almacén dentro
		   de la Microteoria <Mt> (Es una división lógica del Almacén)
		   La función devuelve "true" o "false".
    */
		Predicado pred = new Predicado(predTexto);
		if (pred.getEstado()!=1 || pred.getNumPredicados()<1) return false;
		
		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();

		str1.append("select count(*) ");
		str2.append(" from tbPREDICADOS_CABECERA cab ");
		str2.append(",tbPREDICADOS_DETALLE det1 ");
		if (pred.getNumPredicados()>1) str2.append(",tbPREDICADOS_DETALLE det2 ");
		if (pred.getNumPredicados()>2) str2.append(",tbPREDICADOS_DETALLE det3 ");
		if (pred.getNumPredicados()>3) str2.append(",tbPREDICADOS_DETALLE det4 ");
		if (pred.getNumPredicados()>4) str2.append(",tbPREDICADOS_DETALLE det5 ");
		str2.append("where cab.id_predicado = det1.id_predicado ");
		str2.append("and det1.num_orden=1 ");

		if (pred.getNumPredicados()>1)
		{
			str2.append("and cab.id_predicado=det2.id_predicado ");
			str2.append("and det2.num_orden=2 ");
		}
		if (pred.getNumPredicados()>2)
		{
			str2.append("and cab.id_predicado = det3.id_predicado ");
			str2.append("and det3.num_orden=3 ");
		}
		if (pred.getNumPredicados()>3)
		{
			str2.append("and cab.id_predicado = det4.id_predicado ");
			str2.append("and det4.num_orden=4 ");
		}
		if (pred.getNumPredicados()>4)
		{
			str2.append("and cab.id_predicado = det5.id_predicado ");
			str2.append("and det5.num_orden=4 ");
		}

		if (Mt.length()>0) str2.append(" and cab.mt = '"+Mt+"'");
		
		//Para cada sub-predicado del predicado <pred> hacer
		for (int i=0;i<pred.getNumPredicados();i++)
		{	
			for(int j=0;j<5;j++)
			{
				if (pred.getTermino(i,j).length()<=0)
				{
					//Es el final del predicado [i]
					break;
				}
				else if (pred.getTermino(i,j).startsWith("_"))
				{
					//Tenemos un link a otro predicado
					str2.append(" and det"+(i+1)+".arg"+j+"='"+pred.getTermino(i,j)+"'");
				}
				else
				{
					//Tenemos un término(j) en el predicado(i)
					str2.append(" and det"+(i+1)+".arg"+j+"='"+pred.getTermino(i,j)+"'");
				}
			}
		}

		System.out.println(str2);
		return true;
	}

   /*
       NOTA:
       Partiendo de un predicado en formato "Predicado.class" y de una
		   Microteoría <Mt> se localiza el predicado <pred> en el almacén de datos.
		   Como resultado de la búsqueda tenemos varias opciones:
		   (a) Devolver "T" (verdadero) o "F" (falso).
		   (b) Devolver el número de predicados seleccionados
		   (c) Devolver la lista de predicados seleccionados
		       A su vez se puede devolver los ID o el texto.
		       El resultado se podría devolver sobre tbRESULTADOS (ID, orden, texto)
		*/

	//------------------------------------------------
	public int selectPredicadoPorID(int id, int orden)
	{
		/* Localiza predicado en el almacén por su [ID, orden]
		*/
		return 1;
	}
	
	//-------------------------
	public int resultadoCount()
	{
		/* Ver el resultado de la última sentencia selectPredicado */
		return 1;
	}
	
	//-------------------------
	public int resultadoFirst()
	{
		/* Ver el resultado de la última sentencia selectPredicado */
		return 1;
	}

	//---------------------------
	public String resultadoNext()
	{
		/* Ver el resultado de la última sentencia selectPredicado */
		return "";
	}
	
	//---------------------------------------------
	public int insertPredicado(String p, String Mt)
	{
		//La inserción de la lista/predicado se
		
		//p.SelectPredicado() si no se encuentra continuo
		//Buscar un ID libre
		
		//Para cada predicado
		//p.firstPred()
		//pred1=p.getPred()
		//p.getTermino(pred1)
		//Construir sentencia
		//insert into tbPREDICADOS (ID, orden, arg1, arg2, arg3, arg4, arg5, Mt, usuario, fecha) 
		//values (ID, 1, p.getTerm(1,1), p.getTerm(1,2), p.getTerm(1,3),..., Mt, usuario, fecha)
		//insert into tbPREDICADOS (ID, orden, arg1, arg2, arg3, arg4, arg5, Mt, usuario, fecha) 
		//values (ID, 2, p.getTerm(2,1), p.getTerm(2,2), p.getTerm(2,3),..., Mt, usuario, fecha)
		
		return 1;
	}
	
	//---------------------------------------------
	public int deletePredicado(String p, String Mt)
	{
		//p.SelectPredicado() si se encuentra continuo
		
		//delete tbPREDICADOS where ID = p.SelectPredicado???
		return 1;
	}
	
	
	public static void main(String args[])
	{
		AlmacenFisico almac = new AlmacenFisico();
		
		System.out.println("abrir()            ="+almac.abrir("jdbc:odbc:YEC","",""));
		System.out.println("selectPredicado()  ="+almac.selectPredicado("(isa David Persona)",""));
		System.out.println("insertPredicado()  =");
		System.out.println("deletePredicado()  =");
		System.out.println("cerrar()           ="+almac.cerrar());
	}
}