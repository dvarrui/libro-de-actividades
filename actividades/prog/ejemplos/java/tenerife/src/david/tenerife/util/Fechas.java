package david.tenerife.util;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Trabaja con fechas según el formato dd-mm-aaaa
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051223
 * 
 */
public class Fechas {
	public static String SEPARADOR = "-";

	public static Date fromCadena(String fecha) {
		int a, m, d;
		StringTokenizer st = new StringTokenizer(fecha, SEPARADOR, false);
		Calendar c = Calendar.getInstance();

		d = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		
		c.set(a, m - 1, d,0,0,0);
		return c.getTime();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(Fechas.toCadena(d));
	}

	public static String toCadena(Date fecha) {
		int a, m, d;
		StringBuffer sb = new StringBuffer(10);
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);

		d = c.get(Calendar.DAY_OF_MONTH);
		m = c.get(Calendar.MONTH)+1;
		a = c.get(Calendar.YEAR);

		if (d < 9)
			sb.append("0");
		sb.append(d + SEPARADOR);
		if (m < 9)
			sb.append("0");
		sb.append(m + SEPARADOR);
		sb.append(a);
		return sb.toString();
	}

}
