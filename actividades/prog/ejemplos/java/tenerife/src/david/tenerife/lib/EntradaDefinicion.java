package david.tenerife.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Esta clase realiza la lectura de un fichero de entrada.definición y crea la
 * estructura de objetos BaseDatos->Tabla->Campos que contendrá la definición de
 * la aplicación a generar.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060109
 * 
 */
public class EntradaDefinicion {
	public static final String BASEDATOS = "basedatos";
	public static final String TABLA = "tabla";
	public static final String CAMPO = "campo";
	

	public static BaseDatos ejecutar(Parametros param) {
		BaseDatos bd = new BaseDatos();

		if (param.getValor(Parametros.INPUT_MODE).equals("prueba"))
			bd = ejecutarPrueba();
		else if (param.getValor(Parametros.INPUT_MODE).equals("fichero"))
			bd = ejecutarFichero(Parametros.DIR_BASE
					+ "david/tenerife/rec/definicion.entrada");
		else if (param.getValor(Parametros.INPUT_MODE).equals("basedatos"))
			bd = ejecutarBaseDatos();
		return bd;
	}

	private static BaseDatos ejecutarBaseDatos() {
		// @TODO Incluir código para leer desde BBDD y
		// contruir el objeto definición de la base de datos
		//
		// De momento no se implementa y puede ser que no haga falta.
		return new BaseDatos();
	}

	/**
	 * Carga unos datos de entrada desde un fichero externo
	 * 
	 * @param pEntrada: Nombre del fichero de entrada
	 * @return devuelve un objeto tipo <b>basedatos</b>
	 */
	private static BaseDatos ejecutarFichero(String pEntrada) {
		// Incluir código para leer desde fichero y
		// contruir el objeto definición de la base de datos
		String str, str2;
		StringTokenizer st1, st2;
		BaseDatos bd = new BaseDatos();
		Tabla t = new Tabla();
		Campo c = new Campo();

		try {
			BufferedReader entrada = new BufferedReader(
					new FileReader(pEntrada));
			str = entrada.readLine();
			while (str != null) {
				// Procesar la línea leída
				if (str.trim().toLowerCase().startsWith(BASEDATOS)) {
					st1 = new StringTokenizer(str.trim(), " ", false);
					st1.nextToken(); // basedatos

					bd = new BaseDatos();
					bd.setNombre(st1.nextToken());

					// Se leen los posible atributos de BaseDatos
					while (st1.hasMoreTokens()) {
						st2 = new StringTokenizer(st1.nextToken().trim(), "=",
								false);
						str2 = st2.nextToken();
						if (str2.equals("paqueteJava")) {
							bd.setPaqueteJava(st2.nextToken());
						} else if (str2.equals("nombreProyecto")) {
							bd.setNombreProyecto(st2.nextToken());
						}
					}

				} else if (str.trim().toLowerCase().startsWith(TABLA)) {
					st1 = new StringTokenizer(str.trim(), " ", false);
					st1.nextToken(); // tabla
					t = new Tabla();
					t.setNombre(st1.nextToken());
					bd.addTabla(t);
				} else if (str.trim().toLowerCase().startsWith(CAMPO)) {
					st1 = new StringTokenizer(str.trim(), " ", false);
					st1.nextToken(); // campo

					c = new Campo();
					c.setNombre(st1.nextToken());
					c.setTipo(st1.nextToken());

					while (st1.hasMoreElements()) {
						st2 = new StringTokenizer(st1.nextToken().trim(), "=",
								false);
						str2 = st2.nextToken();
						if (str2.equals("clave")) {
							c.setClave(true);
						} else if (str2.equals("externo")) {
							c.setTablaFK(st2.nextToken());
						} else if (str2.equals("etiqueta")) {
							c.setEtiqueta(st2.nextToken());
						} else if (str2.equals("descripcion")) {
							c.setDescripcion(true);
						} else if (str2.equals("expresionRegular")) {
							c.setExpresionRegular(st2.nextToken());
						}
					}
					t.addCampo(c);
				} else if (!str.trim().startsWith("#")
						&& str.trim().length() > 0 && str != null) {
					System.err.println("[ERROR] Formato de fichero(" + pEntrada
							+ ") incorrecto \n" + str);
				}
				str = entrada.readLine();
			}
			entrada.close();
		} catch (Exception e) {
			System.err.println("[ERROR] EntradaDefinicion.ejecutarFichero():"
					+ e);
		}
		return bd;
	}

	/**
	 * Carga unos datos de entrada a modo prueba
	 * 
	 * @return un objeto <b>basedatos</b>
	 */
	private static BaseDatos ejecutarPrueba() {
		BaseDatos bd;
		Tabla t;
		Campo c;

		bd = new BaseDatos();
		bd.setNombre("PROYECTO_HIGUERITA");
		t = new Tabla();
		t.setNombre("CLIENTES");
		c = new Campo();
		c.setNombre("NOMBRE");
		c.setTipo("TEXT");
		t.addCampo(c);
		c = new Campo();
		c.setNombre("APELLIDO1");
		c.setTipo("TEXT");
		t.addCampo(c);
		c = new Campo();
		c.setNombre("APELLIDO2");
		c.setTipo("TEXT");
		t.addCampo(c);
		c = new Campo();
		c.setNombre("FECHA_NACIMIENTO");
		c.setTipo("DATE");
		t.addCampo(c);
		c = new Campo();
		c.setNombre("DNI");
		c.setClave(true);
		c.setTipo("TEXT");
		t.addCampo(c);
		bd.addTabla(t);

		t = new Tabla();
		t.setNombre("AGENDAS");
		c = new Campo();
		c.setNombre("COD_AGENDA");
		c.setTipo("INT");
		c.setClave(true);
		t.addCampo(c);
		c = new Campo();
		c.setNombre("DES_AGENDA");
		c.setTipo("TEXT");
		t.addCampo(c);
		bd.addTabla(t);

		return bd;
	}

}
