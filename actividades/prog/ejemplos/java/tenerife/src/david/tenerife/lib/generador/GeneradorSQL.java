package david.tenerife.lib.generador;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.Campo;
import david.tenerife.lib.Parametros;
import david.tenerife.lib.Tabla;
import david.tenerife.util.*;

import java.io.*;
import java.util.Date;

/**
 * Generador de código SQL
 * Se generan los ficheros SQL necesarios para la creación de las distintas
 * tablas en la base de datos SQL.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051122
 */
public class GeneradorSQL {
	BaseDatos bd;

	Parametros param;

	public GeneradorSQL(BaseDatos basedatos, Parametros parametros) {
		bd = basedatos;
		param = parametros;
	}

	public void generarCodigo() {
		generarCrearTabla();
	}

	/**
	 * Generar ficheros para crear cada tabla de la base de datos
	 */
	private void generarCrearTabla() {
		try {
			PrintWriter fich;
			Date fecha = new Date();


			for (int i = 0; i < bd.getNumeroTablas(); i++) {
				Tabla t = bd.getTabla(i);

				String ruta = param.getValor(Parametros.DIR_SALIDA)
				+ "/" + Parametros.SQL.toLowerCase() + "/crearTabla";
				Ficheros.crearRuta(ruta);

				// Crear fichero para la creación de las tablas
				fich = new PrintWriter(ruta + "/"+t.getNombre()+".sql");
				fich.println("/* Proyecto TENERIFE */");
				fich.println("/* Fecha:" + fecha.toString()+" */");
				fich.println();
				fich.println("CREATE TABLE " + t.getNombreOrigen());
				fich.println("{");
				for(int j=0; j<t.getNumeroCampos();j++) {
					Campo c = t.getCampo(j);
					fich.print("   "
							+ c.getNombreOrigen() + "   "+Notacion.getTipoSQL(c.getTipo()));
					if (c.isClave()) fich.print("   PRIMARY KEY");
					if (j<t.getNumeroCampos()-1) fich.print(",");
					fich.println();
				}
				fich.println("}\n");
				fich.close();
			}
		} catch (Exception e) {
			System.err.println("[ERROR]:" + e);
		}
	}

}
