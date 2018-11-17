package david.test.util;

import junit.framework.TestCase;
import david.tenerife.util.Configuracion;

public class ConfiguracionTest extends TestCase {

	Configuracion c1;
	String fichero;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		fichero = "david.test.rec.inicio";
		c1 = new Configuracion();
		c1.setFichero(fichero);
	}

	/*
	 * Test method for 'david.tenerife.util.Configuracion.getFichero()'
	 */
	public void testGetFichero() {
		this.assertEquals(fichero,c1.getFichero());
	}

	/*
	 * Test method for 'david.tenerife.util.Configuracion.getInt(String)'
	 */
	public void testGetInt() {
		this.assertEquals(3,c1.getInt("numeroEntornos"));
	}

	/*
	 * Test method for 'david.tenerife.util.Configuracion.getLength(String)'
	 */
	public void testGetLength() {
		this.assertEquals(3,c1.getLength("listaEntornos"));
	}

	/*
	 * Test method for 'david.tenerife.util.Configuracion.getString(String)'
	 */
	public void testGetString() {
		this.assertEquals("david",c1.getString("usuario"));
	}

	/*
	 * Test method for 'david.tenerife.util.Configuracion.getStringArray(String)'
	 */
	public void testGetStringArray0() {
		String[] e ={"E","F","S"};
		this.assertEquals(e[0],c1.getStringArray("listaEntornos")[0]);
	}

	public void testGetStringArray1() {
		String[] e ={"E","F","S"};
		this.assertEquals(e[1],c1.getStringArray("listaEntornos")[1]);
	}

	public void testGetStringArray2() {
		String[] e ={"E","F","S"};
		this.assertEquals(e[2],c1.getStringArray("listaEntornos")[2]);
	}

	/*
	 * Test method for 'david.tenerife.util.Configuracion.getStringAt(String, int)'
	 */
	public void testGetStringAt1() {
		this.assertEquals("E",c1.getStringAt("listaEntornos",0));
	}

	public void testGetStringAt2() {
		this.assertEquals("F",c1.getStringAt("listaEntornos",1));
	}

	public void testGetStringAt3() {
		this.assertEquals("S",c1.getStringAt("listaEntornos",2));
	}

}
