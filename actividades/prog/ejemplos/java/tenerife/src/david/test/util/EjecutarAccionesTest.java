package david.test.util;

import junit.framework.TestCase;
import david.tenerife.util.EjecutarAcciones;

public class EjecutarAccionesTest extends TestCase {

	private EjecutarAcciones e1;
	private EjecutarAcciones e2;
	
	protected void setUp() throws Exception {
		super.setUp();
		e1 = new EjecutarAcciones("src/david/test/rec/acciones.ejecutar.ok");
		e2 = new EjecutarAcciones("src/david/test/rec/acciones.ejecutar.err");
	}

	/*
	 * Test method for 'david.tenerife.util.EjecutarAcciones.ejecutar()'
	 */
	public void testEjecutar1() {
		this.assertTrue(e1.ejecutar());
	}

	/*
	 * Test method for 'david.tenerife.util.EjecutarAcciones.ejecutar()'
	 */
	public void testEjecutar2() {
		this.assertFalse(e2.ejecutar());
	}

}
