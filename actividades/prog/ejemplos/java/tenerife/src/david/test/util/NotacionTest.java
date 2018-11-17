package david.test.util;

import junit.framework.TestCase;
import david.tenerife.util.Notacion;

public class NotacionTest extends TestCase {

	String camel;

	String hungara;

	String nombre;

	String nombre2;

	String nombre3;

	String nombre4;

	String pascal;

	String tipo;

	protected void setUp() throws Exception {
		super.setUp();
		nombre = "david_vargas_ruiz";
		nombre2 = "davidVargasRuiz";
		nombre3 = "DAVID_VARGAS_RUIZ";
		nombre4 = "DavidVargasRuiz";
		pascal = "DavidVargasRuiz";
		camel = "davidVargasRuiz";
		tipo = "string";
		hungara = "stringDavidVargasRuiz";
	}

	/*
	 * Test method for 'david.tenerife.util.Notacion.getCamel(String)'
	 */
	public void testGetCamel() {
		this.assertEquals(camel, Notacion.getCamel(nombre));
	}

	/*
	 * Test method for 'david.tenerife.util.Notacion.getHungara(String, String)'
	 */
	public void testGetHungara() {
		this.assertEquals(hungara, Notacion.getHungara(nombre, tipo));
	}

	/*
	 * Test method for 'david.tenerife.util.Notacion.getPascal(String)'
	 */
	public void testGetPascal() {
		this.assertEquals(pascal, Notacion.getPascal(nombre));
	}

	/*
	 * Test method for 'david.tenerife.util.Notacion.getSinGuiones(String)'
	 */
	public void testGetSinGuiones1() {
		this.assertEquals(nombre2, Notacion.getSinGuiones(nombre));
	}

	public void testGetSinGuiones2() {
		this.assertEquals(nombre4, Notacion.getSinGuiones(nombre3));
	}

}
