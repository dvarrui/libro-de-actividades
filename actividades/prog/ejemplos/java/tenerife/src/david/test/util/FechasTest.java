package david.test.util;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import david.tenerife.util.Fechas;

public class FechasTest extends TestCase {

	Date fecha20050101;
	Date fecha20051130;
	Date fecha20051231;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		Calendar c = Calendar.getInstance();
		c.set(2005,0,1,0,0,0);
		fecha20050101 = c.getTime();
		c.set(2005,10,30,0,0,0);
		fecha20051130= c.getTime();
		c.set(2005,11,31,0,0,0);
		fecha20051231 = c.getTime();
	}

	/*
	 * Test method for 'david.tenerife.util.Fechas.fromCadena(String)'
	 */
	public void testFromCadena1() {
		this.assertEquals(fecha20050101.toString(),Fechas.fromCadena("01-01-2005").toString());
	}

	public void testFromCadena2() {
		this.assertEquals(fecha20051130.toString(),Fechas.fromCadena("30-11-2005").toString());
	}

	public void testFromCadena3() {
		this.assertEquals(fecha20051231.toString(),Fechas.fromCadena("31-12-2005").toString());
	}

	/*
	 * Test method for 'david.tenerife.util.Fechas.toCadena(Date)'
	 */
	public void testToCadena1() {
		this.assertEquals("01-01-2005",Fechas.toCadena(fecha20050101));
	}

	public void testToCadena2() {
		this.assertEquals("30-11-2005",Fechas.toCadena(fecha20051130));
	}

	public void testToCadena3() {
		this.assertEquals("31-12-2005",Fechas.toCadena(fecha20051231));
	}
}
